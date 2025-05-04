package schedule1.schedule1.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class BluntItem extends Item {
    public BluntItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(!world.isClient()){
            user.getMainHandStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) user),
                    item -> user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
        }
        return super.finishUsing(stack, world, user);
    }
}
