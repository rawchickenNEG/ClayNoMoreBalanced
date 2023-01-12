package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.entity.baseentity.ThrowableItemNoFrictionProjectileBase;
import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownVehicleClayBall extends ThrowableItemNoFrictionProjectileBase {

    public ThrownVehicleClayBall(EntityType<? extends ThrownVehicleClayBall> entityType, Level level) {
        super(entityType, level);
    }


    public ThrownVehicleClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_VEHICLE_CLAY_BALL.get(), entity, level);
    }

    public ThrownVehicleClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_VEHICLE_CLAY_BALL.get(), x, y, z, level);
    }

    public boolean staticRide;

    private int clayType = 0;

    public void setStaticRide(boolean staticRide) {
        this.staticRide = staticRide;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    public void setType(int clayType) {
        this.clayType = clayType;
    }

    int i = 0;
    public void tick() {
        super.tick();
        int j = ++i;
        if (this.getOwner() instanceof Player player && j < 10){
            player.startRiding(this);
        }
        if (j >= 10 && !this.isVehicle()){
            this.onVanish();
        }
        if (this.staticRide && this.level.getBlockState(this.getOnPos()).getBlock() == Blocks.AIR){
            this.onVanish();
        }
        if (this.clayType == 1){
            this.level.addParticle(ParticleTypes.FLAME, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
        }
    }

    protected void onHitEntity(EntityHitResult p_37386_) {
        super.onHitEntity(p_37386_);
        if (!this.level.isClientSide || !this.staticRide) {
            p_37386_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.VEHICLE.get());
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (this.getOwner() instanceof Player player){
            player.stopRiding();
        }

    }

    protected void onVanish(){
        if (!this.level.isClientSide) {
            if (!this.staticRide){
                if (this.clayType == 0){
                    this.playSound(SoundEvents.WOOD_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                    this.spawnAtLocation(ItemRegistry.VEHICLE_CLAY_BALL.get());
                } else {
                    this.playSound(SoundEvents.BLAZE_HURT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                    this.spawnAtLocation(ItemRegistry.BLAZE_RIDER_CLAY_BALL.get());
                }

            }
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}