package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import java.util.Objects;

public class ThrownVexClayBall extends ThrowableItemProjectile {

    public ThrownVexClayBall(EntityType<? extends ThrownVexClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownVexClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_VEX_CLAY_BALL.get(), entity, level);
    }

    public ThrownVexClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_VEX_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.VEX_CLAY_BALL.get();
    }

    int i = 0;
    public void tick() {
        super.tick();
        setNoGravity(true);
        this.level.addParticle(ParticleTypes.ASH, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
        int j = ++i;
        if (j >= 100 || this.getY() <= -100 || Math.abs(this.getDeltaMovement().x + this.getDeltaMovement().z)< 0.1){
            this.onVanish();
        }
    }
    public void onVanish(){
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.ENDER_EYE_DEATH , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            Entity thrower = this.getOwner() instanceof Player player ? player : null;
            if ((thrower instanceof Player player && !player.getAbilities().instabuild)){
                Objects.requireNonNull(thrower.spawnAtLocation(ItemRegistry.VEX_CLAY_BALL.get())).setPickUpDelay(0);
                thrower.playSound(SoundEvents.TRIDENT_RETURN , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            } else {
                this.spawnAtLocation(ItemRegistry.VEX_CLAY_BALL.get());
            }
            this.discard();
        }
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.indirectMagic(this, this.getOwner()), Config.CONFIG.VEX.get());
        this.playSound(SoundEvents.PHANTOM_BITE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}