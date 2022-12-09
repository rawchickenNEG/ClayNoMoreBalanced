package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownBoneClayBall extends ThrowableItemProjectile {

    public ThrownBoneClayBall(EntityType<? extends ThrownBoneClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownBoneClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_BONE_CLAY_BALL.get(), entity, level);
    }

    public ThrownBoneClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_BONE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.BONE_CLAY_BALL.get();
    }


    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.AMETHYST.get());
        this.playSound(SoundEvents.AMETHYST_BLOCK_PLACE , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.BONE_CLAY_BALL.get());
        this.discard();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos().relative(p_37488_.getDirection());
        BlockPos pos1 = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if (this.level.getBlockState(pos).getBlock() instanceof BonemealableBlock){
                if (this.level instanceof ServerLevel) {
                    BonemealableBlock bonemealableblock = (BonemealableBlock) this.level.getBlockState(pos).getBlock();
                    bonemealableblock.performBonemeal((ServerLevel) this.level, this.level.random, pos, this.level.getBlockState(pos));
                    this.level.levelEvent(2005, pos, 0);
                }
            } else if (this.level.getBlockState(pos1).getBlock() instanceof BonemealableBlock){
                if (this.level instanceof ServerLevel) {
                    BonemealableBlock bonemealableblock = (BonemealableBlock) this.level.getBlockState(pos1).getBlock();
                    bonemealableblock.performBonemeal((ServerLevel) this.level, this.level.random, pos1, this.level.getBlockState(pos1));
                    this.level.levelEvent(2005, pos1, 0);
                }
            }
            this.playSound(SoundEvents.BONE_MEAL_USE , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.BONE_CLAY_BALL.get());
            this.discard();
        }

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}