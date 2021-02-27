package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.statuseffect.LoveStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

public class BSMStatusEffects {
    public static final StatusEffect LOVE = new LoveStatusEffect();

    public static void init(){
        Util.register(Registry.STATUS_EFFECT,"love", LOVE);
    }
}
