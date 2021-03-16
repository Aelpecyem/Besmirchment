package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.ritualfunction.ColorRitualFunction;
import moriyashiine.bewitchment.api.registry.RitualFunction;
import moriyashiine.bewitchment.common.registry.BWRegistries;
import moriyashiine.bewitchment.common.registry.BWRitualFunctions;
import net.minecraft.util.registry.Registry;

public class BSMRitualFunctions {
    public static final RitualFunction COLOR = new ColorRitualFunction();

    public static void init(){
        Util.register(BWRegistries.RITUAL_FUNCTIONS, "color", COLOR);
    }
}
