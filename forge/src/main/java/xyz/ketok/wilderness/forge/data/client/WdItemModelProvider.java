package xyz.ketok.wilderness.forge.data.client;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import xyz.ketok.wilderness.Wilderness;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.ketok.wilderness.registry.WdBlocks;
import xyz.ketok.wilderness.registry.dynamic.WdBiomes;

import static xyz.ketok.wilderness.forge.data.WdDataGenUtil.*;

public class WdItemModelProvider extends ItemModelProvider {
    public WdItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        wdBlockItems().forEach(block -> withExistingParent(name(block).getPath(), prefix("block/", name(block)))); // These can be overwritten later

        generated(WdBlocks.SHELF_MUSHROOM.get().asItem());
    }

    private void generated(Item item, ResourceLocation path) {
        withExistingParent(name(item).getPath(), "item/generated").texture("layer0", path);
    }

    private void generated(Item item) {
        generated(item, prefix("item/", name(item)));
    }
}
