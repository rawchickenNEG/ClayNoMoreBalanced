package io.github.rawchickenneg.cnmb.common.registry;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        ItemProperties.register(ItemRegistry.CLAY_BOW.get(), new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
            if (p_174637_ == null) {
                return 0.0F;
            } else {
                return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float) (p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 10.0F;
            }
        });
        ItemProperties.register(ItemRegistry.CLAY_BOW.get(), new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) ->
                p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F);
        ItemProperties.register(ItemRegistry.THROWABLE_CLAY_BALL.get(), new ResourceLocation("cb"), (p_174630_, p_174631_, p_174632_, p_174633_) ->
                "chrome ball".equals(p_174630_.getHoverName().getString().trim()) ? 1 : 0);
        ItemProperties.register(ItemRegistry.THROWABLE_CLAY_BALL.get(), new ResourceLocation("clay"), (p_174630_, p_174631_, p_174632_, p_174633_) ->
                "Clay".equals(p_174630_.getHoverName().getString().trim()) ? 1 : 0);
    }
}
