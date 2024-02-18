package xyz.ketok.wilderness.registry;

import dev.architectury.hooks.item.tool.AxeItemHooks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.block.ShelfMushroomBlock;

import java.util.function.Supplier;

public class WdBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Wilderness.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_OAK_LOG = blockItem("overgrown_oak_log", () -> new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG)), CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_OAK_WOOD = blockItem("overgrown_oak_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.OAK_WOOD)), CreativeModeTabs.NATURAL_BLOCKS);

    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_BIRCH_LOG = blockItem("overgrown_birch_log", () -> new RotatedPillarBlock(Properties.copy(Blocks.BIRCH_LOG)), CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_BIRCH_WOOD = blockItem("overgrown_birch_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.BIRCH_WOOD)), CreativeModeTabs.NATURAL_BLOCKS);

    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_SPRUCE_LOG = blockItem("overgrown_spruce_log", () -> new RotatedPillarBlock(Properties.copy(Blocks.SPRUCE_LOG)), CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_SPRUCE_WOOD = blockItem("overgrown_spruce_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.SPRUCE_WOOD)), CreativeModeTabs.NATURAL_BLOCKS);


    public static final RegistrySupplier<ShelfMushroomBlock> SHELF_MUSHROOM = blockItem("shelf_mushroom", () -> new ShelfMushroomBlock(Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY)), CreativeModeTabs.NATURAL_BLOCKS);

    public static void setup() {
        AxeItemHooks.addStrippable(OVERGROWN_OAK_LOG.get(), Blocks.OAK_LOG);
        AxeItemHooks.addStrippable(OVERGROWN_OAK_WOOD.get(), Blocks.OAK_WOOD);
        AxeItemHooks.addStrippable(OVERGROWN_BIRCH_LOG.get(), Blocks.BIRCH_LOG);
        AxeItemHooks.addStrippable(OVERGROWN_BIRCH_WOOD.get(), Blocks.BIRCH_WOOD);
        AxeItemHooks.addStrippable(OVERGROWN_SPRUCE_LOG.get(), Blocks.SPRUCE_LOG);
        AxeItemHooks.addStrippable(OVERGROWN_SPRUCE_WOOD.get(), Blocks.SPRUCE_WOOD);

        FireBlock fire = (FireBlock) Blocks.FIRE;
        for (RegistrySupplier<Block> b : BLOCKS) {
            if (b.getId().getPath().endsWith("_log") || b.getId().getPath().endsWith("_wood")) {
                fire.setFlammable(b.get(), 5, 5);
            }
        }
    }

    private static <T extends Block> RegistrySupplier<T> blockItem(String id, Supplier<T> block, ResourceKey<CreativeModeTab> tab) {
        var regBlock = BLOCKS.register(id, block);
        WdItems.ITEMS.register(id, () -> new BlockItem(regBlock.get(), new Item.Properties().arch$tab(tab)));

        return regBlock;
    }
}
