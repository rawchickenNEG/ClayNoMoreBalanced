package io.github.rawchickenneg.cnmb.common.registry;

import io.github.rawchickenneg.cnmb.ClayNoMoreBalanced;
import io.github.rawchickenneg.cnmb.common.item.*;
import io.github.rawchickenneg.cnmb.common.item.clayballs.*;
import io.github.rawchickenneg.cnmb.common.item.throwableitems.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused", "deprecation"})
public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClayNoMoreBalanced.MOD_ID);
    public static final RegistryObject<Item> CLAY_INGOT = ITEMS.register("clay_ingot", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_NUGGET = ITEMS.register("clay_nugget", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_SCRAP = ITEMS.register("clay_scrap", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_SHARD = ITEMS.register("clay_shard", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_VUG = ITEMS.register("clay_vug", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_PEARL = ITEMS.register("clay_pearl", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_GEM = ITEMS.register("clay_gem", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_STAR = ITEMS.register("clay_star", () -> new SimpleFoiledItem(defaultBuilder().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> THE_ULTIMATE_GALACLAY = ITEMS.register("the_ultimate_galaclay", () -> new SimpleFoiledItem(defaultBuilder().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CLAY_NO_MORE_BALANCED = ITEMS.register("clay_no_more_balanced", () -> new SimpleFoiledItem(defaultBuilder().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> INFINITE_CLAY_CATALYST = ITEMS.register("infinite_clay_catalyst", () -> new SimpleFoiledItem(defaultBuilder().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CLAY_RING = ITEMS.register("clay_ring", () -> new ClayRing(defaultBuilder().stacksTo(1)));
    public static final RegistryObject<Item> CLAY_TOTEM = ITEMS.register("clay_totem", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> CLAY_TRIDENT = ITEMS.register("clay_trident", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> CLAY_ELYTRA = ITEMS.register("clay_elytra", () -> new Item(defaultBuilder().craftRemainder(Items.ELYTRA).stacksTo(1)));
    public static final RegistryObject<Item> CLAY_ROD = ITEMS.register("clay_rod", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAYMORE = ITEMS.register("claymore", () -> new Claymore(defaultBuilder().rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<Item> CLAY_HOE = ITEMS.register("clay_hoe", () -> new HoeItem(ItemTier.ClayIngot, -10, -1.0F, defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_AXE = ITEMS.register("clay_axe", () -> new AxeItem(ItemTier.ClayIngot, 6.0F, -3.1F, defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_PICKAXE = ITEMS.register("clay_pickaxe", () -> new PickaxeItem(ItemTier.ClayIngot, 1, -2.8F, defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_SWORD = ITEMS.register("clay_sword", () -> new SwordItem(ItemTier.ClayIngot, 3, -2.4F, defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_SHOVEL = ITEMS.register("clay_shovel", () -> new ShovelItem(ItemTier.ClayIngot, 1.5F, -1F, defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAY_BOW = ITEMS.register("clay_bow", () -> new ClayBowItem(defaultBuilder().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> AXOLOTL_CLAY_BALL_BLUE = ITEMS.register("axolotl_clay_ball_blue", () -> new Item(defaultBuilder().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> AXOLOTL_CLAY_BALL_CYAN = ITEMS.register("axolotl_clay_ball_cyan", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> AXOLOTL_CLAY_BALL_LUCY = ITEMS.register("axolotl_clay_ball_lucy", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> AXOLOTL_CLAY_BALL_GOLD = ITEMS.register("axolotl_clay_ball_gold", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> AXOLOTL_CLAY_BALL_WILD = ITEMS.register("axolotl_clay_ball_wild", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> TINY_VEX_CLAY_BALL = ITEMS.register("tiny_vex_clay_ball", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BAT_CLAY_BALL = ITEMS.register("bat_clay_ball", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAVAGER_METAL_PARTS = ITEMS.register("ravager_metal_parts", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> DRY_CLAY_BALL = ITEMS.register("dry_clay_ball", () -> new DryClayBall(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FLOWER_CLAY_BALL = ITEMS.register("flower_clay_ball", () -> new FlowerClayBall (defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> GILDED_CLAY_BALL = ITEMS.register("gilded_clay_ball", () -> new GildedClayBall(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HONEY_CLAY_BALL = ITEMS.register("honey_clay_ball", () -> new BeehiveClayBallItem(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LEAVES_CLAY_BALL = ITEMS.register("leaves_clay_ball", () -> new LeavesClayBall(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SPONGE_CLAY_BALL = ITEMS.register("sponge_clay_ball", () -> new SpongeClayBall(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> STICKY_CLAY_BALL = ITEMS.register("sticky_clay_ball", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> STONE_CLAY_BALL = ITEMS.register("stone_clay_ball", () -> new StoneClayBall (defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LAZULI_CLAY_BALL = ITEMS.register("lazuli_clay_ball", () -> new LazuliClayBall (defaultBuilder()));
    public static final RegistryObject<Item> GLOWSTONE_CLAY_BALL = ITEMS.register("glowstone_clay_ball", () -> new GlowstoneClayBall(defaultBuilder()));
    public static final RegistryObject<Item> BASE_CLAY_BALL = ITEMS.register("base_clay_ball", () -> new BaseClayBall(defaultBuilder()));

    public static final RegistryObject<Item> SNOW_CLAY_BALL = ITEMS.register("snow_clay_ball", () -> new SnowClayBall(defaultBuilder()));
    public static final RegistryObject<Item> EGG_CLAY_BALL = ITEMS.register("egg_clay_ball", () -> new EggClayBall(defaultBuilder()));
    public static final RegistryObject<Item> ENDER_CLAY_BALL = ITEMS.register("ender_clay_ball", () -> new EnderClayBall(defaultBuilder()));
    public static final RegistryObject<Item> EXCHANGE_CLAY_BALL = ITEMS.register("exchange_clay_ball", () -> new ExchangeClayBall(defaultBuilder()));
    public static final RegistryObject<Item> BURN_CLAY_BALL = ITEMS.register("burn_clay_ball", () -> new BurnClayBallItem(defaultBuilder()));
    public static final RegistryObject<Item> TNT_CLAY_BALL = ITEMS.register("tnt_clay_ball", () -> new TNTClayBall(defaultBuilder()));
    public static final RegistryObject<Item> EXPLODE_CLAY_BALL = ITEMS.register("explode_clay_ball", () -> new ExplodeClayBallItem(defaultBuilder()));
    public static final RegistryObject<Item> ADVANCED_EXPLODE_CLAY_BALL = ITEMS.register("advanced_explode_clay_ball", () -> new AdvancedExplodeClayBallItem(defaultBuilder()));
    public static final RegistryObject<Item> ULTIMATE_EXPLODE_CLAY_BALL = ITEMS.register("ultimate_explode_clay_ball", () -> new UltimateExplodeClayBallItem(defaultBuilder()));
    public static final RegistryObject<Item> PHANTOM_CLAY_BALL = ITEMS.register("phantom_clay_ball", () -> new PhantomClayBall(defaultBuilder()));
    public static final RegistryObject<Item> RECALL_CLAY_BALL = ITEMS.register("recall_clay_ball", () -> new RecallClayBall(defaultBuilder()));
    public static final RegistryObject<Item> VEX_CLAY_BALL = ITEMS.register("vex_clay_ball", () -> new VexClayBall(defaultBuilder()));
    public static final RegistryObject<Item> VOID_CLAY_BALL = ITEMS.register("void_clay_ball", () -> new VoidClayBall(defaultBuilder()));
    public static final RegistryObject<Item> CHORUS_CLAY_BALL = ITEMS.register("chorus_clay_ball", () -> new ChorusClayBall(defaultBuilder()));
    public static final RegistryObject<Item> LIGHTING_CLAY_BALL = ITEMS.register("lighting_clay_ball", () -> new LightingClayBall(defaultBuilder()));
    public static final RegistryObject<Item> ADVANCED_LIGHTNING_CLAY_BALL = ITEMS.register("advanced_lightning_clay_ball", () -> new AdvancedLightningClayBall(defaultBuilder()));
    public static final RegistryObject<Item> BASKET_CLAY_BALL = ITEMS.register("basket_clay_ball", () -> new BasketClayBallItem(defaultBuilder()));
    public static final RegistryObject<Item> PULL_CLAY_BALL = ITEMS.register("pull_clay_ball", () -> new PullClayBall(defaultBuilder()));
    public static final RegistryObject<Item> FLINT_CLAY_BALL = ITEMS.register("flint_clay_ball", () -> new FlintClayBall(defaultBuilder()));
    public static final RegistryObject<Item> QUARTZ_CLAY_BALL = ITEMS.register("quartz_clay_ball", () -> new QuartzClayBall(defaultBuilder()));
    public static final RegistryObject<Item> OBSIDIAN_CLAY_BALL = ITEMS.register("obsidian_clay_ball", () -> new ObsidianClayBall(defaultBuilder()));
    public static final RegistryObject<Item> GOLDEN_CLAY_BALL = ITEMS.register("golden_clay_ball", () -> new GoldenClayBall(defaultBuilder()));
    public static final RegistryObject<Item> AMETHYST_CLAY_BALL = ITEMS.register("amethyst_clay_ball", () -> new AmethystClayBall(defaultBuilder()));
    public static final RegistryObject<Item> GLOW_CLAY_BALL = ITEMS.register("glow_clay_ball", () -> new GlowClayBall(defaultBuilder()));
    public static final RegistryObject<Item> BONE_CLAY_BALL = ITEMS.register("bone_clay_ball", () -> new BoneClayBall(defaultBuilder()));
    public static final RegistryObject<Item> RAVAGER_CLAY_BALL = ITEMS.register("ravager_clay_ball", () -> new RavagerClayBall(defaultBuilder()));
    public static final RegistryObject<Item> PILLAGER_CLAY_BALL = ITEMS.register("pillager_clay_ball", () -> new PillagerClayBall(defaultBuilder()));
    public static final RegistryObject<Item> PRISMARINE_CLAY_BALL = ITEMS.register("prismarine_clay_ball", () -> new PrismarineClayBall(defaultBuilder()));
    public static final RegistryObject<Item> TRACKING_CLAY_BALL = ITEMS.register("tracking_clay_ball", () -> new TrackingClayBall(defaultBuilder()));
    public static final RegistryObject<Item> FREEZING_CLAY_BALL = ITEMS.register("freezing_clay_ball", () -> new FreezingClayBall(defaultBuilder()));
    public static final RegistryObject<Item> MINER_CLAY_BALL = ITEMS.register("miner_clay_ball", () -> new MinerClayBall(defaultBuilder()));
    public static final RegistryObject<Item> DUPLICATE_CLAY_BALL = ITEMS.register("duplicate_clay_ball", () -> new DuplicateClayBall(defaultBuilder().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> THROWABLE_CLAY_BALL = ITEMS.register("throwable_clay_ball", () -> new ThrowableClayBallItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_BRICK = ITEMS.register("throwable_brick", () -> new ThrowableBrickItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_ARROW = ITEMS.register("throwable_arrow", () -> new ThrowableArrowItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_FIRE_CHARGE = ITEMS.register("throwable_fire_charge", () -> new ThrowableFireChargeItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_IRON_INGOT = ITEMS.register("throwable_iron_ingot", () -> new ThrowableIronIngotItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_GOLD_INGOT = ITEMS.register("throwable_gold_ingot", () -> new ThrowableGoldIngotItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_NETHERITE_INGOT = ITEMS.register("throwable_netherite_ingot", () -> new ThrowableNetheriteIngotItem(defaultBuilder()));
    public static final RegistryObject<Item> THROWABLE_TORCH = ITEMS.register("throwable_torch", () -> new ThrowableTorchItem(defaultBuilder()));

    public static final RegistryObject<Item> CLAY_BLOCK = ITEMS.register("clay_block", () -> new ItemNameBlockItem(BlockRegistry.CLAY_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> CLAY_TREE_SAPLING = ITEMS.register("clay_tree_sapling", () -> new ItemNameBlockItem(BlockRegistry.CLAY_TREE_SAPLING.get(),
            new Item.Properties().rarity(Rarity.UNCOMMON).tab(ClayNoMoreBalanced.CREATIVE_TAB)));
    public static final RegistryObject<Item> CLAY_ROLL_BLOCK = ITEMS.register("clay_roll_block", () -> new ItemNameBlockItem(BlockRegistry.CLAY_ROLL_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> NATURAL_SCLAME_BLOCK = ITEMS.register("natural_sclame_block", () -> new ItemNameBlockItem(BlockRegistry.NATURAL_SCLAME_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> CLAY_LOG = ITEMS.register("clay_log", () -> new ItemNameBlockItem(BlockRegistry.CLAY_LOG.get(), defaultBuilder()));
    public static final RegistryObject<Item> AMETHYST_SHOWCASE = ITEMS.register("amethyst_showcase", () -> new ItemNameBlockItem(BlockRegistry.AMETHYST_SHOWCASE.get(), defaultBuilder()));
    public static final RegistryObject<Item> ENDER_PEARL_BLOCK = ITEMS.register("ender_pearl_block", () -> new ItemNameBlockItem(BlockRegistry.ENDER_PEARL_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_BLOCK = ITEMS.register("porcelain_block", () -> new ItemNameBlockItem(BlockRegistry.PORCELAIN_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_TILE = ITEMS.register("porcelain_tile", () -> new ItemNameBlockItem(BlockRegistry.PORCELAIN_TILE.get(), defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_TILE_LARGE = ITEMS.register("porcelain_tile_large", () -> new ItemNameBlockItem(BlockRegistry.PORCELAIN_TILE_LARGE.get(), defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_TILE_TINY = ITEMS.register("porcelain_tile_tiny", () -> new ItemNameBlockItem(BlockRegistry.PORCELAIN_TILE_TINY.get(), defaultBuilder()));
    public static final RegistryObject<Item> TOILET = ITEMS.register("toilet", () -> new ItemNameBlockItem(BlockRegistry.TOILET.get(), defaultBuilder()));
    public static final RegistryObject<Item> NETHERITE_SPONGE = ITEMS.register("netherite_sponge", () -> new ItemNameBlockItem(BlockRegistry.NETHERITE_SPONGE_BLOCK.get(), defaultBuilder().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPONGE_ABSORBED = ITEMS.register("netherite_sponge_absorbed", () -> new ItemNameBlockItem(BlockRegistry.NETHERITE_SPONGE_ABSORBED_BLOCK.get(),
            defaultBuilder().craftRemainder(ItemRegistry.NETHERITE_SPONGE.get()).stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> SCLAME_BALL = ITEMS.register("sclame_ball", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.5f).build())));

    public static final RegistryObject<Item> SCLAME_POTATO = ITEMS.register("sclame_potato", () -> new ItemNameBlockItem(BlockRegistry.SCLAME_POTATO_BLOCK.get(),
            defaultBuilder().food((new FoodProperties.Builder()).nutrition(5).saturationMod(1f).build())));
    public static final RegistryObject<Item> SPROUTING_SCLAME_POTATO = ITEMS.register("sprouting_sclame_potato", () -> new Item(defaultBuilder().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SCLAME_MEAT = ITEMS.register("sclame_meat", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(12).saturationMod(1.4f).build())));
    public static final RegistryObject<Item> BRICKED_POTATO = ITEMS.register("bricked_potato", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(12).saturationMod(0.8f)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 600, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0), 1.0F).build())));
    public static final RegistryObject<Item> SCLAME_APPLE = ITEMS.register("sclame_apple", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(8).saturationMod(1.2f).build())));
    public static final RegistryObject<Item> SCLAME_BREAD = ITEMS.register("sclame_bread", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(10).saturationMod(1.1f).build())));
    public static final RegistryObject<Item> SCLAME_PIE = ITEMS.register("sclame_pie", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(16).saturationMod(1.25f).build())));
    public static final RegistryObject<Item> SCLAME_CAKE = ITEMS.register("sclame_cake", () -> new ItemNameBlockItem(BlockRegistry.SCLAME_CAKE_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> CLAY_CREAM = ITEMS.register("clay_cream", () -> new ClayCream(new Item.Properties().stacksTo(1).tab(ClayNoMoreBalanced.CREATIVE_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> CLAY_CREAM_BALL = ITEMS.register("clay_cream_ball", () -> new ClayCreamBall(defaultBuilder()));
    public static final RegistryObject<Item> TERRACOTTA_DUST = ITEMS.register("terracotta_dust", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> NETHER_BRICK_DUST = ITEMS.register("nether_brick_dust", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_BRICK = ITEMS.register("porcelain_brick", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_HELMET = ITEMS.register("porcelain_helmet", () -> new ArmorItem(ArmorTier.PORCELAIN, EquipmentSlot.HEAD, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_CHESTPLATE = ITEMS.register("porcelain_chestplate", () -> new ArmorItem(ArmorTier.PORCELAIN, EquipmentSlot.CHEST, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_LEGGINGS = ITEMS.register("porcelain_leggings", () -> new ArmorItem(ArmorTier.PORCELAIN, EquipmentSlot.LEGS, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_BOOTS = ITEMS.register("porcelain_boots", () -> new ArmorItem(ArmorTier.PORCELAIN, EquipmentSlot.FEET, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_HOE = ITEMS.register("porcelain_hoe", () -> new HoeItem(ItemTier.Porcelain, -2, -1.0F, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_AXE = ITEMS.register("porcelain_axe", () -> new AxeItem(ItemTier.Porcelain, 6.0F, -3.1F, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_PICKAXE = ITEMS.register("porcelain_pickaxe", () -> new PickaxeItem(ItemTier.Porcelain, 1, -2.8F, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_SWORD = ITEMS.register("porcelain_sword", () -> new SwordItem(ItemTier.Porcelain, 3, -2.4F, defaultBuilder()));
    public static final RegistryObject<Item> PORCELAIN_SHOVEL = ITEMS.register("porcelain_shovel", () -> new ShovelItem(ItemTier.Porcelain, 1.5F, -1F, defaultBuilder()));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> TINY_BASE_POWDER = ITEMS.register("tiny_base_powder", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> BASE_POWDER = ITEMS.register("base_powder", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> CHICKEN_CHOP = ITEMS.register("chicken_chop", () -> new Item(defaultBuilder().food((new FoodProperties.Builder()).nutrition(10).saturationMod(1.2f).alwaysEat()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 1), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 0), 1.0F).build())));
    public static final RegistryObject<Item> BUDDING_ROSE_QUARTZ = ITEMS.register("budding_rose_quartz", () -> new ItemNameBlockItem(BlockRegistry.BUDDING_ROSE_QUARTZ.get(), defaultBuilder()));
    public static final RegistryObject<Item> SMALL_ROSE_QUARTZ_BUD = ITEMS.register("small_rose_quartz_bud", () -> new ItemNameBlockItem(BlockRegistry.SMALL_ROSE_QUARTZ_BUD.get(), defaultBuilder()));
    public static final RegistryObject<Item> MEDIUM_ROSE_QUARTZ_BUD = ITEMS.register("medium_rose_quartz_bud", () -> new ItemNameBlockItem(BlockRegistry.MEDIUM_ROSE_QUARTZ_BUD.get(), defaultBuilder()));
    public static final RegistryObject<Item> LARGE_ROSE_QUARTZ_BUD = ITEMS.register("large_rose_quartz_bud", () -> new ItemNameBlockItem(BlockRegistry.LARGE_ROSE_QUARTZ_BUD.get(), defaultBuilder()));
    public static final RegistryObject<Item> ROSE_QUARTZ_CLUSTER = ITEMS.register("rose_quartz_cluster", () -> new ItemNameBlockItem(BlockRegistry.ROSE_QUARTZ_CLUSTER.get(), defaultBuilder()));

    private static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(ClayNoMoreBalanced.CREATIVE_TAB);
    }
    
}