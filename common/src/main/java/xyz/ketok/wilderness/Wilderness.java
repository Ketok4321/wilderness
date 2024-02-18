package xyz.ketok.wilderness;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import xyz.ketok.wilderness.config.WdConfig;
import xyz.ketok.wilderness.other.VanillaModifications;
import xyz.ketok.wilderness.region.WildernessRegion;
import xyz.ketok.wilderness.registry.WdBlocks;
import xyz.ketok.wilderness.registry.WdFeatures;
import xyz.ketok.wilderness.registry.WdItems;
import xyz.ketok.wilderness.registry.WdSoundEvents;

public class Wilderness {
    public static final String MOD_ID = "wilderness";
    
    public static void init() {
        WdBlocks.BLOCKS.register();
        WdItems.ITEMS.register();
        WdFeatures.FEATURES.register();
        WdFeatures.TreeDecorators.DECORATORS.register();
        WdSoundEvents.SOUNDS.register();

        LifecycleEvent.SETUP.register(WdConfig::setup);
        LifecycleEvent.SETUP.register(WdBlocks::setup);
        LifecycleEvent.SETUP.register(WdItems::setup);
        LifecycleEvent.SETUP.register(VanillaModifications::setup);
    }

    public static void initTerrablender() {
        WdConfig.setup(); // Calling this second time here to make sure that it's loaded because fabric doesn't allow for any kind of ordering

        Regions.register(new WildernessRegion(new ResourceLocation(MOD_ID, "overworld"), WdConfig.get().regionWeight()));
    }
}
