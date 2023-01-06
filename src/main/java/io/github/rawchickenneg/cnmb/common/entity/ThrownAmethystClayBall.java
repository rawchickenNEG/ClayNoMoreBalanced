package io.github.rawchickenneg.cnmb.common.entity;

import io.github.rawchickenneg.cnmb.common.registry.BlockRegistry;
import io.github.rawchickenneg.cnmb.common.registry.EntityTypeRegistry;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
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

public class ThrownAmethystClayBall extends ThrowableItemProjectile {

    private int crystalType = 0;
    public ThrownAmethystClayBall(EntityType<? extends ThrownAmethystClayBall> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownAmethystClayBall(Level level, LivingEntity entity) {
        super(EntityTypeRegistry.THROWN_AMETHYST_CLAY_BALL.get(), entity, level);
    }

    public ThrownAmethystClayBall(Level level, double x, double y, double z) {
        super(EntityTypeRegistry.THROWN_AMETHYST_CLAY_BALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.AMETHYST_CLAY_BALL.get();
    }

    public void setType(int crystalType) {
        this.crystalType = crystalType;
    }

    protected void onHitEntity(EntityHitResult p_37486_) {
        super.onHitEntity(p_37486_);
        p_37486_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), Config.CONFIG.AMETHYST.get());
        this.playSound(SoundEvents.AMETHYST_BLOCK_PLACE , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.spawnAtLocation(ItemRegistry.AMETHYST_CLAY_BALL.get());
        this.discard();
    }

    protected void onHitBlock(BlockHitResult p_37488_){
        super.onHitBlock(p_37488_);
        BlockPos pos = p_37488_.getBlockPos();
        if (!this.level.isClientSide) {
            if (this.level.getBlockState(pos).getBlock() == Blocks.AMETHYST_BLOCK && this.crystalType == 0){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, Blocks.BUDDING_AMETHYST.defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            }else if (this.level.getBlockState(pos).getBlock() == Registry.BLOCK.get(new ResourceLocation("create:rose_quartz_block")) && this.crystalType == 1){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, BlockRegistry.BUDDING_ROSE_QUARTZ.get().defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            } else if (this.level.getBlockState(pos).getBlock() == Registry.BLOCK.get(new ResourceLocation("tconstruct:earth_slime_crystal_block")) && this.crystalType == 2){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, Registry.BLOCK.get(new ResourceLocation("tconstruct:budding_earth_slime_crystal")).defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            } else if (this.level.getBlockState(pos).getBlock() == Registry.BLOCK.get(new ResourceLocation("tconstruct:sky_slime_crystal_block")) && this.crystalType == 3){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, Registry.BLOCK.get(new ResourceLocation("tconstruct:budding_sky_slime_crystal")).defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            } else if (this.level.getBlockState(pos).getBlock() == Registry.BLOCK.get(new ResourceLocation("tconstruct:ichor_slime_crystal_block")) && this.crystalType == 4){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, Registry.BLOCK.get(new ResourceLocation("tconstruct:budding_ichor_slime_crystal")).defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            } else if (this.level.getBlockState(pos).getBlock() == Registry.BLOCK.get(new ResourceLocation("tconstruct:ender_slime_crystal_block")) && this.crystalType == 5){
                this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                this.level.setBlock(pos, Registry.BLOCK.get(new ResourceLocation("tconstruct:budding_ender_slime_crystal")).defaultBlockState(), 3);
                this.level.levelEvent(2001, pos,
                        Block.getId(this.level.getBlockState(pos)));
            } else {
                this.playSound(SoundEvents.AMETHYST_BLOCK_PLACE , 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                switch (this.crystalType) {
                    case 1 -> this.spawnAtLocation(ItemRegistry.ROSE_QUARTZ_CLAY_BALL.get());
                    case 2 -> this.spawnAtLocation(ItemRegistry.EARTH_SLIME_CLAY_BALL.get());
                    case 3 -> this.spawnAtLocation(ItemRegistry.SKY_SLIME_CLAY_BALL.get());
                    case 4 -> this.spawnAtLocation(ItemRegistry.ICHOR_SLIME_CLAY_BALL.get());
                    case 5 -> this.spawnAtLocation(ItemRegistry.ENDER_SLIME_CLAY_BALL.get());
                    default -> this.spawnAtLocation(ItemRegistry.AMETHYST_CLAY_BALL.get());
                }

            }
            this.discard();
        }

    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}