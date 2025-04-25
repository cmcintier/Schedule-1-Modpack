package schedule1.schedule1.init;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class ItemInit {
    public static final Item Nugget = register("nugget");
    public static Item register(String name){
        Identifier id = Schedule1.id("nugget");
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item.Settings settings = new Item.Settings()
                // If your item is based on a block
                .useBlockPrefixedTranslationKey()
                .registryKey(key);

        return Registry.register(Registries.ITEM, key, new Item(settings));
    }

    public static void load() {}
}
