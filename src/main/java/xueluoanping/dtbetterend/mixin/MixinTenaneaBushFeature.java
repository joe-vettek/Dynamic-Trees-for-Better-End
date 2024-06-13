package xueluoanping.dtbetterend.mixin;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.betterx.betterend.world.features.bushes.TenaneaBushFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin({TenaneaBushFeature.class})
public class MixinTenaneaBushFeature {

    @Inject(at = @At("HEAD"), method = "place", cancellable = true)
    private void zz$place(FeaturePlaceContext<NoneFeatureConfiguration> featureConfig, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }


}
