package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class ThrownExchangeClayBall extends ThrowableItemProjectile {

    public ThrownExchangeClayBall(EntityType<? extends ThrownExchangeClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownExchangeClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_EXCHANGE_CLAY_BALL.get(), entity, level);
    }

    public ThrownExchangeClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_EXCHANGE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.EXCHANGE_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        if (!this.level.isClientSide && !this.isRemoved()) {
            Entity owner = this.getOwner();
            Entity target = hitResult.getEntity();
            if (owner != null && target != owner) {
                BlockPos pos = target.getOnPos(); //先提前把目标实体的坐标给获取到
                target.hurt(DamageSource.thrown(this, owner), Config.CONFIG.EXCHANGE.get());
                target.teleportTo(owner.getX(), owner.getY(), owner.getZ());
                owner.teleportTo(pos.getX(), pos.getY(), pos.getZ());
                owner.resetFallDistance();
                this.spawnAtLocation(ItemRegistry.EXCHANGE_CLAY_BALL.get());
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                this.discard();
            }
        }
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        if (!this.level.isClientSide && !this.isRemoved()) {
            Entity owner = this.getOwner();
            if (owner != null) {
                owner.teleportTo(this.getX(), this.getY(), this.getZ());
                owner.resetFallDistance();
            }
            this.spawnAtLocation(ItemRegistry.EXCHANGE_CLAY_BALL.get());
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            this.discard();
        }
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