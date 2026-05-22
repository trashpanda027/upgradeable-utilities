package rabid.uputils.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractModRecipeProvider extends FabricRecipeProvider {
	protected AbstractModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected final RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		return new RecipeProvider(registries, output) {
			@Override
			public void buildRecipes() {
				HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
				defineRecipes(this, itemLookup, this.output);
			}
		};
	}

	@Override
	public String getName() {
		return "Upgradeable Utilities recipes";
	}

	protected abstract void defineRecipes(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output);

	protected void shaped3x3(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike material) {
		ShapedRecipeBuilder.shaped(itemLookup, category, result)
			.pattern("MMM")
			.pattern("MMM")
			.pattern("MMM")
			.define('M', material)
			.unlockedBy(recipeProvider.getHasName(material), recipeProvider.has(material))
			.save(output);
	}

	protected void surroundedCenter(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike surrounding, ItemLike center) {
		ShapedRecipeBuilder.shaped(itemLookup, category, result)
			.pattern("SSS")
			.pattern("SCS")
			.pattern("SSS")
			.define('S', surrounding)
			.define('C', center)
			.unlockedBy(recipeProvider.getHasName(center), recipeProvider.has(center))
			.save(output);
	}

	protected void surroundedCenter(
		RecipeProvider recipeProvider,
		HolderLookup.RegistryLookup<Item> itemLookup,
		RecipeOutput output,
		RecipeCategory category,
		ItemLike result,
		ItemLike surrounding,
		ItemLike center,
		String recipeName
	) {
		ShapedRecipeBuilder.shaped(itemLookup, category, result)
			.pattern("SSS")
			.pattern("SCS")
			.pattern("SSS")
			.define('S', surrounding)
			.define('C', center)
			.unlockedBy(recipeProvider.getHasName(center), recipeProvider.has(center))
			.save(output, recipeName);
	}

	protected void diamondShapeCenter(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike diamondMaterial, ItemLike center) {
		ShapedRecipeBuilder.shaped(itemLookup, category, result)
			.pattern(" D ")
			.pattern("DCD")
			.pattern(" D ")
			.define('D', diamondMaterial)
			.define('C', center)
			.unlockedBy(recipeProvider.getHasName(center), recipeProvider.has(center))
			.save(output);
	}

	protected void diamondShapeCenter(
		RecipeProvider recipeProvider,
		HolderLookup.RegistryLookup<Item> itemLookup,
		RecipeOutput output,
		RecipeCategory category,
		ItemLike result,
		ItemLike diamondMaterial,
		ItemLike center,
		String recipeName
	) {
		ShapedRecipeBuilder.shaped(itemLookup, category, result)
			.pattern(" D ")
			.pattern("DCD")
			.pattern(" D ")
			.define('D', diamondMaterial)
			.define('C', center)
			.unlockedBy(recipeProvider.getHasName(center), recipeProvider.has(center))
			.save(output, recipeName);
	}

	protected void shaped2x2(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike material) {
		ShapedRecipeBuilder.shaped(itemLookup, category, result)
			.pattern("MM")
			.pattern("MM")
			.define('M', material)
			.unlockedBy(recipeProvider.getHasName(material), recipeProvider.has(material))
			.save(output);
	}

	protected void shapeless(RecipeProvider recipeProvider, HolderLookup.RegistryLookup<Item> itemLookup, RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike... ingredients) {
		ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(itemLookup, category, result);
		for (ItemLike ingredient : ingredients) {
			builder.requires(ingredient);
		}

		if (ingredients.length > 0) {
			builder.unlockedBy(recipeProvider.getHasName(ingredients[0]), recipeProvider.has(ingredients[0]));
		}

		builder.save(output);
	}
}
