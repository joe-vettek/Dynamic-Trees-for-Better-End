package xueluoanping.dtbetterend.systems.growthlogic;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionSelectionContext;
import com.ferreusveritas.dynamictrees.growthlogic.context.PositionalSpeciesContext;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import xueluoanping.dtbetterend.DTBetterEnd;

import java.util.List;
import java.util.Random;

// Thanks to maxhyper
// signal_energy controls the maximum trunk length.
// The value of each position in the six directions of probMap affects the initial branch size,
// but in the end, only values greater than 0 will always grow unless the fertility is exhausted.
// Because each growth pulse is sent throughout the tree along the trunk.
public class LacugroveTreeLogic extends GrowthLogicKit {

    public static final ConfigurationProperty<Integer> TURNING_LENGTH = ConfigurationProperty.integer("turning_length");

    public LacugroveTreeLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(TURNING_LENGTH, 0);
    }

    @Override
    protected void registerProperties() {
        this.register(TURNING_LENGTH);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final Species species = context.species();
        final Level world = context.level();
        final GrowSignal signal = context.signal();
        int[] probMap = new int[]{0, 2, 0, 0, 0, 0};
        final BlockPos pos = context.pos();
        var rootPos = signal.rootPos;
        Direction originDir = signal.dir.getOpposite();
        int currentHeight = signal.numSteps + 1;
        long seed = CoordUtils.coordHashCode(pos, 3) + ((ServerLevel) world).getSeed();
        Random random = new Random(seed);
        var delta = signal.delta;

        {

            if (Mth.abs(delta.getX()) > 5 || Mth.abs(delta.getZ()) > 5)
                probMap[Direction.UP.ordinal()] = 50;

            if (delta.getX() <= 1 && delta.getZ() <= 1)
                if (delta.getY() > 5 + random.nextInt(4) && delta.getY() % Mth.clamp(random.nextInt(3), 1, 3) == 0) {
                    probMap = new int[]{0, 10, random.nextInt(7) + 3, random.nextInt(7) + 3, random.nextInt(7) + 3, random.nextInt(7) + 3};
                    DTBetterEnd.logger(delta);
                }

            if (delta.getX() >= 0 && delta.getZ() > 0) {
                probMap = new int[]{1, 0, 0, 10, 0, 0};
            }
            // north
            else if (delta.getX() <= 0 && delta.getZ() < 0) {
                probMap = new int[]{1, 0, 10, 0, 0, 0};
            }
            // west
            else if (delta.getX() < 0 && delta.getZ() >= 0) {
                probMap = new int[]{1, 0, 0, 0, 10, 0};
            }
            // test east
            else if (delta.getX() > 0 && delta.getZ() <= 0) {
                probMap = new int[]{1, 0, 0, 0, 0, 10};
            }

            if (Mth.abs(delta.getX()) > 2 + random.nextInt(2) || Mth.abs(delta.getZ()) > 2 + random.nextInt(2)) {
                for (Direction direction : List.of(Direction.DOWN, Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH)) {
                    if (probMap[direction.ordinal()] == 0)
                        probMap[direction.ordinal()] = random.nextInt(5);
                    else probMap[direction.ordinal()] = random.nextInt(2);
                }
            }

            // DTBetterEnd.logger(signal.energy, signal.numSteps, ran, seed, delta);
        }
        // DTBetterEnd.logger(delta, probMap);
        probMap[originDir.ordinal()] = 0;
        return probMap;
    }

    @Override
    public Direction selectNewDirection(GrowthLogicKitConfiguration configuration, DirectionSelectionContext context) {
        final Direction newDir = super.selectNewDirection(configuration, context);
        if (context.signal().isInTrunk() && newDir != Direction.UP) { // Turned out of trunk.
            final BlockPos pos = context.pos();
            long seed = CoordUtils.coordHashCode(pos, 3) + ((ServerLevel) context.level()).getSeed();
            Random random = new Random(seed);
            var delta = context.signal().delta;
            context.signal().energy /= delta.getY() > 8 + random.nextInt(4) ? 2 : 4;
            final Float horizontalLimiter = 4f+random.nextInt(3);
            if (context.signal().energy > horizontalLimiter) {
                context.signal().energy = horizontalLimiter;
            }
        }
        return newDir;
    }

    @Override
    public int getLowestBranchHeight(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        return super.getLowestBranchHeight(configuration, context);
    }

    @Override
    public float getEnergy(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        long day = context.level().getGameTime() / 24000L;
        int month = (int) day / 30; // Change the hashs every in-game month
        var en = super.getEnergy(configuration, context) *
                context.species().biomeSuitability(context.level(), context.pos()) +
                (CoordUtils.coordHashCode(context.pos().above(month), 3) %
                        3); // Vary the height energy by a psuedorandom hash function
        return en;
    }
}
