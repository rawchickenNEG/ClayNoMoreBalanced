package io.github.rawchickenneg.cnmb.common.item;

import io.github.rawchickenneg.cnmb.ClayNoMoreBalanced;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ClaymoreCopy extends SwordItem {
    public ClaymoreCopy(Properties rarity) {
        super(ItemTier.Clay, 0, -2.4F, new Properties().tab(ClayNoMoreBalanced.CREATIVE_TAB));
    }

    @Override
    public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
        if (blockstate.getBlock() == Blocks.OBSIDIAN)
            return 114514f;
        return 6f;
    }


    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        boolean result = super.hurtEnemy(p_43278_, p_43279_, p_43280_);

        if (result) {
            if (p_43279_.getHealth() <= 20)
                p_43279_.hurt(DamageSource.GENERIC.bypassArmor().bypassInvul().bypassMagic(), 100);

            p_43279_.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 10, 2));
            p_43279_.hurt(DamageSource.GENERIC.bypassArmor().bypassInvul().bypassMagic(), (float) ((p_43279_.getMaxHealth()) * 0.2));
        }

        return result;
    }
}
