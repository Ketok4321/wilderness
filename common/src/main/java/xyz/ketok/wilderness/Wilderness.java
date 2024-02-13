package xyz.ketok.wilderness;

import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
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

        LifecycleEvent.SETUP.register(WdBlocks::setup);
        LifecycleEvent.SETUP.register(WdItems::setup);
        LifecycleEvent.SETUP.register(VanillaModifications::setup);
    }

    public static void initTerrablender() {
        Regions.register(new WildernessRegion(new ResourceLocation(MOD_ID, "overworld"), 10)); // Vanilla weight (by default) is 10
    }
}
