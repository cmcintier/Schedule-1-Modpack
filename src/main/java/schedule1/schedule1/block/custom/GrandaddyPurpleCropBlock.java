package schedule1.schedule1.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import schedule1.schedule1.item.ModItems;

public class GrandaddyPurpleCropBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = IntProperty.of("age", 0, 4);

    public GrandaddyPurpleCropBlock(Settings settings){
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.SEED_GRANDADDY_PURPLE;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(AGE);
    }
}
