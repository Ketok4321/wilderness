package xyz.ketok.wilderness.registry;

import dev.architectury.registry.CreativeTabOutput;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import xyz.ketok.wilderness.Wilderness;

public class WdItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Wilderness.MOD_ID, Registries.ITEM);

    public static void setup() {
        ComposterBlock.COMPOSTABLES.put(WdBlocks.SHELF_MUSHROOM.get().asItem(), 0.65F);

        CreativeTabRegistry.modifyBuiltin(BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS), (FeatureFlagSet flags, CreativeTabOutput output, boolean canUseGameMasterBlocks) -> {
            output.acceptAfter(Blocks.OAK_WOOD, WdBlocks.OVERGROWN_OAK_LOG.get());
            output.acceptAfter(WdBlocks.OVERGROWN_OAK_LOG.get(), WdBlocks.OVERGROWN_OAK_WOOD.get());
            output.acceptAfter(Blocks.BIRCH_WOOD, WdBlocks.OVERGROWN_BIRCH_LOG.get());
            output.acceptAfter(WdBlocks.OVERGROWN_BIRCH_LOG.get(), WdBlocks.OVERGROWN_BIRCH_WOOD.get());
            output.acceptAfter(Blocks.SPRUCE_WOOD, WdBlocks.OVERGROWN_SPRUCE_LOG.get());
            output.acceptAfter(WdBlocks.OVERGROWN_SPRUCE_LOG.get(), WdBlocks.OVERGROWN_SPRUCE_WOOD.get());
        });

        CreativeTabRegistry.modifyBuiltin(BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS), (FeatureFlagSet flags, CreativeTabOutput output, boolean canUseGameMasterBlocks) -> {
            output.acceptAfter(Blocks.OAK_LOG, WdBlocks.OVERGROWN_OAK_LOG.get());
            output.acceptAfter(Blocks.BIRCH_LOG, WdBlocks.OVERGROWN_BIRCH_LOG.get());
            output.acceptAfter(Blocks.SPRUCE_LOG, WdBlocks.OVERGROWN_SPRUCE_LOG.get());

            output.acceptAfter(Items.WARPED_FUNGUS, WdBlocks.SHELF_MUSHROOM.get());
        });
    }
}
