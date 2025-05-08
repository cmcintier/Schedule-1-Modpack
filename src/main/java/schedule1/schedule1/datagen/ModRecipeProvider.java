package schedule1.schedule1.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        //offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.GRANDADDY_PURPLE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRANDADDY_PURPLE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GRANDADDY_PURPLE_BLOCK)
                .pattern("GGG")
                .pattern("GGG")
                .pattern("GGG")
                .input('G',ModItems.GRANDADDY_PURPLE)
                .criterion(hasItem(ModItems.GRANDADDY_PURPLE), conditionsFromItem(ModItems.GRANDADDY_PURPLE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GRANDADDY_PURPLE, 9)
                .input(ModBlocks.GRANDADDY_PURPLE_BLOCK)
                .criterion(hasItem(ModBlocks.GRANDADDY_PURPLE_BLOCK), conditionsFromItem(ModBlocks.GRANDADDY_PURPLE_BLOCK))
                .offerTo(recipeExporter, Identifier.of(Schedule1.MOD_ID, "grandaddy_purple_from_grandaddy_purple_block-"));













































































































































































































































































    }
}
