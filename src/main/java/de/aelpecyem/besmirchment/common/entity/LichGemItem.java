package de.aelpecyem.besmirchment.common.entity;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LichGemItem extends Item {
    public LichGemItem() {
        super(new FabricItemSettings().group(Besmirchment.BESMIRCHMENT).maxCount(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (isSouled(stack)){
            tooltip.add(new TranslatableText("tooltip.besmirchment.souled").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x8eed5e))));
        }else{
            tooltip.add(new TranslatableText("tooltip.besmirchment.empty").fillStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    public static boolean isSouled(ItemStack stack){
        return stack.hasTag() && stack.getTag().getBoolean("Souled");
    }

    public static void setSouled(ItemStack stack, boolean souled){
        stack.getOrCreateTag().putBoolean("Souled", souled);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return isSouled(stack);
    }
}
