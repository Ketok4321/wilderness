package xyz.ketok.wilderness.forge.data;

import com.google.common.collect.Streams;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import xyz.ketok.wilderness.registry.WdBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class WdDataGenUtil {
    public static <T> Stream<T> entries(DeferredRegister<T> register) {
        return Streams.stream(register.iterator()).map(RegistrySupplier::get);
    }

    public static Stream<Block> wdBlockItems() {
        return entries(WdBlocks.BLOCKS).filter(block -> requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem())).equals(ForgeRegistries.BLOCKS.getKey(block)));
    }

    public static ResourceLocation name(Block block) {
        return requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
    }

    public static ResourceLocation name(Item item) {
        return requireNonNull(ForgeRegistries.ITEMS.getKey(item));
    }

    public static ResourceLocation prefix(String prefix, ResourceLocation rl) {
        return new ResourceLocation(rl.getNamespace(), prefix + rl.getPath());
    }

    public static ResourceLocation suffix(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }
}
