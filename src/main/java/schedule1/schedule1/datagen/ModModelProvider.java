package schedule1.schedule1.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.block.custom.GrandaddyPurpleBushBlock;
import schedule1.schedule1.block.custom.GrandaddyPurpleCropBlock;
import schedule1.schedule1.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GRANDADDY_PURPLE_BLOCK);

        blockStateModelGenerator.registerCrop(ModBlocks.GRANDADDY_PURPLE_CROP, GrandaddyPurpleCropBlock.AGE, 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.GRANDADDY_PURPLE_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED,
                GrandaddyPurpleBushBlock.AGE, 0, 1, 2, 3, 4);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PLASTIC_BAGGY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASS_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_GRINDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FULL_GRINDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.HARVESTER, Models.GENERATED);
    }
}
