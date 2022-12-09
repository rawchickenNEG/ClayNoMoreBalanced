package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownLightingClayBall extends ThrowableItemProjectile {

    public ThrownLightingClayBall(EntityType<? extends ThrownLightingClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownLightingClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_LIGHTING_CLAY_BALL.get(), entity, level);
    }

    public ThrownLightingClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_LIGHTING_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.lightingClayBall.get();
    }

    public void tick() {
        super.tick();
        this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.LIGHTING.get());

    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            LightningBolt bee = EntityType.LIGHTNING_BOLT.create(this.level);
            bee.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            this.level.addFreshEntity(bee);
            this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.discard();
        }

    }



    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}