package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownPillagerClayBall extends ThrowableItemProjectile {

    public ThrownPillagerClayBall(EntityType<? extends ThrownPillagerClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownPillagerClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_PILLAGER_CLAY_BALL.get(), entity, level);
    }

    public ThrownPillagerClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_PILLAGER_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.PILLAGER_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        if (p_37486_.getEntity() instanceof LivingEntity livingEntity){
            if (!(livingEntity instanceof Player) || Config.CONFIG.PILLAGERF.get()){
                for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
                    ItemStack itemstack = livingEntity.getItemBySlot(equipmentslot);
                    if (!itemstack.isEmpty()) {
                        if (EnchantmentHelper.hasBindingCurse(itemstack)) {
                            livingEntity.getSlot(equipmentslot.getIndex() + 300).set(itemstack);
                        } else {
                            this.spawnAtLocation(itemstack);
                        }
                    }
                    livingEntity.setItemSlot(equipmentslot, ItemStack.EMPTY);
                }
            }
        }
        Entity thrower = this.getOwner() instanceof Player player ? player : null;
        p_37486_.getEntity().hurt(DamageSource.playerAttack((Player) thrower), Config.CONFIG.PILLAGER.get());
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.CROSSBOW_SHOOT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.PILLAGER_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}