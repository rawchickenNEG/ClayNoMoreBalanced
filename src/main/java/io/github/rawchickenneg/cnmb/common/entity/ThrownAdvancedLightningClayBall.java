package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class ThrownAdvancedLightningClayBall extends ThrowableItemProjectile {

    public ThrownAdvancedLightningClayBall(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public ThrownAdvancedLightningClayBall(LivingEntity entity, Level level) {
        super(EntityTypeRegistry.THROWN_ADVANCED_LIGHTNING_CLAY_BALL.get(), entity, level);
    }

    public ThrownAdvancedLightningClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_ADVANCED_LIGHTNING_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.ADVANCED_LIGHTNING_CLAY_BALL.get();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > 100) {
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        this.spawnLightning();
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        this.spawnLightning();
    }

    private void spawnLightning() {
        Vec3 vec3 = this.getDeltaMovement();
        SoundEvent sound = SoundEvents.LIGHTNING_BOLT_IMPACT;
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(this.level);
        if (!this.level.isClientSide && lightning != null) {
            double x = this.getRandomX(10.0D);
            double z = this.getRandomZ(10.0D);
            lightning.moveTo(x, this.getRandomY(), z, this.getYRot(), 0.0F);
            this.setDeltaMovement(vec3.x, 0.0D, vec3.z);
            this.level.addFreshEntity(lightning);
            this.playSound(sound, 1.0F, 1.0F);
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}