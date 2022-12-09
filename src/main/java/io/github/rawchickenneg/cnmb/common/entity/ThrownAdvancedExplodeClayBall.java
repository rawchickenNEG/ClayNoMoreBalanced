package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ParticleTypes;
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

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownAdvancedExplodeClayBall extends ThrowableItemProjectile {

    public ThrownAdvancedExplodeClayBall(EntityType<? extends ThrownAdvancedExplodeClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownAdvancedExplodeClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_ADVANCED_EXPLODE_CLAY_BALL.get(), entity, level);
    }

    public ThrownAdvancedExplodeClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_ADVANCED_EXPLODE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.advancedExplodeClayBall.get();
    }

    public void tick() {
        super.tick();
        setRemainingFireTicks(10);
        setNoGravity(true);
        this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0F);
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            boolean flag = Config.CONFIG.ADVANCED.get();
            level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 8.0F, flag, flag ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE);
            this.discard();
        }

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}