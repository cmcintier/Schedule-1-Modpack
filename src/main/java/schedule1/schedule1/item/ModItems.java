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
    public static final Item PLASTIC_BAGGY = registerItem("plastic_baggy", new Item(new Item.Settings()));
    public static final Item GLASS_JAR = registerItem("glass_jar", new Item(new Item.Settings()));
    public static final Item EMPTY_GRINDER = registerItem("empty_grinder", new Item(new Item.Settings()));
    public static final Item FULL_GRINDER = registerItem("full_grinder", new Item(new Item.Settings()));
    public static final Item HARVESTER = registerItem("harvester", new HarvesterItem(new Item.Settings().maxDamage(32)));

    public static final Item SEED_GRANDADDY_PURPLE = registerItem("seed_grandaddy_purple",
            new AliasedBlockItem(ModBlocks.GRANDADDY_PURPLE_CROP, new Item.Settings()));

    public static final Item BUSH_SEED_GRANDADDY_PURPLE = registerItem("bush_seed_grandaddy_purple",
            new AliasedBlockItem(ModBlocks.GRANDADDY_PURPLE_BUSH, new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Schedule1.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Schedule1.LOGGER.info("Registering Mod Items for " + Schedule1.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
    }
}
