package rabid.uputils.registry;

import net.minecraft.world.level.block.Block;
import rabid.uputils.UpgradeableUtilities;

import java.util.IdentityHashMap;
import java.util.Map;

public final class ModAnvilDurability {
	private static final Map<Block, Float> DAMAGE_CHANCE_MULTIPLIER_BY_BLOCK = new IdentityHashMap<>();

	private ModAnvilDurability() {}

	public static void register(Block block, float damageChanceMultiplier) {
		if (damageChanceMultiplier < 0.0F) {
			throw new IllegalArgumentException("Anvil damage chance multiplier must be >= 0.0");
		}
		DAMAGE_CHANCE_MULTIPLIER_BY_BLOCK.put(block, damageChanceMultiplier);
	}

	public static float getDamageChanceMultiplier(Block block) {
		return DAMAGE_CHANCE_MULTIPLIER_BY_BLOCK.getOrDefault(block, 1.0F);
	}

	public static void initialize() {
		// Lower than 1.0 means more durable than vanilla.
		register(ModBlocks.COPPER_ANVIL, 0.5F);
		UpgradeableUtilities.LOGGER.info("Registered anvil durability overrides.");
	}
}
