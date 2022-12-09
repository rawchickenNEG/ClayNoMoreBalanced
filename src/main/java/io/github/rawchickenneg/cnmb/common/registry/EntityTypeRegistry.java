package io.github.rawchickenneg.cnmb.common.registry;

import io.github.rawchickenneg.cnmb.ClayNoMoreBalanced;
import io.github.rawchickenneg.cnmb.common.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ClayNoMoreBalanced.MOD_ID);

    public static final RegistryObject<EntityType<ThrownClayBall>> THROWN_CLAY_BALL = throwableItem("thrown_clay_ball", ThrownClayBall::new);
    public static final RegistryObject<EntityType<ThrownClayBallFromBow>> THROWN_CLAY_BALL_FROM_BOW = throwableItem("thrown_clay_ball_from_bow", ThrownClayBallFromBow::new);
    public static final RegistryObject<EntityType<ThrownBurnClayBall>> THROWN_BURN_CLAY_BALL = throwableItem("thrown_burn_clay_ball", ThrownBurnClayBall::new);
    public static final RegistryObject<EntityType<ThrownBoneClayBall>> THROWN_BONE_CLAY_BALL = throwableItem("thrown_bone_clay_ball", ThrownBoneClayBall::new);
    public static final RegistryObject<EntityType<ThrownTNTClayBall>> THROWN_TNT_CLAY_BALL = throwableItem("thrown_tnt_clay_ball", ThrownTNTClayBall::new);
    public static final RegistryObject<EntityType<ThrownExplodeClayBall>> THROWN_EXPLODE_CLAY_BALL = throwableItem("thrown_explode_clay_ball", ThrownExplodeClayBall::new);
    public static final RegistryObject<EntityType<ThrownAdvancedExplodeClayBall>> THROWN_ADVANCED_EXPLODE_CLAY_BALL = throwableItem("thrown_advanced_explode_clay_ball", ThrownAdvancedExplodeClayBall::new);
    public static final RegistryObject<EntityType<ThrownPullClayBall>> THROWN_PULL_CLAY_BALL = throwableItem("thrown_pull_clay_ball", ThrownPullClayBall::new);
    public static final RegistryObject<EntityType<ThrownFlintClayBall>> THROWN_FLINT_CLAY_BALL = throwableItem("thrown_flint_clay_ball", ThrownFlintClayBall::new);
    public static final RegistryObject<EntityType<ThrownQuartzClayBall>> THROWN_QUARTZ_CLAY_BALL = throwableItem("thrown_quartz_clay_ball", ThrownQuartzClayBall::new);
    public static final RegistryObject<EntityType<ThrownObsidianClayBall>> THROWN_OBSIDIAN_CLAY_BALL = throwableItem("thrown_obsidian_clay_ball", ThrownObsidianClayBall::new);
    public static final RegistryObject<EntityType<ThrownPillagerClayBall>> THROWN_PILLAGER_CLAY_BALL = throwableItem("thrown_pillager_clay_ball", ThrownPillagerClayBall::new);
    public static final RegistryObject<EntityType<ThrownRavagerClayBall>> THROWN_RAVAGER_CLAY_BALL = throwableItem("thrown_ravager_clay_ball", ThrownRavagerClayBall::new);
    public static final RegistryObject<EntityType<ThrownEnderClayBall>> THROWN_ENDER_CLAY_BALL = throwableItem("thrown_ender_clay_ball", ThrownEnderClayBall::new);
    public static final RegistryObject<EntityType<ThrownExchangeClayBall>> THROWN_EXCHANGE_CLAY_BALL = throwableItem("thrown_exchange_clay_ball", ThrownExchangeClayBall::new);
    public static final RegistryObject<EntityType<ThrownUltimateExplodeClayBall>> THROWN_ULTIMATE_EXPLODE_CLAY_BALL = throwableItem("thrown_ultimate_explode_clay_ball", ThrownUltimateExplodeClayBall::new);
    public static final RegistryObject<EntityType<ThrownPhantomClayBall>> THROWN_PHANTOM_CLAY_BALL = throwableItem("thrown_phantom_clay_ball", ThrownPhantomClayBall::new);
    public static final RegistryObject<EntityType<ThrownRecallClayBall>> THROWN_RECALL_CLAY_BALL = throwableItem("thrown_recall_clay_ball", ThrownRecallClayBall::new);
    public static final RegistryObject<EntityType<ThrownVexClayBall>> THROWN_VEX_CLAY_BALL = throwableItem("thrown_vex_clay_ball", ThrownVexClayBall::new);
    public static final RegistryObject<EntityType<ThrownVoidClayBall>> THROWN_VOID_CLAY_BALL = throwableItem("thrown_void_clay_ball", ThrownVoidClayBall::new);
    public static final RegistryObject<EntityType<ThrownChorusClayBall>> THROWN_CHORUS_CLAY_BALL = throwableItem("thrown_chorus_clay_ball", ThrownChorusClayBall::new);
    public static final RegistryObject<EntityType<ThrownLightingClayBall>> THROWN_LIGHTING_CLAY_BALL = throwableItem("thrown_lighting_clay_ball", ThrownLightingClayBall::new);
    public static final RegistryObject<EntityType<ThrownAdvancedLightningClayBall>> THROWN_ADVANCED_LIGHTNING_CLAY_BALL = throwableItem("thrown_advanced_lightning_clay_ball", ThrownAdvancedLightningClayBall::new);
    public static final RegistryObject<EntityType<ThrownBeehiveClayBall>> THROWN_BEEHIVE_CLAY_BALL = throwableItem("thrown_beehive_clay_ball", ThrownBeehiveClayBall::new);
    public static final RegistryObject<EntityType<ThrownBasketClayBall>> THROWN_BASKET_CLAY_BALL = throwableItem("thrown_basket_clay_ball", ThrownBasketClayBall::new);
    public static final RegistryObject<EntityType<ThrownGoldenClayBall>> THROWN_GOLDEN_CLAY_BALL = throwableItem("thrown_golden_clay_ball", ThrownGoldenClayBall::new);
    public static final RegistryObject<EntityType<ThrownGildedClayBall>> THROWN_GILDED_CLAY_BALL = throwableItem("thrown_gilded_clay_ball", ThrownGildedClayBall::new);
    public static final RegistryObject<EntityType<ThrownPrismarineClayBall>> THROWN_PRISMARINE_CLAY_BALL = throwableItem("thrown_prismarine_clay_ball", ThrownPrismarineClayBall::new);
    public static final RegistryObject<EntityType<ThrownGlowClayBall>> THROWN_GLOW_CLAY_BALL = throwableItem("thrown_glow_clay_ball", ThrownGlowClayBall::new);
    public static final RegistryObject<EntityType<ThrownDryClayBall>> THROWN_DRY_CLAY_BALL = throwableItem("thrown_dry_clay_ball", ThrownDryClayBall::new);
    public static final RegistryObject<EntityType<ThrownLeavesClayBall>> THROWN_LEAVES_CLAY_BALL = throwableItem("thrown_leaves_clay_ball", ThrownLeavesClayBall::new);
    public static final RegistryObject<EntityType<ThrownAmethystClayBall>> THROWN_AMETHYST_CLAY_BALL = throwableItem("thrown_amethyst_clay_ball", ThrownAmethystClayBall::new);
    public static final RegistryObject<EntityType<ThrownStoneClayBall>> THROWN_STONE_CLAY_BALL = throwableItem("thrown_stone_clay_ball", ThrownStoneClayBall::new);
    public static final RegistryObject<EntityType<ThrownLazuliClayBall>> THROWN_LAZULI_CLAY_BALL = throwableItem("thrown_lazuli_clay_ball", ThrownLazuliClayBall::new);
    public static final RegistryObject<EntityType<ThrownGlowstoneClayBall>> THROWN_GLOWSTONE_CLAY_BALL = throwableItem("thrown_glowstone_clay_ball", ThrownGlowstoneClayBall::new);
    public static final RegistryObject<EntityType<ThrownBaseClayBall>> THROWN_BASE_CLAY_BALL = throwableItem("thrown_base_clay_ball", ThrownBaseClayBall::new);
    public static final RegistryObject<EntityType<ThrownSpongeClayBall>> THROWN_SPONGE_CLAY_BALL = throwableItem("thrown_sponge_clay_ball", ThrownSpongeClayBall::new);
    public static final RegistryObject<EntityType<ThrownFlowerClayBall>> THROWN_FLOWER_CLAY_BALL = throwableItem("thrown_flower_clay_ball", ThrownFlowerClayBall::new);
    public static final RegistryObject<EntityType<ThrownDuplicateClayBall>> THROWN_DUPLICATE_CLAY_BALL = throwableItem("thrown_duplicate_clay_ball", ThrownDuplicateClayBall::new);
    public static final RegistryObject<EntityType<ThrownBrick>> THROWN_BRICK = throwableItem("thrown_brick", ThrownBrick::new);
    public static final RegistryObject<EntityType<ThrownFireCharge>> THROWN_FIRE_CHARGE = throwableItem("thrown_fire_charge", ThrownFireCharge::new);
    public static final RegistryObject<EntityType<ThrownIronIngot>> THROWN_IRON_INGOT = throwableItem("thrown_iron_ingot", ThrownIronIngot::new);
    public static final RegistryObject<EntityType<ThrownGoldIngot>> THROWN_GOLD_INGOT = throwableItem("thrown_gold_ingot", ThrownGoldIngot::new);
    public static final RegistryObject<EntityType<ThrownNetheriteIngot>> THROWN_NETHERITE_INGOT = throwableItem("thrown_netherite_ingot", ThrownNetheriteIngot::new);
    public static final RegistryObject<EntityType<ThrownTorch>> THROWN_TORCH = throwableItem("thrown_torch", ThrownTorch::new);

    private static <T extends Entity> RegistryObject<EntityType<T>> throwableItem(String name, EntityType.EntityFactory<T> factory) {
        return ENTITIES.register(name, () -> (EntityType.Builder.of(factory, MobCategory.MISC).sized(0.25F, 0.25F)
                .clientTrackingRange(4).updateInterval(10).build(name)));
    }
    
}