package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import de.aelpecyem.besmirchment.common.transformation.LichAccessor;
import de.aelpecyem.besmirchment.common.transformation.LichTransformation;
import de.aelpecyem.besmirchment.common.transformation.WerepyreTransformation;
import dev.emi.nourish.NourishComponent;
import dev.emi.nourish.NourishMain;
import dev.emi.nourish.groups.NourishGroup;
import dev.emi.nourish.groups.NourishGroups;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.api.registry.Transformation;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.WerewolfEntity;
import moriyashiine.bewitchment.common.registry.BWRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BSMTransformations {
    public static final Transformation WEREPYRE = new WerepyreTransformation();
    public static final Transformation LICH = new LichTransformation();
    public static void init(){
        BSMUtil.register(BWRegistries.TRANSFORMATIONS, "werepyre", WEREPYRE);
        BSMUtil.register(BWRegistries.TRANSFORMATIONS, "lich", LICH);
    }

    public static boolean isLich(Entity entity, boolean isGost){
        return entity instanceof TransformationAccessor && ((TransformationAccessor) entity).getTransformation() == LICH && (!isGost || ((TransformationAccessor) entity).getAlternateForm());
    }
    public static boolean isWerepyre(Entity entity, boolean includeHumanForm) {
        if (entity instanceof TransformationAccessor && ((TransformationAccessor)entity).getTransformation() == WEREPYRE) {
            return includeHumanForm || ((TransformationAccessor)entity).getAlternateForm();
        } else {
            return entity instanceof WerepyreEntity;
        }
    }

    public static boolean hasWerepyrePledge(PlayerEntity player){
        return BewitchmentAPI.isPledged(player, "pledge.besmirchment.beelzebub");
    }

    public static void handleNourish(PlayerEntity player){
        if (Bewitchment.isNourishLoaded) {
            NourishComponent nourishComponent = NourishMain.NOURISH.get(player);
            for (NourishGroup group : NourishGroups.groups) {
                if (nourishComponent.getValue(group) != group.getDefaultValue()) {
                    nourishComponent.setValue(group, group.getDefaultValue());
                }
            }
        }
    }
}
