package de.aelpecyem.besmirchment.common.item;

import de.aelpecyem.besmirchment.common.Besmirchment;
import moriyashiine.bewitchment.common.item.ContractItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraft.util.collection.DefaultedList;

public class DemonicDeedItem extends ContractItem {
    public DemonicDeedItem() {
        super(new FabricItemSettings().group(Besmirchment.BESMIRCHMENT).maxCount(1).rarity(Rarity.RARE).fireproof().maxDamage(20));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            stacks.add(new ItemStack(this));
        }
    }
}
