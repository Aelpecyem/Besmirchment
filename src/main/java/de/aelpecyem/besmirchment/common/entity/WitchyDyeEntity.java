package de.aelpecyem.besmirchment.common.entity;

import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class WitchyDyeEntity extends ThrownItemEntity {
    public WitchyDyeEntity(World world, LivingEntity owner) {
        super(BSMEntityTypes.WITCHY_DYE, owner, world);
    }

    public WitchyDyeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            Box box = this.getBoundingBox().expand(2.0D, 2.0D, 2.0D);
            List<LivingEntity> list = this.world.getNonSpectatingEntities(LivingEntity.class, box);
            int color = getItem().hasTag() && getItem().getTag().contains("Color") ? getStack().getTag().getInt("Color") : -1;
            for (LivingEntity livingEntity : list) {
                if (livingEntity instanceof DyeableEntity){
                    if (!(livingEntity instanceof PlayerEntity) || BewitchmentAPI.isWerewolf(livingEntity, false)) {
                        ((DyeableEntity) livingEntity).setColor(color);
                    }
                }
                for (ItemStack itemStack : livingEntity.getItemsEquipped()) {
                    if (itemStack.getItem() instanceof DyeableItem){
                        if (color < 0){
                            ((DyeableItem) itemStack.getItem()).removeColor(itemStack);
                        }else {
                            ((DyeableItem) itemStack.getItem()).setColor(itemStack, color);
                        }
                    }
                }
            }
            this.world.syncWorldEvent(2007, this.getBlockPos(), BSMObjects.WITCHY_DYE.getColor(this.getStack()));
            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return BSMObjects.WITCHY_DYE;
    }
}
