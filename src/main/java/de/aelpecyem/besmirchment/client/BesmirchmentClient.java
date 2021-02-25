package de.aelpecyem.besmirchment.client;

import de.aelpecyem.besmirchment.client.renderer.FinalBroomEntityRenderer;
import de.aelpecyem.besmirchment.common.registry.BSMEntityTypes;
import moriyashiine.bewitchment.api.client.renderer.BroomEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class BesmirchmentClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(BSMEntityTypes.FINAL_BROOM, (dispatcher, context) -> new FinalBroomEntityRenderer(dispatcher));
    }
}
