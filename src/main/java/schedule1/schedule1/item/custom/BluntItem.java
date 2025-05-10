package schedule1.schedule1.item.custom;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import schedule1.schedule1.component.ModDataComponentTypes;
import schedule1.schedule1.component.custom.ConsumableDrugComponent;
import schedule1.schedule1.effect.ModEffects;

public class BluntItem extends Item {
    public BluntItem(Settings settings) {
        super(settings);
    }

//    @Override
//    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
//        if(!world.isClient()){
//            user.getStatusEffect(ModEffects.HIGH);
//            user.getMainHandStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) user),
//                    item -> user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
//        }
//        return super.finishUsing(stack, world, user);
//    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient()) {
            ItemStack mainHand = user.getMainHandStack();
            ConsumableDrugComponent component = mainHand.getComponents().get(ModDataComponentTypes.CONSUMABLE_DRUG);
            for (ConsumableDrugComponent.StatusEffectEntry statusEffectEntry : component.effects()) {
                if (world.random.nextFloat() < statusEffectEntry.probability()) {
                    user.addStatusEffect(statusEffectEntry.effect());
                }
            }
            mainHand.decrementUnlessCreative(1, user);
            user.emitGameEvent(GameEvent.EAT);
        }

        return TypedActionResult.success(user.getMainHandStack());
    }

}
