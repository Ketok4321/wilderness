package xyz.ketok.wilderness.forge.data.server;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import xyz.ketok.wilderness.other.WdHooks;
import xyz.ketok.wilderness.registry.WdBlocks;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;
import static xyz.ketok.wilderness.forge.data.WdDataGenUtil.*;

public class WdRecipeProvider extends RecipeProvider {
    public WdRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        for (var variant : WdHooks.OVERGROWN_VARIANTS.entrySet()) {
            var normal = variant.getKey();
            var overgrown = variant.getValue();

            var location = overgrown.getId();
            if (location.getPath().endsWith("_wood")) {
                location = suffix(location, "_from_moss");
            }

            shapeless(RecipeCategory.BUILDING_BLOCKS, overgrown.get())
                    .requires(normal)
                    .requires(Blocks.MOSS_BLOCK)
                    .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                    .group("overgrown_log")
                    .save(consumer, location);
        }

        woodFromLogs(consumer, WdBlocks.OVERGROWN_OAK_WOOD.get(), WdBlocks.OVERGROWN_OAK_LOG.get());
        woodFromLogs(consumer, WdBlocks.OVERGROWN_BIRCH_WOOD.get(), WdBlocks.OVERGROWN_BIRCH_LOG.get());
        woodFromLogs(consumer, WdBlocks.OVERGROWN_SPRUCE_WOOD.get(), WdBlocks.OVERGROWN_SPRUCE_LOG.get());
    }
}
