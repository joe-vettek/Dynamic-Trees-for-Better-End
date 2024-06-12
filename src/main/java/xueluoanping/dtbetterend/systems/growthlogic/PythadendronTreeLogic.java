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
public class PythadendronTreeLogic extends GrowthLogicKit {

    public static final ConfigurationProperty<Integer> TURNING_LENGTH = ConfigurationProperty.integer("turning_length");

    public PythadendronTreeLogic(ResourceLocation registryName) {
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
        int[] probMap = new int[]{0, 10, 0, 0, 0, 0};
        final BlockPos pos = context.pos();
        var rootPos = signal.rootPos;
        Direction originDir = signal.dir.getOpposite();
        int currentHeight = signal.numSteps + 1;
        long seed = CoordUtils.coordHashCode(pos, 3) + ((ServerLevel) world).getSeed();
        Random random = new Random(seed);
        var delta = signal.delta;

        if (signal.numSteps == 10 + random.nextInt(2)) {
            probMap = new int[]{0, 0, random.nextInt(2), random.nextInt(2), random.nextInt(2), random.nextInt(2)};
            // probMap = new int[]{0, 0, 0, 0, 0, 1};
        } else if (signal.numSteps == 15 + random.nextInt(2)) {
            probMap = new int[]{0, 0, random.nextInt(2), random.nextInt(2), random.nextInt(2), random.nextInt(2)};
            // probMap = new int[]{0, 0, 0, 0, 0, 1};
        } else {
            // south
            float ran = random.nextFloat();
            if (delta.getX() >= 0 && delta.getZ() > 0) {
                probMap = new int[]{0, 0, 0, 10, 0, 0};
                if (ran < 0.5) {
                    probMap = new int[]{0, 10, 0, 0, 0, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 0, 0, 0, 10};
                }
            }
            // north
            else if (delta.getX() <= 0 && delta.getZ() < 0) {
                probMap = new int[]{0, 0, 10, 0, 0, 0};
                if (ran < 0.5) {
                    probMap = new int[]{0, 10, 0, 0, 0, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 0, 0, 10, 0};
                }
            }
            // west
            else if (delta.getX() < 0 && delta.getZ() >= 0) {
                probMap = new int[]{0, 0, 0, 0, 10, 0};
                if (ran < 0.5) {
                    probMap = new int[]{0, 10, 0, 0, 0, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 0, 10, 0, 0};
                }
            }
            // test east
            else if (delta.getX() > 0 && delta.getZ() <= 0) {
                probMap = new int[]{0, 0, 0, 0, 0, 10};
                if (ran < 0.5) {
                    probMap = new int[]{0, 10, 0, 0, 0, 0};
                } else if (ran < 0.66) {
                    probMap = new int[]{0, 0, 10, 0, 0, 0};
                }
            }


            if (Mth.abs(delta.getX()) > 5 || Mth.abs(delta.getZ()) > 5)
                probMap[Direction.UP.ordinal()] = 50;

            seed = CoordUtils.coordHashCode(rootPos, 3) + ((ServerLevel) world).getSeed();
            random = new Random(seed);
            if (delta.getY() > 21 + random.nextInt(4)) {
                probMap = new int[]{0, 0, random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10)};
            }
            if (16 + random.nextInt(2) < delta.getY() && delta.getY() < 18 + random.nextInt(2))
                probMap = new int[]{0, 10, random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10)};

            // for (Direction direction : List.of(Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.EAST)) {
            //         probMap[direction.ordinal()] = 1;
            //     }


            // DTBetterEnd.logger(signal.energy, signal.numSteps, ran, seed, delta);
        }
        // DTBetterEnd.logger(delta, probMap);
        probMap[originDir.ordinal()] = 0;
        return probMap;
    }

    @Override
    public Direction selectNewDirection(GrowthLogicKitConfiguration configuration, DirectionSelectionContext context) {
        var d = super.selectNewDirection(configuration, context);
        // DTBetterEnd.logger(d,context.signal().delta);
        return d;
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
