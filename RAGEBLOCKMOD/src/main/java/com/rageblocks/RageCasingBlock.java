package com.rageblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class RageCasingBlock extends Block {

    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");

    public RageCasingBlock(Settings settings) {
        super(settings);

        setDefaultState(getStateManager().getDefaultState()
                .with(NORTH, false)
                .with(SOUTH, false)
                .with(EAST, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    private boolean isRageCasing(WorldAccess world, BlockPos pos) {
        return world.getBlockState(pos).isOf(ModBlocks.RAGE_CASING);
    }

    private BlockState updateConnections(WorldAccess world, BlockPos pos, BlockState state) {
        return state
                .with(NORTH, isRageCasing(world, pos.north()))
                .with(SOUTH, isRageCasing(world, pos.south()))
                .with(EAST, isRageCasing(world, pos.east()))
                .with(WEST, isRageCasing(world, pos.west()))
                .with(UP, isRageCasing(world, pos.up()))
                .with(DOWN, isRageCasing(world, pos.down()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return updateConnections(
                ctx.getWorld(),
                ctx.getBlockPos(),
                getDefaultState()
        );
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            WorldAccess world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        return updateConnections(world, pos, state);
    }
}