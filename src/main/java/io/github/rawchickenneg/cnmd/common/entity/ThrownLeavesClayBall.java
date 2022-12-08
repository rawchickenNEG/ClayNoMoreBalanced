package io.github.rawchickenneg.cnmd.common.entity;

import io.github.rawchickenneg.cnmd.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmd.config.Config;
import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownLeavesClayBall extends ThrowableItemProjectile {

    public ThrownLeavesClayBall(EntityType<? extends ThrownLeavesClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownLeavesClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownLeavesClayBall.get(), entity, level);
    }

    public ThrownLeavesClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownLeavesClayBall.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.leavesClayBall.get();
    }


    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof Husk) {
            ((Husk) p_37486_.getEntity()).convertTo(EntityType.ZOMBIE, true);
        }else if (p_37486_.getEntity() instanceof Zombie){
            ((Zombie) p_37486_.getEntity()).convertTo(EntityType.DROWNED, true);
        }
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.LEAVES.get());
        this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.leavesClayBall.get());
        this.discard();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if (!this.level.getBlockState(pos).is(BlockTags.LEAVES)){
                if (this.level.getBlockState(pos).is(BlockTags.SAND)){
                        this.playSound(SoundEvents.GRAVEL_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                        this.level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
                        this.level.levelEvent(2001, pos,
                                Block.getId(Blocks.DIRT.defaultBlockState()));
                    }else if (this.level.getBlockState(pos).getBlock() == Blocks.DIRT){
                    this.playSound(SoundEvents.GRASS_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                    this.level.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                    this.level.levelEvent(2001, pos,
                            Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                    }
                this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.spawnAtLocation(ItemRegistry.leavesClayBall.get());
                this.discard();
            }else{
                this.playSound(SoundEvents.GRASS_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                Block.dropResources(this.level.getBlockState(pos), this.level, pos, null);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            }
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}