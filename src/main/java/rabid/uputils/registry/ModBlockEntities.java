package rabid.uputils.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rabid.uputils.UpgradeableUtilities;
import rabid.uputils.blockentity.copper.*;
import rabid.uputils.blockentity.iron.*;
import rabid.uputils.blockentity.gold.*;
import rabid.uputils.blockentity.diamond.*;
import rabid.uputils.blockentity.netherite.*;

public final class ModBlockEntities {
	private ModBlockEntities() {}

	public static final BlockEntityType<CopperFurnaceBlockEntity> COPPER_FURNACE = registerFurnaceEntity("copper_furnace", CopperFurnaceBlockEntity::new, ModBlocks.COPPER_FURNACE);
	public static final BlockEntityType<IronFurnaceBlockEntity> IRON_FURNACE = registerFurnaceEntity("iron_furnace", IronFurnaceBlockEntity::new, ModBlocks.IRON_FURNACE);
	public static final BlockEntityType<GoldFurnaceBlockEntity> GOLD_FURNACE = registerFurnaceEntity("gold_furnace", GoldFurnaceBlockEntity::new, ModBlocks.GOLD_FURNACE);
	public static final BlockEntityType<DiamondFurnaceBlockEntity> DIAMOND_FURNACE = registerFurnaceEntity("diamond_furnace", DiamondFurnaceBlockEntity::new, ModBlocks.DIAMOND_FURNACE);
	public static final BlockEntityType<NetheriteFurnaceBlockEntity> NETHERITE_FURNACE = registerFurnaceEntity("netherite_furnace", NetheriteFurnaceBlockEntity::new, ModBlocks.NETHERITE_FURNACE);

	public static final BlockEntityType<CopperBlastFurnaceBlockEntity> COPPER_BLAST_FURNACE = registerBlastFurnaceEntity("copper_blast_furnace", CopperBlastFurnaceBlockEntity::new, ModBlocks.COPPER_BLAST_FURNACE);
	public static final BlockEntityType<IronBlastFurnaceBlockEntity> IRON_BLAST_FURNACE = registerBlastFurnaceEntity("iron_blast_furnace", IronBlastFurnaceBlockEntity::new, ModBlocks.IRON_BLAST_FURNACE);
	public static final BlockEntityType<GoldBlastFurnaceBlockEntity> GOLD_BLAST_FURNACE = registerBlastFurnaceEntity("gold_blast_furnace", GoldBlastFurnaceBlockEntity::new, ModBlocks.GOLD_BLAST_FURNACE);
	public static final BlockEntityType<DiamondBlastFurnaceBlockEntity> DIAMOND_BLAST_FURNACE = registerBlastFurnaceEntity("diamond_blast_furnace", DiamondBlastFurnaceBlockEntity::new, ModBlocks.DIAMOND_BLAST_FURNACE);
	public static final BlockEntityType<NetheriteBlastFurnaceBlockEntity> NETHERITE_BLAST_FURNACE = registerBlastFurnaceEntity("netherite_blast_furnace", NetheriteBlastFurnaceBlockEntity::new, ModBlocks.NETHERITE_BLAST_FURNACE);

	public static final BlockEntityType<CopperSmokerBlockEntity> COPPER_SMOKER = registerSmokerEntity("copper_smoker", CopperSmokerBlockEntity::new, ModBlocks.COPPER_SMOKER);
	public static final BlockEntityType<IronSmokerBlockEntity> IRON_SMOKER = registerSmokerEntity("iron_smoker", IronSmokerBlockEntity::new, ModBlocks.IRON_SMOKER);
	public static final BlockEntityType<GoldSmokerBlockEntity> GOLD_SMOKER = registerSmokerEntity("gold_smoker", GoldSmokerBlockEntity::new, ModBlocks.GOLD_SMOKER);
	public static final BlockEntityType<DiamondSmokerBlockEntity> DIAMOND_SMOKER = registerSmokerEntity("diamond_smoker", DiamondSmokerBlockEntity::new, ModBlocks.DIAMOND_SMOKER);
	public static final BlockEntityType<NetheriteSmokerBlockEntity> NETHERITE_SMOKER = registerSmokerEntity("netherite_smoker", NetheriteSmokerBlockEntity::new, ModBlocks.NETHERITE_SMOKER);

	private static <T extends BlockEntity> BlockEntityType<T> registerFurnaceEntity(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block furnaceBlock) {
		return register(name, factory, furnaceBlock);
	}

	private static <T extends BlockEntity> BlockEntityType<T> registerBlastFurnaceEntity(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block blastFurnaceBlock) {
		return register(name, factory, blastFurnaceBlock);
	}

	private static <T extends BlockEntity> BlockEntityType<T> registerSmokerEntity(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block smokerBlock) {
		return register(name, factory, smokerBlock);
	}

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


