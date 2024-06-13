package xueluoanping.dtbetterend.handlers;


import com.ferreusveritas.dynamictrees.item.Seed;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.util.RegisterFinderUtil;

// @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TooltipHandler {

    public static final String key = "tooltip.soil.hint";
    public static final String key2 = "tooltip.soil2.hint";

    @SubscribeEvent
    public static void onItemTooltipEvent(final ItemTooltipEvent event) {
        DTBetterEnd.LOGGER.debug("ItemTooltipEvent");
        if (event.getEntity() == null) return;
        // var stack = event.getItemStack();
        // if (stack.getItem() instanceof Seed seedItem) {
        //     var res = RegisterFinderUtil.getItemKey(seedItem);
        //     if (res.compareTo(DTBetterEnd.rl("dragon_tree")) == 0) {
        //         event.getToolTip()
        //                 .add(Component.translatable(key, get("shadow_grass")));
        //     } else if (res.compareTo(DTBetterEnd.rl("lacugrove")) == 0) {
        //         event.getToolTip()
        //                 .add(Component.translatable(key, get("shadow_grass")));
        //     } else if (res.compareTo(DTBetterEnd.rl("lucernia")) == 0) {
        //         event.getToolTip()
        //                 .add(Component.translatable(key, get("shadow_grass")));
        //     } else if (res.compareTo(DTBetterEnd.rl("pythadendron")) == 0) {
        //         event.getToolTip()
        //                 .add(Component.translatable(key, get("shadow_grass")));
        //     } else if (res.compareTo(DTBetterEnd.rl("tenanea")) == 0) {
        //         event.getToolTip()
        //                 .add(Component.translatable(key, get("shadow_grass")));
        //     }
        // }
    }

    public static Block get(String id) {
        return RegisterFinderUtil.getBlock(new ResourceLocation("betterend", id));
    }
}
