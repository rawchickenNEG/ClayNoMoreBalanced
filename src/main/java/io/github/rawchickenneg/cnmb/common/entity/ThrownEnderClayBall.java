package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class ThrownEnderClayBall extends ThrowableItemProjectile {

    private int clayType = 0;

    public ThrownEnderClayBall(EntityType<? extends ThrownEnderClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownEnderClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_ENDER_CLAY_BALL.get(), entity, level);
    }

    public ThrownEnderClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_ENDER_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.ENDER_CLAY_BALL.get();
    }

    public void setType(int clayType) {
        this.clayType = clayType;
    }

    public void tick() {
        super.tick();
        if (this.clayType == 1) {
            this.level.addParticle(ParticleTypes.PORTAL, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
            if (Math.abs(this.getDeltaMovement().x + this.getDeltaMovement().y + this.getDeltaMovement().z)< 0.01){
                this.playSound(SoundEvents.ENDER_EYE_DEATH, 1.0F, 1.0F);
                this.onVanish();
            }
        }
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.ENDER.get());
    }

    protected void onHit(HitResult p_37504_) {
        super.onHit(p_37504_);
        if (!this.level.isClientSide && !this.isRemoved()) {
            Entity entity = this.getOwner();
            if (entity != null) {
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            this.onVanish();
        }

    }

    protected void onVanish(){
        if (this.clayType == 1) {
            this.spawnAtLocation(ItemRegistry.END_CLAY_BALL.get()).setPickUpDelay(0);
        } else {
            this.spawnAtLocation(ItemRegistry.ENDER_CLAY_BALL.get()).setPickUpDelay(0);
        }
        this.discard();
    }

    @Nullable
    public Entity changeDimension(ServerLevel p_37506_, net.minecraftforge.common.util.ITeleporter teleporter) {
        Entity entity = this.getOwner();
        if (entity != null && entity.level.dimension() != p_37506_.dimension()) {
            this.setOwner(null);
        }
        return super.changeDimension(p_37506_, teleporter);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}