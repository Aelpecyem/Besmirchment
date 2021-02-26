package de.aelpecyem.besmirchment.client;

import de.aelpecyem.besmirchment.client.renderer.FinalBroomEntityRenderer;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import de.aelpecyem.besmirchment.common.registry.BSMObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class BesmirchmentClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.FINAL_BROOM, (dispatcher, context) -> new FinalBroomEntityRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.WITCHY_DYE, (dispatcher, context) -> new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? BSMObjects.WITCHY_DYE.getColor(stack): 0xFFFFFF, BSMObjects.WITCHY_DYE);
    }
}
