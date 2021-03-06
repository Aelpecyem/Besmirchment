package de.aelpecyem.besmirchment.common.entity;

import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class WerepyreEntity extends BWHostileEntity {
    public WerepyreEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean hasShiny() {
        return true;
    }

    @Override
    public int getVariants() {
        return 0;
    }
}
