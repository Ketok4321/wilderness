package xyz.ketok.wilderness.forge.data.server;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.jetbrains.annotations.NotNull;
import xyz.ketok.wilderness.forge.data.WdDataGenUtil;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import xyz.ketok.wilderness.registry.WdBlocks;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static xyz.ketok.wilderness.forge.data.WdDataGenUtil.*;

public class WdLootTableProvider extends LootTableProvider {
    public WdLootTableProvider(PackOutput packOutput) {
        super(packOutput, Set.of(), List.of(new SubProviderEntry(WdBlockLootSubProvider::new, LootContextParamSets.BLOCK)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {

    }

    private static class WdBlockLootSubProvider extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

        protected WdBlockLootSubProvider() {
            super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        protected void generate() {
            WdDataGenUtil.wdBlockItems().forEach(this::dropSelf); // These can be overriden later

            add(WdBlocks.SHELF_MUSHROOM.get(), createShearsOnlyDrop(WdBlocks.SHELF_MUSHROOM.get().asItem())); //TODO: Forge tool actions + fabric equivalent
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return entries(WdBlocks.BLOCKS)::iterator;
        }
    }
}
