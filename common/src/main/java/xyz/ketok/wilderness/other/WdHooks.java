package xyz.ketok.wilderness.other;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import xyz.ketok.wilderness.registry.WdBlocks;

import java.util.Map;
import java.util.function.Supplier;

public class WdHooks {
    public static final Map<Block, Supplier<RotatedPillarBlock>> OVERGROWN_VARIANTS = new ImmutableMap.Builder<Block, Supplier<RotatedPillarBlock>>()
            .put(Blocks.OAK_LOG, WdBlocks.OVERGROWN_OAK_LOG)
            .put(Blocks.OAK_WOOD, WdBlocks.OVERGROWN_OAK_WOOD)
            .put(Blocks.BIRCH_LOG, WdBlocks.OVERGROWN_BIRCH_LOG)
            .put(Blocks.BIRCH_WOOD, WdBlocks.OVERGROWN_BIRCH_WOOD)
            .put(Blocks.SPRUCE_LOG, WdBlocks.OVERGROWN_SPRUCE_LOG)
            .put(Blocks.SPRUCE_WOOD, WdBlocks.OVERGROWN_SPRUCE_WOOD)
            .build();

    public static boolean onBonemeal(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        Supplier<RotatedPillarBlock> overgrown = OVERGROWN_VARIANTS.get(blockState.getBlock());

        if (overgrown != null) {
            for (BlockPos searchPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                if (level.getBlockState(searchPos).is(overgrown.get())) {
                    level.setBlock(pos, overgrown.get().withPropertiesOf(blockState), 3);

                    return true;
                }
            }
        }

        return false;
    }
}
