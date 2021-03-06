package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.registry.BSMStatusEffects;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityInteraction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends MerchantEntity {
    @Shadow @Final private VillagerGossips gossip;

    public VillagerEntityMixin(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isReadyToBreed", at = @At("HEAD"), cancellable = true)
    private void isWillingToBreed(CallbackInfoReturnable<Boolean> cir){
        if (hasStatusEffect(BSMStatusEffects.LOVE) && getBreedingAge() > -1){
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onInteractionWith", at = @At("HEAD"), cancellable = true)
    private void onInteraction(EntityInteraction interaction, Entity entity, CallbackInfo ci){
        if (entity instanceof PlayerEntity && BewitchmentAPI.getFamiliar((PlayerEntity) entity) == EntityType.VILLAGER){
            if (interaction == EntityInteraction.ZOMBIE_VILLAGER_CURED) {
                this.gossip.startGossip(entity.getUuid(), VillageGossipType.MAJOR_POSITIVE, 40);
                this.gossip.startGossip(entity.getUuid(), VillageGossipType.MINOR_POSITIVE, 50);
            } else if (interaction == EntityInteraction.TRADE) {
                this.gossip.startGossip(entity.getUuid(), VillageGossipType.TRADING, 5);
            } else if (interaction == EntityInteraction.VILLAGER_HURT) {
                this.gossip.startGossip(entity.getUuid(), VillageGossipType.MINOR_NEGATIVE, 10);
            } else if (interaction == EntityInteraction.VILLAGER_KILLED) {
                this.gossip.startGossip(entity.getUuid(), VillageGossipType.MAJOR_NEGATIVE, 5);
            }
            ci.cancel();
        }
    }
    @Inject(method = "getReputation", at = @At("RETURN"), cancellable = true)
    private void getReputation(PlayerEntity player, CallbackInfoReturnable<Integer> cir){
        if (cir.getReturnValue() < Besmirchment.config.universalFamiliars.villagerFamiliarReputationBase && BewitchmentAPI.getFamiliar(player) == EntityType.VILLAGER){
            cir.setReturnValue(Besmirchment.config.universalFamiliars.villagerFamiliarReputationBase);
        }
    }
}
