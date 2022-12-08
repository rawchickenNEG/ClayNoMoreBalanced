package io.github.rawchickenneg.cnmb.common.block;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class ClayTreeGrower extends AbstractTreeGrower {
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_204329_, boolean p_204330_) {
        return ClayTreeFeatures.CLAY_TREE;
    }
}
