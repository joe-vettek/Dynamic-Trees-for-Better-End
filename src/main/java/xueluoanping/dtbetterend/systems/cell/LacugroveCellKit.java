package xueluoanping.dtbetterend.systems.cell;

import com.ferreusveritas.dynamictrees.api.cell.Cell;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.cell.CellNull;
import com.ferreusveritas.dynamictrees.api.cell.CellSolver;
import com.ferreusveritas.dynamictrees.cell.*;
import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class LacugroveCellKit extends CellKit {
    /**
     * Typical branch with hydration 5
     */
    private final Cell branchCell = new NormalCell(5);

    private final Cell[] lacugroveLeafCells = {
            CellNull.NULL_CELL,
            new LacugroveLeafCell(1),
            new LacugroveLeafCell(2),
            new LacugroveLeafCell(3),
            new LacugroveLeafCell(4),
            new LacugroveLeafCell(5),
            new LacugroveLeafCell(6),
            new LacugroveLeafCell(7)
    };

//     Yes, basically think of each value 0x0FNH as a step in a decision the cellkit makes to place a new leaf block. The solver will compute each value in order, and the first one it finds to succeed will be the one applied.
//
//     Relative to the position of the new leaf block, here's what the values mean:
//     0x0FNH
//     F: Find blocks of this hydro level adjacent to the new block.
//     N: Number of blocks of that hydro level that are needed (between 1 and 6)
//     H: Hydro selected for the new block. if its 0 or if all the values fail then no leaf block will be placed.
//
//     For examples:
//     0x0423 - If an empty block has 2 blocks of hydro 4 adjacent to it, then a new leaf block of hydro 3 will be placed.
//
//     0x0211 - Any empty block adjacent to a leaf of hydro 2 will become filled by a leaf block of hydro 1.
//
//     0x0111 - Any leaf block of hydro 1 will spread with more leaves of hydro 1. This is VERY BAD. (this means leaves will spread infinitely)
//
//     As a rule of thumb, F should always be greater than H to avoid this last example.
//     This whole logic basically defines the shape the canopy will take starting from branch tips

    private final CellKits.BasicSolver lacugroveSolver =
            new CellKits.BasicSolver(new short[]{0x0514, 0x0423, 0x0412, 0x0312, 0x0211});

    public LacugroveCellKit(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public Cell getCellForLeaves(int hydro) {
        return lacugroveLeafCells[hydro];
        // return CellNull.NULL_CELL;
    }

    @Override
    public Cell getCellForBranch(int radius, int meta) {
        return radius == 1 ? this.branchCell : CellNull.NULL_CELL;
    }

    @Override
    public SimpleVoxmap getLeafCluster() {
        // return LeafClusters.DARK_OAK;
        return DARK_OAK;

    }

    @Override
    public CellSolver getCellSolver() {
        // return lacugroveSolver;
        return CellKits.DARK_OAK.getCellSolver();
        // return new CellKits.BasicSolver(new short[]{0x0514, 0x0322, 0x0311, 0x0211});
    }

    @Override
    public int getDefaultHydration() {
        return 6;
    }

    public static class LacugroveLeafCell extends MatrixCell {

        public LacugroveLeafCell(int value) {
            super(value, valMap);
        }

        static final byte[] valMap = {
                0, 1, 2, 3, 3, 4, 0, 0, // D Maps 4 -> 3, * -> *
                0, 1, 2, 3, 3, 4, 0, 0, // U Maps 4 -> 3, * -> *
                0, 1, 2, 3, 4, 5, 0, 0, // N Maps * -> *
                0, 1, 2, 3, 4, 5, 0, 0, // S Maps * -> *
                0, 1, 2, 3, 4, 5, 0, 0, // W Maps * -> *
                0, 1, 2, 3, 4, 5, 0, 0  // E Maps * -> *
        };

    }

    public static final SimpleVoxmap DARK_OAK = new SimpleVoxmap(7, 5, 7, new byte[]{

            // Layer 0(Bottom)
            0, 0, 2, 2, 2, 0, 0,
            0, 2, 3, 3, 3, 2, 0,
            2, 3, 0, 0, 0, 3, 2,
            2, 3, 0, 0, 0, 3, 2,
            2, 3, 0, 0, 0, 3, 2,
            0, 2, 3, 3, 3, 2, 0,
            0, 0, 2, 2, 2, 0, 0,

            // Layer 1
            0, 0, 3, 3, 3, 0, 0,
            0, 3, 4, 4, 4, 3, 0,
            3, 4, 5, 6, 5, 4, 3,
            3, 4, 6, 0, 6, 4, 3,
            3, 4, 5, 6, 5, 4, 3,
            0, 3, 4, 4, 4, 3, 0,
            0, 0, 3, 3, 3, 0, 0,

            // Layer 2
            0, 0, 1, 1, 1, 0, 0,
            0, 1, 2, 2, 2, 1, 0,
            1, 2, 4, 4, 4, 2, 1,
            1, 2, 4, 6, 4, 2, 1,
            1, 2, 4, 4, 4, 2, 1,
            0, 1, 2, 2, 2, 1, 0,
            0, 0, 1, 1, 1, 0, 0,

            // Layer 3
            0, 0, 0, 0, 0, 0, 0,
            0, 0, 1, 1, 1, 0, 0,
            0, 1, 3, 3, 3, 1, 0,
            0, 1, 3, 4, 3, 1, 0,
            0, 1, 3, 3, 3, 1, 0,
            0, 0, 1, 1, 1, 0, 0,
            0, 0, 0, 0, 0, 0, 0,

            // Layer 4 (Top)
            0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 1, 0, 0, 0,
            0, 0, 1, 2, 1, 0, 0,
            0, 0, 0, 1, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0

    }).setCenter(new BlockPos(3, 1, 3));
}
