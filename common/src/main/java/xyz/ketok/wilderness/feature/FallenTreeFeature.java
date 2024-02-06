package xyz.ketok.wilderness.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.*;

public class FallenTreeFeature extends Feature<FallenTreeFeature.Config> {
    public record Config(BlockStateProvider block, IntProvider length, List<TreeDecorator> decorators) implements FeatureConfiguration {
        public static final Codec<Config> CODEC = RecordCodecBuilder.create(builder ->
                builder
                        .group(
                                BlockStateProvider.CODEC.fieldOf("block").forGetter(config -> config.block),
                                IntProvider.POSITIVE_CODEC.fieldOf("length").forGetter(config -> config.length),
                                Codec.list(TreeDecorator.CODEC).fieldOf("decorators").forGetter(config -> config.decorators)
                        )
                        .apply(builder, Config::new)
        );
    }

    public FallenTreeFeature() {
        super(Config.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<Config> context) {
        var level = context.level();
        var startPos = context.origin();
        var config = context.config();
        var random = context.random();

        var direction = random.nextBoolean() ?
                (random.nextBoolean() ? Direction.EAST : Direction.WEST):
                (random.nextBoolean() ? Direction.NORTH : Direction.SOUTH);

        var length = config.length.sample(random);

        var generateStump = random.nextFloat() < 0.75F;

        if(!canPlace(level, startPos, length, direction, generateStump)) {
            direction = getValidDirection(level, startPos, length, generateStump);
            if(direction == null) return false;
        }

        Set<BlockPos> placedLogs = new HashSet<>(length + (generateStump ? 1 : 0));

        if(generateStump) {
            level.setBlock(startPos, config.block.getState(random, startPos), 2);
            placedLogs.add(startPos);

            startPos = startPos.relative(direction, 2);
        }

        for(int i = 0; i < length; i++) {
            BlockPos pos = startPos.relative(direction, i);

            BlockState block = config.block.getState(random, pos);

            if(block.getBlock() instanceof RotatedPillarBlock) {
                block = block.setValue(RotatedPillarBlock.AXIS, direction.getAxis());
            }

            level.setBlock(pos, block, 2);
            placedLogs.add(pos);
        }

        config.decorators.forEach((decorator) -> {
            decorator.place(new TreeDecorator.Context(level, (pos, block) -> level.setBlock(pos, block, 19), random, placedLogs, Set.of(), Set.of()));
        });

        return true;
    }

    private boolean canPlace(WorldGenLevel level, BlockPos startPos, int length, Direction direction, boolean generateStump) {
        if(generateStump) {
            length += 2;
        }

        for(int i = 0; i < length; i++) {
            BlockPos pos = startPos.relative(direction, i);

            if(!TreeFeature.validTreePos(level, pos) || !isDirt(level.getBlockState(pos.below()))) return false;
        }

        return true;
    }

    private Direction getValidDirection(WorldGenLevel level, BlockPos startPos, int length, boolean generateStump) {
        for (Direction dir : List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH)) {
            if (canPlace(level, startPos, length, dir, generateStump)) {
                return dir;
            }
        }

        return null;
    }
}
