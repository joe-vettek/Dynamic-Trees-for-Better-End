package xueluoanping.dtbetterend.systems.featuregen;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.betterx.bclib.blocks.BaseVineBlock;
import org.betterx.bclib.blocks.BlockProperties;
import org.betterx.betterend.blocks.TenaneaFlowersBlock;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

// have to fix some
public class FlowerVinesGenFeature extends com.ferreusveritas.dynamictrees.systems.genfeature.VinesGenFeature {

    public FlowerVinesGenFeature(ResourceLocation registryName) {
        super(registryName);
    }


    // This is WIP (and isn't needed in the base mod anyway, as well as the fact that there's almost certainly a better way of doing this).
    private BlockPos findGround(LevelAccessor level, BlockPos vinePos) {
        BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(vinePos.getX(), vinePos.getY(), vinePos.getZ());
        do {
            mPos.move(Direction.DOWN);
            if (mPos.getY() <= 0) {
                return BlockPos.ZERO;
            }
        } while (level.isEmptyBlock(vinePos) || level.getBlockState(vinePos).getBlock() instanceof DynamicLeavesBlock);

        return mPos.above();
    }


    @Override
    protected void addVerticalVines(GenFeatureConfiguration configuration, LevelAccessor level, Species species, BlockPos rootPos, BlockPos branchPos, SafeChunkBounds safeBounds, boolean worldgen) {
        // Uses fruit ray trace method to grab a position under the tree's leaves.
        BlockPos vinePos = CoordUtils.getRayTraceFruitPos(level, species, rootPos, branchPos, safeBounds);

        if (!safeBounds.inBounds(vinePos, true)) {
            return;
        }

        if (configuration.get(VINE_TYPE) == VineType.FLOOR) {
            vinePos = this.findGround(level, vinePos);
        }

        if (vinePos == BlockPos.ZERO) {
            return;
        }

        var block = configuration.get(BLOCK);

        if (block == Blocks.AIR)
            return;

        if (!level.isEmptyBlock(vinePos)) {
            while (level.getBlockState(vinePos).is(block)) {
                vinePos = vinePos.below();
            }
        }

        if (level.isEmptyBlock(vinePos)) {
            long seed = CoordUtils.coordHashCode(vinePos, 3);
            if (level instanceof ServerLevel serverLevel)
                seed += serverLevel.getSeed();

            var random = new Random(seed);
            if ((random.nextFloat() <= 0.75) && !worldgen) return;

            // var block = configuration.get(BLOCK);
            var nowBlockState = configuration.get(BLOCK).defaultBlockState();

            if (nowBlockState.hasProperty(BaseVineBlock.SHAPE)) {
                level.setBlock(vinePos, nowBlockState.setValue(BaseVineBlock.SHAPE,
                        BlockProperties.TripleShape.TOP), Block.UPDATE_ALL);
                nowBlockState = nowBlockState.
                        setValue(BaseVineBlock.SHAPE,
                                BlockProperties.TripleShape.MIDDLE);
            }


            this.placeVines(level, vinePos.below(), nowBlockState,
                    configuration.get(MAX_LENGTH),
                    configuration.getAsOptional(TIP_BLOCK)
                            .map(b -> {
                                var state = b.defaultBlockState();
                                if (state.hasProperty(BaseVineBlock.SHAPE)) {
                                    state = state.
                                            setValue(BaseVineBlock.SHAPE,
                                                    BlockProperties.TripleShape.BOTTOM);
                                }
                                return state;
                            })
                            .orElse(null),
                    configuration.get(VINE_TYPE), worldgen);
        }

    }

    @Override
    protected void placeVines(LevelAccessor level, BlockPos vinePos, BlockState vinesState, int maxLength, @Nullable BlockState tipState, VineType vineType, boolean worldGen) {
        // Generate a random length for the vine.
        final int len = Mth.clamp(level.getRandom().nextInt(maxLength) + 1, 1, maxLength);
        final BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(vinePos.getX(), vinePos.getY(), vinePos.getZ());

        tipState = tipState == null ? vinesState : tipState;

        for (int i = 0; i < len; i++) {
            if (level.isEmptyBlock(mPos)) {
                // Set the current block either to a vine block or a tip block if it's set.
                level.setBlock(mPos, (i == len - 1) ? tipState : vinesState, 3);
                // Move current position down/up depending on vine type.
                mPos.setY(mPos.getY() + (vineType == VineType.FLOOR ? 1 : -1));
            } else {
                if (i > 0 && vineType != VineType.SIDE) {
                    mPos.setY(mPos.getY() + (vineType == VineType.FLOOR ? -1 : 1)); // if the vine is cut short set the tip on the last block
                    level.setBlock(mPos, tipState, 3);
                }
                break;
            }
        }
        // super.placeVines(level, vinePos, vinesState, maxLength, tipState, vineType, worldGen);
    }
}

