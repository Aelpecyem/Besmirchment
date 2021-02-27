package de.aelpecyem.besmirchment.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
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
}
