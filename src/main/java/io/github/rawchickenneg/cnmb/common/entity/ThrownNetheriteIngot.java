package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownNetheriteIngot extends ThrowableItemProjectile {

    public ThrownNetheriteIngot(EntityType<? extends ThrownNetheriteIngot> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownNetheriteIngot(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_NETHERITE_INGOT.get(), entity, level);
    }

    public ThrownNetheriteIngot(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_NETHERITE_INGOT.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.NETHERITE_INGOT;
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.NETHERITE.get());
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.NETHERITE_BLOCK_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.THROWABLE_NETHERITE_INGOT.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}