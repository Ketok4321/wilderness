package xyz.ketok.wilderness.forge.data.client;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.registry.WdBlocks;

import static xyz.ketok.wilderness.forge.data.WdDataGenUtil.*;

public class WdBlockStateProvider extends BlockStateProvider {
    public WdBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(WdBlocks.MOSSY_OAK_LOG.get());
        axisBlock(WdBlocks.MOSSY_OAK_WOOD.get(), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()));
    }
}
