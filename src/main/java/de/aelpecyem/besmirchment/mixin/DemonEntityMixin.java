package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DemonEntity.class)
public abstract class DemonEntityMixin extends BWHostileEntity {
    @Shadow private TradeOfferList tradeOffers;

    protected DemonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getOffers", at = @At(value = "INVOKE_ASSIGN", target = "moriyashiine/bewitchment/common/entity/living/DemonEntity$TradeGenerator.build(Ljava/util/Random;)Lnet/minecraft/village/TradeOfferList;", shift = At.Shift.AFTER))
    private void addScrollOfTorment(CallbackInfoReturnable<TradeOfferList> ci) {
        addTrades(tradeOffers, random);
    }

    @Unique
    private static void addTrades(TradeOfferList offers, Random random) {
        if (Besmirchment.config.enableBeelzebub && random.nextBoolean()) {
            offers.add(new TradeOffer(new ItemStack(BWObjects.DEMON_HEART), new ItemStack(BWObjects.BOTTLE_OF_BLOOD, 3 + random.nextInt(4)), new ItemStack(BSMObjects.SCROLL_OF_TORMENT), 1, 69, 1));
        }
    }
}
