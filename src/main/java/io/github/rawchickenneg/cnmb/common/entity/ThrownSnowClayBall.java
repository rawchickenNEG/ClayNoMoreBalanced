package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownSnowClayBall extends ThrowableItemProjectile {

    public ThrownSnowClayBall(EntityType<? extends ThrownSnowClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownSnowClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_SNOW_CLAY_BALL.get(), entity, level);
    }

    public ThrownSnowClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_SNOW_CLAY_BALL.get(), x, y, z, level);
    }

    public boolean disableDrop;

    public void setNoDrop(boolean disableDrop) {
        this.disableDrop = disableDrop;
    }
    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.SNOW_CLAY_BALL.get();
    }

    int i = 0;
    boolean used;
    public void tick() {
        super.tick();
        if (this.isPassenger() && this.getVehicle() != null){
            int j = ++i;
            this.getVehicle().setIsInPowderSnow(true);
            this.getVehicle().setTicksFrozen(Math.min(j, 150));
            if (j >= 600){
                this.stopRiding();
            }
        }
    }

    protected void onHitEntity(EntityHitResult p_37386_) {
        super.onHitEntity(p_37386_);
        if (!this.level.isClientSide) {
            Entity entity = p_37386_.getEntity();
            entity.hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.SNOW.get());
            if (!used && entity.canFreeze()){
                this.startRiding(entity);
                used = true;
            }
        }
    }

    protected void onHitBlock(BlockHitResult p_37384_) {
        super.onHitBlock(p_37384_);
        if (!this.level.isClientSide) {
            if (!this.disableDrop){
                this.spawnAtLocation(ItemRegistry.SNOW_CLAY_BALL.get());
            }
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}