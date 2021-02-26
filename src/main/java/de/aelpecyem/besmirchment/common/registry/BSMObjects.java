package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.item.WitchyDyeItem;
import moriyashiine.bewitchment.api.item.BroomItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class BSMObjects {
    public static final Item FINAL_BROOM = new BroomItem(new FabricItemSettings().maxCount(1).group(Besmirchment.BESMIRCHMENT), BSMEntityTypes.FINAL_BROOM);
    public static final WitchyDyeItem WITCHY_DYE = new WitchyDyeItem(new FabricItemSettings().maxCount(1).group(Besmirchment.BESMIRCHMENT));

    public static void init(){
        Util.register(Registry.ITEM,"final_broom", FINAL_BROOM);
        Util.register(Registry.ITEM,"witchy_dye", WITCHY_DYE);
    }
}
