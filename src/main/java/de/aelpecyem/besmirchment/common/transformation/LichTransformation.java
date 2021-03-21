package de.aelpecyem.besmirchment.common.transformation;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import moriyashiine.bewitchment.api.registry.Transformation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.registry.Registry;

public class LichTransformation extends Transformation {
    public LichTransformation() {
    }

    public void onAdded(LivingEntity entity) {
        if (entity instanceof LichAccessor) {
            ((LichAccessor) entity).updateCachedSouls();
        }
    }


    public void onRemoved(LivingEntity entity) {
        if (entity instanceof LichAccessor) {
            ((LichAccessor) entity).updateCachedSouls();
            LichLogic.addAttributes(entity, -1);
        }
    }
}
