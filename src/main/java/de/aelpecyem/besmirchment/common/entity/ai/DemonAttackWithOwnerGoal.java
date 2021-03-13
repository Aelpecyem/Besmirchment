package de.aelpecyem.besmirchment.common.entity.ai;

import de.aelpecyem.besmirchment.common.entity.interfaces.TameableDemon;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;

import java.util.EnumSet;

public class DemonAttackWithOwnerGoal<T extends DemonEntity & TameableDemon> extends TrackTargetGoal {
    private final T demon;
    private LivingEntity attacking;
    private int lastAttackTime;

    public DemonAttackWithOwnerGoal(T demon) {
        super(demon, false);
        this.demon = demon;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    public boolean canStart() {
        if (this.demon.isTamed() && !this.demon.isSitting()) {
            LivingEntity livingEntity = this.demon.getOwner();
            if (livingEntity == null) {
                return false;
            } else {
                this.attacking = livingEntity.getAttacking();
                int i = livingEntity.getLastAttackTime();
                return i != this.lastAttackTime && this.canTrack(this.attacking, TargetPredicate.DEFAULT) && this.demon.canAttackWithOwner(this.attacking, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacking);
        LivingEntity livingEntity = this.demon.getOwner();
        if (livingEntity != null) {
            this.lastAttackTime = livingEntity.getLastAttackTime();
        }

        super.start();
    }
}
