package xueluoanping.dtbetterend.systems.worldgen;

import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtbetterend.DTFruitfulFun;

public class FruitTreesFeatureCanceller extends FeatureCanceller {
    public FruitTreesFeatureCanceller(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        // Note it not in ForgeRegistries.FEATURES
        final ResourceLocation featureName =  ForgeRegistries.FEATURES.getKey(configuredFeature.feature());
        if (featureName == null) {
            return false;
        }
        DTFruitfulFun.logger(22,featureName);
        return   false;
        // && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }



}
