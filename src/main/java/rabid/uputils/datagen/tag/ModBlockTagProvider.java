package rabid.uputils.datagen.tag;

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

public final class ModBlockTagProvider implements DataProvider {
	private static final List<String> PICKAXE_MINEABLE_BLOCKS = List.of(
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
	private static final List<String> NEEDS_IRON_TOOL_BLOCKS = List.of(
		"iron_furnace",
		"gold_furnace",
		"diamond_furnace",
		"iron_blast_furnace",
		"gold_blast_furnace",
		"diamond_blast_furnace",
		"iron_smoker",
		"gold_smoker",
		"diamond_smoker"
	);
	private static final List<String> NEEDS_NETHERITE_TOOL_BLOCKS = List.of(
		"netherite_furnace",
		"netherite_blast_furnace",
		"netherite_smoker"
	);

	private final FabricPackOutput output;

	public ModBlockTagProvider(FabricPackOutput output) {
		this.output = output;
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cachedOutput) {
		return CompletableFuture.allOf(
			DataProvider.saveStable(cachedOutput, tagJson(PICKAXE_MINEABLE_BLOCKS), minecraftBlockTagPath("mineable/pickaxe")),
			DataProvider.saveStable(cachedOutput, tagJson(NEEDS_IRON_TOOL_BLOCKS), minecraftBlockTagPath("needs_iron_tool")),
			DataProvider.saveStable(cachedOutput, tagJson(NEEDS_NETHERITE_TOOL_BLOCKS), minecraftBlockTagPath("needs_diamond_tool")),
			DataProvider.saveStable(cachedOutput, tagJson(NEEDS_NETHERITE_TOOL_BLOCKS), minecraftBlockTagPath("incorrect_for_diamond_tool"))
		);
	}

	@Override
	public String getName() {
		return "Upgradeable Utilities block tags";
	}

	private Path minecraftBlockTagPath(String tagPath) {
		return output.getOutputFolder()
			.resolve("data")
			.resolve("minecraft")
			.resolve("tags")
			.resolve("block")
			.resolve(tagPath + ".json");
	}

	private JsonObject tagJson(List<String> blockIds) {
		JsonObject root = new JsonObject();
		JsonArray values = new JsonArray();

		for (String blockId : blockIds) {
			values.add(Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, blockId).toString());
		}

		root.addProperty("replace", false);
		root.add("values", values);
		return root;
	}
}
