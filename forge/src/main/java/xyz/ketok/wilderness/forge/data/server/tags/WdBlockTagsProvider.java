package xyz.ketok.wilderness.forge.data.server.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.registry.WdBlocks;

import java.util.concurrent.CompletableFuture;

public class WdBlockTagsProvider extends BlockTagsProvider {
    public WdBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.OAK_LOGS).add(WdBlocks.MOSSY_OAK_LOG.get(), WdBlocks.MOSSY_OAK_WOOD.get());
    }
}