package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownPullClayBall extends ThrowableItemProjectile {

    public ThrownPullClayBall(EntityType<? extends ThrownPullClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownPullClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_PULL_CLAY_BALL.get(), entity, level);
    }

    public ThrownPullClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_PULL_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.PULL_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (this.getOwner() != null){
            double d0 = this.getOwner().getX() - p_37486_.getEntity().getX();
            double d1 = this.getOwner().getY() - p_37486_.getEntity().getY();
            double d2 = this.getOwner().getZ() - p_37486_.getEntity().getZ();
            p_37486_.getEntity().push(d0 * 0.5, d1 * 0.5, d2 * 0.25);
        } else {
            double d0 = this.getX() - p_37486_.getEntity().getX();
            double d1 = this.getZ() - p_37486_.getEntity().getZ();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
            p_37486_.getEntity().push(d0 / d2 * 4.0D, 0.5D, d1 / d2 * 4.0D);
        }
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.PULL.get());
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.FISHING_BOBBER_RETRIEVE, 1.0F, 1.0F / (this.random.nextFloat() * 0.2F));
            this.spawnAtLocation(ItemRegistry.PULL_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}