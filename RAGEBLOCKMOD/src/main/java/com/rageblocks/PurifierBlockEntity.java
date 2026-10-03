package com.rageblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class PurifierBlockEntity extends BlockEntity
        implements Inventory, NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(3, ItemStack.EMPTY);

    private int progress = 0;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public PurifierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PURIFIER, pos, state);
    }

    // =========================
    // INVENTORY
    // =========================

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result =
                Inventories.splitStack(inventory, slot, amount);

        if (!result.isEmpty()) {
            markDirty();
        }

        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result =
                Inventories.removeStack(inventory, slot);

        if (!result.isEmpty()) {
            markDirty();
        }

        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);

        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }

        markDirty();
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (world == null || world.getBlockEntity(pos) != this) {
            return false;
        }

        return player.squaredDistanceTo(
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5
        ) <= 64.0;
    }

    // =========================
    // SCREEN
    // =========================

    @Override
    public Text getDisplayName() {
        return Text.literal("Purifier");
    }

    @Override
    public ScreenHandler createMenu(
            int syncId,
            PlayerInventory playerInventory,
            PlayerEntity player
    ) {
        return new PurifierScreenHandler(
                syncId,
                playerInventory,
                this
        );
    }

    // =========================
    // NBT
    // =========================

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        Inventories.writeNbt(nbt, inventory);

        nbt.putInt("Progress", progress);
        nbt.putInt("FuelTime", fuelTime);
        nbt.putInt("MaxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        Inventories.readNbt(nbt, inventory);

        progress = nbt.getInt("Progress");
        fuelTime = nbt.getInt("FuelTime");
        maxFuelTime = nbt.getInt("MaxFuelTime");
    }

    // =========================
    // PROCESSING
    // =========================

    public int getProgress() {
        return progress;
    }

    public int getFuelTime() {
        return fuelTime;
    }

    public int getMaxFuelTime() {
        return maxFuelTime;
    }

    public void tick() {

        if (world == null || world.isClient) {
            return;
        }

        ItemStack input = inventory.get(0);
        ItemStack fuel = inventory.get(1);
        ItemStack output = inventory.get(2);

        // =========================
        // BLAZE POWDER FUEL
        // =========================

        if (fuelTime <= 0) {

            if (fuel.isOf(Items.BLAZE_POWDER)) {

                fuelTime = 100;
                maxFuelTime = 100;

                fuel.decrement(1);

                markDirty();
            }
        }

        // =========================
        // INPUT CHECK
        // =========================

        if (!input.isOf(ModItems.UNPURIFIED_RAGE)) {
            progress = 0;
            return;
        }

        // Don't overwrite output
        if (!output.isEmpty()) {
            return;
        }

        // No fuel
        if (fuelTime <= 0) {
            return;
        }

        // Consume fuel
        fuelTime--;

        // =========================
        // PROCESSING
        // =========================

        progress++;

        // =========================
        // COMPLETION
        // =========================

        if (progress >= 100) {

            input.decrement(1);

            inventory.set(
                    2,
                    new ItemStack(ModItems.PURE_RAGE)
            );

            progress = 0;

            // Completion DING only
            world.playSound(
                    null,
                    pos,
                    ModSounds.PURIFIER_COMPLETE,
                    net.minecraft.sound.SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );

            markDirty();
        }

        if (progress % 20 == 0) {
            markDirty();
        }
    }
}