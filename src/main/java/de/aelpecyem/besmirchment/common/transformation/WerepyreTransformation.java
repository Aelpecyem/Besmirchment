package de.aelpecyem.besmirchment.common.transformation;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import moriyashiine.bewitchment.api.registry.Transformation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.registry.Registry;

public class WerepyreTransformation extends Transformation {
    public WerepyreTransformation() {
    }

    public void onAdded(LivingEntity entity) {
        if (entity instanceof WerepyreAccessor) {
            ((WerepyreAccessor)entity).setWerepyreVariant(entity.getRandom().nextInt(WerepyreEntity.getVariantsStatic()));
        }
        Registry.STATUS_EFFECT.stream().forEach((effect) -> {
            StatusEffectInstance effectInstance = entity.getStatusEffect(effect);
            if (effectInstance != null && !entity.canHaveStatusEffect(effectInstance)) {
                entity.removeStatusEffect(effect);
            }

        });
    }


    public void onRemoved(LivingEntity entity) {
        entity.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
