package rabid.uputils.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rabid.uputils.UpgradeableUtilities;
import rabid.uputils.blockentity.*;

public final class ModBlockEntities {
	private ModBlockEntities() {}

	public static final BlockEntityType<CopperFurnaceBlockEntity> COPPER_FURNACE = register(
		"copper_furnace",
		CopperFurnaceBlockEntity::new,
		ModBlocks.COPPER_FURNACE
	);

	public static final BlockEntityType<IronFurnaceBlockEntity> IRON_FURNACE = register(
			"iron_furnace",
			IronFurnaceBlockEntity::new,
			ModBlocks.IRON_FURNACE
	);

	public static final BlockEntityType<GoldFurnaceBlockEntity> GOLD_FURNACE = register(
			"gold_furnace",
			GoldFurnaceBlockEntity::new,
			ModBlocks.GOLD_FURNACE
	);

	public static final BlockEntityType<DiamondFurnaceBlockEntity> DIAMOND_FURNACE = register(
			"diamond_furnace",
			DiamondFurnaceBlockEntity::new,
			ModBlocks.DIAMOND_FURNACE
	);

	public static final BlockEntityType<NetheriteFurnaceBlockEntity> NETHERITE_FURNACE = register(
			"netherite_furnace",
			NetheriteFurnaceBlockEntity::new,
			ModBlocks.NETHERITE_FURNACE
	);

	public static <T extends BlockEntity> BlockEntityType<T> register(
		String name,
		FabricBlockEntityTypeBuilder.Factory<T> factory,
		Block... blocks
	) {
		return Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			id(name),
			FabricBlockEntityTypeBuilder.create(factory, blocks).build()
		);
	}

	public static void initialize() {
		UpgradeableUtilities.LOGGER.info("Registered block entities.");
	}

	private static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(UpgradeableUtilities.MOD_ID, name);
	}
}
