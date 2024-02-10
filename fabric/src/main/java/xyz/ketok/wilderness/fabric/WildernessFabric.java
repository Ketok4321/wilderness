package xyz.ketok.wilderness.fabric;

import net.fabricmc.api.ModInitializer;
import terrablender.api.TerraBlenderApi;
import xyz.ketok.wilderness.Wilderness;

public class WildernessFabric implements ModInitializer, TerraBlenderApi {
    @Override
    public void onInitialize() {
        Wilderness.init();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        Wilderness.initTerrablender();
    }
}
