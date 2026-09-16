package rabid.uputils.blockentity.gold;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import rabid.uputils.blockentity.SpeedScaledFurnace;
import rabid.uputils.registry.ModBlockEntities;

public final class GoldSmokerBlockEntity extends AbstractFurnaceBlockEntity implements SpeedScaledFurnace {
	private static final Component DEFAULT_NAME = Component.translatable("container.upgradeable-utilities.gold_smoker");

	public GoldSmokerBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.GOLD_SMOKER, pos, state, RecipeType.SMOKING);
	}

	@Override
	protected Component getDefaultName() {
		return DEFAULT_NAME;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new SmokerMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public double upgradeableUtilities$getCookSpeedMultiplier() {
		return 2.0;
	}
}
