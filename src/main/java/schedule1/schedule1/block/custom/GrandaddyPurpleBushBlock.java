package schedule1.schedule1.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
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
import schedule1.schedule1.item.ModItems;

public class GrandaddyPurpleBushBlock extends PlantBlock implements Fertilizable{
    public static final MapCodec<GrandaddyPurpleBushBlock> CODEC = createCodec(GrandaddyPurpleBushBlock::new);
    private static final float MIN_MOVEMENT_FOR_DAMAGE = 0.003F;
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = Properties.AGE_4;
    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(6.0, 0.0, 3.0, 10.0, 8.0, 10.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(6.0, 0.0, 3.0, 10.0, 16.0, 10.0);
    private int MAX_YIELD;
    private int current_yield;
    private int harvested;


    @Override
    public MapCodec<GrandaddyPurpleBushBlock> getCodec() {
        return CODEC;
    }

    public GrandaddyPurpleBushBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.MAX_YIELD = (int)Math.round(Math.random() * 5) + 7;
        this.current_yield = 0;
        this.harvested = 0;
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
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

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = state.get(AGE);
        boolean bl = i == 3;
        return !bl && stack.isOf(Items.BONE_MEAL)
                ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                : super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int i = state.get(AGE);
        if (i == MAX_AGE) {
            // int j = 1 + world.random.nextInt(2);
            // If max age, drop j + 1
            // dropStack(world, pos, new ItemStack(ModItems.GRANDADDY_PURPLE, j + (bl ? 1 : 0)));
            dropStack(world, pos, new ItemStack(ModItems.GRANDADDY_PURPLE, 1));
            if(!world.isClient) {
                this.harvested++;
                Schedule1.LOGGER.info(String.valueOf(this.harvested));
                Schedule1.LOGGER.info(String.valueOf(this.MAX_YIELD));
                Schedule1.LOGGER.info(String.valueOf(state.getBlock().toString()));
            }

            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            if (this.harvested == this.MAX_YIELD) {
                Schedule1.LOGGER.info("Break");
                BlockState blockState = state.with(AGE, 1);
                world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            }
            return ActionResult.success(world.isClient);
        } else if (i == MAX_AGE - 1 && this.harvested <= this.current_yield) {
            dropStack(world, pos, new ItemStack(ModItems.GRANDADDY_PURPLE, 1));
            if(!world.isClient) {
                Schedule1.LOGGER.info("Young");
                this.harvested++;
            }
            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
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
        world.setBlockState(pos, state.with(AGE, i), Block.NOTIFY_LISTENERS);
        if(state.get(AGE) == MAX_AGE - 1) {
            current_yield += 2;
            Schedule1.LOGGER.info(String.valueOf("Grow stage 3 " + current_yield));
        }
        else if(state.get(AGE) == MAX_AGE)
            current_yield += MAX_YIELD - current_yield;
    }
}

