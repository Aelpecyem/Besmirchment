package de.aelpecyem.besmirchment.client.packet;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.interfaces.WerepyreAccessor;
import io.netty.buffer.Unpooled;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.MagicAccessor;
import moriyashiine.bewitchment.common.entity.interfaces.PolymorphAccessor;
import moriyashiine.bewitchment.common.registry.BWStatusEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class WerepyreJumpPacket {
    public static final Identifier ID = Besmirchment.id("werepyre_jump");

    @Environment(EnvType.CLIENT)
    public static void send(){
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        ClientPlayNetworking.send(ID, buf);
    }

    public static void handle(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        server.execute(() -> {
            player.fallDistance = 0;
            player.jump();
            ((WerepyreAccessor) player).setLastJumpTicks(0);
        });
    }

    private static boolean canUseAbility(PlayerEntity player) {
        EntityType<?> familiar = BewitchmentAPI.getFamiliar(player);
        if (familiar == EntityType.SHEEP || familiar == EntityType.PARROT || familiar == EntityType.COW) {
            return true;
        }
        return false;
    }

    public static void useAbility(PlayerEntity player){
        EntityType<?> familiar = BewitchmentAPI.getFamiliar(player);
        World world = player.world;
        if (EntityType.PARROT.equals(familiar) && !player.hasStatusEffect(BWStatusEffects.POLYMORPH)){
            Vec3d vec3d = player.getCameraPosVec(1);
            Vec3d vec3d2 = player.getRotationVec(1);
            Vec3d vec3d3 = vec3d.add(vec3d2.x * 16, vec3d2.y * 16, vec3d2.z * 16);
            double distance = Math.pow(16, 2);
            EntityHitResult hit = ProjectileUtil.getEntityCollision(world, player, vec3d, vec3d3, player.getBoundingBox().stretch(vec3d2.multiply(distance)).expand(1.0D, 1.0D, 1.0D), (target) -> target instanceof PlayerEntity && !target.isSpectator() && player.canSee(target));
            if (hit != null && hit.getEntity() instanceof PlayerEntity && BewitchmentAPI.usePlayerMagic(player,50, true)){
                PlayerEntity polyMorphPlayer = (PlayerEntity) hit.getEntity();
                ((PolymorphAccessor) player).setPolymorphUUID(polyMorphPlayer.getUuid());
                ((PolymorphAccessor) player).setPolymorphName(polyMorphPlayer.getDisplayName().getString());
                player.addStatusEffect(new StatusEffectInstance(BWStatusEffects.POLYMORPH, 2400, 0, true, false, false));
                BewitchmentAPI.usePlayerMagic(player,50, false);
            }
        }else if (EntityType.COW.equals(familiar) && player.isHolding(Items.BUCKET)){
            Hand hand = player.getMainHandStack().getItem() == Items.BUCKET ? Hand.MAIN_HAND : Hand.OFF_HAND;
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_COW_MILK, SoundCategory.PLAYERS, 1, 1);
            ItemStack milk = ItemUsage.method_30012(player.getStackInHand(hand), player, new ItemStack(Items.MILK_BUCKET));
            player.setStackInHand(hand, milk);
            player.swingHand(hand);
        }
        if (player.canModifyBlocks()) {
            if (EntityType.SHEEP.equals(familiar) && (player.getHungerManager().isNotFull() || player.isCreative())) {
                Vec3d startVec = player.getCameraPosVec(1);
                Vec3d rotationVec = player.getRotationVec(1);
                double range = ReachEntityAttributes.getReachDistance(player, 4);
                Vec3d endVec = startVec.add(rotationVec.x * range, rotationVec.y * range, rotationVec.z * range);
                BlockHitResult blockHit = world.raycast(new RaycastContext(startVec, endVec, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
                if (blockHit.getBlockPos() != null){
                    BlockPos grassPos = blockHit.getBlockPos();
                    if (world.getBlockState(grassPos).isOf(Blocks.GRASS_BLOCK)){
                        world.syncWorldEvent(2001,grassPos, Block.getRawIdFromState(Blocks.GRASS_BLOCK.getDefaultState()));
                        world.setBlockState(grassPos, Blocks.DIRT.getDefaultState(), 2);
                        player.heal(0.5F);
                        player.getHungerManager().add(2, 0.5F);
                        ((MagicAccessor) player).fillMagic(5, false);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_COW_MILK, SoundCategory.PLAYERS, 0.7F, 0.6F + player.getRandom().nextFloat() * 0.5F);
                    }
                }
            }
        }
    }
}
