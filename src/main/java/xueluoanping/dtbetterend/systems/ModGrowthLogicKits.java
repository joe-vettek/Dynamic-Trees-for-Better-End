package xueluoanping.dtbetterend.systems;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.systems.growthlogic.*;


public class ModGrowthLogicKits {
    public static final GrowthLogicKit HEMLOCK = new RedwoodLogic(new ResourceLocation(DTBetterEnd.MOD_ID, "hemlock"));
    public static final GrowthLogicKit WISTERIA = new WisteriaTreeLogic(new ResourceLocation(DTBetterEnd.MOD_ID, "wisteria"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(HEMLOCK);
        registry.register(WISTERIA);
    }
}
