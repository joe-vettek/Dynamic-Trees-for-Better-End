package xueluoanping.dtbetterend.systems;


import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.ResourceLocation;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.systems.featuregen.FlowerVinesGenFeature;


public class ModFeatures {
    public static final GenFeature FALLEN_LEAVES = new FlowerVinesGenFeature(regName("flower_vines"));

    private static ResourceLocation regName(String name) {
        return DTBetterEnd.rl(name);
    }

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(FALLEN_LEAVES);
    }
}
