package de.aelpecyem.besmirchment.common.ritualfunction;

import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.item.WitchyDyeItem;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.registry.RitualFunction;
import moriyashiine.bewitchment.common.block.entity.GlyphBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class ColorRitualFunction extends RitualFunction {
    public ColorRitualFunction() {
        super(ParticleTypes.CRIT, null);
    }

    @Override
    public void start(ServerWorld world, BlockPos glyphPos, BlockPos effectivePos, Inventory inventory, boolean catFamiliar) {
        int amount = catFamiliar ? 12 : 4;
        Box box = new Box(effectivePos.add(-4, -2, -4), effectivePos.add(4, 2, 4));
        List<Entity> entityList = world.getEntitiesByClass(Entity.class, box, entity -> (entity instanceof DyeableEntity || entity instanceof SheepEntity || (entity instanceof ItemEntity && ((ItemEntity) entity).getStack().getItem() instanceof DyeableItem)));
        for (int i = 0; i < Math.min(amount, entityList.size()); i++) {
            Entity entity = entityList.get(i);
            int color = Color.HSBtoRGB(world.random.nextFloat(), 1, 1);
            if (entity instanceof DyeableEntity && !(entity instanceof PlayerEntity)){
                ((DyeableEntity) entity).setColor(color);
             }else if (entity instanceof ItemEntity && ((ItemEntity) entity).getStack().getItem() instanceof DyeableItem){
                if (((ItemEntity) entity).getStack().getItem() instanceof WitchyDyeItem){
                    for (int i1 = 0; i1 < ((ItemEntity) entity).getStack().getCount(); i1++) {
                        ItemStack stack = new ItemStack(BSMObjects.WITCHY_DYE);
                        BSMObjects.WITCHY_DYE.setColor(stack, world.random.nextFloat() < 0.1F ? WitchyDyeItem.FUNNI_NUMBER : Color.HSBtoRGB(world.random.nextFloat(), 1, 1));
                        world.spawnEntity(new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack));
                    }
                    entity.remove();
                }else {
                    ((DyeableItem) ((ItemEntity) entity).getStack().getItem()).setColor(((ItemEntity) entity).getStack(), color);
                }
            } else if (entity instanceof SheepEntity){
                ((SheepEntity) entity).setColor(DyeColor.values()[world.random.nextInt(DyeColor.values().length)]);
            }
        }
        super.start(world, glyphPos, effectivePos, inventory, catFamiliar);
    }

    @Override
    public void tick(World world, BlockPos glyphPos, BlockPos effectivePos, boolean catFamiliar) {
        super.tick(world, glyphPos, effectivePos, catFamiliar);
        System.out.println("h");
        if (world.isClient) {
            Random random = world.getRandom();
            Color color = Color.getHSBColor(random.nextFloat(), 1, 1);
            world.addParticle(new DustParticleEffect(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1 + random.nextFloat()), effectivePos.getX() + 0.5 + random.nextGaussian() * 2, effectivePos.getY() + random.nextFloat() * 2, effectivePos.getZ() + 0.5 + random.nextGaussian() * 2, random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
        }
    }
}
