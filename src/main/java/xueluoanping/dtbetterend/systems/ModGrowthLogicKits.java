package xueluoanping.dtbetterend.systems;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.systems.growthlogic.*;


public class ModGrowthLogicKits {
    public static final GrowthLogicKit WISTERIA = new TenaneaTreeLogic(new ResourceLocation(DTBetterEnd.MOD_ID, "tenanea"));
    public static final GrowthLogicKit LUCERNIA = new LucerniaTreeLogic(DTBetterEnd.rl("lucernia"));
    public static final GrowthLogicKit DRAGON_TREE = new DragonTreeLogic(DTBetterEnd.rl("dragon_tree"));
    public static final GrowthLogicKit LACUGROVE = new LacugroveTreeLogic(DTBetterEnd.rl("lacugrove"));
    public static final GrowthLogicKit PYTHADENDRON = new PythadendronTreeLogic(DTBetterEnd.rl("pythadendron"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.register(WISTERIA);
        registry.register(LUCERNIA);
        registry.register(DRAGON_TREE);
        registry.register(LACUGROVE);
        registry.register(PYTHADENDRON);
    }
}
