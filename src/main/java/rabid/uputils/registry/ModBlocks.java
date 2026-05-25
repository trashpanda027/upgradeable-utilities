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
import rabid.uputils.UpgradeableUtilities;

import java.util.function.Function;
import rabid.uputils.block.copper.*;
import rabid.uputils.block.iron.*;
import rabid.uputils.block.gold.*;
import rabid.uputils.block.diamond.*;
import rabid.uputils.block.netherite.*;

public final class ModBlocks {
	private ModBlocks() {}

	// Furnaces
	public static final Block COPPER_FURNACE = registerFurnace("copper_furnace", CopperFurnaceBlock::new);
	public static final Block IRON_FURNACE = registerFurnace("iron_furnace", IronFurnaceBlock::new);
	public static final Block GOLD_FURNACE = registerFurnace("gold_furnace", GoldFurnaceBlock::new);
	public static final Block DIAMOND_FURNACE = registerFurnace("diamond_furnace", DiamondFurnaceBlock::new);
	public static final Block NETHERITE_FURNACE = registerFurnace("netherite_furnace", NetheriteFurnaceBlock::new);
	// Blast Furnaces
	public static final Block COPPER_BLAST_FURNACE = registerBlastFurnace("copper_blast_furnace", CopperBlastFurnaceBlock::new);
	public static final Block IRON_BLAST_FURNACE = registerBlastFurnace("iron_blast_furnace", IronBlastFurnaceBlock::new);
	public static final Block GOLD_BLAST_FURNACE = registerBlastFurnace("gold_blast_furnace", GoldBlastFurnaceBlock::new);
	public static final Block DIAMOND_BLAST_FURNACE = registerBlastFurnace("diamond_blast_furnace", DiamondBlastFurnaceBlock::new);
	public static final Block NETHERITE_BLAST_FURNACE = registerBlastFurnace("netherite_blast_furnace", NetheriteBlastFurnaceBlock::new);
	// Smokers
	public static final Block COPPER_SMOKER = registerSmoker("copper_smoker", CopperSmokerBlock::new);
	public static final Block IRON_SMOKER = registerSmoker("iron_smoker", IronSmokerBlock::new);
	public static final Block GOLD_SMOKER = registerSmoker("gold_smoker", GoldSmokerBlock::new);
	public static final Block DIAMOND_SMOKER = registerSmoker("diamond_smoker", DiamondSmokerBlock::new);
	public static final Block NETHERITE_SMOKER = registerSmoker("netherite_smoker", NetheriteSmokerBlock::new);
	// Anvils
	public static final Block COPPER_ANVIL = registerAnvil("copper_anvil", CopperAnvilBlock::new);

	private static Block registerFurnace(String id, Function<BlockBehaviour.Properties, Block> blockFactory) {
		return registerWithItem(
			id,
			blockFactory,
			BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE),
			new Item.Properties()
		);
	}

	private static Block registerBlastFurnace(String id, Function<BlockBehaviour.Properties, Block> blockFactory) {
		return registerWithItem(
			id,
			blockFactory,
			BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE),
			new Item.Properties()
		);
	}

	private static Block registerSmoker(String id, Function<BlockBehaviour.Properties, Block> blockFactory) {
		return registerWithItem(
			id,
			blockFactory,
			BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER),
			new Item.Properties()
		);
	}

	private static Block registerAnvil(String id, Function<BlockBehaviour.Properties, Block> blockFactory) {
		return registerWithItem(
			id,
			blockFactory,
			BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL),
			new Item.Properties()
		);
	}

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


