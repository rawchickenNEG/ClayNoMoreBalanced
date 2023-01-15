package io.github.rawchickenneg.cnmb.common.item.tools.base;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class ClayShovel extends ShovelItem {
    public ClayShovel(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return this.getItemStackLimit(pStack) == 1;
    }
}
