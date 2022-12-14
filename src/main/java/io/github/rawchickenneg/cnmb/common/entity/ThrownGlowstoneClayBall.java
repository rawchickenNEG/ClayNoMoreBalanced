package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrownGlowstoneClayBall extends ThrowableItemProjectile {

    public ThrownGlowstoneClayBall(EntityType<? extends ThrownGlowstoneClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownGlowstoneClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_GLOWSTONE_CLAY_BALL.get(), entity, level);
    }

    public ThrownGlowstoneClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_GLOWSTONE_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.GLOWSTONE_CLAY_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.GLOWSTONE.get());
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if ((this.level.getBlockState(pos).is(BlockTags.create(new ResourceLocation("forge:ores" )))) || (this.level.getBlockState(pos).is(BlockTags.create(new ResourceLocation("forge:storage_blocks" ))))){
                if (this.random.nextInt() <= (10 / 20)){
                    this.playSound(SoundEvents.PLAYER_LEVELUP , 1.0F, 1.0F);
                    Block.dropResources(this.level.getBlockState(pos), this.level, pos, null);
                    this.level.levelEvent(2001, pos,
                            Block.getId(this.level.getBlockState(pos)));
                }else{
                    this.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    this.playSound(SoundEvents.ITEM_BREAK , 1.0F, 1.0F);
                    this.spawnAtLocation(ItemRegistry.TINY_BASE_POWDER.get());
                    this.level.levelEvent(2001, pos,
                            Block.getId(Blocks.COAL_BLOCK.defaultBlockState()));
                }
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.STONE_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.spawnAtLocation(ItemRegistry.GLOWSTONE_CLAY_BALL.get());
            this.discard();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}