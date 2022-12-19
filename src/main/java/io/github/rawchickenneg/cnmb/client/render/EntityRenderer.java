package io.github.rawchickenneg.cnmb.client.render;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRenderer {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.CLAY_CHICKEN.get(), ClayChickenRenderer::new);
    }

}