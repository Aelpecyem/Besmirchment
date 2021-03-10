package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.DyeableEntity;
import de.aelpecyem.besmirchment.common.entity.WerepyreAccessor;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantConditions")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements DyeableEntity, WerepyreAccessor {
    private static final TrackedData<Integer> JUMP_TICKS = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private static final TrackedData<Integer> WEREPYRE_VARIANT = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Shadow @Nullable
    public abstract ItemEntity dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership);


    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setWerepyreVariant(int variant) {
        dataTracker.set(WEREPYRE_VARIANT, variant);
    }

    @Override
    public int getWerepyreVariant() {
        return dataTracker.get(WEREPYRE_VARIANT);
    }

    @Override
    public void setLastJumpTicks(int ticks) {
        dataTracker.set(JUMP_TICKS, ticks);
    }

    @Override
    public int getLastJumpTicks() {
        return dataTracker.get(JUMP_TICKS);
    }

    @Override
    public void setColor(int color) {
        dataTracker.set(COLOR, color);
    }

    @Override
    public int getColor() {
        return dataTracker.get(COLOR);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci){
        if (getLastJumpTicks() < 200){
            setLastJumpTicks(getLastJumpTicks() + 1);
        }
        if (isSneaking() && BewitchmentAPI.getFamiliar((PlayerEntity) (Object)this) == EntityType.CHICKEN){
            if (!isOnGround() && !hasStatusEffect(StatusEffects.SLOW_FALLING)){
                addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0, false, false, false));
            }
            if (random.nextFloat() < Besmirchment.config.universalFamiliars.chickenFamiliarEggChance){
                dropItem(new ItemStack(Items.EGG), true, true);
            }
        }
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void onDeath(DamageSource source, CallbackInfo ci){
        if (BewitchmentAPI.getFamiliar((PlayerEntity) (Object)this) == EntityType.PIG){
            dropItem(new ItemStack(source.isFire() ? Items.COOKED_PORKCHOP : Items.PORKCHOP, random.nextInt(6) + 1), true, true);
        }
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void eat(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> callbackInfo) {
        if (!world.isClient && BewitchmentAPI.getFamiliar((PlayerEntity) (Object)this) == EntityType.PIG) {
            addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0, true, false));
            addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0, true, false));
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void handleFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (isSneaking() && (Object) this instanceof PlayerEntity && BewitchmentAPI.getFamiliar((PlayerEntity) (Object) this) == EntityType.CHICKEN) {
            callbackInfo.setReturnValue(false);
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo callbackInfo) {
        setColor(tag.getInt("BSMColor"));
        setLastJumpTicks(tag.getInt("BSMLastJumpTicks"));
        setWerepyreVariant(tag.getInt("BSMWerepyreVariant"));
    }

    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo callbackInfo) {
        tag.putInt("BSMColor", getColor());
        tag.putInt("BSMLastJumpTicks", getLastJumpTicks());
        tag.putInt("BSMWerepyreVariant", getWerepyreVariant());
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(CallbackInfo callbackInfo) {
        dataTracker.startTracking(COLOR, -1);
        dataTracker.startTracking(JUMP_TICKS, 0);
        dataTracker.startTracking(WEREPYRE_VARIANT, 0);
    }
}
