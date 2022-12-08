package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownDuplicateClayBall extends ThrowableItemProjectile {

    public ThrownDuplicateClayBall(EntityType<? extends ThrownDuplicateClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownDuplicateClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownDuplicateClayBall.get(), entity, level);
    }

    public ThrownDuplicateClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownDuplicateClayBall.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.duplicateClayBall.get();
    }

    public void tick() {
        super.tick();
        this.level.addParticle(ParticleTypes.WITCH, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0.0F);
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        BlockState blockstate = this.level.getBlockState(pos);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.STONE_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            BlockEntity blockentity = blockstate.hasBlockEntity() ? this.level.getBlockEntity(pos) : null;
            if (blockstate.hasBlockEntity()){
                Block.dropResources(blockstate, this.level, pos, blockentity);
            }else{
                ItemEntity entityToSpawn = new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockstate.getBlock()));
                entityToSpawn.setDefaultPickUpDelay();
                this.level.addFreshEntity(entityToSpawn);
            }
            this.level.levelEvent(2001, pos,
                    Block.getId(this.level.getBlockState(pos)));

        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.STONE_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.duplicateClayBall.get());
            this.discard();
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}