package schedule1.schedule1.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;

public class ModRecipes {
    public static final RecipeSerializer<PackingStationRecipe> PACKING_STATION_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Schedule1.MOD_ID, "packing_station"),
            new PackingStationRecipe.Serializer());
    public static final RecipeType<PackingStationRecipe> PACKING_STATION_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Schedule1.MOD_ID, "packing_station"), new RecipeType<PackingStationRecipe>() {
                @Override
                public String toString() {
                    return "packing_station";
                }
            });

    public static void registerRecipes() {
        Schedule1.LOGGER.info("Registering Custom Recipes for " + Schedule1.MOD_ID);
    }
}
