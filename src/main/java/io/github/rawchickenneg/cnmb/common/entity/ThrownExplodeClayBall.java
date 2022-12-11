package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownExplodeClayBall extends ThrowableItemProjectile {

    private boolean canBreak;
    private int explosionPower = 4;

    public ThrownExplodeClayBall(EntityType<? extends ThrownExplodeClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownExplodeClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_EXPLODE_CLAY_BALL.get(), entity, level);
    }

    public ThrownExplodeClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_EXPLODE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.EXPLODE_CLAY_BALL.get();
    }

    public void setCanBreak(boolean canBreak) {
        this.canBreak = canBreak;
    }

    public void setExplosionPower(int explosionPower) {
        this.explosionPower = explosionPower;
    }

    public void tick() {
        super.tick();
        setRemainingFireTicks(10);
        setNoGravity(true);
        this.level.addParticle(ParticleTypes.FLAME, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0F);
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction type = this.canBreak ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE;
            level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.explosionPower, this.canBreak, type);
            this.discard();
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("CanBreak", this.canBreak);
        tag.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setCanBreak(tag.getBoolean("CanBreak"));
        if (tag.contains("ExplosionPower", 99)) {
            this.explosionPower = tag.getByte("ExplosionPower");
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}