package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.MethodsReturnNonnullByDefault;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownBaseClayBall extends ThrowableItemProjectile {

    public ThrownBaseClayBall(EntityType<? extends ThrownBaseClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownBaseClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownBaseClayBall.get(), entity, level);
    }

    public ThrownBaseClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownBaseClayBall.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.baseClayBall.get();
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.BASE.get());
        this.spawnAtLocation(ItemRegistry.baseClayBall.get());
    }


    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if ((this.level.getBlockState(pos).is(BlockTags.create(new ResourceLocation("forge:ores" )))) || (this.level.getBlockState(pos).is(BlockTags.create(new ResourceLocation("forge:storage_blocks" ))))){
                    this.playSound(SoundEvents.PLAYER_LEVELUP , 1.0F, 1.0F);
                    Block.dropResources(this.level.getBlockState(pos), this.level, pos, null);
                    this.level.levelEvent(2001, pos,
                            Block.getId(this.level.getBlockState(pos)));
            }else{
                this.spawnAtLocation(ItemRegistry.baseClayBall.get());
            }
        }
    }

    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            this.playSound(SoundEvents.STONE_PLACE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.discard();
        }
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}