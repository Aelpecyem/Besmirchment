package de.aelpecyem.besmirchment.common.item;

import de.aelpecyem.besmirchment.common.Besmirchment;
import moriyashiine.bewitchment.api.item.PoppetItem;
import moriyashiine.bewitchment.common.item.ContractItem;
import moriyashiine.bewitchment.common.item.TaglockItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DemonicDeedItem extends Item {
    public DemonicDeedItem() {
        super(new FabricItemSettings().group(Besmirchment.BESMIRCHMENT).maxCount(1).rarity(Rarity.RARE).fireproof().maxDamage(20));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (TaglockItem.hasTaglock(stack)) {
            tooltip.add((new LiteralText(TaglockItem.getTaglockName(stack))).formatted(Formatting.GRAY));
        }
    }
}
