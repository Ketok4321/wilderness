package xyz.ketok.wilderness.forge.data.server.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import xyz.ketok.wilderness.feature.FallenTreeFeature;
import xyz.ketok.wilderness.feature.treedecorators.BlockOnLogDecorator;
import xyz.ketok.wilderness.registry.WdBlocks;
import xyz.ketok.wilderness.registry.WdFeatures;
import xyz.ketok.wilderness.registry.dynamic.WdPlacedFeatures;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

import static xyz.ketok.wilderness.registry.dynamic.WdConfiguredFeatures.*;

public class WdConfiguredFeatureProvider {
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        var placed = context.lookup(Registries.PLACED_FEATURE);

        context.register(FALLEN_OAK, fallenTree(() -> BlockStateProviders.MOSSY_OAK_LOG, blockOnTop(Blocks.MOSS_CARPET, 0.4F), block(WdBlocks.SHELF_MUSHROOM.get(), 0.2F)));
        context.register(FALLEN_BIRCH, fallenTree(() -> BlockStateProvider.simple(Blocks.BIRCH_LOG), blockOnTop(Blocks.MOSS_CARPET, 0.4F), block(WdBlocks.SHELF_MUSHROOM.get(), 0.2F)));
        context.register(FALLEN_SPRUCE, fallenTree(() -> BlockStateProvider.simple(Blocks.SPRUCE_LOG), blockOnTop(Blocks.MOSS_CARPET, 0.3F), block(WdBlocks.SHELF_MUSHROOM.get(), 0.25F)));
        context.register(FALLEN_JUNGLE_TREE, fallenTree(() -> BlockStateProvider.simple(Blocks.JUNGLE_LOG), blockOnTop(Blocks.MOSS_CARPET, 0.5F), TrunkVineDecorator.INSTANCE));

        context.register(MEDIUM_OAK, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProviders.MOSSY_OAK_LOG/*BlockStateProvider.simple(Blocks.OAK_LOG)*/,
                new FancyTrunkPlacer(6, 2, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
        ).ignoreVines().build()));

        context.register(MOSSY_FANCY_OAK, new ConfiguredFeature<>(Feature.TREE, createFancyOak(BlockStateProviders.MOSSY_OAK_LOG)
                .decorators(List.of(new BeehiveDecorator(0.02F), block(WdBlocks.SHELF_MUSHROOM.get(), 0.02F)))
                .build()
        ));

        context.register(TREES_OLD_GROWTH_FOREST, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(TreePlacements.OAK_BEES_002), 0.1F),
                        new WeightedPlacedFeature(placed.getOrThrow(TreePlacements.BIRCH_BEES_002), 0.1F)
                ), placed.getOrThrow(WdPlacedFeatures.MOSSY_FANCY_OAK)
        )));

        context.register(TREES_MIXED_FOREST, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(placed.getOrThrow(TreePlacements.SPRUCE_CHECKED), 0.5F),
                        new WeightedPlacedFeature(placed.getOrThrow(TreePlacements.OAK_BEES_002), 0.1F)),
                placed.getOrThrow(WdPlacedFeatures.MEDIUM_OAK)
        )));

        context.register(BROWN_RED_MUSHROOM_PATCH, new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder()
                                .add(Blocks.RED_MUSHROOM.defaultBlockState(), 1)
                                .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                                .build()
                )))));

        context.register(PATCH_COARSE_DIRT, surfacePatch(Blocks.COARSE_DIRT));
        context.register(PATCH_PODZOL, surfacePatch(Blocks.PODZOL));
        context.register(PATCH_MOSS, new ConfiguredFeature<>(Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.DIRT,
                        BlockStateProvider.simple(Blocks.MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(Holder.direct(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(new WeightedStateProvider(
                                        SimpleWeightedRandomList.<BlockState>builder()
                                                .add(Blocks.MOSS_CARPET.defaultBlockState(), 8)
                                                .add(Blocks.GRASS.defaultBlockState(), 2)
                                ))
                        ))),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0,
                        5,
                        0.5F,
                        ConstantInt.of(1),
                        0.3F
                )
        ));
    }

    private static BlockOnLogDecorator block(Block block, float chance) {
        return new BlockOnLogDecorator(block.defaultBlockState(), chance, false);
    }

    private static BlockOnLogDecorator blockOnTop(Block block, float chance) {
        return new BlockOnLogDecorator(block.defaultBlockState(), chance, true);
    }

    private static ConfiguredFeature<?, ?> fallenTree(Supplier<BlockStateProvider> log, TreeDecorator... decorators) {
        return new ConfiguredFeature<>(WdFeatures.FALLEN_TREE.get(), new FallenTreeFeature.Config(
                log.get(),
                UniformInt.of(5, 6),
                List.of(decorators)
        ));
    }

    //TODO: Use Feature.VEGETATION_PATCH?
    private static ConfiguredFeature<?, ?> surfacePatch(Block block) {
        return new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(
                        new BlockMatchTest(Blocks.GRASS_BLOCK),
                        block.defaultBlockState(),
                        33
                )
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyOak(BlockStateProvider trunkProvider) {
        return (new TreeConfiguration.TreeConfigurationBuilder(trunkProvider, new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    public static class BlockStateProviders {
        public static BlockStateProvider MOSSY_OAK_LOG = new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(WdBlocks.MOSSY_OAK_LOG.get().defaultBlockState(), 2).add(Blocks.OAK_LOG.defaultBlockState(), 1));
    }
}
