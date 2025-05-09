package schedule1.schedule1.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import schedule1.schedule1.item.ModItems;
import schedule1.schedule1.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.SMOKEABLE_ITEMS)
                .add(ModItems.BONG)
                .add(ModItems.BLUNT);
        getOrCreateTagBuilder(ModTags.Items.WEED_STRAINS)
                .add(ModItems.GRANDADDY_PURPLE);
        getOrCreateTagBuilder(ModTags.Items.GROUNDS)
                .add(ModItems.GRANDADDY_PURPLE_GROUNDS);
        getOrCreateTagBuilder(ModTags.Items.PACKING_STATION_ITEMS)
                .add(ModItems.GRANDADDY_PURPLE)
                .add(ModItems.GRANDADDY_PURPLE_GROUNDS);
        getOrCreateTagBuilder(ModTags.Items.PACKAGING_ITEMS)
                .add(ModItems.PLASTIC_BAGGY)
                .add(ModItems.GLASS_JAR)
                .add(ModItems.PREROLL);
    }
}
