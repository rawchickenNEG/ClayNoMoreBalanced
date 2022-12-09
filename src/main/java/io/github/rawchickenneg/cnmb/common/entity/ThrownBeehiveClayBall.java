package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownBeehiveClayBall extends ThrowableItemProjectile {

    public ThrownBeehiveClayBall(EntityType<? extends ThrownBeehiveClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownBeehiveClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_BEEHIVE_CLAY_BALL.get(), entity, level);
    }

    public ThrownBeehiveClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_BEEHIVE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.honeyClayBall.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (!(p_37486_.getEntity() instanceof Bee))
            p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.HONEY.get());

    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            for(int j = 0; j < 3; ++j) {
                Bee bee = EntityType.BEE.create(this.level);
                bee.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                this.level.addFreshEntity(bee);
            }
            this.playSound(SoundEvents.WOOD_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.honeyClayBall.get());
            this.discard();
        }

    }



    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}