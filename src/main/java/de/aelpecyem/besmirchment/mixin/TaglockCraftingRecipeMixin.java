package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import moriyashiine.bewitchment.common.item.TaglockItem;
import moriyashiine.bewitchment.common.recipe.TaglockCraftingRecipe;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TaglockCraftingRecipe.class)
public class TaglockCraftingRecipeMixin {
    @Inject(method = "isTaglockCraftable", at = @At("HEAD"), cancellable = true)
    private static void isTaglockCraftable(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if (stack.getItem() == BSMObjects.DEMONIC_DEED){
            cir.setReturnValue(TaglockItem.getTaglockUUID(stack) == null);
        }
    }
}
