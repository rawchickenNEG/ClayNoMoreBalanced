package io.github.rawchickenneg.cnmb.common.registry;

import io.github.rawchickenneg.cnmb.ClayNoMoreBalanced;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ClayNoMoreBalanced.MOD_ID);
    public static final RegistryObject<SoundEvent> TNT_LAUNCH = SOUNDS.register("tnt_launch", () -> new SoundEvent(new ResourceLocation(ClayNoMoreBalanced.MOD_ID, "tnt_launch")));
    public static final RegistryObject<SoundEvent> TNT_EXPLODE = SOUNDS.register("tnt_explode", () -> new SoundEvent(new ResourceLocation(ClayNoMoreBalanced.MOD_ID, "tnt_explode")));
    public static final RegistryObject<SoundEvent> EGG_LAUNCH = SOUNDS.register("egg_launch", () -> new SoundEvent(new ResourceLocation(ClayNoMoreBalanced.MOD_ID, "egg_launch")));
}
