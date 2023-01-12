package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.entity.baseentity.ThrowableItemNoFrictionProjectileBase;
import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownPrismarineClayBall extends ThrowableItemNoFrictionProjectileBase {

    public ThrownPrismarineClayBall(EntityType<? extends ThrownPrismarineClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownPrismarineClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_PRISMARINE_CLAY_BALL.get(), entity, level);
    }

    public ThrownPrismarineClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_PRISMARINE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.PRISMARINE_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.PRISMARINE.get());
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            Block block = this.level.getBlockState(pos).getBlock();
            if (block == Blocks.PRISMARINE || block == Blocks.PRISMARINE_BRICKS || block == Blocks.DARK_PRISMARINE){
                int count = (int) Math.round( Math.random() * 3 + 1 );
                for (int i = 0; i < count; ++i) {
                    this.spawnAtLocation(Items.PRISMARINE_SHARD);
                }
                this.level.levelEvent(2001, pos, Block.getId(this.level.getBlockState(pos)));
                this.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            } else if (block == Blocks.SEA_LANTERN) {
                int count = (int) Math.round(Math.random() * 4 + 1 );
                for (int i = 0; i < count; ++i) {
                    this.spawnAtLocation(Items.PRISMARINE_CRYSTALS);
                }
                this.level.levelEvent(2001, pos, Block.getId(this.level.getBlockState(pos)));
                this.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.PRISMARINE_CLAY_BALL.get());
            this.discard();
        }

    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}