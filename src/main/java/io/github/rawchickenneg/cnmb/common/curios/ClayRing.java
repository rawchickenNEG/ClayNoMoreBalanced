package io.github.rawchickenneg.cnmb.common.curios;

import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ClayRing extends CuriosItem {

    public ClayRing(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if (this == ItemRegistry.BRICK_RING.get()){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 62, 0, false, false, true));
        }
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if (this == ItemRegistry.CLAY_RING.get()){
            return new ItemStack(ItemRegistry.CLAY_RING.get(), 1);
        } else if (this == ItemRegistry.BRICK_RING.get()){
            return new ItemStack(ItemRegistry.BRICK_RING.get(), 1);
        }
        return new ItemStack(ItemRegistry.CLAY_RING.get(), 1);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }


}
