package xyz.ketok.wilderness.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import xyz.ketok.wilderness.registry.dynamic.WdBiomes;

import java.util.function.Consumer;

public class WildernessRegionBuilder {
    /*
    private static final ResourceKey<Biome>[][] VANILLA_MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,  Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,  Biomes.TAIGA}, // ICY
            {Biomes.PLAINS,        Biomes.PLAINS,       Biomes.FOREST,       Biomes.TAIGA,        Biomes.OLD_GROWTH_SPRUCE_TAIGA}, // COOL
            {Biomes.FLOWER_FOREST, Biomes.PLAINS,       Biomes.FOREST,       Biomes.BIRCH_FOREST, Biomes.DARK_FOREST}, // NEUTRAL
            {Biomes.SAVANNA,       Biomes.SAVANNA,      Biomes.FOREST,       Biomes.JUNGLE,       Biomes.JUNGLE}, // WARM
            {Biomes.DESERT,        Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT} // HOT
    };

    private static final ResourceKey<Biome>[][] VANILLA_MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null},
            {null, null, null, null, Biomes.OLD_GROWTH_PINE_TAIGA},
            {Biomes.SUNFLOWER_PLAINS, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null},
            {null, null, Biomes.PLAINS, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE},
            {null, null, null, null, null}};
    */

