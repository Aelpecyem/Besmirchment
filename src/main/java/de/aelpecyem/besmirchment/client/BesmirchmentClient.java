package de.aelpecyem.besmirchment.client;

import de.aelpecyem.besmirchment.client.renderer.FinalBroomEntityRenderer;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@Environment(EnvType.CLIENT)
public class BesmirchmentClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.FINAL_BROOM, (dispatcher, context) -> new FinalBroomEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.WITCHY_DYE, (dispatcher, context) -> new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? BSMObjects.WITCHY_DYE.getColor(stack): 0xFFFFFF, BSMObjects.WITCHY_DYE);

      /*  ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (tintIndex == 1 && MinecraftClient.getInstance().world != null){
                ClientWorld clientWorld = MinecraftClient.getInstance().world;
                return Color.HSBtoRGB(((clientWorld.getTime() + MinecraftClient.getInstance().getTickDelta()) % 100) / 100F, 1, 1);
            }
            return 16777215;
        }, BSMObjects.ELITE_COFFIN);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 1 && MinecraftClient.getInstance().world != null){
                ClientWorld clientWorld = MinecraftClient.getInstance().world;
                return Color.HSBtoRGB(((clientWorld.getTime() + MinecraftClient.getInstance().getTickDelta()) % 100) / 100F, 1, 1);
            }
            return 16777215;
        }, BSMObjects.ELITE_COFFIN);*/
    }
}
