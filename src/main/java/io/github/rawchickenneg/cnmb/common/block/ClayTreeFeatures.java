package io.github.rawchickenneg.cnmb.common.block;

import io.github.rawchickenneg.cnmb.common.registry.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.OptionalInt;

public class ClayTreeFeatures {
    private static TreeConfiguration.TreeConfigurationBuilder createClayTree() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockRegistry.CLAY_LOG.get()), new FancyTrunkPlacer(10, 30, 5), BlockStateProvider.simple(BlockRegistry.NATURAL_SCLAME_BLOCK.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CLAY_TREE = FeatureUtils.register("clay_tree", Feature.TREE, createClayTree().build());
}
