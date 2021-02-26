package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.recipe.WitchyDyeCraftingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.registry.Registry;

public class BSMRecipeTypes {
    public static final RecipeSerializer<WitchyDyeCraftingRecipe> WITCHY_DYE_SERIALIZER = new SpecialRecipeSerializer<WitchyDyeCraftingRecipe>(WitchyDyeCraftingRecipe::new);

    public static void init(){
        Util.register(Registry.RECIPE_SERIALIZER, "witchy_dye_crafting", WITCHY_DYE_SERIALIZER);
    }
}
