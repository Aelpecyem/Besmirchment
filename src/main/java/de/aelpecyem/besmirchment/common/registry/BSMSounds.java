package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class BSMSounds {
    public static final SoundEvent ENTITY_GENERIC_SPIT = new SoundEvent(Besmirchment.id("entity.generic.spit"));
    public static final SoundEvent LICH_REVIVE = new SoundEvent(Besmirchment.id("entity.lich.revive"));

    public static final SoundEvent BEELZEBUB_AMBIENT = new SoundEvent(Besmirchment.id("entity.beelzebub.ambient"));
    public static final SoundEvent BEELZEBUB_HURT = new SoundEvent(Besmirchment.id("entity.beelzebub.hurt"));
    public static final SoundEvent BEELZEBUB_DEATH = new SoundEvent(Besmirchment.id("entity.beelzebub.death"));
    public static void init(){
        BSMUtil.register(Registry.SOUND_EVENT, "entity.generic.spit", ENTITY_GENERIC_SPIT);
        BSMUtil.register(Registry.SOUND_EVENT, "entity.beelzebub.ambient", BEELZEBUB_AMBIENT);
        BSMUtil.register(Registry.SOUND_EVENT, "entity.beelzebub.hurt", BEELZEBUB_HURT);
        BSMUtil.register(Registry.SOUND_EVENT, "entity.beelzebub.death", BEELZEBUB_DEATH);
    }
}
