package xyz.ketok.wilderness.registry;

import com.google.common.collect.ImmutableMap;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.block.MossyLogBlock;

import java.util.HashMap;
import java.util.function.Supplier;

public class WdBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Wilderness.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> MOSSY_OAK_LOG = blockItem("mossy_oak_log", () -> new MossyLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static void setup() {
        if (AxeItem.STRIPPABLES instanceof ImmutableMap<Block, Block>) {
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
        }

        AxeItem.STRIPPABLES.put(MOSSY_OAK_LOG.get(), Blocks.OAK_LOG);
    }

    private static RegistrySupplier<Block> blockItem(String id, Supplier<Block> block) {
        var regBlock = BLOCKS.register(id, block);
        WdItems.ITEMS.register(id, () -> new BlockItem(regBlock.get(), new Item.Properties()));

        return regBlock;
    }
}
