package rabid.uputils.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import rabid.uputils.block.*;
import rabid.uputils.UpgradeableUtilities;

import java.util.function.Function;

public final class ModBlocks {
	private ModBlocks() {}

	public static final Block COPPER_FURNACE = registerWithItem(
		"copper_furnace",
		CopperFurnaceBlock::new,
		BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE),
		new Item.Properties()
	);

	public static final Block IRON_FURNACE = registerWithItem(
			"iron_furnace",
			IronFurnaceBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE),
			new Item.Properties()
	);

	public static final Block GOLD_FURNACE = registerWithItem(
			"gold_furnace",
			GoldFurnaceBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE),
			new Item.Properties()
	);

	public static final Block DIAMOND_FURNACE = registerWithItem(
			"diamond_furnace",
			DiamondFurnaceBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE),
			new Item.Properties()
	);

	public static final Block NETHERITE_FURNACE = registerWithItem(
			"netherite_furnace",
			NetheriteFurnaceBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE),
			new Item.Properties()
	);

	public static final Block COPPER_BLAST_FURNACE = registerWithItem(
			"copper_blast_furnace",
			CopperBlastFurnaceBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE),
			new Item.Properties()
	);

	public static Block register(String name, Block block) {
		return Registry.register(BuiltInRegistries.BLOCK, id(name), block);
	}

	public static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
		BlockBehaviour.Properties idBoundSettings = settings.setId(ResourceKey.create(Registries.BLOCK, id(name)));
		return register(name, blockFactory.apply(idBoundSettings));
	}

	public static Block registerWithItem(String name, Block block, Item.Properties itemSettings) {
		Block registeredBlock = register(name, block);
		Item.Properties idBoundItemSettings = itemSettings.setId(ResourceKey.create(Registries.ITEM, id(name)));
		Registry.register(BuiltInRegistries.ITEM, id(name), new BlockItem(registeredBlock, idBoundItemSettings));
		return registeredBlock;
	}

	public static Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties blockSettings, Item.Properties itemSettings) {
		Block registeredBlock = register(name, blockFactory, blockSettings);
		Item.Properties idBoundItemSettings = itemSettings.setId(ResourceKey.create(Registries.ITEM, id(name)));
		Registry.register(BuiltInRegistries.ITEM, id(name), new BlockItem(registeredBlock, idBoundItemSettings));
		return registeredBlock;
	}

	public static void initialize() {
		UpgradeableUtilities.LOGGER.info("Registered blocks.");
	}

	private static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, name);
	}
}
