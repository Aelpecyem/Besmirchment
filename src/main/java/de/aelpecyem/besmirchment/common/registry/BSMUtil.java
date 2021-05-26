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

public class BSMUtil {
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

    public static int HSBtoRGB(float hue, float saturation, float brightness){
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float)Math.floor(hue)) * 6.0f;
            float f = h - (float) Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }
}
