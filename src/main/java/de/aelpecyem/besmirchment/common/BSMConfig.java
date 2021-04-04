package de.aelpecyem.besmirchment.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;

@Config(name = Besmirchment.MODID)
public class BSMConfig implements ConfigData {
    @RequiresRestart
    public boolean enableFinalBroom = true;
    @RequiresRestart
    public boolean enableWitchyDye = true;
    @RequiresRestart
    public boolean enableEliteCoffin = true;
    @RequiresRestart
    public boolean enableLovePotion = true;
    @RequiresRestart
    public boolean enableWerepyrism = true;
    @RequiresRestart
    public boolean enableSunscreen = true;
    @RequiresRestart
    public boolean enableTamableDemons = true;
    @RequiresRestart
    public boolean enableLichdom = true;
    @ConfigEntry.Gui.CollapsibleObject
    public Mobs mobs = new Mobs();
    public static class Mobs{
        @RequiresRestart
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public final int werepyreWeight = 10;
        @RequiresRestart
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
        public final int werepyreMinGroupCount = 1;
        @RequiresRestart
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
        public final int werepyreMaxGroupCount = 1;
        @RequiresRestart
        public boolean enableBeelzebub = true;

        public float tormentScrollTradeChance = 0.5F;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public UniversalFamiliars universalFamiliars = new UniversalFamiliars();
    public static class UniversalFamiliars{
        public boolean enable = true;
        @ConfigEntry.BoundedDiscrete(min = -200, max = 200)
        public int villagerFamiliarReputationBase = 20;
        public float chickenFamiliarEggChance = 0.005F;
    }
}
