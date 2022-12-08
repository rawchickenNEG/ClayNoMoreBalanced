package io.github.rawchickenneg.cnmd.common.registry;

import io.github.rawchickenneg.cnmd.ClayNoMoreBalanced;
import io.github.rawchickenneg.cnmd.common.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ClayNoMoreBalanced.MOD_ID);
    public static final RegistryObject<EntityType<ThrownClayBall>> thrownClayBall = ENTITIES.register("thrown_clay_ball", () -> (
            EntityType.Builder.<ThrownClayBall>of(ThrownClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_clay_ball")));
    public static final RegistryObject<EntityType<ThrownClayBallFromBow>> thrownClayBallFromBow = ENTITIES.register("thrown_clay_ball_from_bow", () -> (
            EntityType.Builder.<ThrownClayBallFromBow>of(ThrownClayBallFromBow::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_clay_ball_from_bow")));
    public static final RegistryObject<EntityType<ThrownBurnClayBall>> thrownBurnClayBall = ENTITIES.register("thrown_burn_clay_ball", () -> (
            EntityType.Builder.<ThrownBurnClayBall>of(ThrownBurnClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_burn_clay_ball")));
    public static final RegistryObject<EntityType<ThrownBoneClayBall>> thrownBoneClayBall = ENTITIES.register("thrown_bone_clay_ball", () -> (
            EntityType.Builder.<ThrownBoneClayBall>of(ThrownBoneClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_bone_clay_ball")));
    public static final RegistryObject<EntityType<ThrownTNTClayBall>> thrownTNTClayBall = ENTITIES.register("thrown_tnt_clay_ball", () -> (
            EntityType.Builder.<ThrownTNTClayBall>of(ThrownTNTClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_tnt_clay_ball")));
    public static final RegistryObject<EntityType<ThrownExplodeClayBall>> thrownExplodeClayBall = ENTITIES.register("thrown_explode_clay_ball", () -> (
            EntityType.Builder.<ThrownExplodeClayBall>of(ThrownExplodeClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_explode_clay_ball")));
    public static final RegistryObject<EntityType<ThrownAdvancedExplodeClayBall>> thrownAdvancedExplodeClayBall = ENTITIES.register("thrown_advanced_explode_clay_ball", () -> (
            EntityType.Builder.<ThrownAdvancedExplodeClayBall>of(ThrownAdvancedExplodeClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_advanced_explode_clay_ball")));
    public static final RegistryObject<EntityType<ThrownPullClayBall>> thrownPullClayBall = ENTITIES.register("thrown_pull_clay_ball", () -> (
            EntityType.Builder.<ThrownPullClayBall>of(ThrownPullClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_pull_clay_ball")));
    public static final RegistryObject<EntityType<ThrownFlintClayBall>> thrownFlintClayBall = ENTITIES.register("thrown_flint_clay_ball", () -> (
            EntityType.Builder.<ThrownFlintClayBall>of(ThrownFlintClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_flint_clay_ball")));
    public static final RegistryObject<EntityType<ThrownQuartzClayBall>> thrownQuartzClayBall = ENTITIES.register("thrown_quartz_clay_ball", () -> (
            EntityType.Builder.<ThrownQuartzClayBall>of(ThrownQuartzClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_quartz_clay_ball")));
    public static final RegistryObject<EntityType<ThrownObsidianClayBall>> thrownObsidianClayBall = ENTITIES.register("thrown_obsidian_clay_ball", () -> (
            EntityType.Builder.<ThrownObsidianClayBall>of(ThrownObsidianClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_obsidian_clay_ball")));
    public static final RegistryObject<EntityType<ThrownPillagerClayBall>> thrownPillagerClayBall = ENTITIES.register("thrown_pillager_clay_ball", () -> (
            EntityType.Builder.<ThrownPillagerClayBall>of(ThrownPillagerClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_pillager_clay_ball")));
    public static final RegistryObject<EntityType<ThrownRavagerClayBall>> thrownRavagerClayBall = ENTITIES.register("thrown_ravager_clay_ball", () -> (
            EntityType.Builder.<ThrownRavagerClayBall>of(ThrownRavagerClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_ravager_clay_ball")));
    public static final RegistryObject<EntityType<ThrownEnderClayBall>> thrownEnderClayBall = ENTITIES.register("thrown_ender_clay_ball", () -> (
            EntityType.Builder.<ThrownEnderClayBall>of(ThrownEnderClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_ender_clay_ball")));
    public static final RegistryObject<EntityType<ThrownExchangeClayBall>> thrownExchangeClayBall = ENTITIES.register("thrown_exchange_clay_ball", () -> (
            EntityType.Builder.<ThrownExchangeClayBall>of(ThrownExchangeClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_exchange_clay_ball")));
    public static final RegistryObject<EntityType<ThrownUltimateExplodeClayBall>> thrownUltimateExplodeClayBall = ENTITIES.register("thrown_ultimate_explode_clay_ball", () -> (
            EntityType.Builder.<ThrownUltimateExplodeClayBall>of(ThrownUltimateExplodeClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_ultimate_explode_clay_ball")));
    public static final RegistryObject<EntityType<ThrownPhantomClayBall>> thrownPhantomClayBall = ENTITIES.register("thrown_phantom_clay_ball", () -> (
            EntityType.Builder.<ThrownPhantomClayBall>of(ThrownPhantomClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_phantom_clay_ball")));
    public static final RegistryObject<EntityType<ThrownRecallClayBall>> thrownRecallClayBall = ENTITIES.register("thrown_recall_clay_ball", () -> (
            EntityType.Builder.<ThrownRecallClayBall>of(ThrownRecallClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_recall_clay_ball")));
    public static final RegistryObject<EntityType<ThrownVexClayBall>> thrownVexClayBall = ENTITIES.register("thrown_vex_clay_ball", () -> (
            EntityType.Builder.<ThrownVexClayBall>of(ThrownVexClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_vex_clay_ball")));
    public static final RegistryObject<EntityType<ThrownVoidClayBall>> thrownVoidClayBall = ENTITIES.register("thrown_void_clay_ball", () -> (
            EntityType.Builder.<ThrownVoidClayBall>of(ThrownVoidClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_void_clay_ball")));
    public static final RegistryObject<EntityType<ThrownChorusClayBall>> thrownChorusClayBall = ENTITIES.register("thrown_chorus_clay_ball", () -> (
            EntityType.Builder.<ThrownChorusClayBall>of(ThrownChorusClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_chorus_clay_ball")));
    public static final RegistryObject<EntityType<ThrownLightingClayBall>> thrownLightingClayBall = ENTITIES.register("thrown_lighting_clay_ball", () -> (
            EntityType.Builder.<ThrownLightingClayBall>of(ThrownLightingClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_lighting_clay_ball")));
    public static final RegistryObject<EntityType<ThrownBeehiveClayBall>> thrownBeehiveClayBall = ENTITIES.register("thrown_beehive_clay_ball", () -> (
            EntityType.Builder.<ThrownBeehiveClayBall>of(ThrownBeehiveClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_beehive_clay_ball")));
    public static final RegistryObject<EntityType<ThrownBasketClayBall>> thrownBasketClayBall = ENTITIES.register("thrown_basket_clay_ball", () -> (
            EntityType.Builder.<ThrownBasketClayBall>of(ThrownBasketClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_basket_clay_ball")));
    public static final RegistryObject<EntityType<ThrownGoldenClayBall>> thrownGoldenClayBall = ENTITIES.register("thrown_golden_clay_ball", () -> (
            EntityType.Builder.<ThrownGoldenClayBall>of(ThrownGoldenClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_golden_clay_ball")));
    public static final RegistryObject<EntityType<ThrownGildedClayBall>> thrownGildedClayBall = ENTITIES.register("thrown_gilded_clay_ball", () -> (
            EntityType.Builder.<ThrownGildedClayBall>of(ThrownGildedClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_gilded_clay_ball")));
    public static final RegistryObject<EntityType<ThrownPrismarineClayBall>> thrownPrismarineClayBall = ENTITIES.register("thrown_prismarine_clay_ball", () -> (
            EntityType.Builder.<ThrownPrismarineClayBall>of(ThrownPrismarineClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_prismarine_clay_ball")));
    public static final RegistryObject<EntityType<ThrownGlowClayBall>> thrownGlowClayBall = ENTITIES.register("thrown_glow_clay_ball", () -> (
            EntityType.Builder.<ThrownGlowClayBall>of(ThrownGlowClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_glow_clay_ball")));
    public static final RegistryObject<EntityType<ThrownDryClayBall>> thrownDryClayBall = ENTITIES.register("thrown_dry_clay_ball", () -> (
            EntityType.Builder.<ThrownDryClayBall>of(ThrownDryClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_dry_clay_ball")));
    public static final RegistryObject<EntityType<ThrownLeavesClayBall>> thrownLeavesClayBall = ENTITIES.register("thrown_leaves_clay_ball", () -> (
            EntityType.Builder.<ThrownLeavesClayBall>of(ThrownLeavesClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_leaves_clay_ball")));
    public static final RegistryObject<EntityType<ThrownAmethystClayBall>> thrownAmethystClayBall = ENTITIES.register("thrown_amethyst_clay_ball", () -> (
            EntityType.Builder.<ThrownAmethystClayBall>of(ThrownAmethystClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_amethyst_clay_ball")));
    public static final RegistryObject<EntityType<ThrownStoneClayBall>> thrownStoneClayBall = ENTITIES.register("thrown_stone_clay_ball", () -> (
            EntityType.Builder.<ThrownStoneClayBall>of(ThrownStoneClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_stone_clay_ball")));
    public static final RegistryObject<EntityType<ThrownLazuliClayBall>> thrownLazuliClayBall = ENTITIES.register("thrown_lazuli_clay_ball", () -> (
            EntityType.Builder.<ThrownLazuliClayBall>of(ThrownLazuliClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_lazuli_clay_ball")));
    public static final RegistryObject<EntityType<ThrownGlowstoneClayBall>> thrownGlowstoneClayBall = ENTITIES.register("thrown_glowstone_clay_ball", () -> (
            EntityType.Builder.<ThrownGlowstoneClayBall>of(ThrownGlowstoneClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_glowstone_clay_ball")));
    public static final RegistryObject<EntityType<ThrownBaseClayBall>> thrownBaseClayBall = ENTITIES.register("thrown_base_clay_ball", () -> (
            EntityType.Builder.<ThrownBaseClayBall>of(ThrownBaseClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_base_clay_ball")));
    public static final RegistryObject<EntityType<ThrownSpongeClayBall>> thrownSpongeClayBall = ENTITIES.register("thrown_sponge_clay_ball", () -> (
            EntityType.Builder.<ThrownSpongeClayBall>of(ThrownSpongeClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_sponge_clay_ball")));
    public static final RegistryObject<EntityType<ThrownFlowerClayBall>> thrownFlowerClayBall = ENTITIES.register("thrown_flower_clay_ball", () -> (
            EntityType.Builder.<ThrownFlowerClayBall>of(ThrownFlowerClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_flower_clay_ball")));
    public static final RegistryObject<EntityType<ThrownDuplicateClayBall>> thrownDuplicateClayBall = ENTITIES.register("thrown_duplicate_clay_ball", () -> (
            EntityType.Builder.<ThrownDuplicateClayBall>of(ThrownDuplicateClayBall::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_duplicate_clay_ball")));
    public static final RegistryObject<EntityType<ThrownBrick>> thrownBrick = ENTITIES.register("thrown_brick", () -> (
            EntityType.Builder.<ThrownBrick>of(ThrownBrick::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_brick")));
    public static final RegistryObject<EntityType<ThrownFireCharge>> thrownFireCharge = ENTITIES.register("thrown_fire_charge", () -> (
            EntityType.Builder.<ThrownFireCharge>of(ThrownFireCharge::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_fire_charge")));
    public static final RegistryObject<EntityType<ThrownIronIngot>> thrownIronIngot = ENTITIES.register("thrown_iron_ingot", () -> (
            EntityType.Builder.<ThrownIronIngot>of(ThrownIronIngot::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_iron_ingot")));
    public static final RegistryObject<EntityType<ThrownGoldIngot>> thrownGoldIngot = ENTITIES.register("thrown_gold_ingot", () -> (
            EntityType.Builder.<ThrownGoldIngot>of(ThrownGoldIngot::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_gold_ingot")));
    public static final RegistryObject<EntityType<ThrownNetheriteIngot>> thrownNetheriteIngot = ENTITIES.register("thrown_netherite_ingot", () -> (
            EntityType.Builder.<ThrownNetheriteIngot>of(ThrownNetheriteIngot::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_netherite_ingot")));
    public static final RegistryObject<EntityType<ThrownTorch>> thrownTorch = ENTITIES.register("thrown_torch", () -> (
            EntityType.Builder.<ThrownTorch>of(ThrownTorch::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_torch")));
}
