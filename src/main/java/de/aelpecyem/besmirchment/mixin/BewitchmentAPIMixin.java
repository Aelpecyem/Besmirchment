package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.transformation.WerepyreAccessor;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.item.PoppetItem;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.BWObjects;
import moriyashiine.bewitchment.common.registry.BWPledges;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BewitchmentAPI.class, remap = false)
public class BewitchmentAPIMixin {
    @Environment(EnvType.CLIENT)
    @Inject(method = "getTransformedPlayerEntity", at = @At("RETURN"), cancellable = true)
    private static void getTransformedPlayerEntity(PlayerEntity player, CallbackInfoReturnable<LivingEntity> cir){
        if (BSMTransformations.isWerepyre(player, false)){
            WerepyreEntity entity = BSMEntityTypes.WEREPYRE.create(player.world);
            entity.getDataTracker().set(BWHostileEntity.VARIANT, ((WerepyreAccessor) player).getWerepyreVariant());
            entity.setLastJumpTime(((WerepyreAccessor) player).getLastJumpTicks());
            entity.jumpBeginProgress = ((WerepyreAccessor) player).getLastJumpProgress();
            cir.setReturnValue(entity);
        }
        if (cir.getReturnValue() instanceof DyeableEntity){
            LivingEntity returnEntity = cir.getReturnValue();
            ((DyeableEntity) returnEntity).setColor(((DyeableEntity) player).getColor());
        }
    }

    @Inject(method = "isWerewolf", at = @At("HEAD"), cancellable = true)
    private static void isWerewolf(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (BSMTransformations.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isPledged", at = @At("HEAD"), cancellable = true)
    private static void isPledged(PlayerEntity player, String pledge, CallbackInfoReturnable<Boolean> cir){
        if (pledge.equals(BWPledges.HERNE) && BSMTransformations.hasWerepyrePledge(player)){
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getPoppet", at = @At("HEAD"), cancellable = true)
    private static void getPoppet(World world, PoppetItem item, Entity owner, PlayerEntity specificInventory, CallbackInfoReturnable<ItemStack> cir){
        if (item == BWObjects.VAMPIRIC_POPPET && (BSMTransformations.isWerepyre(owner, true) ||BSMTransformations.isWerepyre(specificInventory, true))){
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}
