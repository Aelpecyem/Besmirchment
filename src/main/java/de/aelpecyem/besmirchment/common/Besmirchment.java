package de.aelpecyem.besmirchment.common;

import de.aelpecyem.besmirchment.client.packet.FamiliarAbilityPacket;
import de.aelpecyem.besmirchment.client.packet.WerepyreJumpPacket;
import de.aelpecyem.besmirchment.common.registry.*;
import de.aelpecyem.besmirchment.common.transformation.LichAccessor;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import moriyashiine.bewitchment.common.block.entity.GlyphBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.CraftRequestC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;

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
        BSMBlockEntityTypes.init();
        BSMStatusEffects.init();
        BSMTransformations.init();
        BSMSounds.init();
        BSMRitualFunctions.init();
        ServerPlayNetworking.registerGlobalReceiver(FamiliarAbilityPacket.ID, FamiliarAbilityPacket::handle);
        ServerPlayNetworking.registerGlobalReceiver(WerepyreJumpPacket.ID, WerepyreJumpPacket::handle);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (handler.player instanceof LichAccessor){
                ((LichAccessor) handler.player).updateCachedSouls();
            }
        });
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, b) -> ((LichAccessor)newPlayer).updateCachedSouls());
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

}
