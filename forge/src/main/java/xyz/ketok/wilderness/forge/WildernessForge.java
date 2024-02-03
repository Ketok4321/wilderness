package xyz.ketok.wilderness.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.ketok.wilderness.Wilderness;

@Mod(Wilderness.MOD_ID)
public class WildernessForge {
    public WildernessForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Wilderness.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Wilderness.init();
    }
}
