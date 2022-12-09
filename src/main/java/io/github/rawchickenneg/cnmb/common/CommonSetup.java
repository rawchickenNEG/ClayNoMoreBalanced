package io.github.rawchickenneg.cnmb.common;

import io.github.rawchickenneg.cnmb.common.entity.*;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CommonSetup {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(CommonSetup::registerDispenserBehaviors);
    }

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ItemRegistry.THROWABLE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.THROWABLE_BRICK.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBrick(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.THROWABLE_IRON_INGOT.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownIronIngot(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.THROWABLE_GOLD_INGOT.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGoldIngot(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.THROWABLE_NETHERITE_INGOT.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownNetheriteIngot(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.ADVANCED_EXPLODE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownAdvancedExplodeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.AMETHYST_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownAmethystClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.BASE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBaseClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.BASKET_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBasketClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.BONE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBoneClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.HONEY_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBeehiveClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.BURN_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownBurnClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.CHORUS_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownChorusClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.DRY_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownDryClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.TRACKING_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownTrackingClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.DUPLICATE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownDuplicateClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.EXPLODE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownExplodeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.THROWABLE_FIRE_CHARGE.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownFireCharge(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.FLINT_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownFlintClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.FLOWER_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownFlowerClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.GILDED_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGildedClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.GLOW_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGlowClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.GLOWSTONE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGlowstoneClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.GOLDEN_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownGoldenClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.LEAVES_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownLeavesClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.LAZULI_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownLazuliClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.LIGHTING_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownLightingClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.ADVANCED_LIGHTNING_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownAdvancedLightningClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.OBSIDIAN_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownObsidianClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.PHANTOM_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPhantomClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.PILLAGER_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPillagerClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.PRISMARINE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPrismarineClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.PULL_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownPullClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.QUARTZ_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownQuartzClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.RAVAGER_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownRavagerClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.RECALL_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownRecallClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.SPONGE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownSpongeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.STONE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownStoneClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.TNT_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownTNTClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.ULTIMATE_EXPLODE_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownUltimateExplodeClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.VEX_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownVexClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.VOID_CLAY_BALL.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new ThrownVoidClayBall(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
    }

}
