package xyz.ketok.wilderness.forge.data.server.worldgen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.registry.WdBlocks;

import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WdRegistryProvider extends DatapackBuiltinEntriesProvider {
    public WdRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, WdRegistryProvider.createBuilder(), Set.of(Wilderness.MOD_ID));
    }

    private static RegistrySetBuilder createBuilder() {
        return new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, WdConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, WdPlacedFeatures::bootstrap)
                .add(Registries.BIOME, WdBiomes::bootstrap);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyOak(BlockStateProvider trunkProvider) {
        return (new TreeConfiguration.TreeConfigurationBuilder(trunkProvider, new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    public static class BlockStateProviders {
        public static BlockStateProvider MOSSY_OAK_LOG = new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(WdBlocks.MOSSY_OAK_LOG.get().defaultBlockState(), 2).add(Blocks.OAK_LOG.defaultBlockState(), 1));
    }
}
