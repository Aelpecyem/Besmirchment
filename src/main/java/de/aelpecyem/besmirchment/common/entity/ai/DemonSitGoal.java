package de.aelpecyem.besmirchment.common.entity.ai;

import de.aelpecyem.besmirchment.common.entity.interfaces.TameableDemon;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DemonSitGoal<T extends DemonEntity & TameableDemon> extends Goal {
    private final T demon;

    public DemonSitGoal(T demon) {
        this.demon = demon;
        this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
    }

    public boolean shouldContinue() {
        return this.demon.isSitting();
    }

    public boolean canStart() {
        if (!this.demon.isTamed()) {
            return false;
        } else if (this.demon.isInsideWaterOrBubbleColumn()) {
            return false;
        } else if (!this.demon.isOnGround()) {
            return false;
        } else {
            LivingEntity livingEntity = this.demon.getOwner();
            if (livingEntity == null) {
                return true;
            } else {
                return (!(this.demon.squaredDistanceTo(livingEntity) < 144.0D) || livingEntity.getAttacker() == null) && this.demon.isSitting();
            }
        }
    }

    public void start() {
        this.demon.getNavigation().stop();
    }

    public void stop() {

    }
}
