package io.github.rawchickenneg.cnmb.common.entity;

import com.google.common.collect.Lists;
import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import java.util.Queue;

import static net.minecraft.world.level.block.Block.dropResources;

public class ThrownSpongeClayBall extends ThrowableItemProjectile {

    public ThrownSpongeClayBall(EntityType<? extends ThrownSpongeClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownSpongeClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_SPONGE_CLAY_BALL.get(), entity, level);
    }

    public ThrownSpongeClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_SPONGE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.SPONGE_CLAY_BALL.get();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        if (!this.level.isClientSide) {
            Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
            queue.add(new Tuple<>(p_37488_.getBlockPos(), 0));
            int i = 0;
            while (!queue.isEmpty()) {
                Tuple<BlockPos, Integer> tuple = queue.poll();
                BlockPos blockpos = tuple.getA();
                int j = tuple.getB();
                for (Direction direction : Direction.values()) {
                    BlockPos blockpos1 = blockpos.relative(direction);
                    BlockState blockstate = this.level.getBlockState(blockpos1);
                    FluidState fluidstate = this.level.getFluidState(blockpos1);
                    Material material = blockstate.getMaterial();
                    if (fluidstate.is(FluidTags.WATER)){
                        if (blockstate.getBlock() instanceof BucketPickup && !((BucketPickup) blockstate.getBlock()).pickupBlock(this.level, blockpos1, blockstate).isEmpty()) {
                            ++i;
                            if (j < 6) {
                                queue.add(new Tuple<>(blockpos1, j + 1));
                            }
                        } else if (blockstate.getBlock() instanceof LiquidBlock) {
                            this.level.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                            ++i;
                            if (j < 6) {
                                queue.add(new Tuple<>(blockpos1, j + 1));
                            }
                        } else if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
                            BlockEntity blockentity = blockstate.hasBlockEntity() ? this.level.getBlockEntity(blockpos1) : null;
                            dropResources(blockstate, this.level, blockpos1, blockentity);
                            this.level.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                            ++i;
                            if (j < 6) {
                                queue.add(new Tuple<>(blockpos1, j + 1));
                            }
                        }
                    }
                }

                if (i > 256) {
                    break;
                }
            }
            this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.SPONGE_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}