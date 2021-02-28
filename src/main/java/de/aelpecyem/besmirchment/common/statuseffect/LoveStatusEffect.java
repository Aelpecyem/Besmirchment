package de.aelpecyem.besmirchment.common.statuseffect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.passive.AnimalEntity;

public class LoveStatusEffect extends StatusEffect {
    public LoveStatusEffect() {
        super(StatusEffectType.NEUTRAL, 0xFF325B);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof AnimalEntity && !entity.isBaby() && ((AnimalEntity) entity).getLoveTicks() <= 0){
            ((AnimalEntity) entity).setLoveTicks(80);
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
