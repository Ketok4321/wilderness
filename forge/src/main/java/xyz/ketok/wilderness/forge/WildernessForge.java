package xyz.ketok.wilderness.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.ketok.wilderness.Wilderness;

@Mod(Wilderness.MOD_ID)
public class WildernessForge {
    public WildernessForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Wilderness.MOD_ID, bus);

        bus.addListener(this::commonSetup);

        Wilderness.init();
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Wilderness::initTerrablender);
    }
}
