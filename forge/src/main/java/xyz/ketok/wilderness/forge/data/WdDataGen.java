package xyz.ketok.wilderness.forge.data;

import xyz.ketok.wilderness.forge.data.client.WdBlockStateProvider;
import xyz.ketok.wilderness.forge.data.client.WdItemModelProvider;
import xyz.ketok.wilderness.forge.data.server.WdLootTableProvider;
import xyz.ketok.wilderness.forge.data.server.WdRecipeProvider;
import xyz.ketok.wilderness.forge.data.server.tags.WdBlockTagsProvider;
import xyz.ketok.wilderness.forge.data.server.tags.WdItemTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.ketok.wilderness.Wilderness;
import xyz.ketok.wilderness.forge.data.server.registry.WdRegistryProvider;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdDataGen {
    @SubscribeEvent
    public static void onDataSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        // Server
        {
            generator.addProvider(event.includeServer(), new WdRecipeProvider(output));
            generator.addProvider(event.includeServer(), new WdLootTableProvider(output));

            var blockTags = generator.addProvider(event.includeServer(), new WdBlockTagsProvider(output, lookupProvider, existingFileHelper));
            generator.addProvider(event.includeServer(), new WdItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

            generator.addProvider(event.includeServer(), new WdRegistryProvider(output, lookupProvider));
        }

        // Client
        {
            generator.addProvider(event.includeClient(), new WdBlockStateProvider(output, existingFileHelper));
            generator.addProvider(event.includeClient(), new WdItemModelProvider(output, existingFileHelper));
        }
    }
}
