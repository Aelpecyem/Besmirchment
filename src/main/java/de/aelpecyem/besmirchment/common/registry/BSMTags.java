package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;

public class BSMTags {
    public static Tag<EntityType<?>> ILLEGAL_FAMILIARS = TagRegistry.entityType(Besmirchment.id("illegal_familiars"));
}
