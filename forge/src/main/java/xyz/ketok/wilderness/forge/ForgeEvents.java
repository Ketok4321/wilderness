package xyz.ketok.wilderness.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.other.WdHooks;
import xyz.ketok.wilderness.registry.WdBlocks;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    public static void onBonemeal(BonemealEvent event) {
        if (WdHooks.onBonemeal(event.getLevel(), event.getPos())) {
            event.setResult(Event.Result.ALLOW);
        }
    }

    // I only made this so the survival world i used for testing won't break, the mod has not even been yet released lol
    @SubscribeEvent
    public static void fixMissingMappings(MissingMappingsEvent event) {
        for (MissingMappingsEvent.Mapping<Block> mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, Wilderness.MOD_ID)) {
            switch (mapping.getKey().getPath()) {
                case "shelf_mushroom": mapping.remap(WdBlocks.SHELF_FUNGI.get());
            }
        }

        for (MissingMappingsEvent.Mapping<Item> mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, Wilderness.MOD_ID)) {
            switch (mapping.getKey().getPath()) {
                case "shelf_mushroom": mapping.remap(WdBlocks.SHELF_FUNGI.get().asItem());
            }
        }
    }
}
