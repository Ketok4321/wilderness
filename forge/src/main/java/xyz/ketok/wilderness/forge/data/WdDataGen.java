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

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdDataGen {
    @SubscribeEvent
    public static void onDataSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        // Server
        {
            generator.addProvider(event.includeServer(), new WdRecipeProvider(packOutput));
            generator.addProvider(event.includeServer(), new WdLootTableProvider(packOutput));

            WdBlockTagsProvider blockTags = new WdBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
            generator.addProvider(event.includeServer(), blockTags);
            generator.addProvider(event.includeServer(), new WdItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        }

        // Client
        {
            generator.addProvider(event.includeClient(), new WdBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(event.includeClient(), new WdItemModelProvider(packOutput, existingFileHelper));
        }
    }
}
