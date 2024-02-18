package xyz.ketok.wilderness.config;

import xyz.ketok.wilderness.Wilderness;

public record WdConfig(int regionWeight, boolean generateFallenTrees, boolean removeLavaFromForests) {

    private static WdConfig instance;

    public static WdConfig get() {
        return instance;
    }

    private static String provider( String filename ) {
        return """
# Features

# Terrablender region weight
regionWeight = 10

# If fallen trees should generate
# Their generation can also be configured further via #wilderness:has_fallen_tree/* biome tags
generateFallenTrees = true

# Tweaks

# If surface lava pools and lava springs should be removed from #minecraft:is_forest and #minecraft:is_taiga biomes
removeLavaFromForests = true
        """;
    }

    public static void setup() {
        if (instance != null) return;

        SimpleConfig config = SimpleConfig.of(Wilderness.MOD_ID).provider(WdConfig::provider).request();

        instance = new WdConfig(
                config.getOrDefault("regionWeight", 10),
                config.getOrDefault("generateFallenTrees", true),
                config.getOrDefault("removeLavaFromForests", true)
        );
    }
}
