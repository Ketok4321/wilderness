package xyz.ketok.wilderness.registry;

import dev.architectury.hooks.item.tool.AxeItemHooks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
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

    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_OAK_LOG = blockItem("overgrown_oak_log", () -> new RotatedPillarBlock(Properties.copy(Blocks.OAK_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_OAK_WOOD = blockItem("overgrown_oak_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_BIRCH_LOG = blockItem("overgrown_birch_log", () -> new RotatedPillarBlock(Properties.copy(Blocks.BIRCH_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_BIRCH_WOOD = blockItem("overgrown_birch_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.BIRCH_WOOD)));

    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_SPRUCE_LOG = blockItem("overgrown_spruce_log", () -> new RotatedPillarBlock(Properties.copy(Blocks.SPRUCE_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> OVERGROWN_SPRUCE_WOOD = blockItem("overgrown_spruce_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.SPRUCE_WOOD)));


    public static final RegistrySupplier<ShelfMushroomBlock> SHELF_FUNGI = blockItem("shelf_fungi", () -> new ShelfMushroomBlock(Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY)));

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

    private static <T extends Block> RegistrySupplier<T> blockItem(String id, Supplier<T> block) {
        var regBlock = BLOCKS.register(id, block);
        WdItems.ITEMS.register(id, () -> new BlockItem(regBlock.get(), new Item.Properties()));

        return regBlock;
    }
}
