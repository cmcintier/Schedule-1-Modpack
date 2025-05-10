package schedule1.schedule1.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record PackingStationRecipeInput(ItemStack productInput, ItemStack packagingInput) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot){
        return slot == 0 ? productInput : packagingInput;
    }

    public int getSize(){
        return 2;
    }
}
