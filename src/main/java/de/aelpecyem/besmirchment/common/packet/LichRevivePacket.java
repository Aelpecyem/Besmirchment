package de.aelpecyem.besmirchment.common.packet;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.registry.BSMSounds;
import io.netty.buffer.Unpooled;
import moriyashiine.bewitchment.common.registry.BWSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class LichRevivePacket {
    public static final Identifier ID = Besmirchment.id("lich_revive");

    public static void send(LivingEntity entity){
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getEntityId());
        if (entity instanceof ServerPlayerEntity){
            ServerPlayNetworking.send((ServerPlayerEntity) entity, ID, buf);
        }
        PlayerLookup.tracking(entity).forEach(player -> ServerPlayNetworking.send(player, ID, buf));
    }

    @Environment(EnvType.CLIENT)
    public static void handle(MinecraftClient client, ClientPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender sender) {
        int id = buf.readInt();
        client.execute(() -> {
            ClientWorld world = client.world;
            if (world != null) {
                Entity entity = world.getEntityById(id);
                if (entity != null) {
                    for (int i = 0; i < 25; i++) {
                        entity.world.addParticle(new DustParticleEffect(0.5F, 1F, 0.5F, 2F), entity.getParticleX(1), entity.getRandomBodyY(), entity.getParticleZ(1), 0, 0, 0);
                    }
                    if (entity.equals(client.player)){
                        client.player.playSound(BSMSounds.LICH_REVIVE, 1, 1);
                    }
                }
            }
        });
    }
}
