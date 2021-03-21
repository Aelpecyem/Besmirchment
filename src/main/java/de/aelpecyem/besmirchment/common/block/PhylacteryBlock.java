package de.aelpecyem.besmirchment.common.block;

import de.aelpecyem.besmirchment.common.block.entity.PhylacteryBlockEntity;
import de.aelpecyem.besmirchment.common.entity.LichGemItem;
import de.aelpecyem.besmirchment.common.transformation.LichAccessor;
import de.aelpecyem.besmirchment.common.transformation.LichLogic;
import de.aelpecyem.besmirchment.common.world.BSMWorldState;
import moriyashiine.bewitchment.common.registry.BWSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class PhylacteryBlock extends Block implements BlockEntityProvider, Waterloggable {
    public PhylacteryBlock() {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.GREEN).luminance(10));
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockView world) {
        return new PhylacteryBlockEntity();
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean client = world.isClient;
        BlockEntity phylactery = world.getBlockEntity(pos);
        if (!client && phylactery instanceof PhylacteryBlockEntity) {
            ItemStack stack = player.getStackInHand(hand);
            if (LichGemItem.isSouled(stack)) {
                if (((PhylacteryBlockEntity) phylactery).addSouls(1) > 0) {
                    if (!player.isCreative()) {
                        LichGemItem.setSouled(stack, false);
                    }
                    world.playSound(null, pos, BWSoundEvents.ENTITY_GENERIC_PLING, SoundCategory.BLOCKS, 0.8F, MathHelper.nextFloat(world.random, 0.8F, 1.5F));
                }
            }else{
                int soulCount = ((PhylacteryBlockEntity) phylactery).souls;
                if (soulCount == 1){ //imagine supporting dual-case in code that would be very funny I think
                    player.sendMessage(new TranslatableText("message.besmirchment.phylactery_soul", ((PhylacteryBlockEntity) phylactery).souls), true);
                }else {
                    player.sendMessage(new TranslatableText("message.besmirchment.phylactery_souls", ((PhylacteryBlockEntity) phylactery).souls), true);
                }
            }
        }

        return ActionResult.success(client);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.get(Properties.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient && placer instanceof PlayerEntity) {
            BSMWorldState worldState = BSMWorldState.get(world);
            Pair<ServerWorld, PhylacteryBlockEntity> existingPhylactery = LichLogic.getPhylactery(placer);
            int startSouls = 0;
            if (existingPhylactery != null) {
                startSouls = existingPhylactery.getRight().souls;
                existingPhylactery.getLeft().breakBlock(existingPhylactery.getRight().getPos(), true, placer);
            }
            worldState.addPhylactery(placer.getUuid(), pos);
            if (world.getBlockEntity(pos) instanceof PhylacteryBlockEntity){
                ((PhylacteryBlockEntity) world.getBlockEntity(pos)).addSouls(startSouls);
            }
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient && state.getBlock() != newState.getBlock()) {
            BSMWorldState worldState = BSMWorldState.get(world);
            UUID foundUUID = LichLogic.getPlayerForPhylactery(worldState, pos);
            if (foundUUID != null) {
                worldState.removePhylactery(foundUUID);
                for (ServerPlayerEntity player : PlayerLookup.all(world.getServer())) {
                    if (player.getUuid().equals(foundUUID)){
                        ((LichAccessor) player).updateCachedSouls();
                    }
                }
            }
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.WATERLOGGED);
    }
}
