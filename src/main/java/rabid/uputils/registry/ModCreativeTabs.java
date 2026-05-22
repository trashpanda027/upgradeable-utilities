package rabid.uputils.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import rabid.uputils.UpgradeableUtilities;

import java.util.Comparator;

public final class ModCreativeTabs {
	private ModCreativeTabs() {}

	public static final CreativeModeTab UPGRADEABLE_UTILITIES = Registry.register(
		BuiltInRegistries.CREATIVE_MODE_TAB,
		Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, "upgradeable_utilities"),
		CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
			.title(Component.translatable("itemGroup.upgradeable_utilities"))
			.icon(() -> new ItemStack(ModBlocks.COPPER_FURNACE))
			.displayItems((parameters, output) -> {
				BuiltInRegistries.ITEM.stream()
					.filter(item -> item != Items.AIR)
					.filter(item -> {
						Identifier itemId = BuiltInRegistries.ITEM.getKey(item);
						return itemId != null && UpgradeableUtilities.MOD_ID.equals(itemId.getNamespace());
					})
					.sorted(Comparator.comparing(item -> BuiltInRegistries.ITEM.getKey(item).toString()))
					.forEach(output::accept);
			})
			.build()
	);

	public static void initialize() {
		UpgradeableUtilities.LOGGER.info("Registered creative mode tabs.");
	}
}
