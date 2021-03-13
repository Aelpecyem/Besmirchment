package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(targets = "moriyashiine.bewitchment.common.entity.living.DemonEntity$TradeGenerator", remap = false)
public class DemonTradeBuilderMixin {
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "Lmoriyashiine/bewitchment/common/entity/living/DemonEntity$TradeGenerator;build(Ljava/util/Random;)Lnet/minecraft/class_1916;", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addTormentTrade(Random random, CallbackInfoReturnable<TradeOfferList> cir, TradeOfferList offers){
        if (random.nextFloat() < Besmirchment.config.mobs.tormentScrollTradeChance){
            offers.add(new TradeOffer(new ItemStack(BWObjects.DEMON_HEART), new ItemStack(BWObjects.BOTTLE_OF_BLOOD, 3 + random.nextInt(4)), new ItemStack(BSMObjects.SCROLL_OF_TORMENT), 1, 69, 1));
        }
    }
}
