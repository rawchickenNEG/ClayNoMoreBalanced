package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownPhantomClayBall extends ThrowableItemProjectile {

    public ThrownPhantomClayBall(EntityType<? extends ThrownPhantomClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownPhantomClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_PHANTOM_CLAY_BALL.get(), entity, level);
    }

    public ThrownPhantomClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_PHANTOM_CLAY_BALL.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.phantomClayBall.get();
    }

    public void tick() {
        super.tick();
        setNoGravity(true);
        this.level.addParticle(ParticleTypes.ASH, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.indirectMagic(this, this.getOwner()), Config.CONFIG.PHANTOM.get());
        this.playSound(SoundEvents.PHANTOM_BITE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.ENDER_EYE_DEATH , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.phantomClayBall.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}