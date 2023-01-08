package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;

public class ThrownEggClayBall extends ThrowableItemProjectile {

    public ThrownEggClayBall(EntityType<? extends ThrownEggClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownEggClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_EGG_CLAY_BALL.get(), entity, level);
    }

    public ThrownEggClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_EGG_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.EGG_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof LivingEntity livingEntity) {
            String name = Objects.requireNonNull(livingEntity.getType().getRegistryName()).toString();
            if (Registry.ITEM.get(new ResourceLocation(name + "_spawn_egg")) != ItemStack.EMPTY.getItem() && livingEntity.getHealth() <= 10.0F){
                this.spawnAtLocation(Registry.ITEM.get(new ResourceLocation(name + "_spawn_egg")));
                Level pLevel = this.getLevel();
                pLevel.levelEvent(2009, livingEntity.getOnPos(), 0);
                this.playSound(SoundEvents.PUFFER_FISH_BLOW_UP , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                livingEntity.discard();
                this.discard();
            } else {
                p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.CLAY.get());
                this.spawnAtLocation(ItemRegistry.EGG_CLAY_BALL.get());
                this.discard();
            }
        }
    }

    protected void onHitBlock(BlockHitResult p_37488_) {
        super.onHitBlock(p_37488_);
        if (!this.level.isClientSide) {
            this.spawnAtLocation(ItemRegistry.EGG_CLAY_BALL.get());
            this.discard();
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}