package de.aelpecyem.besmirchment.common;

import de.aelpecyem.besmirchment.client.packet.FamiliarAbilityPacket;
import de.aelpecyem.besmirchment.client.packet.WerepyreJumpPacket;
import de.aelpecyem.besmirchment.common.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import moriyashiine.bewitchment.api.interfaces.entity.TransformationAccessor;
import moriyashiine.bewitchment.common.entity.living.WerewolfEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Besmirchment implements ModInitializer {
    public static final String MODID = "besmirchment";
    public static final ItemGroup BESMIRCHMENT = FabricItemGroupBuilder.create(Besmirchment.id("group")).icon(() -> new ItemStack(BSMObjects.FINAL_BROOM)).build();
    public static BSMConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(BSMConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(BSMConfig.class).getConfig();
        BSMConditions.init();
        BSMContracts.init();
        BSMEntityTypes.init();
        BSMObjects.init();
        BSMStatusEffects.init();
        BSMRecipeTypes.init();
        BSMTransformations.init();
        ServerPlayNetworking.registerGlobalReceiver(FamiliarAbilityPacket.ID, FamiliarAbilityPacket::handle);
        ServerPlayNetworking.registerGlobalReceiver(WerepyreJumpPacket.ID, WerepyreJumpPacket::handle);
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    public static boolean isWerepyre(Entity entity, boolean includeHumanForm) {
        if (entity instanceof TransformationAccessor && ((TransformationAccessor)entity).getTransformation() == BSMTransformations.WEREPYRE) {
            return includeHumanForm || ((TransformationAccessor)entity).getAlternateForm();
        } else {
            return entity instanceof WerewolfEntity;
        }
    }
}
