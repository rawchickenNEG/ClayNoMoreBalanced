package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownFlowerClayBall extends ThrowableItemProjectile {

    public ThrownFlowerClayBall(EntityType<? extends ThrownFlowerClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownFlowerClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_FLOWER_CLAY_BALL.get(), entity, level);
    }

    public ThrownFlowerClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_FLOWER_CLAY_BALL.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.flowerClayBall.get();
    }

    public void tick() {
        super.tick();
        this.level.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof Animal animal) {
            Player thrower = this.getOwner() instanceof Player player ? player : null;
            animal.setInLove(thrower);
            if ((animal instanceof Sheep sheep)){
                int color = (int) Math.round( Math.random() * 15 + 1 );
                sheep.setColor(DyeColor.byId(color));
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.flowerClayBall.get());
            this.discard();
        }
    }




    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}