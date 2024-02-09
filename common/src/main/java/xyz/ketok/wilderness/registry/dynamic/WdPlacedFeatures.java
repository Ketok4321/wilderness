package xyz.ketok.wilderness.registry.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import xyz.ketok.wilderness.Wilderness;

public class WdPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FALLEN_OAK = key("fallen_oak");
    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH = key("fallen_birch");
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE = key("fallen_spruce");
    public static final ResourceKey<PlacedFeature> FALLEN_JUNGLE_TREE = key("fallen_jungle_tree");

    public static final ResourceKey<PlacedFeature> MEDIUM_OAK = key("medium_oak");
    public static final ResourceKey<PlacedFeature> MOSSY_FANCY_OAK = key("mossy_fancy_oak");

    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_FOREST = key("trees_old_growth_forest");
    public static final ResourceKey<PlacedFeature> TREES_MIXED_FOREST = key("trees_mixed_forest");

    public static final ResourceKey<PlacedFeature> BROWN_RED_MUSHROOM_PATCH = key("brown_red_mushroom_patch");
    public static final ResourceKey<PlacedFeature> FOREST_ROCK_RARE = key("forest_rock_rare");
    public static final ResourceKey<PlacedFeature> PATCH_COARSE_DIRT = key("patch_coarse_dirt");
    public static final ResourceKey<PlacedFeature> PATCH_PODZOL = key("patch_podzol");
    public static final ResourceKey<PlacedFeature> PATCH_MOSS = key("patch_moss");

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Wilderness.MOD_ID, name));
    }
}
