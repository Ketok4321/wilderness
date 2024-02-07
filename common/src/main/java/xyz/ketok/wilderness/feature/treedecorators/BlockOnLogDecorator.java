package xyz.ketok.wilderness.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;
import xyz.ketok.wilderness.registry.WdFeatures;

import java.util.List;

public class BlockOnLogDecorator extends TreeDecorator {
    public static final Codec<BlockOnLogDecorator> CODEC = RecordCodecBuilder.create(builder ->
            builder
                    .group(
                            BlockState.CODEC.fieldOf("block").forGetter(config -> config.block),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter(config -> config.chance),
                            Codec.BOOL.fieldOf("on_top").forGetter(config -> config.onTop)
                    )
                    .apply(builder, BlockOnLogDecorator::new)
    );

    public final BlockState block;
    public final float chance;
    public final boolean onTop;

    public BlockOnLogDecorator(BlockState block, float chance, boolean onTop) {
        this.block = block;
        this.chance = chance;
        this.onTop = onTop;
    }

    @Override
    protected @NotNull TreeDecoratorType<?> type() {
        return WdFeatures.TreeDecorators.BLOCK_ON_LOG.get();
    }

    @Override
    public void place(Context context) {
        for (BlockPos pos : context.logs()) {
            if (this.onTop) {
                BlockPos placePos = pos.above();
                if (context.isAir(placePos)) {
                    if (context.random().nextFloat() < this.chance) {
                        context.setBlock(placePos, this.block);
                    }
                }
            }
            else {
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    BlockPos placePos = pos.relative(dir);
                    if (context.isAir(placePos)) {
                        if (context.random().nextFloat() < this.chance) {
                            BlockState blockToPlace = this.block;
                            if (blockToPlace.hasProperty(HorizontalDirectionalBlock.FACING)) {
                                blockToPlace = blockToPlace.setValue(HorizontalDirectionalBlock.FACING, dir);
                            }
                            context.setBlock(placePos, blockToPlace);
                        }
                    }
                }
            }
        }
    }
}
