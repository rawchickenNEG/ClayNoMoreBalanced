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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.network.NetworkHooks;

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

    //public void tick() {
    //    super.tick();
    //    final Vec3 center = new Vec3(this.getX(), this.getY(), this.getZ());
    //    List<Entity> entities = this.getLevel().getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(5), e -> true).stream()
    //            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
    //    for (Entity target : entities) {
    //        if (target instanceof EnderDragon enderDragon && enderDragon.getHealth() <= 10.0F){
    //            this.spawnAtLocation(Items.DRAGON_EGG);
    //            enderDragon.hurt(DamageSource.playerAttack((Player) Objects.requireNonNull(this.getOwner())),200);
    //            this.discard();
    //        }
    //    }
    //}

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof LivingEntity livingEntity) {
            ItemStack egg = new ItemStack(ForgeSpawnEggItem.fromEntityType(livingEntity.getType()));
            if (livingEntity.getHealth() <= 10.0F){
                if (egg.getItem() != ItemStack.EMPTY.getItem()){
                    this.spawnAtLocation(egg);
                }
                Level pLevel = this.getLevel();
                pLevel.levelEvent(2009, livingEntity.getOnPos(), 0);
                this.playSound(SoundEvents.PUFFER_FISH_BLOW_UP , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                livingEntity.discard();
                this.discard();
            }
        }
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.EGG.get());
        this.playSound(SoundEvents.TURTLE_EGG_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.EGG_CLAY_BALL.get());
        this.discard();
    }


    protected void onHitBlock(BlockHitResult p_37488_) {
        super.onHitBlock(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.TURTLE_EGG_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.EGG_CLAY_BALL.get());
            this.discard();
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}