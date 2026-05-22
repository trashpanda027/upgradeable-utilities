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

		// Netherite Furnace Recipe
		surroundedCenter(
				recipeProvider,
				itemLookup,
				output,
				RecipeCategory.DECORATIONS,
				ModBlocks.COPPER_BLAST_FURNACE,
				Items.COPPER_INGOT,
				Blocks.FURNACE,
				"copper_blast_furnace_base"
		);
	}
}
