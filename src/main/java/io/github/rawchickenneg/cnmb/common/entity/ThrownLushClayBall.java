package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownLushClayBall extends ThrowableItemProjectile {

    public ThrownLushClayBall(EntityType<? extends ThrownLushClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownLushClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_LUSH_CLAY_BALL.get(), entity, level);
    }

    public ThrownLushClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_LUSH_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.LUSH_CLAY_BALL.get();
    }


    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0);
        if (p_37486_.getEntity() instanceof Chicken chicken){
            chicken.eggTime = 0;
        }
        this.playSound(SoundEvents.GRASS_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.LUSH_CLAY_BALL.get());
        this.discard();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos().relative(p_37488_.getDirection());
        BlockPos pos1 = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if (this.level.getBlockState(pos).getBlock() instanceof BonemealableBlock){
                if (this.level instanceof ServerLevel) {
                    if (this.level.getBlockState(pos).getBlock() instanceof CropBlock cropblock){
                        if(this.level.getBlockState(pos.offset(1,0,0)).getBlock() == Blocks.AIR && this.level.getBlockState(pos.offset(1,-1,0)).getBlock() == Blocks.FARMLAND){
                            this.level.setBlock(pos.offset(1,0,0), cropblock.defaultBlockState(), 3);
                        }
                        if(this.level.getBlockState(pos.offset(-1,0,0)).getBlock() == Blocks.AIR && this.level.getBlockState(pos.offset(-1,-1,0)).getBlock() == Blocks.FARMLAND){
                            this.level.setBlock(pos.offset(-1,0,0), cropblock.defaultBlockState(), 3);
                        }
                        if(this.level.getBlockState(pos.offset(0,0,1)).getBlock() == Blocks.AIR && this.level.getBlockState(pos.offset(0,-1,1)).getBlock() == Blocks.FARMLAND){
                            this.level.setBlock(pos.offset(0,0,1), cropblock.defaultBlockState(), 3);
                        }
                        if(this.level.getBlockState(pos.offset(0,0,-1)).getBlock() == Blocks.AIR && this.level.getBlockState(pos.offset(0,-1,-1)).getBlock() == Blocks.FARMLAND){
                            this.level.setBlock(pos.offset(0,0,-1), cropblock.defaultBlockState(), 3);
                        }
                    }
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
            this.spawnAtLocation(ItemRegistry.LUSH_CLAY_BALL.get());
            this.discard();
        }

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}