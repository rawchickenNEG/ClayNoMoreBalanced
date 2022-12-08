package io.github.rawchickenneg.cnmd.common.item;

import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ClayRing extends Item {

    public ClayRing(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return new ItemStack(ItemRegistry.clayRing.get(), 1);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }


}
