package xueluoanping.dtbetterend.systems;

import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModFeatureCanceller extends FeatureCanceller {

    List<String> features = List.of("betterend:tenanea",
            "betterend:tenanea_bush",
            "betterend:pythadendron_tree",
            "betterend:bush_feature",
            "betterend:bush_with_outer_feature",
            "betterend:lucernia",
            "betterend:bush_with_outer_feature",
            "betterend:lacugrove",
            "betterend:dragon_tree",
            "betterend:bush_feature");

    public ModFeatureCanceller(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        // Note it not in ForgeRegistries.FEATURES
        final ResourceLocation featureName = ForgeRegistries.FEATURES.getKey(configuredFeature.feature());
        // DTNaturesSpirit.logger(ForgeRegistries.FEATURES.getKey(configuredFeature.feature()));
        if (featureName == null) {
            return false;
        }
        // DTNaturesSpirit.logger(featureName,featureName.toString().equals("natures_spirit:joshua_tree_feature"));
        return features.contains(featureName.toString());
        // && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }


}
