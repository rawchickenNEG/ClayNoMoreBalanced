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
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownIronIngot extends ThrowableItemProjectile {

    public ThrownIronIngot(EntityType<? extends ThrownIronIngot> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownIronIngot(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_IRON_INGOT.get(), entity, level);
    }

    public ThrownIronIngot(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_IRON_INGOT.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.IRON_INGOT;
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        Entity entity = p_37486_.getEntity();
        if (entity instanceof IronGolem){
            float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
            ((IronGolem) entity).heal(25.0F);
            this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
        } else {
            this.spawnAtLocation(ItemRegistry.THROWABLE_IRON_INGOT.get());
            entity.hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.IRON.get());
        }
    }

    protected void onHitBlock(BlockHitResult p_37384_) {
        super.onHitBlock(p_37384_);
        if (!this.level.isClientSide) {
            this.spawnAtLocation(ItemRegistry.THROWABLE_IRON_INGOT.get());
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.METAL_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}