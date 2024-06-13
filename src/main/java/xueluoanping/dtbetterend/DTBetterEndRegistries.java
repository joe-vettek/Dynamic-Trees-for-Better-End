package xueluoanping.dtbetterend;

import com.ferreusveritas.dynamictrees.api.applier.ApplierRegistryEvent;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.block.rooty.SoilHelper;
import com.ferreusveritas.dynamictrees.deserialisation.PropertyAppliers;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.google.gson.JsonElement;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtbetterend.systems.*;
import xueluoanping.dtbetterend.systems.leaves.FurOuterLeaveProperties;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTBetterEndRegistries {

    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, DTBetterEnd.MOD_ID);

    static {
        items.register("dragon_tree_seed", () -> new ModSeed.DragonTreeSeed(Species.REGISTRY.get(DTBetterEnd.rl("dragon_tree"))));
        items.register("lacugrove_seed", () -> new ModSeed.LacugroveSeed(Species.REGISTRY.get(DTBetterEnd.rl("lacugrove"))));
        items.register("lucernia_seed", () -> new ModSeed.LucerniaSeed(Species.REGISTRY.get(DTBetterEnd.rl("lucernia"))));
        items.register("pythadendron_seed", () -> new ModSeed.PythadendronSeed(Species.REGISTRY.get(DTBetterEnd.rl("pythadendron"))));
        items.register("tenanea_seed", () -> new ModSeed.TenaneaSeed(Species.REGISTRY.get(DTBetterEnd.rl("tenanea"))));
    }


    public static void addSoil() {
        SoilHelper.createNewAdjective("");
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {
        DTBetterEnd.LOGGER.debug("registerLeavesPropertiesTypes");
        // event.registerType(new ResourceLocation(DTFruitfulFun.MOD_ID, "cherry"), CherryLeavesProperties.TYPE);
        // event.registerType(new ResourceLocation(DTFruitfulFun.MOD_ID, "fruittrees"), FruitsLeavesProperties.TYPE);
        event.registerType(DTBetterEnd.rl("fur"), FurOuterLeaveProperties.TYPE);
        //


    }

    @SubscribeEvent
    public static void registerFruitTypes(final TypeRegistryEvent<Fruit> event) {
        DTBetterEnd.LOGGER.debug("registerFruitTypes");
        // event.registerType(new ResourceLocation(DTFruitfulFun.MOD_ID, "fly_passable"), FruitTypes.TYPE);
        // event.registerType(new ResourceLocation(DTFruitfulFun.MOD_ID, "named_fruit"), NamedFruitTypes.TYPE);

    }


    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().register(new ModFeatureCanceller(DTBetterEnd.rl("betterend_tree")));
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
        ModFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitsRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GrowthLogicKit> event) {
        ModGrowthLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onCellKitsRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<CellKit> event) {
        ModCellKit.register(event.getRegistry());
    }


//    @SubscribeEvent
//    public static void registerAppliersFruit(final ApplierRegistryEvent.Reload<Fruit, JsonElement> event) {
//        registerFruitAppliers(event.getAppliers());
//    }
//
//    @SubscribeEvent
//    public static void registerAppliersPod(final ApplierRegistryEvent.Reload<Pod, JsonElement> event) {
//        registerPodAppliers(event.getAppliers());
//    }

    // public static void registerSpeciesAppliers(PropertyAppliers<Species, JsonElement> appliers) {
    //     appliers.register("extra_soil_for_worldgen", GenOnExtraSoilSpecies.class, Block.class,
    //                     GenOnExtraSoilSpecies::setExtraSoil)
    //             .register("log_drop_item", FruitLogSpecies.class, Item.class, FruitLogSpecies::setDropItem)
    //             //.register("log_drop_item", FruitLogSpecies.class, ResourceLocation.class, FruitLogSpecies::setDropItem)
    //             .register("log_drop_multiplier", FruitLogSpecies.class, Float.class, FruitLogSpecies::setMultiplier)
    //             .register("log_drop_fake_log", FruitLogSpecies.class, Item.class, FruitLogSpecies::setFakeLog);
    // }

//    public static void registerFruitAppliers(PropertyAppliers<Fruit, JsonElement> appliers) {
//        appliers.register("item_stack", DTPHC2Fruit.class, ResourceLocation.class, DTPHC2Fruit::setItemStackLoc)
//                .register("item_stack", FallingFruit.class, ResourceLocation.class, DTPHC2Fruit::setItemStackLoc)
//                .register("item_stack", OffsetFruit.class, ResourceLocation.class, DTPHC2Fruit::setItemStackLoc);
//    }
//    public static void registerPodAppliers(PropertyAppliers<Pod, JsonElement> appliers) {
//        appliers.register("item_stack", DTPHC2Pod.class, ResourceLocation.class, DTPHC2Pod::setItemStackLoc);
//    }

    // @SubscribeEvent
    // public static void registerAppliersSpecies(final ApplierRegistryEvent.GatherData<Species, JsonElement> event) {
    //     registerSpeciesAppliers(event.getAppliers());
    // }
//    @SubscribeEvent public static void registerAppliersFruit(final ApplierRegistryEvent.GatherData<Fruit, JsonElement> event) {
//        registerFruitAppliers(event.getAppliers());
//    }
//    @SubscribeEvent public static void registerAppliersPod(final ApplierRegistryEvent.GatherData<Pod, JsonElement> event) { registerPodAppliers(event.getAppliers()); }

    @SubscribeEvent
    public static void registerAppliersSpecies(final ApplierRegistryEvent.Reload<LeavesProperties, JsonElement> event) {
        registerLeavesAppliers(event.getAppliers());
    }

    @SubscribeEvent
    public static void registerAppliersSpecies(final ApplierRegistryEvent.GatherData<LeavesProperties, JsonElement> event) {
        registerLeavesAppliers(event.getAppliers());
    }


    public static void registerLeavesAppliers(PropertyAppliers<LeavesProperties, JsonElement> appliers) {
        appliers.register("outer_block", FurOuterLeaveProperties.class, Block.class,
                FurOuterLeaveProperties::setOuter);
    }
}
