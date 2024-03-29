package xyz.ketok.wilderness.forge.data.client;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.registry.WdBlocks;

import static xyz.ketok.wilderness.forge.data.WdDataGenUtil.*;

public class WdBlockStateProvider extends BlockStateProvider {
    public WdBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(WdBlocks.OVERGROWN_OAK_LOG.get());
        woodBlock(WdBlocks.OVERGROWN_OAK_WOOD.get(), WdBlocks.OVERGROWN_OAK_LOG.get());
        logBlock(WdBlocks.OVERGROWN_BIRCH_LOG.get());
        woodBlock(WdBlocks.OVERGROWN_BIRCH_WOOD.get(), WdBlocks.OVERGROWN_BIRCH_LOG.get());
        logBlock(WdBlocks.OVERGROWN_SPRUCE_LOG.get());
        woodBlock(WdBlocks.OVERGROWN_SPRUCE_WOOD.get(), WdBlocks.OVERGROWN_SPRUCE_LOG.get());
    }

    public void woodBlock(RotatedPillarBlock woodBlock, RotatedPillarBlock logBlock) {
        axisBlock(woodBlock, blockTexture(logBlock), blockTexture(logBlock));
    }
}
