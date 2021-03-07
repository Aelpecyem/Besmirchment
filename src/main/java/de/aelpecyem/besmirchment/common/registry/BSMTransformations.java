package de.aelpecyem.besmirchment.common.registry;

import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.api.registry.Transformation;
import moriyashiine.bewitchment.common.entity.living.WerewolfEntity;
import moriyashiine.bewitchment.common.registry.BWRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class BSMTransformations {
    public static final Transformation WEREPYRE = new Transformation();
    public static void init(){
        Util.register(BWRegistries.TRANSFORMATIONS, "werepyre", WEREPYRE);
    }

    public static boolean isWerepyre(Entity entity, boolean includeHumanForm) {
        if (entity instanceof TransformationAccessor && ((TransformationAccessor)entity).getTransformation() == WEREPYRE) {
            return includeHumanForm || ((TransformationAccessor)entity).getAlternateForm();
        } else {
            return entity instanceof WerewolfEntity;
        }
    }

    public static boolean hasWerepyrePledge(PlayerEntity player){
        return true/*BewitchmentAPI.isPledged(player.world, "pledge.besmirchment.beelzebub", player.getUuid())*/;
    }
}
