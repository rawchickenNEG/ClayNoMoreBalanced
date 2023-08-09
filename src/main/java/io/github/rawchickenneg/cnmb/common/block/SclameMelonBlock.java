package io.github.rawchickenneg.cnmb.common.block;

import io.github.rawchickenneg.cnmb.common.registry.BlockRegistry;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SclameMelonBlock extends StemGrownBlock {
    public SclameMelonBlock(BlockBehaviour.Properties p_54829_) {
        super(p_54829_);
    }

    public StemBlock getStem() {
        return (StemBlock) BlockRegistry.SCLAME_MELON_STEM_BLOCK.get();
    }

    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock)BlockRegistry.ATTACHED_SCLAME_MELON_STEM_BLOCK.get();
    }
}