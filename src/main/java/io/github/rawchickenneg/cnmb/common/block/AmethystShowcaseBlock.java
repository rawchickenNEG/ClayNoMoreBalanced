package io.github.rawchickenneg.cnmb.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AmethystShowcaseBlock extends Block {
    public AmethystShowcaseBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public float getEnchantPowerBonus(BlockState blockstate, LevelReader levelReader, BlockPos blockPos) {
        return 1f;
    }
}
