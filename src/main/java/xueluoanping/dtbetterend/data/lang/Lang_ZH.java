package xueluoanping.dtbetterend.data.lang;


import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.dtbetterend.DTBetterEnd;
import xueluoanping.dtbetterend.ModConstants;


public class Lang_ZH extends LangHelper {
	public Lang_ZH(PackOutput gen, ExistingFileHelper helper) {
		super(gen, helper, DTBetterEnd.MOD_ID, "zh_cn");
	}


	@Override
	protected void addTranslations() {
		add(DTBetterEnd.MOD_ID, "动态的树：妙趣果园附属");
		add(ModConstants.POMEGRANATE_SAPLING.get(), "石榴树苗");
		// add(ModConstants.POMEGRANATE_BRANCH.get(), "石榴树");
		add(ModConstants.POMEGRANATE_SEED.get(), "石榴籽");
		addSpecie("pomegranate","石榴树");

		add(ModConstants.CHERRY_SAPLING.get(), "樱桃树苗");
		// add(ModConstants.CHERRY_BRANCH.get(), "樱桃树");
		add(ModConstants.CHERRY_SEED.get(), "樱桃核");
		addSpecie("cherry","樱桃树");

		add(ModConstants.TANGERINE_SAPLING.get(), "橘子树苗");
		add(ModConstants.TANGERINE_BRANCH.get(), "橘子树");
		add(ModConstants.TANGERINE_SEED.get(), "橘核");
		addSpecie("tangerine","橘子籽");

		add(ModConstants.LIME_SAPLING.get(), "酸橙树苗");
		add(ModConstants.LIME_BRANCH.get(), "酸橙树");
		add(ModConstants.LIME_SEED.get(), "酸橙籽");
		addSpecie("lime","酸橙树");

		add(ModConstants.CITRON_SAPLING.get(), "香橼树苗");
		add(ModConstants.CITRON_BRANCH.get(), "香橼树");
		add(ModConstants.CITRON_SEED.get(), "香橼籽");
		addSpecie("citron","香橼树");

		add(ModConstants.POMELO_SAPLING.get(), "柚子树苗");
		add(ModConstants.POMELO_BRANCH.get(), "柚子树");
		add(ModConstants.POMELO_SEED.get(), "柚子籽");
		addSpecie("pomelo","柚子树");

		add(ModConstants.ORANGE_SAPLING.get(), "甜橙树苗");
		add(ModConstants.ORANGE_BRANCH.get(), "甜橙树");
		add(ModConstants.ORANGE_SEED.get(), "甜橙籽");
		addSpecie("orange","甜橙树");

		add(ModConstants.LEMON_SAPLING.get(), "柠檬树苗");
		add(ModConstants.LEMON_BRANCH.get(), "柠檬树");
		add(ModConstants.LEMON_SEED.get(), "柠檬籽");
		addSpecie("lemon","柠檬树");

		add(ModConstants.GRAPEFRUIT_SAPLING.get(), "西柚树苗");
		add(ModConstants.GRAPEFRUIT_BRANCH.get(), "西柚树");
		add(ModConstants.GRAPEFRUIT_SEED.get(), "西柚籽");
		addSpecie("grapefruit","西柚树");

		add(ModConstants.REDLOVE_SAPLING.get(), "红心果树苗");
		add(ModConstants.REDLOVE_BRANCH.get(), "红心果树");
		add(ModConstants.REDLOVE_SEED.get(), "红心果籽");
		addSpecie("redlove","红心果树");

		add("recipeType.fruitfulfun.hybridizing/dtfruitfulfun/hybridizing","蜜蜂授粉（更有活力的）");
		add("tip.dtfruitfulfun.hybridizing","当它们种植在一起时，蜜蜂会收集花粉，可能会长出新的果实，从中你可以获得新的种子。");


	}


}
