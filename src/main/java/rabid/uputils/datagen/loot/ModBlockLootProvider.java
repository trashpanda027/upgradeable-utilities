package rabid.uputils.datagen.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.Identifier;
import rabid.uputils.UpgradeableUtilities;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ModBlockLootProvider implements DataProvider {
	private static final List<String> SELF_DROPPING_BLOCKS = List.of(
		"copper_furnace",
		"iron_furnace",
		"gold_furnace",
		"diamond_furnace",
		"netherite_furnace",
		"copper_blast_furnace",
		"iron_blast_furnace",
		"gold_blast_furnace",
		"diamond_blast_furnace",
		"netherite_blast_furnace",
		"copper_smoker",
		"iron_smoker",
		"gold_smoker",
		"diamond_smoker",
		"netherite_smoker"
	);

	private final FabricPackOutput output;

	public ModBlockLootProvider(FabricPackOutput output) {
		this.output = output;
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cachedOutput) {
		return CompletableFuture.allOf(SELF_DROPPING_BLOCKS.stream()
			.map(blockId -> DataProvider.saveStable(cachedOutput, selfDropLootTable(blockId), blockLootTablePath(blockId)))
			.toArray(CompletableFuture[]::new));
	}

	@Override
	public String getName() {
		return "Upgradeable Utilities block loot tables";
	}

	private Path blockLootTablePath(String blockId) {
		return output.getOutputFolder()
			.resolve("data")
			.resolve(UpgradeableUtilities.MOD_ID)
			.resolve("loot_table")
			.resolve("blocks")
			.resolve(blockId + ".json");
	}

	private JsonObject selfDropLootTable(String blockId) {
		Identifier blockIdentifier = Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, blockId);

		JsonObject root = new JsonObject();
		root.addProperty("type", "minecraft:block");

		JsonObject pool = new JsonObject();
		pool.addProperty("bonus_rolls", 0.0);
		pool.addProperty("rolls", 1.0);

		JsonArray conditions = new JsonArray();
		JsonObject survivesExplosion = new JsonObject();
		survivesExplosion.addProperty("condition", "minecraft:survives_explosion");
		conditions.add(survivesExplosion);
		pool.add("conditions", conditions);

		JsonObject entry = new JsonObject();
		entry.addProperty("type", "minecraft:item");
		entry.addProperty("name", blockIdentifier.toString());

		JsonArray entries = new JsonArray();
		entries.add(entry);
		pool.add("entries", entries);

		JsonArray pools = new JsonArray();
		pools.add(pool);
		root.add("pools", pools);
		root.addProperty("random_sequence", blockIdentifier.withPath(path -> "blocks/" + path).toString());

		return root;
	}
}
