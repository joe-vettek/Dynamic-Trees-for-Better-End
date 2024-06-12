package xueluoanping.dtbetterend.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.betterx.betterend.blocks.basis.FurBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;


@Mixin({FurBlock.class})
public class MixinFurBlock {

    @Inject(at = @At("HEAD"), method = "getDrops", cancellable = true)
    private void zz$getDrops(BlockState state, LootParams.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        cir.setReturnValue(new ArrayList<>());
    }
}
