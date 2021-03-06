package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.entity.FinalBroomEntity;
import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import de.aelpecyem.besmirchment.common.entity.WitchyDyeEntity;
import moriyashiine.bewitchment.common.entity.living.WerewolfEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class BSMEntityTypes {
    public static final EntityType<FinalBroomEntity> FINAL_BROOM = FabricEntityTypeBuilder.create(SpawnGroup.MISC, FinalBroomEntity::new).dimensions(EntityType.ARROW.getDimensions()).build();
    public static final EntityType<WitchyDyeEntity> WITCHY_DYE = FabricEntityTypeBuilder.<WitchyDyeEntity>create(SpawnGroup.MISC, WitchyDyeEntity::new).dimensions(EntityType.POTION.getDimensions()).trackable(4, 10).build();
    public static final EntityType<WerepyreEntity> WEREPYRE = FabricEntityTypeBuilder.<WerepyreEntity>create(SpawnGroup.MONSTER, WerepyreEntity::new).dimensions(EntityDimensions.fixed(0.8F, 2.8F)).build();

    public static void init(){
        Util.register(Registry.ENTITY_TYPE, "final_broom", FINAL_BROOM);
        Util.register(Registry.ENTITY_TYPE, "witchy_dye", WITCHY_DYE);
        Util.register(Registry.ENTITY_TYPE, "werepyre", WEREPYRE);
        FabricDefaultAttributeRegistry.register(WEREPYRE, WerewolfEntity.createAttributes());
    }
}
