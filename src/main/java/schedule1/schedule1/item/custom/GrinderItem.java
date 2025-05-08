package schedule1.schedule1.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import schedule1.schedule1.component.ModDataComponentTypes;
import schedule1.schedule1.item.ModItems;
import schedule1.schedule1.util.ModTags;

import java.util.List;
import java.util.Objects;

public class GrinderItem extends Item {

    public GrinderItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack offHandStack = user.getOffHandStack();
        ItemStack grinder = user.getStackInHand(hand);

        // Don't do anything on the client
        if (!world.isClient()) {
            if(Objects.requireNonNull(grinder.getComponents().get(ModDataComponentTypes.STRAIN)).isEmpty()){
                if (offHandStack.isIn(ModTags.Items.WEED_STRAINS)) {
                    // Read the current count and increase it by one
                    String offHandStrain = offHandStack.get(ModDataComponentTypes.STRAIN);
                    grinder.set(ModDataComponentTypes.STRAIN, offHandStrain);
                    offHandStack.decrement(1);
                    return TypedActionResult.success(grinder);
                }
            } else if (!Objects.requireNonNull(grinder.getComponents().get(ModDataComponentTypes.STRAIN)).isEmpty()) {
                int count = grinder.getOrDefault(ModDataComponentTypes.GRIND_STATE, 0);
                if(count < 9) {
                    grinder.set(ModDataComponentTypes.GRIND_STATE, ++count);
                    return TypedActionResult.success(grinder);
                }else if (count == 9){
                    user.giveItemStack(ModItems.GRANDADDY_PURPLE_GROUNDS.getDefaultStack());
                    grinder.set(ModDataComponentTypes.GRIND_STATE, 0);
                    grinder.set(ModDataComponentTypes.STRAIN, "");
                }
            }

        }
        return TypedActionResult.pass(grinder);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type){
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.schedule1.grinder.shift_down.line1"));
            tooltip.add(Text.literal("Strain: " + stack.get(ModDataComponentTypes.STRAIN)));
            if(stack.get(ModDataComponentTypes.GRIND_STATE) != null){
                tooltip.add(Text.literal("Grind%: " + stack.get(ModDataComponentTypes.GRIND_STATE) * 10));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.schedule1.grinder.line1"));
            tooltip.add(Text.translatable("tooltip.schedule1.grinder.line2"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
