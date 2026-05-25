package rabid.uputils.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import rabid.uputils.registry.ModBlocks;

import java.util.concurrent.CompletableFuture;

public final class ModRecipeProvider extends AbstractModRecipeProvider {
	public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void defineRecipes(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output) {
		// Copper Furnace Recipe
		surroundedCenter(
			recipeProvider,
			itemLookup,
			output,
			RecipeCategory.DECORATIONS,
			ModBlocks.COPPER_FURNACE,
			Items.COPPER_INGOT,
			Blocks.FURNACE,
			"copper_furnace_base"
		);

		// Iron Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.IRON_FURNACE,
				Items.IRON_INGOT,
				Blocks.FURNACE,
				"iron_furnace_base"
		);

		// Gold Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.GOLD_FURNACE,
				Items.GOLD_INGOT,
				Blocks.FURNACE,
				"gold_furnace_base"
		);

		// Diamond Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.DIAMOND_FURNACE,
				Items.DIAMOND,
				Blocks.FURNACE,
				"diamond_furnace_base"
		);

		// Netherite Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.NETHERITE_FURNACE,
				Items.NETHERITE_INGOT,
				Blocks.FURNACE,
				"netherite_furnace_base"
		);

		// Iron Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.IRON_FURNACE,
				Items.IRON_INGOT,
				ModBlocks.COPPER_FURNACE,
				"iron_furnace_upgrade"
		);

		// Gold Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.GOLD_FURNACE,
				Items.GOLD_INGOT,
				ModBlocks.IRON_FURNACE,
				"gold_furnace_upgrade"
		);

		// Diamond Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.DIAMOND_FURNACE,
				Items.DIAMOND,
				ModBlocks.GOLD_FURNACE,
				"diamond_furnace_upgrade"
		);

		// Netherite Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.NETHERITE_FURNACE,
				Items.NETHERITE_INGOT,
				ModBlocks.DIAMOND_FURNACE,
				"netherite_furnace_upgrade"
		);

		// Copper Blast Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.COPPER_BLAST_FURNACE,
				Items.COPPER_INGOT,
				Blocks.BLAST_FURNACE,
				"copper_blast_furnace_base"
		);

		// Copper Smoker Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.COPPER_SMOKER,
				Items.COPPER_INGOT,
				Blocks.SMOKER,
				"copper_smoker_base"
		);

		// Iron Smoker Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.IRON_SMOKER,
				Items.IRON_INGOT,
				Blocks.SMOKER,
				"iron_smoker_base"
		);

		// Gold Smoker Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.GOLD_SMOKER,
				Items.GOLD_INGOT,
				Blocks.SMOKER,
				"gold_smoker_base"
		);

		// Diamond Smoker Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.DIAMOND_SMOKER,
				Items.DIAMOND,
				Blocks.SMOKER,
				"diamond_smoker_base"
		);

		// Netherite Smoker Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.NETHERITE_SMOKER,
				Items.NETHERITE_INGOT,
				Blocks.SMOKER,
				"netherite_smoker_base"
		);

		// Iron Blast Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.IRON_BLAST_FURNACE,
				Items.IRON_INGOT,
				Blocks.BLAST_FURNACE,
				"iron_blast_furnace_base"
		);

		// Gold Blast Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.GOLD_BLAST_FURNACE,
				Items.GOLD_INGOT,
				Blocks.BLAST_FURNACE,
				"gold_blast_furnace_base"
		);

		// Diamond Blast Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.DIAMOND_BLAST_FURNACE,
				Items.DIAMOND,
				Blocks.BLAST_FURNACE,
				"diamond_blast_furnace_base"
		);

		// Netherite Blast Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.NETHERITE_BLAST_FURNACE,
				Items.NETHERITE_INGOT,
				Blocks.BLAST_FURNACE,
				"netherite_blast_furnace_base"
		);

		// Iron Blast Furnace Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.IRON_BLAST_FURNACE,
				Items.IRON_INGOT,
				ModBlocks.COPPER_BLAST_FURNACE,
				"iron_blast_furnace_upgrade"
		);

		// Gold Blast Furnace Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.GOLD_BLAST_FURNACE,
				Items.GOLD_INGOT,
				ModBlocks.IRON_BLAST_FURNACE,
				"gold_blast_furnace_upgrade"
		);

		// Diamond Blast Furnace Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.DIAMOND_BLAST_FURNACE,
				Items.DIAMOND,
				ModBlocks.GOLD_BLAST_FURNACE,
				"diamond_blast_furnace_upgrade"
		);

		// Netherite Blast Furnace Upgrade Recipe
		diamondShapeCenter(recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.NETHERITE_BLAST_FURNACE,
				Items.NETHERITE_INGOT,
				ModBlocks.DIAMOND_BLAST_FURNACE,
				"netherite_blast_furnace_upgrade"
		);

		// Iron Smoker Upgrade Recipe
		diamondShapeCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.IRON_SMOKER,
				Items.IRON_INGOT,
				ModBlocks.COPPER_SMOKER,
				"iron_smoker_upgrade"
		);

		// Gold Smoker Upgrade Recipe
		diamondShapeCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.GOLD_SMOKER,
				Items.GOLD_INGOT,
				ModBlocks.IRON_SMOKER,
				"gold_smoker_upgrade"
		);

		// Diamond Smoker Upgrade Recipe
		diamondShapeCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.DIAMOND_SMOKER,
				Items.DIAMOND,
				ModBlocks.GOLD_SMOKER,
				"diamond_smoker_upgrade"
		);

		// Netherite Smoker Upgrade Recipe
		diamondShapeCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.NETHERITE_SMOKER,
				Items.NETHERITE_INGOT,
				ModBlocks.DIAMOND_SMOKER,
				"netherite_smoker_upgrade"
		);

		// Copper Anvil Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.COPPER_ANVIL,
				Items.COPPER_INGOT,
				Blocks.ANVIL,
				"copper_anvil_base"
		);
	}
}
