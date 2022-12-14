package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownRavagerClayBall extends ThrowableItemProjectile {

    public ThrownRavagerClayBall(EntityType<? extends ThrownRavagerClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownRavagerClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_RAVAGER_CLAY_BALL.get(), entity, level);
    }

    public ThrownRavagerClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_RAVAGER_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.RAVAGER_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        if (hitResult.getEntity() instanceof Villager) {
            int i = random.nextInt(5);
            if (i < 1) {
                ((Villager) hitResult.getEntity()).convertTo(EntityType.ILLUSIONER, false).setItemSlot(EquipmentSlot.MAINHAND, Items.BOW.getDefaultInstance());
            } else if (i < 2){
                ((Villager) hitResult.getEntity()).convertTo(EntityType.PILLAGER, false).setItemSlot(EquipmentSlot.MAINHAND, Items.CROSSBOW.getDefaultInstance());
            } else if (i < 3){
                ((Villager) hitResult.getEntity()).convertTo(EntityType.VINDICATOR, false).setItemSlot(EquipmentSlot.MAINHAND, Items.IRON_AXE.getDefaultInstance());
            } else if (i < 4){
                ((Villager) hitResult.getEntity()).convertTo(EntityType.EVOKER, false);
            } else {
                ((Villager) hitResult.getEntity()).convertTo(EntityType.WITCH, false);
            }
        } else if (hitResult.getEntity() instanceof IronGolem){
            ((IronGolem) hitResult.getEntity()).convertTo(EntityType.RAVAGER, false);
        }

        hitResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.RAVAGER.get());
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.METAL_BREAK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.RAVAGER_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}