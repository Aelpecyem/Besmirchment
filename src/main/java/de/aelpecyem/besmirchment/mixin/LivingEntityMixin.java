package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.client.renderer.LichRollAccessor;
import de.aelpecyem.besmirchment.common.block.entity.PhylacteryBlockEntity;
import de.aelpecyem.besmirchment.common.entity.LichGemItem;
import de.aelpecyem.besmirchment.common.registry.BSMContracts;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import de.aelpecyem.besmirchment.common.registry.BSMTags;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import de.aelpecyem.besmirchment.common.transformation.LichAccessor;
import de.aelpecyem.besmirchment.common.transformation.LichLogic;
import moriyashiine.bewitchment.api.interfaces.entity.BloodAccessor;
import moriyashiine.bewitchment.api.interfaces.entity.ContractAccessor;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.common.registry.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 999)
public abstract class LivingEntityMixin extends Entity implements LichRollAccessor, LichAccessor {
    private int bsm_lastRevive = 0;
    private int bsm_cachedSouls = 0;
    @Environment(EnvType.CLIENT)
    private int bsm_lastRoll = 100;

    @Shadow
    protected abstract float getSoundVolume();

    @Shadow
    protected abstract float getSoundPitch();

    @Shadow
    public abstract boolean teleport(double x, double y, double z, boolean particleEffects);

    @Shadow
    public abstract void setHealth(float health);

    @Shadow
    public abstract boolean clearStatusEffects();

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow
    public abstract float getMaxHealth();

    @Shadow
    public abstract boolean isAlive();

    @Shadow
    public abstract float getYaw(float tickDelta);

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean isSleeping();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyVariable(method = "applyArmorToDamage", at = @At("HEAD"))
    private float modifyDamage1(float amount, DamageSource source) {
        if (source.isProjectile()) {
            if (source.getAttacker() instanceof ContractAccessor) {
                ContractAccessor accessor = (ContractAccessor) source.getAttacker();
                if (accessor.hasContract(BSMContracts.CONQUEST)) {
                    return amount * 2;
                }
            }
        }
        return amount;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        if (!world.isClient) {
            if (bsm_lastRevive < 1000 && isAlive()) {
                bsm_lastRevive++;
            }
            LivingEntity livingEntity = (LivingEntity) (Object) this;
            int damage = 0;
            if (livingEntity.getMainHandStack().getItem() == BWObjects.GARLIC && BSMTransformations.isWerepyre(this, true)) {
                damage++;
            }
            if (livingEntity.getOffHandStack().getItem() == BWObjects.GARLIC && BSMTransformations.isWerepyre(this, true)) {
                damage++;
            }
            if (damage > 0) {
                damage(BWDamageSources.MAGIC_COPY, damage);
            }
            //mathematically equal to not changing the blood at all
            if (BWTags.HAS_BLOOD.contains(getType()) && BSMTransformations.isWerepyre(this, true) && random.nextFloat() < (isSleeping() ? 1 / 50f : 1 / 500f)) {
                ((BloodAccessor) livingEntity).drainBlood(1, false);
            }
        }
    }

    @Inject(method = "onKilledBy", at = @At("RETURN"))
    private void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        if (getType().isIn(BSMTags.PURE_SOULS) && adversary != null && adversary.isHolding(BSMObjects.LICH_GEM)) {
            for (ItemStack itemStack : adversary.getItemsEquipped()) {
                if (itemStack.getItem().equals(BSMObjects.LICH_GEM) && !LichGemItem.isSouled(itemStack)) {
                    LichGemItem.setSouled(itemStack, true);
                    playSound(BWSoundEvents.ENTITY_GENERIC_PLING, 0.5F, MathHelper.nextFloat(random, 0.7F, 1.5F));
                    break;
                }
            }
        }
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if ((Object) this instanceof PlayerEntity && BSMTransformations.isLich(this, false) && hasStatusEffect(StatusEffects.WEAKNESS)) {
            if (stack.getItem().equals(Items.GOLDEN_APPLE)) {
                playSound(BWSoundEvents.ENTITY_GENERIC_TRANSFORM, 1, 1);
                ((TransformationAccessor) this).setAlternateForm(false);
                ((TransformationAccessor) this).getTransformation().onRemoved((PlayerEntity) (Object) this);
                ((TransformationAccessor) this).setTransformation(BWTransformations.HUMAN);
                ((TransformationAccessor) this).getTransformation().onAdded((PlayerEntity) (Object) this);
                if (world.isClient) {
                    for (int i = 0; i < 20; i++) {
                        world.addParticle(new DustParticleEffect(0.98F, 0.8F, 0, MathHelper.nextFloat(random, 1.2F, 3F)), getParticleX(1), getRandomBodyY(), getParticleZ(1), 0, 0, 0);
                    }
                }
            }
        }
    }

    @Inject(method = "tryUseTotem", at = @At("RETURN"), cancellable = true)
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && BSMTransformations.isLich(this, false)) {
            if (LichLogic.revive((LivingEntity) (Object) this, source, bsm_lastRevive)) {
                cir.setReturnValue(true);
                bsm_lastRevive = 0;
            }
            updateCachedSouls();
        }
    }

    @Inject(method = "isInSwimmingPose", at = @At("HEAD"), cancellable = true)
    private void isInSwimmingPose(CallbackInfoReturnable<Boolean> cir) {
        if (BSMTransformations.isLich(this, true)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"), cancellable = true)
    private void swingHand(Hand hand, CallbackInfo ci) {
        if (BSMTransformations.isLich(this, true)) {
            ci.cancel();
            if (world.isClient && bsm_lastRoll >= 20) {
                bsm_lastRoll = 0;
            }
        }
    }

    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void tickMovement(CallbackInfo ci) {
        if (world.isClient) {
            bsm_lastRoll++;
        }
    }

    @Override
    public int getLastRollTicks() {
        return bsm_lastRoll;
    }

    @Override
    public int getCachedSouls() {
        return bsm_cachedSouls;
    }

    @Override
    public void updateCachedSouls() {
        Pair<ServerWorld, PhylacteryBlockEntity> phylactery = LichLogic.getPhylactery((LivingEntity) (Object) this);
        if (phylactery != null) {
            this.bsm_cachedSouls = phylactery.getRight().souls;
        } else {
            this.bsm_cachedSouls = 0;
        }
        if (BSMTransformations.isLich(this, false)) {
            if ((Object) this instanceof PlayerEntity) {
                LichLogic.addAttributes((LivingEntity) (Object) this, bsm_cachedSouls);
            }
        }
       /* if (BSMTransformations.isLich(this, true)) {
            TransformationAbilityPacket.useAbility((PlayerEntity) (Object) this, true);
        }*/
    }

    @Inject(method = "getGroup", at = @At("HEAD"), cancellable = true)
    private void getGroup(CallbackInfoReturnable<EntityGroup> cir) {
        if (BSMTransformations.isLich(this, false)) {
            cir.setReturnValue(EntityGroup.UNDEAD);
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void handleFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (BSMTransformations.isWerepyre(this, false) && fallDistance <= 12) {
            callbackInfo.setReturnValue(false);
        }
    }

    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.putInt("BSMLastRevive", bsm_lastRevive);
        tag.putInt("BSMSoulCache", bsm_cachedSouls);
    }

    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        bsm_lastRevive = tag.getInt("BSMLastRevive");
        bsm_cachedSouls = tag.getInt("BSMSoulCache");
        if (world instanceof ServerWorld) {
            updateCachedSouls();
        }
    }
}
