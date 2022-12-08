package io.github.rawchickenneg.cnmd.common.entity;

import io.github.rawchickenneg.cnmd.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmd.config.Config;
import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThrownAmethystClayBall extends ThrowableItemProjectile {

    public ThrownAmethystClayBall(EntityType<? extends ThrownAmethystClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownAmethystClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.thrownAmethystClayBall.get(), entity, level);
    }

    public ThrownAmethystClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.thrownAmethystClayBall.get(), x, y, z, level);
    }


    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.amethystClayBall.get();
    }


    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.AMETHYST.get());
        this.playSound(SoundEvents.AMETHYST_BLOCK_PLACE , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.amethystClayBall.get());
        this.discard();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if (this.level.getBlockState(pos).getBlock() == Blocks.AMETHYST_BLOCK){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, Blocks.BUDDING_AMETHYST.defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(Blocks.AMETHYST_BLOCK.defaultBlockState()));
            }else{
                this.playSound(SoundEvents.AMETHYST_BLOCK_PLACE , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.spawnAtLocation(ItemRegistry.amethystClayBall.get());
            }
            this.discard();
        }

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}