package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

public class Util {
    public static <T> T register(Registry<? super T> registry, String name, T entry) {
        return Registry.register(registry, Besmirchment.id(name), entry);
    }

    public static Block registerBlock(String name, Block entry) {
        Registry.register(Registry.ITEM, Besmirchment.id(name), new BlockItem(entry, new FabricItemSettings().group(Besmirchment.BESMIRCHMENT)));
        return Registry.register(Registry.BLOCK, Besmirchment.id(name), entry);
    }
}
