package de.aelpecyem.besmirchment.client.packet;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import de.aelpecyem.besmirchment.common.Besmirchment;
import io.netty.buffer.Unpooled;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.MagicAccessor;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class FamiliarAbilityPacket {
    public static final Identifier ID = Besmirchment.id("familiar_ability");
    public static void send(){
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        ClientPlayNetworking.send(ID, buf);
    }

    public static void handle(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        server.execute(() -> {
            if (canUseAbility(player)) {
                useAbility(player);
            }
        });
    }

    private static boolean canUseAbility(PlayerEntity player) {
        if (BewitchmentAPI.getFamiliar(player) == EntityType.SHEEP) {
            return true;
        }
        return false;
    }

    public static void useAbility(PlayerEntity player){
        if (player.canModifyBlocks()) {
            if (EntityType.SHEEP.equals(BewitchmentAPI.getFamiliar(player)) && player.getHungerManager().isNotFull() || player.isCreative()) {
                World world = player.world;
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
                        player.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.7F, 0.6F + player.getRandom().nextFloat() * 0.5F);
                    }
                }
            }
        }
    }
}
