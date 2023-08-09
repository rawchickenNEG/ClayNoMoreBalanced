package io.github.rawchickenneg.cnmb;

import io.github.rawchickenneg.cnmb.common.registry.*;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import javax.annotation.Nonnull;

@Mod(ClayNoMoreBalanced.MOD_ID)
public class ClayNoMoreBalanced {
    public ClayNoMoreBalanced(){
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityTypeRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MobEffectRegistry.MOB_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        SoundEventRegistry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
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

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
    }

}
