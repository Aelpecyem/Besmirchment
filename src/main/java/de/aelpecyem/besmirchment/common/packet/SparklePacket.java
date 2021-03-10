package de.aelpecyem.besmirchment.common.packet;

import de.aelpecyem.besmirchment.common.Besmirchment;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SparklePacket {
    public static final Identifier ID = Besmirchment.id("familiar_ability");

    public static void send(LivingEntity entity){
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getEntityId());
        if (entity instanceof ServerPlayerEntity){
            ServerPlayNetworking.send((ServerPlayerEntity) entity, ID, buf);
        }
        PlayerLookup.tracking(entity).forEach(player -> ServerPlayNetworking.send(player, ID, buf));
    }

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender sender) {
        Entity entity = client.world.getEntityById(buf.readInt());
        client.execute(() -> {
            entity.world.addParticle(ParticleTypes.END_ROD, entity.getParticleX(1), entity.getRandomBodyY(), entity.getParticleZ(1), 0, 0, 0);
        });
    }
}
