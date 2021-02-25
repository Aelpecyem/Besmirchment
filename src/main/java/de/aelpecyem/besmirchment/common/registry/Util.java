package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Util {
    public static <T> T register(Registry<? super T> registry, String name, T entry) {
        return Registry.register(registry, Besmirchment.id(name), entry);
    }
}
