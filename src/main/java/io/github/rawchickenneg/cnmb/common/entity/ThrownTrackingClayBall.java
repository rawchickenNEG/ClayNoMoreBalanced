package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class ThrownTrackingClayBall extends ThrowableItemProjectile {

    public int trackCountdown = 10;
    public float explosionRadius = 10.0F;
    public double redirectionSpeed = 0.25D;

    public ThrownTrackingClayBall(EntityType<? extends ThrownTrackingClayBall> type, Level level) {
        super(type, level);
    }

    public ThrownTrackingClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_TRACKING_CLAY_BALL.get(), x, y, z, level);
    }

    public ThrownTrackingClayBall(LivingEntity entity, Level level) {
        super(EntityTypeRegistry.THROWN_TRACKING_CLAY_BALL.get(), entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.TRACKING_CLAY_BALL.get();
    }

    @Override
    public void tick() {
        super.tick();
        double rx = this.getRandomX(0.5D);
        double rz = this.getRandomZ(0.5D);
        this.level.addParticle(ParticleTypes.CLOUD, rx, this.getRandomY(),
                rz, 0.0D, 0.0D, 0.0D);
        if (this.tickCount > this.trackCountdown) {
            this.setSharedFlag(6, true);
            LivingEntity target = this.getTarget();
            if (!this.level.isClientSide && target != null) {
                Vec3 vec3 = this.getDeltaMovement();
                double c0 = (1.0D - this.redirectionSpeed);
                double d0 = target.getY() + target.getBbHeight() / 2.0D;
                double x = vec3.x * c0 + (target.getX() - this.getX()) * this.redirectionSpeed;
                double y = vec3.y * c0 + (d0 - this.getY()) * this.redirectionSpeed;
                double z = vec3.z * c0 + (target.getZ() - this.getZ()) * this.redirectionSpeed;
                this.setDeltaMovement(x, y, z);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction type = Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), this.explosionRadius, type);
            this.discard();
        }
    }

    @Nullable
    private LivingEntity getTarget() {
        LivingEntity target = null;
        double r = Config.CONFIG.TRACKING.get();
        AABB aabb = new AABB(this.getX() - r, this.getY() - r, this.getZ() - r,
                this.getX() + r, this.getY() + r, this.getZ() + r);
        for (LivingEntity entity : this.level.getEntitiesOfClass(LivingEntity.class, aabb)) {
            if (entity != null && !entity.getUUID().equals(this.uuid) && entity != this.getOwner()) {
                if (target == null || entity.distanceToSqr(this) < target.distanceToSqr(this)) {
                    target = entity;
                }
            }
        }
        return target;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}