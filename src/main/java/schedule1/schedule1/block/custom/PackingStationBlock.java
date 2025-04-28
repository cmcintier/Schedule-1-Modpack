//package schedule1.schedule1.block.custom;
//
//import com.mojang.serialization.MapCodec;
//import net.minecraft.block.BlockEntityProvider;
//import net.minecraft.block.BlockRenderType;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.BlockWithEntity;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.util.ItemScatterer;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class PackingStationBlock extends BlockWithEntity implements BlockEntityProvider {
//
//    public static final MapCodec<PackingStationBlock> CODEC = PackingStationBlock.createCodec(PackingStationBlock::new);
//
//    public PackingStationBlock(Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    protected MapCodec<? extends BlockWithEntity> getCodec() {
//        return CODEC;
//    }
//
//    @Override
//    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return
//    }
//
//    @Override
//    protected BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.MODEL;
//    }
//
//    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved){
//        if(state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof PackingStationBlockEntity) {
//                ItemScatterer.spawn(world, pos, ((PackingStationBlockEntity) blockEntity));
//            }
//        }
//    }
//}
