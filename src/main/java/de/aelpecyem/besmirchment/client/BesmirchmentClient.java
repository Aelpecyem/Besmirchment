package de.aelpecyem.besmirchment.client;

import de.aelpecyem.besmirchment.client.packet.FamiliarAbilityPacket;
import de.aelpecyem.besmirchment.client.packet.WerepyreJumpPacket;
import de.aelpecyem.besmirchment.client.renderer.*;
import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.interfaces.WerepyreAccessor;
import de.aelpecyem.besmirchment.common.item.WitchyDyeItem;
import de.aelpecyem.besmirchment.common.packet.LichRevivePacket;
import de.aelpecyem.besmirchment.common.packet.SparklePacket;
import de.aelpecyem.besmirchment.common.registry.BSMBlockEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.client.BewitchmentClient;
import moriyashiine.bewitchment.common.item.TaglockItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class BesmirchmentClient implements ClientModInitializer {
    public static final KeyBinding FAMILIAR_ABILITY = new KeyBinding("key." + Besmirchment.MODID +".familiar_ability", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "itemGroup.besmirchment.group");
    public int abilityCooldown = 20;
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BSMObjects.PHYLACTERY);

        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.FINAL_BROOM, (dispatcher, context) -> new FinalBroomEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.WITCHY_DYE, (dispatcher, context) -> new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.WEREPYRE, (dispatcher, context) -> new WerepyreEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.BEELZEBUB, (dispatcher, context) -> new BeelzebubEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.INFECTIOUS_SPIT, (dispatcher, context) -> new InfectiousSpitEntityRenderer(dispatcher));

        BlockEntityRendererRegistry.INSTANCE.register(BSMBlockEntityTypes.PHYLACTERY, PhylacteryBlockEntityRenderer::new);
        FabricModelPredicateProviderRegistry.register(BSMObjects.DEMONIC_DEED, Besmirchment.id("variant"), (stack, world, entity) -> TaglockItem.hasTaglock(stack) ? 1 : 0);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? BSMObjects.WITCHY_DYE.getColor(stack) == WitchyDyeItem.FUNNI_NUMBER ? Color.HSBtoRGB(
                ((MinecraftClient.getInstance().world.getTime() + MinecraftClient.getInstance().getTickDelta()) % 100) / 100F,
                1, 1) : BSMObjects.WITCHY_DYE.getColor(stack) : 0xFFFFFF, BSMObjects.WITCHY_DYE);

        KeyBindingRegistryImpl.registerKeyBinding(FAMILIAR_ABILITY);
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (minecraftClient.player != null){
                if (BSMTransformations.isLich(minecraftClient.player, true)){
                    minecraftClient.player.setVelocity(minecraftClient.player.getRotationVector());
                }
                if (minecraftClient.player.input.jumping && BSMTransformations.isWerepyre(minecraftClient.player, false) && !minecraftClient.player.isOnGround() && ((WerepyreAccessor) minecraftClient.player).getLastJumpTicks() > 5) {
                    minecraftClient.player.jump();
                    WerepyreJumpPacket.send();
                }
            }
            if (FAMILIAR_ABILITY.wasPressed() && abilityCooldown <= 0){
                FamiliarAbilityPacket.send();
                abilityCooldown = 20;
            }else if (abilityCooldown > 0){
                abilityCooldown--;
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(SparklePacket.ID, SparklePacket::handle);
        ClientPlayNetworking.registerGlobalReceiver(LichRevivePacket.ID, LichRevivePacket::handle);
    }
}
