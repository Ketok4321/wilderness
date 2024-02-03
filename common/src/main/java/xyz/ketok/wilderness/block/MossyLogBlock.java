package xyz.ketok.wilderness.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class MossyLogBlock extends RotatedPillarBlock implements BonemealableBlock {
    public MossyLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        Direction.Axis axis = state.getValue(AXIS);
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);

        BlockPos growPos = pos.relative(direction);

        BlockState growBlock = level.getBlockState(growPos);

        if(growBlock.getBlock() == Blocks.OAK_LOG){
            level.setBlock(growPos, transferAllBlockStates(growBlock, this.defaultBlockState()), 2);
        }
        else if (growBlock.getBlock() == this){
            performBonemeal(level, random, growPos, growBlock);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static BlockState transferAllBlockStates(BlockState initial, BlockState after) {
        BlockState block = after;
        for (Property property : initial.getBlock().getStateDefinition().getProperties()) {
            if (after.hasProperty(property) && initial.getValue(property) != null) {
                block = block.setValue(property, initial.getValue(property));
            }
        }
        return block;
    }
}