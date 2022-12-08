package io.github.rawchickenneg.cnmd.common.entity;

import io.github.rawchickenneg.cnmd.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmd.config.Config;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownPrismarineClayBall extends ThrowableItemProjectile {

    public ThrownPrismarineClayBall(EntityType<? extends ThrownPrismarineClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownPrismarineClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownPrismarineClayBall.get(), entity, level);
    }

    public ThrownPrismarineClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownPrismarineClayBall.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.prismarineClayBall.get();
    }

    @Override
    public void tick() {
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level.getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, blockpos, blockstate, this, (TheEndGatewayBlockEntity)blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
            f = 0.99F;

        this.setDeltaMovement(vec3.scale((double)f));
        if (!this.isNoGravity()) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, vec31.y - (double)this.getGravity(), vec31.z);
        }

        this.setPos(d2, d0, d1);

        if (this.isInWater()) {
            for(int i = 0; i < 4; ++i) {
                this.level.addParticle(ParticleTypes.BUBBLE, d2 - vec3.x * 0.25D, d0 - vec3.y * 0.25D, d1 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
            }
        }
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.PRISMARINE.get());
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            Block block = this.level.getBlockState(pos).getBlock();
            if (block == Blocks.PRISMARINE || block == Blocks.PRISMARINE_BRICKS || block == Blocks.DARK_PRISMARINE){
                int count = (int) Math.round( Math.random() * 3 + 1 );
                for(int i = 0; i < count; ++i){
                    this.spawnAtLocation(Items.PRISMARINE_SHARD);
                }
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
                this.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            } else if (block == Blocks.SEA_LANTERN){
                int count = (int) Math.round( Math.random() * 4 + 1 );
                for(int i = 0; i < count; ++i){
                    this.spawnAtLocation(Items.PRISMARINE_CRYSTALS);
                }
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
                this.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.prismarineClayBall.get());
            this.discard();
        }

    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}