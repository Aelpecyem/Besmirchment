package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.entity.FinalBroomEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class BSMEntityTypes {

    public static final EntityType<FinalBroomEntity> FINAL_BROOM = FabricEntityTypeBuilder.create(SpawnGroup.MISC, FinalBroomEntity::new).dimensions(EntityType.ARROW.getDimensions()).build();

    public static void init(){
        Util.register(Registry.ENTITY_TYPE, "final_broom", FINAL_BROOM);
    }
}
