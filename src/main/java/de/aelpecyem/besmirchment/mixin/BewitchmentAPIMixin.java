package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.entity.interfaces.WerepyreAccessor;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BewitchmentAPI.class, remap = false)
public class BewitchmentAPIMixin {
    @Inject(method = "Lmoriyashiine/bewitchment/api/BewitchmentAPI;getTransformedPlayerEntity(Lnet/minecraft/class_1657;)Lnet/minecraft/class_1309;", at = @At("RETURN"), cancellable = true)
    private static void getTransformedPlayerEntity(PlayerEntity player, CallbackInfoReturnable<LivingEntity> cir){
        if (BSMTransformations.isWerepyre(player, false)){
            WerepyreEntity entity = BSMEntityTypes.WEREPYRE.create(player.world);
            entity.getDataTracker().set(BWHostileEntity.VARIANT, ((WerepyreAccessor) player).getWerepyreVariant());
            entity.setLastJumpTime(((WerepyreAccessor) player).getLastJumpTicks());
            cir.setReturnValue(entity);
        }
        if (cir.getReturnValue() instanceof DyeableEntity){
            LivingEntity returnEntity = cir.getReturnValue();
            ((DyeableEntity) returnEntity).setColor(((DyeableEntity) player).getColor());
        }
    }
    @Inject(method = "isVampire(Lnet/minecraft/class_1297;Z)Z", at = @At("HEAD"), cancellable = true)
    private static void isVampire(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (BSMTransformations.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(includeHumanForm);
        }
    }

    @Inject(method = "isWerewolf(Lnet/minecraft/class_1297;Z)Z", at = @At("HEAD"), cancellable = true)
    private static void isWerewolf(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (BSMTransformations.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(true);
        }
    }
}
