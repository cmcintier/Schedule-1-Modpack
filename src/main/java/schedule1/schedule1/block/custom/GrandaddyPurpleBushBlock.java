package schedule1.schedule1.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.Schedule1DataGenerator;
import schedule1.schedule1.item.ModItems;

public class GrandaddyPurpleBushBlock extends PlantBlock implements Fertilizable{
    public static final MapCodec<GrandaddyPurpleBushBlock> CODEC = createCodec(GrandaddyPurpleBushBlock::new);
    private static final float MIN_MOVEMENT_FOR_DAMAGE = 0.003F;
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = Properties.AGE_4;
    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(6.0, 0.0, 3.0, 10.0, 8.0, 10.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(6.0, 0.0, 3.0, 10.0, 16.0, 10.0);
    private static final IntProperty MAX_YIELD = IntProperty.of("max_yield", 7, 12);
    private static final IntProperty CURRENT_YIELD = IntProperty.of("current_yield", 0, 12);
    private static final IntProperty HARVESTED = IntProperty.of("harvested", 0, 12);


    @Override
    public MapCodec<GrandaddyPurpleBushBlock> getCodec() {
        return CODEC;
    }

    public GrandaddyPurpleBushBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
        this.setDefaultState(this.stateManager.getDefaultState().with(MAX_YIELD, 7));
        this.setDefaultState(this.stateManager.getDefaultState().with(CURRENT_YIELD, 0));
        this.setDefaultState(this.stateManager.getDefaultState().with(HARVESTED, 0));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) == 0) {
            return SMALL_SHAPE;
        } else {
            return state.get(AGE) < MAX_AGE ? LARGE_SHAPE : super.getOutlineShape(state, world, pos, context);
        }
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.GRANDADDY_PURPLE);
    }

//    @Override
//    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
//      if(!world.isClient) {
//          world.setBlockState(pos, state.with(MAX_YIELD, (int) Math.round(Math.random() * 5) + 7));
//      }
//    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = state.get(AGE);
        boolean bl = i == MAX_AGE;
        if(!bl && stack.isOf(Items.BONE_MEAL)){
            return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        } else if (bl && stack.isOf(ModItems.HARVESTER)) {
            // int j = 1 + world.random.nextInt(2);
            // If max age, drop j + 1
            // dropStack(world, pos, new ItemStack(ModItems.GRANDADDY_PURPLE, j + (bl ? 1 : 0)));

            if (!world.isClient) {
                dropStack(world, pos, new ItemStack(ModItems.GRANDADDY_PURPLE, 1));
                // incrementing
                world.setBlockState(pos, state.with(HARVESTED, state.get(HARVESTED) + 1));
//                Schedule1.LOGGER.info(String.valueOf(world.getBlockState(pos).get(HARVESTED)));
//                Schedule1.LOGGER.info(String.valueOf(world.getBlockState(pos).get(CURRENT_YIELD)));
                //Schedule1.LOGGER.info(String.valueOf(state.getBlock().toString()));

                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
//                Schedule1.LOGGER.info("Harvested: {} Max Yield: {}", world.getBlockState(pos).get(HARVESTED), world.getBlockState(pos).get(MAX_YIELD));

                if (world.getBlockState(pos).get(HARVESTED).equals(state.get(MAX_YIELD))) {
                    BlockState blockState = state.with(AGE, 1);
                    world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
                }
            }


            return ItemActionResult.success(world.isClient);
        } else if (i == MAX_AGE - 1 && state.get(HARVESTED) < state.get(CURRENT_YIELD)) {
            if(!world.isClient) {
                dropStack(world, pos, new ItemStack(ModItems.GRANDADDY_PURPLE, 1));
                world.setBlockState(pos, state.with(HARVESTED, state.get(HARVESTED) + 1));
                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            }
                return ItemActionResult.success(world.isClient);
        }


        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(MAX_YIELD);
        builder.add(CURRENT_YIELD);
        builder.add(HARVESTED);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {

        int i = Math.min(MAX_AGE, state.get(AGE) + 1);
        //AGE State (state.with(AGE, i) not updating properly and setBlockState is updating routine
        //Why was AGE State updated inside setBlockState
        if(!world.isClient) {
            if (i == MAX_AGE - 1) {
                Schedule1.LOGGER.info("[Stage3] (MAX_AGE - 1) Hit");
                world.setBlockState(pos, state.with(CURRENT_YIELD, 2));
            } else if (i == MAX_AGE) {
                Schedule1.LOGGER.info("[Stage4] Max Age Hit");
                world.setBlockState(pos, state.with(MAX_YIELD, (int)Math.round(Math.random() * 5) + 7));
            }
            //
            world.setBlockState(pos, world.getBlockState(pos).with(AGE, i), Block.NOTIFY_LISTENERS);
            // get updated state world.getBlockState(pos).get(<STATE>)
            Schedule1.LOGGER.info("Grow stage " + world.getBlockState(pos).get(AGE) + " " + world.getBlockState(pos).get(CURRENT_YIELD));
        }

    }
}

