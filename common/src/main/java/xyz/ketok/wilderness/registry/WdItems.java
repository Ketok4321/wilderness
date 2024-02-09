package xyz.ketok.wilderness.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import xyz.ketok.wilderness.Wilderness;

public class WdItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Wilderness.MOD_ID, Registries.ITEM);

    public static void setup() {
        ComposterBlock.COMPOSTABLES.put(WdBlocks.SHELF_MUSHROOM.get().asItem(), 0.65F);
    }
}
