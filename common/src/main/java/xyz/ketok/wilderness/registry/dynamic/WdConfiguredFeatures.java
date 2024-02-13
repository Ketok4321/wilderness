package xyz.ketok.wilderness.registry.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import xyz.ketok.wilderness.Wilderness;

public class WdConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK = key("fallen_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH = key("fallen_birch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE = key("fallen_spruce");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_OAK = key("medium_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_FANCY_OAK = key("mossy_fancy_oak");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_OLD_GROWTH_FOREST = key("trees_old_growth_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_MIXED_FOREST = key("trees_mixed_forest");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_RED_MUSHROOM_PATCH = key("brown_red_mushroom_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_COARSE_DIRT = key("patch_coarse_dirt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PODZOL = key("patch_podzol");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_MOSS = key("patch_moss");

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Wilderness.MOD_ID, name));
    }
}
