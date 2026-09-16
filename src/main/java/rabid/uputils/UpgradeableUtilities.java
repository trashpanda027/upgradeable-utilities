package rabid.uputils;

import net.fabricmc.api.ModInitializer;
import rabid.uputils.registry.ModBlockEntities;
import rabid.uputils.registry.ModBlocks;
import rabid.uputils.registry.ModCreativeTabs;
import rabid.uputils.registry.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpgradeableUtilities implements ModInitializer {
	public static final String MOD_ID = "upgradeable-utilities";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.initialize();
		ModBlockEntities.initialize();
		ModCreativeTabs.initialize();

		LOGGER.info("Upgradeable Utilities initialized.");
	}
}
