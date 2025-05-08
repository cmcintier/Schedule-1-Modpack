package schedule1.schedule1.item.custom;

//import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.Hand;
//import net.minecraft.util.TypedActionResult;
//import net.minecraft.world.World;
//import schedule1.schedule1.component.ModDataComponentTypes;
//import schedule1.schedule1.item.ModItems;
//import schedule1.schedule1.util.ModTags;
public class PrerollItem extends Item {
    public PrerollItem(Settings settings) {
        super(settings);
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        ItemStack mainHand = user.getMainHandStack();
//        ItemStack offHand = user.getOffHandStack();
//
//        if(!world.isClient) {
//            if (mainHand.isOf(ModItems.PREROLL)) {
//                if(offHand.isIn(ModTags.Items.GROUNDS) && offHand.getCount() >= 3){
//                    offHand.decrement(3);
//                    mainHand.decrement(1);
//                }
//            }
//        }
//        return super.use(world, user, hand);
//    }
}
