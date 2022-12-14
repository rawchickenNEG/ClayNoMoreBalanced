package io.github.rawchickenneg.cnmb.common.item;

import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTier {

    public static final Tier Clay = new ForgeTier(6, 0, 16F, Config.CONFIG.CLAYMORE.get() - 1, 30,
            BlockTags.NEEDS_DIAMOND_TOOL, Ingredient::of);
    public static final Tier ClayIngot = new ForgeTier(6, 0, 16F, 12F, 30,
            BlockTags.NEEDS_DIAMOND_TOOL, Ingredient::of);

    public static final Tier Porcelain = new ForgeTier(2, 225, 10F, 4F, 15,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.PORCELAIN_BRICK.get()));

}