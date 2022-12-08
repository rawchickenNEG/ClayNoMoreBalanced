package io.github.rawchickenneg.cnmd.common.registry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SCLAME_POTATO_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SCLAME_CAKE_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CLAY_TREE_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.LARGE_ROSE_QUARTZ_BUD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.MEDIUM_ROSE_QUARTZ_BUD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SMALL_ROSE_QUARTZ_BUD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ROSE_QUARTZ_CLUSTER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.TOILET.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NATURAL_SCLAME_BLOCK.get(), RenderType.translucent());
        });
    }
}