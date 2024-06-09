package xueluoanping.dtbetterend.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.ModConstants;

public class Lang_EN extends LangHelper {
    public Lang_EN(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, DTBetterEnd.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        add(DTBetterEnd.MOD_ID, "Dynamic Trees for Fruitful Fun");

        add(ModConstants.POMEGRANATE_SAPLING.get(), "Pomegranate Sapling");
        // add(ModConstants.POMEGRANATE_BRANCH.get(), "Pomegranate Tree");
        add(ModConstants.POMEGRANATE_SEED.get(), "Pomegranate Aril");
        addSpecie("pomegranate","Pomegranate Fruit");

        add(ModConstants.CHERRY_SAPLING.get(), "Cherry Sapling");
        // add(ModConstants.CHERRY_BRANCH.get(), "Cherry Tree");
        add(ModConstants.CHERRY_SEED.get(), "Cherry Pit");
        addSpecie("cherry","Cherry Fruit");

        add(ModConstants.TANGERINE_SAPLING.get(), "Tangerine Sapling");
        add(ModConstants.TANGERINE_BRANCH.get(), "Tangerine Tree");
        add(ModConstants.TANGERINE_SEED.get(), "Tangerine Seed");
        addSpecie("tangerine","Tangerine Fruit");

        add(ModConstants.LIME_SAPLING.get(), "Lime Sapling");
        add(ModConstants.LIME_BRANCH.get(), "Lime Tree");
        add(ModConstants.LIME_SEED.get(), "Lime Seed");
        addSpecie("lime","Lime Fruit");

        add(ModConstants.CITRON_SAPLING.get(), "Citron Sapling");
        add(ModConstants.CITRON_BRANCH.get(), "Citron Tree");
        add(ModConstants.CITRON_SEED.get(), "Citron Seed");
        addSpecie("citron","Citron Fruit");

        add(ModConstants.POMELO_SAPLING.get(), "Pomelo Sapling");
        add(ModConstants.POMELO_BRANCH.get(), "Pomelo Tree");
        add(ModConstants.POMELO_SEED.get(), "Pomelo Seed");
        addSpecie("pomelo","Pomelo Fruit");

        add(ModConstants.ORANGE_SAPLING.get(), "Orange Sapling");
        add(ModConstants.ORANGE_BRANCH.get(), "Orange Tree");
        add(ModConstants.ORANGE_SEED.get(), "Orange Seed");
        addSpecie("orange","Orange Fruit");

        add(ModConstants.LEMON_SAPLING.get(), "Lemon Sapling");
        add(ModConstants.LEMON_BRANCH.get(), "Lemon Tree");
        add(ModConstants.LEMON_SEED.get(), "Lemon Seed");
        addSpecie("lemon","Lemon Fruit");

        add(ModConstants.GRAPEFRUIT_SAPLING.get(), "Grapefruit Sapling");
        add(ModConstants.GRAPEFRUIT_BRANCH.get(), "Grapefruit Tree");
        add(ModConstants.GRAPEFRUIT_SEED.get(), "Grapefruit Seed");
        addSpecie("grapefruit","Grapefruit Fruit");

        add(ModConstants.REDLOVE_SAPLING.get(), "Redlove Sapling");
        add(ModConstants.REDLOVE_BRANCH.get(), "Redlove Tree");
        add(ModConstants.REDLOVE_SEED.get(), "Redlove Seed");
        addSpecie("redlove","Redlove Fruit");

        add("recipeType.fruitfulfun.hybridizing/dtfruitfulfun/hybridizing","Bee Pollinating (Dynamic)");
        add("tip.dtfruitfulfun.hybridizing","When these are planted together, bees collect their pollen, potentially growing new fruit from which you can obtain new seeds.");
        // DTFruitTrees.logger(ForgeRegistries.ITEMS.getEntries().stream().filter(registryKeyBlockEntry -> registryKeyBlockEntry.getValue().getRegistryName().getNamespace().startsWith("dtfruittrees")).map(registryKeyBlockEntry -> registryKeyBlockEntry.getValue().getRegistryName()).collect(Collectors.toList()));
    }
}
