package xyz.ketok.wilderness.forge;

import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.other.WdHooks;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    public static void onBonemeal(BonemealEvent event) {
        if (WdHooks.onBonemeal(event.getLevel(), event.getPos())) {
            event.setResult(Event.Result.ALLOW);
        }
    }
}
