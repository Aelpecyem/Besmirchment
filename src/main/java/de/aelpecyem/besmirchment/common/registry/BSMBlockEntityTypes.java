package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.block.entity.PhylacteryBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BSMBlockEntityTypes {
    public static final BlockEntityType<PhylacteryBlockEntity> PHYLACTERY =  BlockEntityType.Builder.create(PhylacteryBlockEntity::new, BSMObjects.PHYLACTERY).build(null);
    public static void init(){
        Util.register(Registry.BLOCK_ENTITY_TYPE, "phylactery", PHYLACTERY);
    }
}
