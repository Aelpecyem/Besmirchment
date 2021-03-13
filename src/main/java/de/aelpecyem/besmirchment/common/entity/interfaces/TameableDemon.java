package de.aelpecyem.besmirchment.common.entity.interfaces;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
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

    @Environment(EnvType.CLIENT)
    void showEmoteParticle(boolean positive);

    default boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof TameableDemon) {
                TameableDemon demon = (TameableDemon)target;
                return !demon.isTamed() || demon.getOwner() != owner;
            } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
                return false;
            } else if (target instanceof HorseBaseEntity && ((HorseBaseEntity)target).isTame()) {
                return false;
            } else {
                return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
            }
        } else {
            return false;
        }
    }
}
