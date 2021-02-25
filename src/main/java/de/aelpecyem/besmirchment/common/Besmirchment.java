package de.aelpecyem.besmirchment.common;

import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Besmirchment implements ModInitializer {
	public static String MODID = "besmirchment";
	public static final ItemGroup BESMIRCHMENT = FabricItemGroupBuilder.build(Besmirchment.id("group"), () -> new ItemStack(BSMObjects.FINAL_BROOM));

	@Override
	public void onInitialize() {
		BSMEntityTypes.init();
		BSMObjects.init();
	}

	public static Identifier id(String path){
		return new Identifier(MODID, path);
	}
}
