package schedule1.schedule1.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;


public record PackingStationRecipe(Ingredient productInput, Ingredient packagingInput, ItemStack output) implements Recipe<PackingStationRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients(){
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.productInput);
        list.add(this.packagingInput);
        return list;
    }

    // read Recipe JSON files --> new PackingStationRecipe

    @Override
    public boolean matches(PackingStationRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return productInput.test(input.getStackInSlot(0)) && packagingInput.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(PackingStationRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PACKING_STATION_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PACKING_STATION_TYPE;
    }

    public static class Serializer implements RecipeSerializer<PackingStationRecipe> {
        public static final MapCodec<PackingStationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient1").forGetter(PackingStationRecipe::productInput),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient2").forGetter(PackingStationRecipe::packagingInput),
                ItemStack.CODEC.fieldOf("result").forGetter(PackingStationRecipe::output)
        ).apply(inst, PackingStationRecipe::new));

        public static final PacketCodec<RegistryByteBuf, PackingStationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, PackingStationRecipe::productInput,
                        Ingredient.PACKET_CODEC, PackingStationRecipe::packagingInput,
                        ItemStack.PACKET_CODEC, PackingStationRecipe::output,
                        PackingStationRecipe::new);

        @Override
        public MapCodec<PackingStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, PackingStationRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
