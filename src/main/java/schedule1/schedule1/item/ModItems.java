package schedule1.schedule1.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.component.ModDataComponentTypes;
import schedule1.schedule1.item.custom.*;

import java.util.List;


public class ModItems {
    public static final Item GRANDADDY_PURPLE = registerItem("grandaddy_purple",
            new Item(new Item.Settings().component(ModDataComponentTypes.STRAIN, "grandaddy_purple")){
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    if(Screen.hasShiftDown()){
                        tooltip.add(Text.translatable("tooltip.schedule1.grandaddy_purple.shift_down.line1"));
                        tooltip.add(Text.literal("Strain: " + stack.get(ModDataComponentTypes.STRAIN)));
                    } else {
                        tooltip.add(Text.translatable("tooltip.schedule1.grandaddy_purple.line1"));
                        tooltip.add(Text.translatable("tooltip.schedule1.grandaddy_purple.line2"));
                    }
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item PLASTIC_BAGGY = registerItem("plastic_baggy", new Item(new Item.Settings()));
    public static final Item GLASS_JAR = registerItem("glass_jar", new Item(new Item.Settings()));
    public static final Item GRINDER = registerItem("grinder", new GrinderItem(new Item.Settings()
            .component(ModDataComponentTypes.STRAIN, "")));
    public static final Item HARVESTER = registerItem("harvester", new HarvesterItem(new Item.Settings().maxDamage(32)));
    public static final Item BLUNT = registerItem("blunt", new BluntItem(new Item.Settings()
            .component(ModDataComponentTypes.STRAIN, "")));
    public static final Item BONG = registerItem("bong", new BongItem(new Item.Settings()));
    public static final Item GRANDADDY_PURPLE_GROUNDS = registerItem("grandaddy_purple_grounds", new Item(new Item.Settings()));
    public static final Item PREROLL = registerItem("preroll", new PrerollItem(new Item.Settings()));

    public static final Item BUSH_SEED_GRANDADDY_PURPLE = registerItem("bush_seed_grandaddy_purple",
            new AliasedBlockItem(ModBlocks.GRANDADDY_PURPLE_BUSH, new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Schedule1.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Schedule1.LOGGER.info("Registering Mod Items for " + Schedule1.MOD_ID);

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
//
//        });
    }
}
