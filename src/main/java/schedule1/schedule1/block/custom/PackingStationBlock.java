package schedule1.schedule1.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import schedule1.schedule1.block.entity.custom.PackingStationBlockEntity;

public class PackingStationBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<PackingStationBlock> CODEC = PackingStationBlock.createCodec(PackingStationBlock::new);

    public PackingStationBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PackingStationBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved){
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PackingStationBlockEntity) {
                ItemScatterer.spawn(world, pos, ((PackingStationBlockEntity) blockEntity));
            }
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world.getBlockEntity(pos) instanceof PackingStationBlockEntity packingStationBlockEntity){
            if(!world.isClient){
                player.openHandledScreen(packingStationBlockEntity);
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
