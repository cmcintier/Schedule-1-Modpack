package schedule1.schedule1.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.block.ModBlocks;


public class ModItems {
    public static final Item GRANDADDY_PURPLE = registerItem("grandaddy_purple", new Item(new Item.Settings()));

    public static final Item SEED_GRANDADDY_PURPLE = registerItem("seed_grandaddy_purple",
            new AliasedBlockItem(ModBlocks.GRANDADDY_PURPLE_CROP, new Item.Settings()));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Schedule1.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Schedule1.LOGGER.info("Registering Mod Items for " + Schedule1.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(GRANDADDY_PURPLE);
            entries.add(SEED_GRANDADDY_PURPLE);
        });
    }
}
