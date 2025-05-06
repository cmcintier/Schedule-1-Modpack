package schedule1.schedule1.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.component.ModDataComponentTypes;

import java.util.List;

public class GrinderItem extends Item {

    public GrinderItem(Settings settings) {
        super(settings);

    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack offHandStack = user.getOffHandStack();
        ItemStack mainHandStack = user.getStackInHand(hand);

        // Don't do anything on the client
        if (!world.isClient()) {
            // Read the current count and increase it by one
            int count = mainHandStack.getOrDefault(ModDataComponentTypes.CLICK_COUNT_COMPONENT, 0);
            mainHandStack.set(ModDataComponentTypes.CLICK_COUNT_COMPONENT, ++count);
            return TypedActionResult.success(mainHandStack);
        }

        // Return the original stack
        return TypedActionResult.success(mainHandStack);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type){
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.schedule1.grinder.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.schedule1.grinder"));
        }
        if(stack.get(ModDataComponentTypes.CLICK_COUNT_COMPONENT) != null){
            tooltip.add(Text.literal("Clicked: " + stack.get(ModDataComponentTypes.CLICK_COUNT_COMPONENT)));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
