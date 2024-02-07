package xyz.ketok.wilderness.registry;

import com.google.common.collect.ImmutableMap;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.block.MossyLogBlock;
import xyz.ketok.wilderness.block.ShelfMushroomBlock;

import java.util.HashMap;
import java.util.function.Supplier;

public class WdBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Wilderness.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<MossyLogBlock> MOSSY_OAK_LOG = blockItem("mossy_oak_log", () -> new MossyLogBlock(Properties.copy(Blocks.OAK_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> MOSSY_OAK_WOOD = blockItem("mossy_oak_wood", () -> new RotatedPillarBlock(Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistrySupplier<ShelfMushroomBlock> SHELF_MUSHROOM = blockItem("shelf_mushroom", () -> new ShelfMushroomBlock(Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY)));

    public static void setup() {
        if (AxeItem.STRIPPABLES instanceof ImmutableMap<Block, Block>) {
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
        }

        AxeItem.STRIPPABLES.put(MOSSY_OAK_LOG.get(), Blocks.OAK_LOG);

        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(MOSSY_OAK_LOG.get(), 5, 5);
        fire.setFlammable(MOSSY_OAK_WOOD.get(), 5, 5);
    }

    private static <T extends Block> RegistrySupplier<T> blockItem(String id, Supplier<T> block) {
        var regBlock = BLOCKS.register(id, block);
        WdItems.ITEMS.register(id, () -> new BlockItem(regBlock.get(), new Item.Properties()));

        return regBlock;
    }
}
