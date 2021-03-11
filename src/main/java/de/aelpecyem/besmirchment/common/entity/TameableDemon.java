package de.aelpecyem.besmirchment.common.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface TameableDemon {
    UUID getOwnerUuid();
    void setOwnerUuid(@Nullable UUID uuid);
    void setOwner(PlayerEntity player);
    LivingEntity getOwner();
    boolean isOwner(LivingEntity entity);
    boolean isSitting();
    void setSitting(boolean sitting);

    boolean isTamed();
    void setTamed(boolean tamed);

    default void onTamedChanged(){

    }
    boolean isInSittingPose();
    void setInSittingPose(boolean sittingPose);

    @Environment(EnvType.CLIENT)
    void showEmoteParticle(boolean positive);
}
