package io.github.rawchickenneg.cnmb.client.render;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ThrownClayBallRenderer {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_CLAY_BALL_FROM_BOW.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BURN_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_TNT_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_EXPLODE_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_ADVANCED_EXPLODE_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_ULTIMATE_EXPLODE_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_PHANTOM_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_VEX_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_RECALL_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_VOID_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_CHORUS_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_FLINT_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BONE_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_LIGHTING_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_ADVANCED_LIGHTNING_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_TORCH.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BEEHIVE_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BASKET_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_PULL_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_GOLDEN_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_GILDED_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_AMETHYST_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_OBSIDIAN_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_QUARTZ_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_RAVAGER_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_ENDER_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_EXCHANGE_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_PILLAGER_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_SPONGE_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_FLOWER_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_DRY_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_LEAVES_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_STONE_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_LAZULI_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_PRISMARINE_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_TRACKING_CLAY_BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_DUPLICATE_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_GLOWSTONE_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BASE_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_GLOW_CLAY_BALL.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BRICK.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_FIRE_CHARGE.get(), (p_174086_) -> new ThrownItemRenderer<>(p_174086_, 1.0F, true));
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_IRON_INGOT.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_GOLD_INGOT.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_NETHERITE_INGOT.get(), ThrownItemRenderer::new);
    }

}