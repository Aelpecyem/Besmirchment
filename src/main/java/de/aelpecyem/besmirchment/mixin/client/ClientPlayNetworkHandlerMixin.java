package de.aelpecyem.besmirchment.mixin.client;

import de.aelpecyem.besmirchment.common.entity.FinalBroomEntity;
import de.aelpecyem.besmirchment.common.entity.InfectiousSpitEntity;
import de.aelpecyem.besmirchment.common.entity.WitchyDyeEntity;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    private ClientWorld world;

    @Inject(method = "onEntitySpawn", at = @At("TAIL"))
    private void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo callbackInfo) {
        EntityType<?> type = packet.getEntityTypeId();
        double x = packet.getX();
        double y = packet.getY();
        double z = packet.getZ();
        Entity entity = null;
        if (type == BSMEntityTypes.FINAL_BROOM) {
            entity = new FinalBroomEntity(BSMEntityTypes.FINAL_BROOM, world);
        }else if (type == BSMEntityTypes.WITCHY_DYE){
            entity = new WitchyDyeEntity(BSMEntityTypes.WITCHY_DYE, world);
        }else if (type == BSMEntityTypes.INFECTIOUS_SPIT){
            entity = new InfectiousSpitEntity(BSMEntityTypes.INFECTIOUS_SPIT, world);
        }
        if (entity != null) {
            int id = packet.getId();
            entity.updateTrackedPosition(x, y, z);
            entity.refreshPositionAfterTeleport(x, y, z);
            entity.pitch = (float) (packet.getPitch() * 360) / 256f;
            entity.yaw = (float) (packet.getYaw() * 360) / 256f;
            entity.setEntityId(id);
            entity.setUuid(packet.getUuid());
            world.addEntity(id, entity);
        }
    }
}
