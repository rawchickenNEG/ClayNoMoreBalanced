package io.github.rawchickenneg.cnmd.common.entity;

import io.github.rawchickenneg.cnmd.config.Config;
import io.github.rawchickenneg.cnmd.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownGildedClayBall extends ThrowableItemProjectile {

    public ThrownGildedClayBall(EntityType<? extends ThrownGildedClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownGildedClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownGildedClayBall.get(), entity, level);
    }

    public ThrownGildedClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownGildedClayBall.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.gildedClayBall.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof Piglin piglin && !piglin.hasEffect(MobEffects.CONFUSION)) {
            piglin.setItemInHand(InteractionHand.OFF_HAND, Items.GOLD_INGOT.getDefaultInstance());
            piglin.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 10, 0));
        }else{
            p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.GILDED.get());
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.STONE_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.gildedClayBall.get());
            this.discard();
        }

    }



    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}