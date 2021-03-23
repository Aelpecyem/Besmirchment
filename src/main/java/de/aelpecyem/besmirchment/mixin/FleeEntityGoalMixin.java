package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FleeEntityGoal.class)
public class FleeEntityGoalMixin {
    @Shadow @Final protected PathAwareEntity mob;

    @Shadow protected LivingEntity targetEntity;

    @Shadow protected Path fleePath;

    @Shadow @Final protected EntityNavigation fleeingEntityNavigation;

    @Inject(method = "canStart", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getClosestEntityIncludingUngeneratedChunks(Ljava/lang/Class;Lnet/minecraft/entity/ai/TargetPredicate;Lnet/minecraft/entity/LivingEntity;DDDLnet/minecraft/util/math/Box;)Lnet/minecraft/entity/LivingEntity;"), cancellable = true)
    private void canStart(CallbackInfoReturnable<Boolean> cir) {
        if (targetEntity == null && !(mob instanceof HostileEntity)){
            targetEntity = mob.world.getClosestPlayer(new TargetPredicate().setBaseMaxDistance(12).setPredicate(player -> BSMTransformations.isLich(player, false)), mob);
            if (targetEntity != null) {
                Vec3d vec3d = TargetFinder.findTargetAwayFrom(this.mob, 16, 7, this.targetEntity.getPos());
                if (vec3d == null || this.targetEntity.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < this.targetEntity.squaredDistanceTo(this.mob)) {
                    cir.setReturnValue(false);
                } else {
                    this.fleePath = this.fleeingEntityNavigation.findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
                    cir.setReturnValue(this.fleePath != null);
                }
            }
        }
    }
}
