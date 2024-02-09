package xyz.ketok.wilderness.grower;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class BiomeTreeGrower extends AbstractTreeGrower {
    public final ResourceKey<Biome> target;
    public final ResourceKey<ConfiguredFeature<?, ?>> configuredFeature;
    public final AbstractTreeGrower fallback;
    public final float fallbackChance;

    public BiomeTreeGrower(ResourceKey<Biome> target, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, AbstractTreeGrower fallback, float fallbackChance) {
        this.target = target;
        this.configuredFeature = configuredFeature;
        this.fallback = fallback;
        this.fallbackChance = fallbackChance;
    }

    @Override
    public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
        if (level.getBiome(pos).unwrapKey().orElseThrow() == this.target && random.nextFloat() > this.fallbackChance) {
            return super.growTree(level, generator, pos, state, random);
        }

        return this.fallback.growTree(level, generator, pos, state, random);
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return this.configuredFeature;
    }
}
