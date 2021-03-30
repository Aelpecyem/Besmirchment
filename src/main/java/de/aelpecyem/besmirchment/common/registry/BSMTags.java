package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;

public class BSMTags {
    public static Tag<EntityType<?>> ILLEGAL_FAMILIARS = TagRegistry.entityType(Besmirchment.id("illegal_familiars"));
    public static Tag<EntityType<?>> PURE_SOULS = TagRegistry.entityType(Besmirchment.id("pure_souls"));
    public static Tag<Block> GHOST_IMPASSABLE = TagRegistry.block(Besmirchment.id("ghost_impassable"));

}
