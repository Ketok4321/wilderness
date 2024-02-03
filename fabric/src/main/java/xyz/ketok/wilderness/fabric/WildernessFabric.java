package xyz.ketok.wilderness.fabric;

import net.fabricmc.api.ModInitializer;
import xyz.ketok.wilderness.Wilderness;

public class WildernessFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Wilderness.init();
    }
}
