package io.github.rawchickenneg.cnmd.common.item;

import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmd.config.Config;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTier {
    public static final Tier Clay = new ForgeTier(4, 0, 8F, Config.CONFIG.CLAYMORE.get() - 1, 20,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of()
    );

    public static final Tier Porcelain = new ForgeTier(2, 225, 6F, 4F, 20,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ItemRegistry.porcelainBrick.get())
    );
}