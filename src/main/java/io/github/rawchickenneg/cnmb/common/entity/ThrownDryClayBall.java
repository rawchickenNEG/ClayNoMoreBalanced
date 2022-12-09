package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownDryClayBall extends ThrowableItemProjectile {

    public ThrownDryClayBall(EntityType<? extends ThrownDryClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownDryClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_DRY_CLAY_BALL.get(), entity, level);
    }

    public ThrownDryClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_DRY_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.dryClayBall.get();
    }


    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof Drowned) {
            ((Drowned) p_37486_.getEntity()).convertTo(EntityType.ZOMBIE, true);
        }else if (p_37486_.getEntity() instanceof Zombie){
            ((Zombie) p_37486_.getEntity()).convertTo(EntityType.HUSK, true);
        }
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.DRY.get());
        this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.dryClayBall.get());
        this.discard();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        double x = p_37488_.getBlockPos().getX();
        double y = p_37488_.getBlockPos().getY();
        double z = p_37488_.getBlockPos().getZ();
        BlockPos pos = new BlockPos(x, y, z);
        if (!this.level.isClientSide) {
            if (!this.level.getBlockState(new BlockPos(x, y, z)).is(BlockTags.LEAVES)){
                if (this.level.getBlockState(new BlockPos(x, y, z)).is(BlockTags.DIRT)){
                    if (this.level.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.DIRT){
                        this.playSound(SoundEvents.GRAVEL_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                        this.level.setBlock(new BlockPos(x, y, z), Blocks.SAND.defaultBlockState(), 3);
                        this.level.levelEvent(2001, new BlockPos(x, y, z),
                                Block.getId(Blocks.SAND.defaultBlockState()));
                    } else {
                        this.playSound(SoundEvents.GRASS_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                        this.level.setBlock(new BlockPos(x, y, z), Blocks.DIRT.defaultBlockState(), 3);
                        this.level.levelEvent(2001, new BlockPos(x, y, z),
                                Block.getId(Blocks.DIRT.defaultBlockState()));
                    }
                }
                this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.spawnAtLocation(ItemRegistry.dryClayBall.get());
                this.discard();
            } else {
                this.playSound(SoundEvents.GRASS_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
                Block.dropResources(Blocks.DEAD_BUSH.defaultBlockState(), this.level, new BlockPos(x, y, z), null);
                this.level.levelEvent(2001, new BlockPos(x, y, z), Block.getId(Blocks.DEAD_BUSH.defaultBlockState()));
            }
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}