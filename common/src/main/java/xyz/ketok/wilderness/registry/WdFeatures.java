package xyz.ketok.wilderness.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.feature.FallenTreeFeature;
import xyz.ketok.wilderness.feature.treedecorators.BlockOnFallenLogDecorator;

public class WdFeatures {
    public static DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Wilderness.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<Feature<FallenTreeFeature.Config>> FALLEN_TREE = FEATURES.register("fallen_tree", FallenTreeFeature::new);

    public static class TreeDecorators {
        public static DeferredRegister<TreeDecoratorType<?>> DECORATORS = DeferredRegister.create(Wilderness.MOD_ID, Registries.TREE_DECORATOR_TYPE);

        public static final RegistrySupplier<TreeDecoratorType<BlockOnFallenLogDecorator>> BLOCK_ON_FALLEN_LOG = DECORATORS.register("block_on_fallen_log", () -> new TreeDecoratorType<>(BlockOnFallenLogDecorator.CODEC));
    }
}
