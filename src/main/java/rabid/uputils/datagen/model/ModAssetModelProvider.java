package rabid.uputils.datagen.model;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.Identifier;
import rabid.uputils.UpgradeableUtilities;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ModAssetModelProvider implements DataProvider {
	private final FabricPackOutput output;
	private final List<AssetDefinition> assets = new ArrayList<>();

	public ModAssetModelProvider(FabricPackOutput output) {
		this.output = output;

		// Furnaces
		assets.add(new AssetDefinition("copper_furnace", "copperfurnace", 0));
		assets.add(new AssetDefinition("iron_furnace", "ironfurnace", 0));
		assets.add(new AssetDefinition("gold_furnace", "goldfurnace", 0));
		assets.add(new AssetDefinition("diamond_furnace", "diamondfurnace", 0));
		assets.add(new AssetDefinition("netherite_furnace", "netheritefurnace", 0));

		// Blast furnaces
		assets.add(new AssetDefinition("copper_blast_furnace", "copperblastfurnace", 2));
		assets.add(new AssetDefinition("iron_blast_furnace", "ironblastfurnace", 2));
		assets.add(new AssetDefinition("gold_blast_furnace", "goldblastfurnace", 2));
		assets.add(new AssetDefinition("diamond_blast_furnace", "diamondblastfurnace", 2));
		assets.add(new AssetDefinition("netherite_blast_furnace", "netheriteblastfurnace", 2));

		// Smokers
		assets.add(new AssetDefinition("copper_smoker", "coppersmoker", 3));
		assets.add(new AssetDefinition("iron_smoker", "ironsmoker", 3));
		assets.add(new AssetDefinition("gold_smoker", "goldsmoker", 3));
		assets.add(new AssetDefinition("diamond_smoker", "diamondsmoker", 3));
		assets.add(new AssetDefinition("netherite_smoker", "netheritesmoker", 3));

		// Anvils
		assets.add(AssetDefinition.anvil("copper_anvil", "copperanvil"));
	}
	@Override
	public CompletableFuture<?> run(CachedOutput cachedOutput) {
		List<CompletableFuture<?>> saves = new ArrayList<>();

		for (AssetDefinition asset : assets) {
			Identifier id = Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, asset.blockId());
			if (asset.kind() == AssetKind.ANVIL) {
				saves.add(DataProvider.saveStable(cachedOutput, createAnvilBlockstateJson(id), blockstatePath(asset.blockId())));
				saves.add(DataProvider.saveStable(cachedOutput, createAnvilModelJson(asset.textureFolder(), "anvil_top"), blockModelPath(asset.blockId())));
				saves.add(DataProvider.saveStable(cachedOutput, createAnvilModelJson(asset.textureFolder(), "chipped_anvil_top"), blockModelPath("chipped_" + asset.blockId())));
				saves.add(DataProvider.saveStable(cachedOutput, createAnvilModelJson(asset.textureFolder(), "damaged_anvil_top"), blockModelPath("damaged_" + asset.blockId())));
				saves.add(DataProvider.saveStable(cachedOutput, createItemDefinitionJson(id), itemDefinitionPath(asset.blockId())));
			} else {
				saves.add(DataProvider.saveStable(cachedOutput, createBlockstateJson(id), blockstatePath(asset.blockId())));
				saves.add(DataProvider.saveStable(cachedOutput, createBlockModelJson(asset.textureFolder(), asset.blockId(), false), blockModelPath(asset.blockId())));
				saves.add(DataProvider.saveStable(cachedOutput, createBlockModelJson(asset.textureFolder(), asset.blockId(), true), blockModelPath(asset.blockId() + "_on")));
				saves.add(DataProvider.saveStable(cachedOutput, createItemDefinitionJson(id), itemDefinitionPath(asset.blockId())));
				if (asset.litAnimationFrames() > 0) {
					saves.add(DataProvider.saveStable(
						cachedOutput,
						createFrontOnAnimationMetaJson(asset.litAnimationFrames()),
						textureAnimationMetaPath(asset.textureFolder(), asset.blockId())
					));
				}
			}
		}

		return CompletableFuture.allOf(saves.toArray(CompletableFuture[]::new));
	}

	@Override
	public String getName() {
		return "Upgradeable Utilities asset models";
	}

	private Path blockstatePath(String blockId) {
		return output.getOutputFolder()
			.resolve("assets")
			.resolve(UpgradeableUtilities.MOD_ID)
			.resolve("blockstates")
			.resolve(blockId + ".json");
	}

	private Path blockModelPath(String modelId) {
		return output.getOutputFolder()
			.resolve("assets")
			.resolve(UpgradeableUtilities.MOD_ID)
			.resolve("models")
			.resolve("block")
			.resolve(modelId + ".json");
	}

	private Path itemDefinitionPath(String itemId) {
		return output.getOutputFolder()
			.resolve("assets")
			.resolve(UpgradeableUtilities.MOD_ID)
			.resolve("items")
			.resolve(itemId + ".json");
	}

	private Path textureAnimationMetaPath(String textureFolder, String texturePrefix) {
		return output.getOutputFolder()
			.resolve("assets")
			.resolve(UpgradeableUtilities.MOD_ID)
			.resolve("textures")
			.resolve("block")
			.resolve(textureFolder)
			.resolve(texturePrefix + "_front_on.png.mcmeta");
	}

	private JsonObject createBlockstateJson(Identifier id) {
		JsonObject root = new JsonObject();
		JsonObject variants = new JsonObject();
		String baseModel = id.withPath(path -> "block/" + path).toString();
		String litModel = id.withPath(path -> "block/" + path + "_on").toString();

		variants.add("facing=north,lit=false", modelVariant(baseModel, 0));
		variants.add("facing=east,lit=false", modelVariant(baseModel, 90));
		variants.add("facing=south,lit=false", modelVariant(baseModel, 180));
		variants.add("facing=west,lit=false", modelVariant(baseModel, 270));

		variants.add("facing=north,lit=true", modelVariant(litModel, 0));
		variants.add("facing=east,lit=true", modelVariant(litModel, 90));
		variants.add("facing=south,lit=true", modelVariant(litModel, 180));
		variants.add("facing=west,lit=true", modelVariant(litModel, 270));

		root.add("variants", variants);
		return root;
	}

	private JsonObject createAnvilBlockstateJson(Identifier id) {
		JsonObject root = new JsonObject();
		JsonObject variants = new JsonObject();
		String undamagedModel = id.withPath(path -> "block/" + path).toString();
		addAnvilFacingVariants(variants, undamagedModel);

		root.add("variants", variants);
		return root;
	}

	private void addAnvilFacingVariants(JsonObject variants, String modelPath) {
		// Match vanilla anvil-facing rotations.
		variants.add("facing=south", modelVariant(modelPath, 0));
		variants.add("facing=west", modelVariant(modelPath, 90));
		variants.add("facing=north", modelVariant(modelPath, 180));
		variants.add("facing=east", modelVariant(modelPath, 270));
	}

	private JsonObject modelVariant(String modelPath, int yRotation) {
		JsonObject variant = new JsonObject();
		variant.addProperty("model", modelPath);
		if (yRotation != 0) {
			variant.addProperty("y", yRotation);
		}
		return variant;
	}

	private JsonObject createBlockModelJson(String textureFolder, String texturePrefix, boolean lit) {
		JsonObject root = new JsonObject();
		root.addProperty("parent", "minecraft:block/orientable_with_bottom");

		JsonObject textures = new JsonObject();
		textures.addProperty("top", texturePath(textureFolder, texturePrefix + "_top"));
		textures.addProperty("side", texturePath(textureFolder, texturePrefix + "_side"));
		textures.addProperty("front", texturePath(textureFolder, texturePrefix + (lit ? "_front_on" : "_front")));
		// Fallback: use top as bottom when a dedicated bottom texture is not provided.
		textures.addProperty("bottom", texturePath(textureFolder, texturePrefix + "_top"));

		root.add("textures", textures);
		return root;
	}

	private JsonObject createAnvilModelJson(String textureFolder, String topTextureName) {
		JsonObject root = new JsonObject();
		root.addProperty("parent", "minecraft:block/template_anvil");

		JsonObject textures = new JsonObject();
		textures.addProperty("top", texturePath(textureFolder, topTextureName));
		textures.addProperty("body", texturePath(textureFolder, "anvil"));
		textures.addProperty("particle", texturePath(textureFolder, "anvil"));

		root.add("textures", textures);
		return root;
	}

	private JsonObject createItemDefinitionJson(Identifier blockId) {
		JsonObject root = new JsonObject();
		JsonObject model = new JsonObject();
		model.addProperty("type", "minecraft:model");
		model.addProperty("model", blockId.withPath(path -> "block/" + path).toString());
		root.add("model", model);
		return root;
	}

	private JsonObject createFrontOnAnimationMetaJson(int frameCount) {
		JsonObject root = new JsonObject();
		JsonObject animation = new JsonObject();
		animation.addProperty("frametime", 2);

		com.google.gson.JsonArray frames = new com.google.gson.JsonArray();
		for (int i = 0; i < frameCount; i++) {
			frames.add(i);
		}
		animation.add("frames", frames);
		root.add("animation", animation);
		return root;
	}

	private String texturePath(String textureFolder, String textureName) {
		return UpgradeableUtilities.MOD_ID + ":block/" + textureFolder + "/" + textureName;
	}

	private enum AssetKind {
		FURNACE_LIKE,
		ANVIL
	}

	private record AssetDefinition(String blockId, String textureFolder, int litAnimationFrames, AssetKind kind) {
		private AssetDefinition(String blockId, String textureFolder, int litAnimationFrames) {
			this(blockId, textureFolder, litAnimationFrames, AssetKind.FURNACE_LIKE);
		}

		private static AssetDefinition anvil(String blockId, String textureFolder) {
			return new AssetDefinition(blockId, textureFolder, 0, AssetKind.ANVIL);
		}
	}
}
