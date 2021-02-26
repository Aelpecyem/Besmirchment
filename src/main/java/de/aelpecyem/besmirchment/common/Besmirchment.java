package de.aelpecyem.besmirchment.common;

import de.aelpecyem.besmirchment.common.registry.BSMConditions;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import de.aelpecyem.besmirchment.common.registry.BSMRecipeTypes;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Besmirchment implements ModInitializer {
    public static final String MODID = "besmirchment";
    public static final ItemGroup BESMIRCHMENT = FabricItemGroupBuilder.build(Besmirchment.id("group"), () -> new ItemStack(BSMObjects.FINAL_BROOM));
    public static BSMConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(BSMConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(BSMConfig.class).getConfig();
        BSMConditions.init();
        BSMEntityTypes.init();
        BSMObjects.init();
        BSMRecipeTypes.init();
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
