package xyz.ketok.wilderness.forge.data.server.worldgen;

import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeBuilder;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.function.Consumer;

public class WdBiomeBuilder {
    private BiomeGenerationSettings generation;
    private MobSpawnSettings spawns;
    private Float temperature, downfall;
    private TemperatureModifier temperatureModifier = TemperatureModifier.NONE;
    private boolean hasPrecipitation = true;
    private BiomeSpecialEffects.Builder specialEffects = new BiomeSpecialEffects.Builder()
            .waterColor(4159204)
            .waterFogColor(329011)
            .fogColor(12638463);

    public WdBiomeBuilder generationAndSpawns(BiomeGenerationSettings.Builder generation, MobSpawnSettings.Builder spawns) {
        this.generation = generation.build();
        this.spawns = spawns.build();

        return this;
    }

    public WdBiomeBuilder temperatureAndDownfall(float temperature, float downfall) {
        this.temperature = temperature;
        this.downfall = downfall;
        this.specialEffects.skyColor(calculateSkyColor(temperature));
        return this;
    }

    public WdBiomeBuilder temperatureAdjustment(TemperatureModifier temperatureSettings) {
        this.temperatureModifier = temperatureSettings;
        return this;
    }

    public WdBiomeBuilder hasPrecipitation(boolean hasPrecipitation) {
        this.hasPrecipitation = hasPrecipitation;
        return this;
    }

    public WdBiomeBuilder specialEffects(Consumer<BiomeSpecialEffects.Builder> specialEffectsModifications) {
        specialEffectsModifications.accept(specialEffects);
        return this;
    }

    public Biome build() {
        if (generation != null && spawns != null && temperature != null && downfall != null) {
            return new BiomeBuilder()
                    .hasPrecipitation(hasPrecipitation)
                    .temperature(temperature).downfall(downfall)
                    .specialEffects(specialEffects.build())
                    .mobSpawnSettings(spawns)
                    .generationSettings(generation)
                    .temperatureAdjustment(temperatureModifier)
                .build();
        } else {
            throw new IllegalStateException("You are missing parameters to build a proper biome");
        }
    }

    private static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
