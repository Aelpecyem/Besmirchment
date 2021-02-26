package de.aelpecyem.besmirchment.common.recipe;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import de.aelpecyem.besmirchment.common.registry.BSMRecipeTypes;
import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WitchyDyeCraftingRecipe extends SpecialCraftingRecipe {
    public WitchyDyeCraftingRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        if (!Besmirchment.config.enableWitchyDye){
            return false;
        }

        //can delete that ig
        System.out.println("trying oof");
        boolean foundBottle = false;
        int foundDye = 0;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (stack.getItem() == BWObjects.AQUA_CERATE || stack.getItem() == BSMObjects.WITCHY_DYE) {
                if (!foundBottle) {
                    foundBottle = true;
                }else{
                    return false;
                }
            } else if (stack.getItem() instanceof DyeItem) {
                foundDye++;
            }
        }
        return foundBottle && foundDye > 0;
    }

    @Override
    public ItemStack craft(CraftingInventory inv) {
        ItemStack dye = null;
        List<DyeItem> colors = new ArrayList<>();
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (dye == null) {
                if (stack.getItem() == BSMObjects.WITCHY_DYE) {
                    dye = stack.copy();
                } else if (stack.getItem() == BWObjects.AQUA_CERATE) {
                    dye = new ItemStack(BSMObjects.WITCHY_DYE);
                }
            }
            if (stack.getItem() instanceof DyeItem){
                colors.add((DyeItem) stack.getItem());
            }
        }
        return DyeableItem.blendAndSetColor(dye, colors);
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BSMRecipeTypes.WITCHY_DYE_SERIALIZER;
    }
}