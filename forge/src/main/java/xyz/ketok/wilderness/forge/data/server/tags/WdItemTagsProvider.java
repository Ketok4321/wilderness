package xyz.ketok.wilderness.forge.data.server.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.registry.WdItems;

import java.util.concurrent.CompletableFuture;

public class WdItemTagsProvider extends ItemTagsProvider {
    public WdItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);
        copy(BlockTags.BIRCH_LOGS, ItemTags.BIRCH_LOGS);
        copy(BlockTags.SPRUCE_LOGS, ItemTags.SPRUCE_LOGS);

        tag(ItemTags.TRIM_TEMPLATES).add(WdItems.ROOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get());
    }
}
