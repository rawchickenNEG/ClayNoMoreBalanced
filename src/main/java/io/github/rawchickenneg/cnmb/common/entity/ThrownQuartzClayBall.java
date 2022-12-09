package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
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
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownQuartzClayBall extends ThrowableItemProjectile {

    public ThrownQuartzClayBall(EntityType<? extends ThrownQuartzClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownQuartzClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_QUARTZ_CLAY_BALL.get(), entity, level);
    }

    public ThrownQuartzClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_QUARTZ_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.QUARTZ_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        Entity thrower = this.getOwner() instanceof Player player ? player : null;
        p_37486_.getEntity().hurt(DamageSource.playerAttack((Player) thrower), Config.CONFIG.QUARTZ.get());
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.NETHER_ORE_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.QUARTZ_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}