package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownClayBall extends ThrowableItemProjectile {

    public ThrownClayBall(EntityType<? extends ThrownClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_CLAY_BALL.get(), entity, level);
    }

    public ThrownClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.CLAY_BALL;
    }

    public void handleEntityEvent(byte p_37484_) {
        if (p_37484_ == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * d0, ((double)this.random.nextFloat() - 0.5D) * d0, ((double)this.random.nextFloat() - 0.5D) * d0);
            }
        }
    }

    public void spawnParticle(){
        double r0 = 2.0D;
        double n0 = 32.0D;
        for(int i = 0; i < n0; ++i) {
            double sin = r0 * Math.sin((0.5 * i / n0) * 180D * Math.PI);
            double cos = Math.cos((0.5 * i / n0) * 180D * Math.PI);
            if (i % 2 == 0){
                this.level.addParticle(ParticleTypes.DRIPPING_WATER, this.getX() + sin, this.getY(), this.getZ() + r0 * cos, 0, 0, 0);
            }else {
                this.level.addParticle(ParticleTypes.DRIPPING_LAVA, this.getX() + sin, this.getY(), this.getZ() + r0 * cos, 0, 0, 0);
            }

        }
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof Axolotl axolotl){
            if (axolotl.getVariant() == Axolotl.Variant.BLUE){
                this.spawnAtLocation(ItemRegistry.AXOLOTL_CLAY_BALL_BLUE.get());
            }else if (axolotl.getVariant() == Axolotl.Variant.WILD){
                this.spawnAtLocation(ItemRegistry.AXOLOTL_CLAY_BALL_WILD.get());
            }else if (axolotl.getVariant() == Axolotl.Variant.GOLD){
                this.spawnAtLocation(ItemRegistry.AXOLOTL_CLAY_BALL_GOLD.get());
            }else if (axolotl.getVariant() == Axolotl.Variant.LUCY){
                this.spawnAtLocation(ItemRegistry.AXOLOTL_CLAY_BALL_LUCY.get());
            }else if (axolotl.getVariant() == Axolotl.Variant.CYAN){
                this.spawnAtLocation(ItemRegistry.AXOLOTL_CLAY_BALL_CYAN.get());
            }
            this.spawnParticle();
            p_37486_.getEntity().discard();
        } else if (p_37486_.getEntity() instanceof Rabbit rabbit){
            rabbit.setRabbitType(99);
            this.spawnParticle();
        } else if (p_37486_.getEntity() instanceof Ravager ravager && ravager.getStunnedTick() != 0){
            this.playSound(SoundEvents.RAVAGER_ROAR, 1.0F, 1.0F);
            level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 2.0F, Explosion.BlockInteraction.NONE);
            this.spawnAtLocation(ItemRegistry.RAVAGER_METAL_PARTS.get());
            this.spawnAtLocation(Items.SADDLE);
            ravager.discard();
            this.spawnParticle();
        } else if (p_37486_.getEntity() instanceof Vex vex){
            this.spawnAtLocation(ItemRegistry.TINY_VEX_CLAY_BALL.get());
            vex.discard();
            this.spawnParticle();
        } else if (p_37486_.getEntity() instanceof Bat bat){
            this.spawnAtLocation(ItemRegistry.BAT_CLAY_BALL.get());
            bat.discard();
            this.spawnParticle();
        }else if (p_37486_.getEntity() instanceof Chicken chicken && chicken.isInLove() && chicken.isOnFire()){
            this.spawnAtLocation(ItemRegistry.CHICKEN_CHOP.get());
            chicken.discard();
            this.spawnParticle();
        }else{
            p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.CLAY.get());
            this.spawnAtLocation(ItemRegistry.THROWABLE_CLAY_BALL.get());
        }
    }

    protected void onHitBlock(BlockHitResult p_37488_) {
        super.onHitBlock(p_37488_);
        if (!this.level.isClientSide){
            this.spawnAtLocation(ItemRegistry.THROWABLE_CLAY_BALL.get());
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.GRAVEL_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}