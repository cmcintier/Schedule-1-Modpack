package schedule1.schedule1.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup WEED_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(Schedule1.MOD_ID, "weed_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.GRANDADDY_PURPLE))
                    .displayName(Text.translatable("itemgroup.schedule1.weed_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.GRANDADDY_PURPLE);
                        entries.add(ModItems.PLASTIC_BAGGY);
                        entries.add(ModItems.GLASS_JAR);
//                        entries.add(ModItems.EMPTY_GRINDER);
//                        entries.add(ModItems.FULL_GRINDER);
                        entries.add(ModItems.GRINDER);
                        entries.add(ModItems.BUSH_SEED_GRANDADDY_PURPLE);
                        entries.add(ModItems.HARVESTER);
                        entries.add(ModItems.BLUNT);
                        entries.add(ModItems.BONG);
                        entries.add(ModItems.GRANDADDY_PURPLE_GROUNDS);
                        entries.add(ModItems.PREROLL);

                        entries.add(ModBlocks.GRANDADDY_PURPLE_BLOCK);
                        entries.add(ModBlocks.PACKING_STATION_BLOCK);
                    })
                    .build());

    public static void registerItemGroups(){
        Schedule1.LOGGER.info("Registering Item Groups for " + Schedule1.MOD_ID);
    }
}
