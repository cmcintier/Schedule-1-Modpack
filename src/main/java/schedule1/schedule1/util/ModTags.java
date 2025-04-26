package schedule1.schedule1.util;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;

public class ModTags {

    public static class Blocks{
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Schedule1.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<net.minecraft.item.Item> SMOKEABLE_ITEMS = createTag("smokeable_items");
        private static TagKey<net.minecraft.item.Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Schedule1.MOD_ID, name));
        }
    }
}
