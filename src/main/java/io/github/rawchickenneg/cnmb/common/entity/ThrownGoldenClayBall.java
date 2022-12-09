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
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownGoldenClayBall extends ThrowableItemProjectile {

    public ThrownGoldenClayBall(EntityType<? extends ThrownGoldenClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownGoldenClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_GOLDEN_CLAY_BALL.get(), entity, level);
    }

    public ThrownGoldenClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_GOLDEN_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.goldenClayBall.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof ZombieVillager zombieVillager) {
            zombieVillager.convertTo(EntityType.VILLAGER, true);
        } else if (p_37486_.getEntity() instanceof ZombifiedPiglin zombifiedPiglin) {
            zombifiedPiglin.convertTo(EntityType.PIGLIN, true);
        } else if (p_37486_.getEntity() instanceof Zoglin zoglin) {
            zoglin.convertTo(EntityType.HOGLIN, true);
        } else{
            p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.GOLDEN.get());
        }

    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.METAL_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.goldenClayBall.get());
            this.discard();
        }

    }



    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}