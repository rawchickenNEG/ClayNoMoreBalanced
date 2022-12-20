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

public class ThrownCactusClayBall extends ThrowableItemProjectile {

    public ThrownCactusClayBall(EntityType<? extends ThrownCactusClayBall> entityType, Level level) {
        super(entityType, level);
    }


    public ThrownCactusClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_CACTUS_CLAY_BALL.get(), entity, level);
    }

    public ThrownCactusClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_CACTUS_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.CACTUS_CLAY_BALL.get();
    }

    int i = 0;
    boolean used;
    public void tick() {
        super.tick();
        if (this.isPassenger() && this.getVehicle() != null){
            this.getVehicle().hurt(DamageSource.CACTUS, 1);
        }
        int j = ++i;
        if (j >= 200){
            this.stopRiding();
        }
    }

    protected void onHitEntity(EntityHitResult p_37386_) {
        super.onHitEntity(p_37386_);
        if (!this.level.isClientSide) {
            Entity entity = p_37386_.getEntity();
            entity.hurt(DamageSource.indirectMagic(this, this.getOwner()), Config.CONFIG.CACTUS.get());
            if (!used){
                this.startRiding(entity);
                used = true;
            }
        }
    }

    protected void onHitBlock(BlockHitResult p_37384_) {
        super.onHitBlock(p_37384_);
        if (!this.level.isClientSide) {
            this.spawnAtLocation(ItemRegistry.CACTUS_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}