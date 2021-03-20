package de.aelpecyem.besmirchment.common.block;

import de.aelpecyem.besmirchment.common.block.entity.PhylacteryBlockEntity;
import de.aelpecyem.besmirchment.common.transformation.LichLogic;
import de.aelpecyem.besmirchment.common.world.BSMWorldState;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

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
          if (!client) {
              int soulsAdded = ((PhylacteryBlockEntity) world.getBlockEntity(pos)).addSouls(1);
              if (soulsAdded > 0){
                  player.damage(DamageSource.ANVIL, soulsAdded);
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
            if (existingPhylactery != null){
                existingPhylactery.getLeft().breakBlock(existingPhylactery.getRight().getPos(), true, placer);
            }
            worldState.phylacteries.put(placer.getUuid(), pos);
            worldState.markDirty();
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient && state.getBlock() != newState.getBlock()) {
            BSMWorldState worldState = BSMWorldState.get(world);

            for (UUID uuid : worldState.phylacteries.keySet()) {
                if (worldState.phylacteries.get(uuid).equals(pos)){
                    worldState.phylacteries.remove(uuid);
                    worldState.markDirty();
                }
            }
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.WATERLOGGED);
    }
}
