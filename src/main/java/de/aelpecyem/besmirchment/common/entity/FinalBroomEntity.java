package de.aelpecyem.besmirchment.common.entity;

import moriyashiine.bewitchment.common.entity.DragonsBloodBroomEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class FinalBroomEntity extends DragonsBloodBroomEntity {
    public FinalBroomEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (age % 20 == 0 && getPrimaryPassenger() instanceof LivingEntity){
            ((LivingEntity) getPrimaryPassenger()).addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 1, true, false, false));
        }
        List<Entity> list = this.world.getOtherEntities(this, this.getBoundingBox().expand(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntityPredicates.canBePushedBy(this));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (!entity.hasPassenger(this)) {
                    this.pushAwayFrom(entity);
                }
            }
        }
    }

    @Override
    public void pushAwayFrom(Entity entity) {
        if (entity instanceof LivingEntity && getVelocity().length() > 1){
            entity.damage(DamageSource.FLY_INTO_WALL, (float) Math.min(getVelocity().lengthSquared(), 10));
            ((LivingEntity)entity).takeKnockback((float) getVelocity().length() * 0.5F, MathHelper.sin(this.yaw * 0.017453292F), (-MathHelper.cos(this.yaw * 0.017453292F)));
            this.setVelocity(this.getVelocity().multiply(0.6D, 1.0D, 0.6D));
        }
        super.pushAwayFrom(entity);
    }


    @Override
    protected float getSpeed() {
        return 2.5F;
    }
}
