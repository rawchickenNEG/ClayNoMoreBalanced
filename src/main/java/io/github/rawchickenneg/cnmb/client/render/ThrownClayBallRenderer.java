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
        event.registerEntityRenderer(EntityTypeRegistry.thrownClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownClayBallFromBow.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownBurnClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownTNTClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownExplodeClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownAdvancedExplodeClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownUltimateExplodeClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownPhantomClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownVexClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownRecallClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownVoidClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownChorusClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownFlintClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownBoneClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownLightingClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownTorch.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownBeehiveClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownBasketClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownPullClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownGoldenClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownGildedClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownAmethystClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownObsidianClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownQuartzClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownRavagerClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownEnderClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownExchangeClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownPillagerClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownSpongeClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownFlowerClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownDryClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownLeavesClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownStoneClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownLazuliClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownPrismarineClayBall.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownDuplicateClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownGlowstoneClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownBaseClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownGlowClayBall.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownBrick.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownFireCharge.get(), (p_174086_) -> {
            return new ThrownItemRenderer<>(p_174086_, 1.0F, true);
        });
        event.registerEntityRenderer(EntityTypeRegistry.thrownIronIngot.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownGoldIngot.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.thrownNetheriteIngot.get(), ThrownItemRenderer::new);
    }
}

