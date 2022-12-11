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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class ThrownMinerClayBall extends ThrowableItemProjectile {

    private int freezingCountdown = 100;

    public ThrownMinerClayBall(EntityType<? extends ThrownMinerClayBall> type, Level level) {
        super(type, level);
    }

    public ThrownMinerClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_MINER_CLAY_BALL.get(), x, y, z, level);
    }

    public ThrownMinerClayBall(LivingEntity entity, Level level) {
        super(EntityTypeRegistry.THROWN_MINER_CLAY_BALL.get(), entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.MINER_CLAY_BALL.get();
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            Vec3 vec3 = this.getDeltaMovement();
            if (Math.abs(vec3.x) >= Math.abs(vec3.z)){
                this.setDeltaMovement(vec3.x, 0, 0);
            } else {
                this.setDeltaMovement(0, 0, vec3.z);
            }
            this.setNoGravity(true);
            this.startFreeze();
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.makeTrail();
        HitResult hitResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            if (!this.level.isClientSide && this.tickCount > this.freezingCountdown || Math.abs(this.getDeltaMovement().x + this.getDeltaMovement().z)< 0.1) {
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
        BlockPos pos = this.blockPosition().offset(0, 3, 0);
        int r = Config.CONFIG.MINING_RADIUS.get();
        for (BlockPos tmpPos : BlockPos.withinManhattan(pos, r, 3, r)) {
            this.doTerrainEffect(tmpPos);
        }
    }

    private void doTerrainEffect(BlockPos pos) {
        BlockState state = this.level.getBlockState(pos);
        if (state.is(BlockTags.create(new ResourceLocation("forge:miner_breakable" )))) {
            this.level.levelEvent(2001, pos,
                    Block.getId(this.level.getBlockState(pos)));
            this.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }

    private void makeTrail() {
        Vec3 vec3 = this.getDeltaMovement();
        BlockState stateId = Blocks.GRAVEL.defaultBlockState();
        ParticleType<BlockParticleOption> type = ParticleTypes.FALLING_DUST;
        ParticleOptions particle = new BlockParticleOption(type, stateId);
        for (int i = 0; i < 10; i++) {
            double dx = getX() + 1.5F * (random.nextFloat() - 0.5F);
            double dy = getY() + 1.5F * (random.nextFloat() - 0.5F);
            double dz = getZ() + 1.5F * (random.nextFloat() - 0.5F);
            this.level.addParticle(particle, dx, dy, dz, -vec3.x, -vec3.y, vec3.z);
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}