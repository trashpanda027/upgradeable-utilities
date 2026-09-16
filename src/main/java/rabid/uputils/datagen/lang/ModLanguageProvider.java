package rabid.uputils.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ModLanguageProvider extends FabricLanguageProvider {
	public ModLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
		translationBuilder.add("itemGroup.upgradeable_utilities", "Upgradeable Utilities");
		translationBuilder.add("item.upgradeable-utilities.example_item", "Example Item");

		List<TranslationEntry> entries = List.of(
			new TranslationEntry("copper_furnace", "Copper Furnace", true),
			new TranslationEntry("iron_furnace", "Iron Furnace", true),
			new TranslationEntry("gold_furnace", "Gold Furnace", true),
			new TranslationEntry("diamond_furnace", "Diamond Furnace", true),
			new TranslationEntry("netherite_furnace", "Netherite Furnace", true),
			new TranslationEntry("copper_blast_furnace", "Copper Blast Furnace", true),
			new TranslationEntry("iron_blast_furnace", "Iron Blast Furnace", true),
			new TranslationEntry("gold_blast_furnace", "Gold Blast Furnace", true),
			new TranslationEntry("diamond_blast_furnace", "Diamond Blast Furnace", true),
			new TranslationEntry("netherite_blast_furnace", "Netherite Blast Furnace", true),
			new TranslationEntry("copper_smoker", "Copper Smoker", true),
			new TranslationEntry("iron_smoker", "Iron Smoker", true),
			new TranslationEntry("gold_smoker", "Gold Smoker", true),
			new TranslationEntry("diamond_smoker", "Diamond Smoker", true),
			new TranslationEntry("netherite_smoker", "Netherite Smoker", true)
		);

		for (TranslationEntry entry : entries) {
			addBlockItemAndContainerTranslations(translationBuilder, entry);
		}
	}

	private static void addBlockItemAndContainerTranslations(TranslationBuilder builder, TranslationEntry entry) {
		builder.add("block.upgradeable-utilities." + entry.id(), entry.displayName());
		builder.add("item.upgradeable-utilities." + entry.id(), entry.displayName());

		if (entry.includeContainer()) {
			builder.add("container.upgradeable-utilities." + entry.id(), entry.displayName());
		}
	}

	private record TranslationEntry(String id, String displayName, boolean includeContainer) {}
}
