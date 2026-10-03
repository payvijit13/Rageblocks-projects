package com.rageblocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class PurifierScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    // Constructor used by ModScreenHandlers
    public PurifierScreenHandler(
            int syncId,
            PlayerInventory playerInventory
    ) {
        this(
                syncId,
                playerInventory,
                new net.minecraft.inventory.SimpleInventory(3)
        );
    }

    // Constructor used by the actual Purifier block
    public PurifierScreenHandler(
            int syncId,
            PlayerInventory playerInventory,
            Inventory inventory
    ) {
        super(
                ModScreenHandlers.PURIFIER_SCREEN_HANDLER,
                syncId
        );

        this.inventory = inventory;

        // Input
        this.addSlot(
                new Slot(
                        inventory,
                        0,
                        56,
                        35
                )
        );

        // Blaze Powder fuel
        this.addSlot(
                new Slot(
                        inventory,
                        1,
                        86,
                        35
                )
        );

        // Output
        this.addSlot(
                new Slot(
                        inventory,
                        2,
                        116,
                        35
                )
        );

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(
                        new Slot(
                                playerInventory,
                                col + row * 9 + 9,
                                8 + col * 18,
                                84 + row * 18
                        )
                );
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(
                    new Slot(
                            playerInventory,
                            col,
                            8 + col * 18,
                            142
                    )
            );
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(
            PlayerEntity player,
            int slot
    ) {
        return ItemStack.EMPTY;
    }
}