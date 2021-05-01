package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.block.util.BWCropBlock;
import moriyashiine.bewitchment.common.registry.BWDamageSources;
import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BWCropBlock.class)
public class BWCropBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("TAIL"), cancellable = true)
    private void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci){
        if (entity instanceof LivingEntity) {
            if ((Object) this == BWObjects.GARLIC_CROP && BSMTransformations.isWerepyre(entity, true)) {
                entity.damage(BWDamageSources.MAGIC_COPY, ((LivingEntity) entity).getMaxHealth() * 1 / 4f);
            }
        }
    }
}
