package xyz.ketok.wilderness.other;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import xyz.ketok.wilderness.grower.BiomeTreeGrower;
import xyz.ketok.wilderness.registry.dynamic.WdBiomes;
import xyz.ketok.wilderness.registry.dynamic.WdConfiguredFeatures;
import xyz.ketok.wilderness.registry.dynamic.WdPlacedFeatures;

public class VanillaModifications {
    public static void setup() {
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_OAK), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WdPlacedFeatures.FALLEN_OAK));
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_BIRCH), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WdPlacedFeatures.FALLEN_BIRCH));
        BiomeModifications.addProperties(c -> c.hasTag(WdTags.Biomes.HAS_FALLEN_SPRUCE), (c, b) -> b.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WdPlacedFeatures.FALLEN_SPRUCE));

        var oakSapling = (SaplingBlock) Blocks.OAK_SAPLING;
        oakSapling.treeGrower = new BiomeTreeGrower(WdBiomes.OLD_GROWTH_FOREST, WdConfiguredFeatures.OVERGROWN_FANCY_OAK, oakSapling.treeGrower, 0.1F);
        oakSapling.treeGrower = new BiomeTreeGrower(WdBiomes.MIXED_FOREST, WdConfiguredFeatures.MEDIUM_OAK, oakSapling.treeGrower, 0.1F);

        var spruceSapling = (SaplingBlock) Blocks.SPRUCE_SAPLING;
        spruceSapling.treeGrower = new BiomeTreeGrower(WdBiomes.MIXED_FOREST, WdConfiguredFeatures.OVERGROWN_SPRUCE, spruceSapling.treeGrower, 0.0F);
    }
}
