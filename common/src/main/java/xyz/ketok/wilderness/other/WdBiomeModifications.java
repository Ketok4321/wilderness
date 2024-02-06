package xyz.ketok.wilderness.other;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import xyz.ketok.wilderness.Wilderness;

public class WdBiomeModifications {
    public static void setup() {
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_OAK), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, key("fallen_oak")));
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_BIRCH), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, key("fallen_birch")));
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_SPRUCE), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, key("fallen_spruce")));
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_JUNGLE_TREE), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, key("fallen_jungle_tree")));
    }

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Wilderness.MOD_ID, name));
    }
}
