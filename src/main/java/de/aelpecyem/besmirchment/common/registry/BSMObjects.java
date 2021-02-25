package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import moriyashiine.bewitchment.api.item.BroomItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class BSMObjects {
    public static final Item FINAL_BROOM = new BroomItem(new FabricItemSettings().maxCount(1).group(Besmirchment.BESMIRCHMENT), BSMEntityTypes.FINAL_BROOM);
    public static void init(){
        Util.register(Registry.ITEM,"final_broom", FINAL_BROOM);
    }
}
