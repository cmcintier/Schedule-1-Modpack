package schedule1.schedule1.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class HarvesterItem extends Item {
    public static final Map<Block, Block> HARVESTER_MAP =
            Map.of(
                    Blocks.GRASS_BLOCK, Blocks.VINE
            );


    public HarvesterItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(HARVESTER_MAP.containsKey(clickedBlock)){
            if(!world.isClient()){
                world.setBlockState(context.getBlockPos(), HARVESTER_MAP.get(clickedBlock).getDefaultState());
                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS);
            }
        }


        return ActionResult.SUCCESS;
    }
}
