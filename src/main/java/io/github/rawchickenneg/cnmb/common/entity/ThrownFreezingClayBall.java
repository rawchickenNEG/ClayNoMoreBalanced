package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class ThrownFreezingClayBall extends ThrowableItemProjectile {

    private int freezingCountdown = 100;

    public ThrownFreezingClayBall(EntityType<? extends ThrownFreezingClayBall> type, Level level) {
        super(type, level);
    }

    public ThrownFreezingClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_FREEZING_CLAY_BALL.get(), x, y, z, level);
    }

    public ThrownFreezingClayBall(LivingEntity entity, Level level) {
        super(EntityTypeRegistry.THROWN_FREEZING_CLAY_BALL.get(), entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.FREEZING_CLAY_BALL.get();
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            Vec3 vec3 = this.getDeltaMovement();
            double y = hitResult.getType() == HitResult.Type.BLOCK ? -0.1D : 0.0D;
            this.setDeltaMovement(vec3.x, y, vec3.z);
            this.startFreeze();
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.makeTrail();
        this.makeIceZone();
        HitResult hitResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            if (!this.level.isClientSide && this.tickCount > this.freezingCountdown) {
                this.discard();
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("FreezingCountdown", this.freezingCountdown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("FreezingCountdown", 99)) {
            this.setFreezingCountdown(tag.getInt("FreezingCountdown"));
        }
    }

    public void setFreezingCountdown(int freezingCountdown) {
        this.freezingCountdown = freezingCountdown;
    }

    private void startFreeze() {
        BlockPos pos = this.blockPosition();
        int r = Config.CONFIG.FREEZING_RADIUS.get();
        for (BlockPos tmpPos : BlockPos.withinManhattan(pos, r, r, r)) {
            this.doTerrainEffect(tmpPos);
        }
    }

    private void doTerrainEffect(BlockPos pos) {
        BlockState state = this.level.getBlockState(pos);
        Material material = state.getMaterial();
        float hardness = state.getDestroySpeed(this.level, pos);
        if (!state.is(BlockTags.ICE) && !state.is(BlockTags.SNOW) && hardness <= 50.0F && hardness >= 0F) {
            if (!state.is(BlockTags.REPLACEABLE_PLANTS) && !state.isAir()) {
                if (material == Material.WATER) {
                    this.level.setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
                } else if (material == Material.LAVA) {
                    this.level.setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
                } else {
                    this.level.setBlockAndUpdate(pos, Blocks.PACKED_ICE.defaultBlockState());
                }
            }
        }
    }

    private void makeTrail() {
        Vec3 vec3 = this.getDeltaMovement();
        BlockState stateId = Blocks.SNOW.defaultBlockState();
        ParticleType<BlockParticleOption> type = ParticleTypes.FALLING_DUST;
        ParticleOptions particle = new BlockParticleOption(type, stateId);
        for (int i = 0; i < 10; i++) {
            double dx = getX() + 1.5F * (random.nextFloat() - 0.5F);
            double dy = getY() + 1.5F * (random.nextFloat() - 0.5F);
            double dz = getZ() + 1.5F * (random.nextFloat() - 0.5F);
            this.level.addParticle(particle, dx, dy, dz, -vec3.x, -vec3.y, vec3.z);
        }
    }

    private void makeIceZone() {
        if (this.level.isClientSide) {
            BlockState stateId = Blocks.SNOW.defaultBlockState();
            ParticleType<BlockParticleOption> type = ParticleTypes.FALLING_DUST;
            ParticleOptions particle = new BlockParticleOption(type, stateId);
            for (int i = 0; i < 15; i++) {
                double dx = this.getX() + (random.nextFloat() - random.nextFloat()) * 3.0F;
                double dy = this.getY() + (random.nextFloat() - random.nextFloat()) * 3.0F;
                double dz = this.getZ() + (random.nextFloat() - random.nextFloat()) * 3.0F;
                this.level.addParticle(particle, dx, dy, dz, 0, 0, 0);
            }
        } else {
            this.hitNearbyEntities();
        }
    }

    private void hitNearbyEntities() {
        AABB aabb = this.getBoundingBox().inflate(3, 2, 3);
        List<LivingEntity> nearby = this.level.getEntitiesOfClass(LivingEntity.class, aabb);
        for (LivingEntity entity : nearby) {
            if (entity != this.getOwner()) {
                BlockPos pos = new BlockPos(entity.xOld, entity.getY(), entity.zOld);
                this.level.setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
                this.level.setBlockAndUpdate(pos.above(), Blocks.ICE.defaultBlockState());
            }
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}