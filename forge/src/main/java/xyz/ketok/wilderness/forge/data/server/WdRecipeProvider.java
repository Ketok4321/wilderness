package xyz.ketok.wilderness.forge.data.server;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import xyz.ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class WdRecipeProvider extends RecipeProvider {
    public WdRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, WdBlocks.OVERGROWN_OAK_LOG.get())
                .requires(Blocks.OAK_LOG)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                .group("overgrown_log")
                .save(consumer);

        woodFromLogs(consumer, WdBlocks.OVERGROWN_OAK_WOOD.get(), WdBlocks.OVERGROWN_OAK_LOG.get());

        shapeless(RecipeCategory.BUILDING_BLOCKS, WdBlocks.OVERGROWN_BIRCH_LOG.get())
                .requires(Blocks.BIRCH_LOG)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                .group("overgrown_log")
                .save(consumer);

        woodFromLogs(consumer, WdBlocks.OVERGROWN_BIRCH_WOOD.get(), WdBlocks.OVERGROWN_BIRCH_LOG.get());

        shapeless(RecipeCategory.BUILDING_BLOCKS, WdBlocks.OVERGROWN_SPRUCE_LOG.get())
                .requires(Blocks.SPRUCE_LOG)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                .group("overgrown_log")
                .save(consumer);

        woodFromLogs(consumer, WdBlocks.OVERGROWN_SPRUCE_WOOD.get(), WdBlocks.OVERGROWN_SPRUCE_LOG.get());
    }
}
