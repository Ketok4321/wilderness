package xyz.ketok.wilderness.forge.data.server;

import net.minecraft.data.recipes.RecipeCategory;
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
        shapeless(RecipeCategory.BUILDING_BLOCKS, WdBlocks.MOSSY_OAK_LOG.get())
                .requires(Blocks.OAK_LOG)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                .save(consumer);

        woodFromLogs(consumer, WdBlocks.MOSSY_OAK_WOOD.get(), WdBlocks.MOSSY_OAK_LOG.get());
    }
}
