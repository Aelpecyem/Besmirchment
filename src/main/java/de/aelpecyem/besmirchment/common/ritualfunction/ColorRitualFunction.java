package de.aelpecyem.besmirchment.common.ritualfunction;

import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.item.WitchyDyeItem;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import de.aelpecyem.besmirchment.common.registry.BSMUtil;
import moriyashiine.bewitchment.api.registry.RitualFunction;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ColorRitualFunction extends RitualFunction {
    public ColorRitualFunction() {
        super(ParticleTypes.ENCHANTED_HIT, null);
    }


    @Override
    public boolean isValid(ServerWorld world, BlockPos pos, Inventory inventory) {
        return super.isValid(world, pos, inventory);
    }

    @Override
    public void tick(World world, BlockPos glyphPos, BlockPos effectivePos, boolean catFamiliar) {
        super.tick(world, glyphPos, effectivePos, catFamiliar);
        if (world.isClient) {
            Random random = world.getRandom();
            Vector3f rgb = new Vector3f(Vec3d.unpackRgb(BSMUtil.HSBtoRGB(random.nextFloat(), 1, 1)));
            world.addParticle(new DustParticleEffect(rgb.getX(), rgb.getY(), rgb.getZ(), 1 + random.nextFloat()), effectivePos.getX() + 0.5 + random.nextGaussian() * 2, effectivePos.getY() + random.nextFloat() * 2, effectivePos.getZ() + 0.5 + random.nextGaussian() * 2, random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
        }else if (world.getTime() % 10 == 0){
            int amount = catFamiliar ? 12 : 4;
            Box box = new Box(effectivePos.add(-4, -2, -4), effectivePos.add(4, 2, 4));
            List<Entity> entityList = world.getEntitiesByClass(Entity.class, box, entity -> (entity instanceof DyeableEntity || entity instanceof SheepEntity || (entity instanceof ItemEntity && ((ItemEntity) entity).getStack().getItem() instanceof DyeableItem)));
            for (int i = 0; i < Math.min(amount, entityList.size()); i++) {
                Entity entity = entityList.get(i);
                int color = BSMUtil.HSBtoRGB(world.random.nextFloat(), 1, 1);
                if (entity instanceof DyeableEntity && !(entity instanceof PlayerEntity)){
                    ((DyeableEntity) entity).setColor(color);
                }else if (entity instanceof ItemEntity && ((ItemEntity) entity).getStack().getItem() instanceof DyeableItem){
                    if (((ItemEntity) entity).getStack().getItem() instanceof WitchyDyeItem){
                        for (int i1 = 0; i1 < ((ItemEntity) entity).getStack().getCount(); i1++) {
                            ItemStack stack = new ItemStack(BSMObjects.WITCHY_DYE);
                            BSMObjects.WITCHY_DYE.setColor(stack, world.random.nextFloat() < 0.1F ? WitchyDyeItem.FUNNI_NUMBER : BSMUtil.HSBtoRGB(world.random.nextFloat(), 1, 1));
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
        }
    }
}
