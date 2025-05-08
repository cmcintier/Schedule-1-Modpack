package schedule1.schedule1.screen.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import schedule1.schedule1.screen.ModScreenHandlers;
import schedule1.schedule1.util.ModTags;



public class PackingStationScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final RecipeInputInventory input = new CraftingInventory(this, 2, 1);
    private final CraftingResultInventory result = new CraftingResultInventory();

    public PackingStationScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(blockPos));
    }

    public PackingStationScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.PACKING_STATION_SCREEN_HANDLER, syncId);
        this.inventory = ((Inventory) blockEntity);

        this.addSlot(new PaymentSlot(inventory, 0, 33, 35, ModTags.Items.PACKING_STATION_ITEMS));
        this.addSlot(new PaymentSlot(inventory, 1, 59, 35, ModTags.Items.PACKAGING_ITEMS));
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.input, this.result, 2, 116, 35));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int inv) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(inv);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (inv < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}

class PaymentSlot extends Slot {
    private final TagKey<Item> tagKey;

    public PaymentSlot(final Inventory inventory, final int index, final int x, final int y, TagKey<Item> tagKey) {
        super(inventory, index, x, y);
        this.tagKey = tagKey;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(this.tagKey);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
