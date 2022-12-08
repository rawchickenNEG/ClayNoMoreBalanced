package io.github.rawchickenneg.cnmd.common;

import io.github.rawchickenneg.cnmd.common.entity.*;
import io.github.rawchickenneg.cnmd.common.registry.ItemRegistry;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class CommonSetup {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            registerDispenserBehaviors();
        });
    }

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ItemRegistry.throwableClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.throwableBrick.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBrick(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.throwableIronIngot.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownIronIngot(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.throwableGoldIngot.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGoldIngot(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.throwableNetheriteIngot.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownNetheriteIngot(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.advancedExplodeClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownAdvancedExplodeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.amethystClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownAmethystClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.baseClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBaseClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.basketClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBasketClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.boneClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBoneClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.honeyClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBeehiveClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.burnClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBurnClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.chorusClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownChorusClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.dryClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownDryClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.duplicateClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownDuplicateClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.explodeClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownExplodeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.throwableFireCharge.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownFireCharge(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.flintClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownFlintClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.flowerClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownFlowerClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.gildedClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGildedClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.glowClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGlowClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.glowstoneClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGlowstoneClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.goldenClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGoldenClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.leavesClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownLeavesClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.lazuliClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownLazuliClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.lightingClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownLightingClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.obsidianClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownObsidianClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.phantomClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPhantomClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.pillagerClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPillagerClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.prismarineClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPrismarineClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.pullClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPullClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.quartzClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownQuartzClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.ravagerClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownRavagerClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.recallClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownRecallClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.spongeClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownSpongeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.stoneClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownStoneClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.TNTClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownTNTClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.ultimateExplodeClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownUltimateExplodeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.vexClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownVexClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.voidClayBall.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownVoidClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
    }

}
