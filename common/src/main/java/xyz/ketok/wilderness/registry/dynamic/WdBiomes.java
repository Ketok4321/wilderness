package xyz.ketok.wilderness.registry.dynamic;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import xyz.ketok.wilderness.Wilderness;

public class WdBiomes {
    public static final ResourceKey<Biome> OLD_GROWTH_FOREST = key("old_growth_forest");
    public static final ResourceKey<Biome> MIXED_FOREST = key("mixed_forest");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Wilderness.MOD_ID, name));
    }
}
