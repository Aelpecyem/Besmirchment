package de.aelpecyem.besmirchment.common.entity.ai;

import de.aelpecyem.besmirchment.common.entity.interfaces.TameableDemon;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;

import java.util.EnumSet;

public class DemonTrackAttackerGoal<T extends DemonEntity & TameableDemon> extends TrackTargetGoal {
    private final T demon;
    private LivingEntity attacker;
    private int lastAttackedTime;

    public DemonTrackAttackerGoal(T demon) {
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
                this.attacker = livingEntity.getAttacker();
                int i = livingEntity.getLastAttackedTime();
                return i != this.lastAttackedTime && this.canTrack(this.attacker, TargetPredicate.DEFAULT) && this.demon.canAttackWithOwner(this.attacker, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacker);
        LivingEntity livingEntity = this.demon.getOwner();
        if (livingEntity != null) {
            this.lastAttackedTime = livingEntity.getLastAttackedTime();
        }

        super.start();
    }
}
