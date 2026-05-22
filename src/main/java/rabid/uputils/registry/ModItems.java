package rabid.uputils.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import rabid.uputils.UpgradeableUtilities;

import java.util.function.Function;

public final class ModItems {
	private ModItems() {}

	// Example item. Replace/expand this list with your real items.
	public static final Item EXAMPLE_ITEM = register("example_item", Item::new, new Item.Properties());

	public static Item register(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, id(name), item);
	}

	public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
		Item.Properties idBoundSettings = settings.setId(ResourceKey.create(Registries.ITEM, id(name)));
		return register(name, itemFactory.apply(idBoundSettings));
	}

	public static void initialize() {
		UpgradeableUtilities.LOGGER.info("Registered items.");
	}

	private static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, name);
	}
}