    private static final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
            {null, null, null, null, null}, // ICY
            {null, null, WdBiomes.MIXED_FOREST, null, null}, // COOL
            {null, null, null, null, WdBiomes.OLD_GROWTH_FOREST}, // NEUTRAL
            {null, null, null, null, null}, // WARM`
            {null, null, null, null, null} // HOT
    };

    private static final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {null, null, null, null, null}, // ICY
            {null, null, null, null, null}, // COOL
            {null, null, null, null, WdBiomes.MIXED_FOREST}, // NEUTRAL
            {null, null, null, null, null}, // WARM
            {null, null, null, null, null} // HOT
    };

    private static final float VALLEY_SIZE = 0.05F;
    private static final float LOW_START = 0.26666668F;
    public static final float HIGH_START = 0.4F;
    private static final float HIGH_END = 0.93333334F;
    private static final float PEAK_SIZE = 0.1F;
    public static final float PEAK_START = 0.56666666F;
    private static final float PEAK_END = 0.7666667F;
    public static final float NEAR_INLAND_START = -0.11F;
    public static final float MID_INLAND_START = 0.03F;
    public static final float FAR_INLAND_START = 0.3F;
    public static final float EROSION_INDEX_1_START = -0.78F;
    public static final float EROSION_INDEX_2_START = -0.375F;
    private static final float EROSION_DEEP_DARK_DRYNESS_THRESHOLD = -0.225F;
    private static final float DEPTH_DEEP_DARK_DRYNESS_THRESHOLD = 0.9F;
    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
    private final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
    private final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F)};
    private final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
    private final Climate.Parameter FROZEN_RANGE;
    private final Climate.Parameter UNFROZEN_RANGE;
    private final Climate.Parameter mushroomFieldsContinentalness;
    private final Climate.Parameter deepOceanContinentalness;
    private final Climate.Parameter oceanContinentalness;
    private final Climate.Parameter coastContinentalness;
    private final Climate.Parameter inlandContinentalness;
    private final Climate.Parameter nearInlandContinentalness;
    private final Climate.Parameter midInlandContinentalness;
    private final Climate.Parameter farInlandContinentalness;

    public WildernessRegionBuilder() {
        this.FROZEN_RANGE = this.temperatures[0];
        this.UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
        this.mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
        this.deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
        this.oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
        this.coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
        this.inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
        this.nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
        this.midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
        this.farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);
    }

    public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> key) {
        this.addInlandBiomes(key);
    }

    private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addMidSlice(consumer, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(consumer, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(consumer, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(consumer, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(consumer, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(consumer, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(consumer, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(consumer, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(consumer, Climate.Parameter.span(0.26666668F, 0.4F));
        this.addHighSlice(consumer, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(consumer, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(consumer, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(consumer, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter parameter2 = this.humidities[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickPlateauBiome(i, j, param);
                ResourceKey<Biome> resourceKey5 = this.pickShatteredBiome(i, j, param);
                ResourceKey<Biome> resourceKey6 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey5);
                ResourceKey<Biome> resourceKey7 = this.pickPeakBiome(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.midInlandContinentalness, this.erosions[3], param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.farInlandContinentalness, this.erosions[3], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], param, 0.0F, resourceKey6);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], param, 0.0F, resourceKey);
            }
        }
    }

    private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter parameter2 = this.humidities[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickPlateauBiome(i, j, param);
                ResourceKey<Biome> resourceKey5 = this.pickShatteredBiome(i, j, param);
                ResourceKey<Biome> resourceKey6 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey);
                ResourceKey<Biome> resourceKey7 = this.pickSlopeBiome(i, j, param);
                ResourceKey<Biome> resourceKey8 = this.pickPeakBiome(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, this.erosions[0], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], param, 0.0F, resourceKey8);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, this.erosions[1], param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.midInlandContinentalness, this.erosions[3], param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.farInlandContinentalness, this.erosions[3], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], param, 0.0F, resourceKey6);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], param, 0.0F, resourceKey);
            }
        }
    }

    private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter parameter2 = this.humidities[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickShatteredBiome(i, j, param);
                ResourceKey<Biome> resourceKey5 = this.pickPlateauBiome(i, j, param);
                ResourceKey<Biome> resourceKey6 = this.pickBeachBiome(i, j);
                ResourceKey<Biome> resourceKey7 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey);
                ResourceKey<Biome> resourceKey8 = this.pickShatteredCoastBiome(i, j, param);
                ResourceKey<Biome> resourceKey9 = this.pickSlopeBiome(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], param, 0.0F, resourceKey9);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.farInlandContinentalness, this.erosions[1], param, 0.0F, i == 0 ? resourceKey9 : resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, this.erosions[2], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.midInlandContinentalness, this.erosions[2], param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.farInlandContinentalness, this.erosions[2], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], param, 0.0F, resourceKey2);
                if (param.max() < 0L) {
                    this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, this.erosions[4], param, 0.0F, resourceKey6);
                    this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], param, 0.0F, resourceKey);
                } else {
                    this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], param, 0.0F, resourceKey);
                }

                this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, this.erosions[5], param, 0.0F, resourceKey8);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, this.erosions[5], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], param, 0.0F, resourceKey4);
                if (param.max() < 0L) {
                    this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, this.erosions[6], param, 0.0F, resourceKey6);
                } else {
                    this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, this.erosions[6], param, 0.0F, resourceKey);
                }

                if (i == 0) {
                    this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], param, 0.0F, resourceKey);
                }
            }
        }
    }

    private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter parameter2 = this.humidities[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickBeachBiome(i, j);
                ResourceKey<Biome> resourceKey5 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey);
                ResourceKey<Biome> resourceKey6 = this.pickShatteredCoastBiome(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, this.erosions[5], param, 0.0F, resourceKey6);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.nearInlandContinentalness, this.erosions[5], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, this.coastContinentalness, this.erosions[6], param, 0.0F, resourceKey4);
                if (i == 0) {
                    this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], param, 0.0F, resourceKey);
                }
            }
        }
    }

    private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter parameter2 = this.humidities[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), param, 0.0F, resourceKey);
            }
        }
    }

    private ResourceKey<Biome> pickMiddleBiome(int temperature, int humidity, Climate.Parameter param) {
        if (param.max() < 0L) {
            return MIDDLE_BIOMES[temperature][humidity];
        } else {
            ResourceKey<Biome> resourceKey = MIDDLE_BIOMES_VARIANT[temperature][humidity];
            return resourceKey == null ? MIDDLE_BIOMES[temperature][humidity] : resourceKey;
        }
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int temperature, int humidity, Climate.Parameter param) {
        return temperature == 4 ? this.pickBadlandsBiome(humidity, param) : this.pickMiddleBiome(temperature, humidity, param);
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int temperature, int humidity, Climate.Parameter param) {
        return temperature == 0 ? this.pickSlopeBiome(temperature, humidity, param) : this.pickMiddleBiomeOrBadlandsIfHot(temperature, humidity, param);
    }

    private ResourceKey<Biome> maybePickWindsweptSavannaBiome(int temperature, int humidity, Climate.Parameter param, ResourceKey<Biome> key) {
        return temperature > 1 && humidity < 4 && param.max() >= 0L ? null : key;
    }

    private ResourceKey<Biome> pickShatteredCoastBiome(int temperature, int humidity, Climate.Parameter param) {
        ResourceKey<Biome> resourceKey = param.max() >= 0L ? this.pickMiddleBiome(temperature, humidity, param) : this.pickBeachBiome(temperature, humidity);
        return this.maybePickWindsweptSavannaBiome(temperature, humidity, param, resourceKey);
    }

    private ResourceKey<Biome> pickBeachBiome(int temperature, int humidity) {
        return null;
    }

    private ResourceKey<Biome> pickBadlandsBiome(int humidity, Climate.Parameter param) {
        return null;
    }

    private ResourceKey<Biome> pickPlateauBiome(int temperature, int humidity, Climate.Parameter param) {
        return null;
    }

    private ResourceKey<Biome> pickPeakBiome(int temperature, int humidity, Climate.Parameter param) {
        return null;
    }

    private ResourceKey<Biome> pickSlopeBiome(int temperature, int humidity, Climate.Parameter param) {
        return null;
    }

    private ResourceKey<Biome> pickShatteredBiome(int temperature, int humidity, Climate.Parameter param) {
        return this.pickMiddleBiome(temperature, humidity, param);
    }

    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter depth, float weirdness, ResourceKey<Biome> key) {
        consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), depth, weirdness), key));
        consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), depth, weirdness), key));
    }
}
