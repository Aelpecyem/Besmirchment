package de.aelpecyem.besmirchment.common.transformation;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import moriyashiine.bewitchment.api.registry.Transformation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.Registry;

public class LichTransformation extends Transformation {
    public LichTransformation() {
    }

    @Override
    public void onAdded(PlayerEntity entity) {
        if (entity instanceof LichAccessor) {
            ((LichAccessor) entity).updateCachedSouls();
        }
    }

    @Override
    public void onRemoved(PlayerEntity entity) {
        if (entity instanceof LichAccessor) {
            ((LichAccessor) entity).updateCachedSouls();
            LichLogic.addAttributes(entity, -1);
        }
    }
}
