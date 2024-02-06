package xyz.ketok.wilderness.other;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import xyz.ketok.wilderness.Wilderness;

public class WdTags {
    public static class Biomes {
        public static final TagKey<Biome> HAS_FALLEN_OAK = tag("has_fallen_tree/oak");
        public static final TagKey<Biome> HAS_FALLEN_BIRCH = tag("has_fallen_tree/birch.json");
        public static final TagKey<Biome> HAS_FALLEN_SPRUCE = tag("has_fallen_tree/spruce");
        public static final TagKey<Biome> HAS_FALLEN_JUNGLE_TREE = tag("has_fallen_tree/jungle");

        private static TagKey<Biome> tag(String name)
        {
            return TagKey.create(Registries.BIOME, new ResourceLocation(Wilderness.MOD_ID, name));
        }
    }
}
