package io.github.rawchickenneg.cnmb.common.item.tools.base;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ClayHoe extends HoeItem {
    public ClayHoe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return this.getItemStackLimit(pStack) == 1;
    }
}
