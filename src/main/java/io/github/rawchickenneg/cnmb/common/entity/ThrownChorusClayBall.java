package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownChorusClayBall extends ThrowableItemProjectile {

    public ThrownChorusClayBall(EntityType<? extends ThrownChorusClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownChorusClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_CHORUS_CLAY_BALL.get(), entity, level);
    }

    public ThrownChorusClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_CHORUS_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.CHORUS_CLAY_BALL.get();
    }


    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (!this.level.isClientSide) {
            LivingEntity p_40714_ = (LivingEntity) p_37486_.getEntity();

            for(int i = 0; i < 16; ++i) {
                double d3 = p_40714_.getX() + (p_40714_.getRandom().nextDouble() - 0.5D) * 32.0D;
                double d4 = Mth.clamp(p_40714_.getY() + (double)(p_40714_.getRandom().nextInt(16) - 8), this.level.getMinBuildHeight(), (this.level.getMinBuildHeight() + ((ServerLevel)this.level).getLogicalHeight() - 1));
                double d5 = p_40714_.getZ() + (p_40714_.getRandom().nextDouble() - 0.5D) * 32.0D;
                if (p_40714_.isPassenger()) {
                    p_40714_.stopRiding();
                }

                net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(p_40714_, d3, d4, d5);
                if (p_40714_.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    this.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }
            p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 6.0F);
        }
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        double x = p_37488_.getBlockPos().getX();
        double y = p_37488_.getBlockPos().getY();
        double z = p_37488_.getBlockPos().getZ();
        BlockPos pos = new BlockPos(x, y, z);
        if (!this.level.isClientSide) {
            if ((this.level.getBlockState(pos).is(Blocks.CHORUS_FLOWER))){
                this.level.setBlock(pos, Blocks.CHORUS_FLOWER.defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(Blocks.CHORUS_FLOWER.defaultBlockState()));
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.WOOD_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.CHORUS_CLAY_BALL.get());
            this.discard();
        }

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}