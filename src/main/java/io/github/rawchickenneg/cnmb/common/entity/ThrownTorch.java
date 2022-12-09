package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownTorch extends ThrowableItemProjectile {

    public ThrownTorch(EntityType<? extends ThrownTorch> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownTorch(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_TORCH.get(), entity, level);
    }

    public ThrownTorch(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_TORCH.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.TORCH;
    }

    protected void onHitEntity(EntityHitResult p_37386_) {
        super.onHitEntity(p_37386_);
        if (!this.level.isClientSide) {
            Entity entity = p_37386_.getEntity();
            if (!entity.fireImmune()) {
                Entity entity1 = this.getOwner();
                int i = entity.getRemainingFireTicks();
                entity.setSecondsOnFire(5);
                boolean flag = entity.hurt(DamageSource.thrown(this, this.getOwner()), 0.0F);
                if (!flag) {
                    entity.setRemainingFireTicks(i);
                } else if (entity1 instanceof LivingEntity) {
                    this.doEnchantDamageEffects((LivingEntity)entity1, entity);
                }
            }

        }
    }

    protected void onHitBlock(BlockHitResult p_37384_) {
        super.onHitBlock(p_37384_);
        if (!this.level.isClientSide) {
            BlockPos blockpos = p_37384_.getBlockPos().relative(p_37384_.getDirection());
            if (this.level.isEmptyBlock(blockpos)) {
                this.level.setBlockAndUpdate(blockpos, Blocks.TORCH.defaultBlockState());
            } else {
                this.spawnAtLocation(ItemRegistry.THROWABLE_TORCH.get());
            }
        }
    }

    protected void onHit(HitResult p_37388_) {
        super.onHit(p_37388_);
        if (!this.level.isClientSide) {
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}