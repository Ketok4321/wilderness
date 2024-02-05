package xyz.ketok.wilderness.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import xyz.ketok.wilderness.registry.WdFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BlockOnFallenLogDecorator extends TreeDecorator {
    public static final Codec<BlockOnFallenLogDecorator> CODEC = RecordCodecBuilder.create(builder ->
            builder
                    .group(
                            BlockState.CODEC.fieldOf("block").forGetter(config -> config.block),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter(config -> config.chance)
                    )
                    .apply(builder, BlockOnFallenLogDecorator::new)
    );

    public final BlockState block;
    public final float chance;

    public BlockOnFallenLogDecorator(Block block, float chance) {
        this(block.defaultBlockState(), chance);
    }

    public BlockOnFallenLogDecorator(BlockState block, float chance) {
        this.block = block;
        this.chance = chance;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return WdFeatures.TreeDecorators.BLOCK_ON_FALLEN_LOG.get();
    }

    @Override
    public void place(Context context) {
        for (BlockPos pos : context.logs()) {
            if(!context.level().isStateAtPosition(pos.above(), BlockBehaviour.BlockStateBase::isAir)) continue;
            if(context.random().nextFloat() < chance) context.setBlock(pos.above(), block);
        }
    }
}
