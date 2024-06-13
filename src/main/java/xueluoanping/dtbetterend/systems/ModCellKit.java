package xueluoanping.dtbetterend.systems;


import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.systems.cell.LacugroveCellKit;



public class ModCellKit {
    public static final LacugroveCellKit LACUGROVE = new LacugroveCellKit(regName("lacugrove"));


    private static ResourceLocation regName(String name) {
        return DTBetterEnd.rl(name);
    }

    public static void register(final Registry<CellKit> registry) {
        registry.register(LACUGROVE);
    }
}
