package com.rageblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.AbstractBlock;

public class PurifierBlock extends Block implements BlockEntityProvider {

    public PurifierBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(
            BlockPos pos,
            BlockState state
    ) {
        return new PurifierBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world,
            BlockState state,
            BlockEntityType<T> type
    ) {
        return type == ModBlockEntities.PURIFIER
                ? (world1, pos, state1, blockEntity) ->
                        ((PurifierBlockEntity) blockEntity).tick()
                : null;
    }

    @Override
    public ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit
    ) {

        if (!world.isClient) {

            BlockEntity blockEntity =
                    world.getBlockEntity(pos);

            if (blockEntity instanceof PurifierBlockEntity purifier) {
                player.openHandledScreen(purifier);
            }
        }

        return ActionResult.SUCCESS;
    }
}