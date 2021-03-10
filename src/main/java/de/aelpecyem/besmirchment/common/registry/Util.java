package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BlockItem;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class Util {
    public static <T> T register(Registry<? super T> registry, String name, T entry) {
        return Registry.register(registry, Besmirchment.id(name), entry);
    }

    public static Block registerBlock(String name, Block entry) {
        Registry.register(Registry.ITEM, Besmirchment.id(name), new BlockItem(entry, new FabricItemSettings().group(Besmirchment.BESMIRCHMENT)));
        return Registry.register(Registry.BLOCK, Besmirchment.id(name), entry);
    }

    public static EntityHitResult hitscanEntity(World world, LivingEntity user, double distance, Predicate<Entity> targetPredicate){
        Vec3d vec3d = user.getCameraPosVec(1);
        Vec3d vec3d2 = user.getRotationVec(1);
        Vec3d vec3d3 = vec3d.add(vec3d2.x * 16, vec3d2.y * 16, vec3d2.z * 16);
        double squareDistance = Math.pow(distance, 2);
        return ProjectileUtil.getEntityCollision(world, user, vec3d, vec3d3, user.getBoundingBox().stretch(vec3d2.multiply(squareDistance)).expand(1.0D, 1.0D, 1.0D), targetPredicate);
    }
}
