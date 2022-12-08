package io.github.rawchickenneg.cnmd.common.entity;

import io.github.rawchickenneg.cnmd.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmd.config.Config;
import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
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
public class ThrownGlowClayBall extends ThrowableItemProjectile {

    public ThrownGlowClayBall(EntityType<? extends ThrownGlowClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownGlowClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownGlowClayBall.get(), entity, level);
    }

    public ThrownGlowClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownGlowClayBall.get(), x, y, z, level);
    }

    public void tick() {
        super.tick();
        this.level.addParticle(ParticleTypes.GLOW, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.glowClayBall.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20 * 30, 0));
        }
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.GLOW.get());
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.glowClayBall.get());
            this.discard();
        }

    }



    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}