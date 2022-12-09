package io.github.rawchickenneg.cnmb.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class Toilet extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public Toilet(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> Shapes.or (box(4, 0, 0, 12, 10, 16), box(2, 10, 0, 14, 19, 5), box(3, 6, 0, 13, 10, 18));
            case NORTH -> Shapes.or (box(4, 0, 0, 12, 10, 16), box(2, 10, 11, 14, 19, 16), box(3, 6, -2, 13, 10, 16));
            case EAST -> Shapes.or (box(0, 0, 4, 16, 10, 12), box(0, 10, 2, 5, 19, 14), box(0, 6, 3, 18, 10, 13));
            case WEST -> Shapes.or (box(0, 0, 4, 16, 10, 12), box(11, 10, 2, 16, 19, 14), box(-2, 6, 3, 16, 10, 13));
        };
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
