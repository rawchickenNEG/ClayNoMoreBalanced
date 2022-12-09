package io.github.rawchickenneg.cnmb;

import io.github.rawchickenneg.cnmb.common.registry.BlockRegistry;
import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.common.registry.SoundEventRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(ClayNoMoreBalanced.MOD_ID)
public class ClayNoMoreBalanced {
    public ClayNoMoreBalanced(){
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityTypeRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SoundEventRegistry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(ClayNoMoreBalanced.MOD_ID)
    {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.CLAYMORE.get());
        }
    };
    public static final String MOD_ID = "clay_no_more_balanced";
}
