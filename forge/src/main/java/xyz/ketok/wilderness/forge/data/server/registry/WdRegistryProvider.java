package xyz.ketok.wilderness.forge.data.server.registry;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import xyz.ketok.wilderness.Wilderness;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WdRegistryProvider extends DatapackBuiltinEntriesProvider {
    public WdRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, WdRegistryProvider.createBuilder(), Set.of(Wilderness.MOD_ID));
    }

    private static RegistrySetBuilder createBuilder() {
        return new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, WdConfiguredFeatureProvider::bootstrap)
                .add(Registries.PLACED_FEATURE, WdPlacedFeatureProvider::bootstrap)
                .add(Registries.BIOME, WdBiomeProvider::bootstrap);
    }
}
