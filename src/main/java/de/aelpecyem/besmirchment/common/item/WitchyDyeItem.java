package de.aelpecyem.besmirchment.common.item;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.WitchyDyeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Collections;

public class WitchyDyeItem extends Item implements DyeableItem {
    public WitchyDyeItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            ThrownItemEntity potion = new WitchyDyeEntity(world, user);
            potion.setItem(itemStack);
            potion.setProperties(user, user.pitch, user.yaw, 0.0F, 0.8F, 1.0F);
            world.spawnEntity(potion);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public int getColor(ItemStack stack){
        if (stack.hasTag() && stack.getTag().contains("Color")){
            return stack.getTag().getInt("Color");
        }
        return 0xFFFFFF;
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("Color") ? super.getTranslationKey(stack) : "item." + Besmirchment.MODID + ".witchy_bleach";
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("Color", color);
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("Color");
    }

    @Override
    public void removeColor(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("Color")) {
            stack.getTag().remove("color");
        }
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (isIn(group)) {
            for (DyeColor value : DyeColor.values()) {
                ItemStack stack = new ItemStack(this);
                stacks.add(DyeableItem.blendAndSetColor(stack, Collections.singletonList(DyeItem.byColor(value))));
            }
        }
    }
}