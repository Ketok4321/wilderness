package xyz.ketok.wilderness.forge.data.client;

import net.minecraft.data.PackOutput;
import xyz.ketok.wilderness.Wilderness;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static xyz.ketok.wilderness.forge.data.WdDataGenUtil.*;

public class WdItemModelProvider extends ItemModelProvider {
    public WdItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        wdBlockItems().forEach(block -> withExistingParent(name(block).getPath(), prefix("block/", name(block))));
    }

    private void generated(Item item) {
        withExistingParent(name(item).getPath(), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + name(item)));
    }
}
