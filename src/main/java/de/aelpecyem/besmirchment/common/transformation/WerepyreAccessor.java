package de.aelpecyem.besmirchment.common.transformation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface WerepyreAccessor {
    void setWerepyreVariant(int variant);
    int getWerepyreVariant();

    int getLastJumpTicks();
    void setLastJumpTicks(int ticks);

    @Environment(EnvType.CLIENT)
    float getLastJumpProgress();
}
