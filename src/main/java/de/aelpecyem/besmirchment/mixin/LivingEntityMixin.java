package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.entity.WerepyreAccessor;
import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import de.aelpecyem.besmirchment.common.registry.BSMContracts;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.ContractAccessor;
import moriyashiine.bewitchment.api.interfaces.entity.CurseAccessor;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.client.network.packet.SpawnSmokeParticlesPacket;
import moriyashiine.bewitchment.common.entity.living.VampireEntity;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.BWCurses;
import moriyashiine.bewitchment.common.registry.BWPledges;
import moriyashiine.bewitchment.common.registry.BWSoundEvents;
import moriyashiine.bewitchment.common.registry.BWTransformations;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 1001)
public abstract class LivingEntityMixin extends Entity {
    @Shadow protected abstract float getSoundVolume();

    @Shadow protected abstract float getSoundPitch();

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
            ContractAccessor accessor = (ContractAccessor) this;
            if (accessor.hasNegativeEffects() && accessor.hasContract(BSMContracts.CONQUEST)) {
                return amount * 2;
            }
        }
        return amount;
    }

    @Inject(method = "tryUseTotem", at = @At("RETURN"))
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        if (cir.getReturnValue() && this instanceof TransformationAccessor && ((CurseAccessor) this).hasCurse(BWCurses.SUSCEPTIBILITY)){
            TransformationAccessor transformationAccessor = (TransformationAccessor) this;
            if (transformationAccessor.getTransformation() == BWTransformations.WEREWOLF){ //no vampire because they can't use totems
                if (source.getSource() instanceof VampireEntity || (BewitchmentAPI.isVampire(source.getSource(), true) && BewitchmentAPI.isPledged(world, BWPledges.LILITH, source.getSource().getUuid()))) {
                    transformationAccessor.getTransformation().onRemoved((LivingEntity) (Object) this);
                    transformationAccessor.setTransformation(BSMTransformations.WEREPYRE);
                    transformationAccessor.getTransformation().onAdded((LivingEntity) (Object) this);
                    PlayerLookup.tracking(this).forEach(foundPlayer -> SpawnSmokeParticlesPacket.send(foundPlayer, this));
                    if ((Object) this instanceof PlayerEntity) {
                        SpawnSmokeParticlesPacket.send((PlayerEntity) (Object) this, this);
                    }
                    world.playSound(null, getBlockPos(), BWSoundEvents.ENTITY_GENERIC_CURSE, getSoundCategory(), getSoundVolume(), getSoundPitch());
                }
            }else if (transformationAccessor.getTransformation() == BWTransformations.HUMAN){
                if (source.getSource() instanceof WerepyreEntity || (BSMTransformations.isWerepyre(source.getSource(), true) && BSMTransformations.hasWerepyrePledge((PlayerEntity) source.getSource()))){
                    transformationAccessor.getTransformation().onRemoved((LivingEntity) (Object) this);
                    transformationAccessor.setTransformation(BSMTransformations.WEREPYRE);
                    transformationAccessor.getTransformation().onAdded((LivingEntity) (Object) this);
                    PlayerLookup.tracking(this).forEach(foundPlayer -> SpawnSmokeParticlesPacket.send(foundPlayer, this));
                    if ((Object) this instanceof PlayerEntity) {
                        SpawnSmokeParticlesPacket.send((PlayerEntity) (Object) this, this);
                    }
                    if (this instanceof WerepyreAccessor) {
                        int variant = -1;
                        if (source.getSource() instanceof WerepyreEntity) {
                            variant = source.getSource().getDataTracker().get(BWHostileEntity.VARIANT);
                        } else if (source.getSource() instanceof WerepyreAccessor && BSMTransformations.hasWerepyrePledge((PlayerEntity) source.getSource())) {
                            variant = ((WerepyreAccessor) source.getSource()).getWerepyreVariant();
                        }
                        if (variant > -1) {
                            ((WerepyreAccessor) this).setWerepyreVariant(variant);
                        }
                    }
                    world.playSound(null, getBlockPos(), BWSoundEvents.ENTITY_GENERIC_CURSE, getSoundCategory(), getSoundVolume(), getSoundPitch());
                }
            }
        }
    }
}
