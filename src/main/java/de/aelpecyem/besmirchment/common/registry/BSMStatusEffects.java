package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.statuseffect.LoveStatusEffect;
import moriyashiine.bewitchment.common.statuseffect.EmptyStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.registry.Registry;

public class BSMStatusEffects {
    public static final StatusEffect LOVE = new LoveStatusEffect();
    public static final StatusEffect SUNSCREEN = new EmptyStatusEffect(StatusEffectType.BENEFICIAL, 0xFFFF8F);

    public static void init(){
        Util.register(Registry.STATUS_EFFECT,"love", LOVE);
        Util.register(Registry.STATUS_EFFECT,"sunscreen", SUNSCREEN);
    }
}
