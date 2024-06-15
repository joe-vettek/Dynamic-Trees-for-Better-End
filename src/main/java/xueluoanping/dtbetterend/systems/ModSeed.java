package xueluoanping.dtbetterend.systems;

import com.ferreusveritas.dynamictrees.item.Seed;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.betterx.bclib.interfaces.SurvivesOnSpecialGround;
import org.betterx.betterend.interfaces.survives.*;
import org.jetbrains.annotations.Nullable;
import xueluoanping.dtbetterend.DTBetterEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModSeed {

    public static class DragonTreeSeed extends Seed implements SurvivesOnShadowGrass {
        public DragonTreeSeed(Species dragonTree) {
            super(dragonTree);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            SurvivesOnSpecialGround.appendHoverText(this, tooltip);
            super.appendHoverText(stack, level, tooltip, flag);
        }
    }

    public static class LacugroveSeed extends Seed implements SurvivesOnMossOrDust {
        public LacugroveSeed(Species lacugrove) {
            super(lacugrove);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            SurvivesOnSpecialGround.appendHoverText(this, tooltip);
            super.appendHoverText(stack, level, tooltip, flag);

        }
    }

    public static class LucerniaSeed extends Seed implements SurvivesOnRutiscus {
        public LucerniaSeed(Species lucernia) {
            super(lucernia);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            SurvivesOnSpecialGround.appendHoverText(this, tooltip);
            super.appendHoverText(stack, level, tooltip, flag);

        }
    }

    public static class PythadendronSeed extends Seed implements SurvivesOnChorusNylium {
        public PythadendronSeed(Species pythadendron) {
            super(pythadendron);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            SurvivesOnSpecialGround.appendHoverText(this, tooltip);
            super.appendHoverText(stack, level, tooltip, flag);
        }
    }

    public static class TenaneaSeed extends Seed implements SurvivesOnPinkMoss {
        public TenaneaSeed(Species tenanea) {
            super(tenanea);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            SurvivesOnSpecialGround.appendHoverText(this, tooltip);
            super.appendHoverText(stack, level, tooltip, flag);
        }
    }
}
