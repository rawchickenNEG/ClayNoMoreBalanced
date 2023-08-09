package io.github.rawchickenneg.cnmb.common.registry;

import io.github.rawchickenneg.cnmb.ClayNoMoreBalanced;
import io.github.rawchickenneg.cnmb.common.effect.Undying;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ClayNoMoreBalanced.MOD_ID);

    public static final RegistryObject<MobEffect> UNDYING = MOB_EFFECTS.register("undying", () -> new Undying(MobEffectCategory.BENEFICIAL, -13382656));
}
