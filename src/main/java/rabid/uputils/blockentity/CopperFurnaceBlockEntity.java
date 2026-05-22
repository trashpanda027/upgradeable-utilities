package rabid.uputils.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import rabid.uputils.registry.ModBlockEntities;

public final class CopperFurnaceBlockEntity extends AbstractFurnaceBlockEntity implements SpeedScaledFurnace {
	private static final Component DEFAULT_NAME = Component.translatable("container.upgradeable-utilities.copper_furnace");

	public CopperFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.COPPER_FURNACE, pos, state, RecipeType.SMELTING);
	}

	@Override
	protected Component getDefaultName() {
		return DEFAULT_NAME;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public double upgradeableUtilities$getCookSpeedMultiplier() {
		return 1.25;
	}
}
