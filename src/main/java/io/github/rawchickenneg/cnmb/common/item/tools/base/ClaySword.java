package io.github.rawchickenneg.cnmb.common.item.tools.base;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ClaySword extends SwordItem {
    public ClaySword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return this.getItemStackLimit(pStack) == 1;
    }
}
