package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.sensor.VillagerHostilesSensor;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(VillagerHostilesSensor.class)
public class VillagerHostilesSensorMixin {
    @Inject(method = "getNearestHostile", at = @At("RETURN"), cancellable = true)
    private void getNearestHostile(LivingEntity entity, CallbackInfoReturnable<Optional<LivingEntity>> cir){
        if (!cir.getReturnValue().isPresent()){
            PlayerEntity fleePlayer = entity.world.getClosestPlayer(new TargetPredicate().setBaseMaxDistance(12).setPredicate(player -> BSMTransformations.isLich(player, false)), entity);
            if (fleePlayer != null){
                cir.setReturnValue(Optional.of(fleePlayer));
            }
        }
    }
}
