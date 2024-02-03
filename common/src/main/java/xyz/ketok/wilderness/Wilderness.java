package xyz.ketok.wilderness;

import dev.architectury.event.events.common.LifecycleEvent;
import xyz.ketok.wilderness.registry.WdBlocks;
import xyz.ketok.wilderness.registry.WdItems;

public class Wilderness {
    public static final String MOD_ID = "wilderness";
    
    public static void init() {
        WdBlocks.BLOCKS.register();
        WdItems.ITEMS.register();

        LifecycleEvent.SETUP.register(WdBlocks::setup);
    }
}
