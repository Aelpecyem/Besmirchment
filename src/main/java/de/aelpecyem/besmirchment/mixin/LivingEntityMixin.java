package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMContracts;
import moriyashiine.bewitchment.api.interfaces.entity.ContractAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
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
            ContractAccessor accessor = (ContractAccessor)this;
            if (accessor.hasNegativeEffects() && accessor.hasContract(BSMContracts.CONQUEST)){
                return amount * 2;
            }
        }
        return amount;
    }
}
