package rabid.uputils.blockentity.netherite;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import rabid.uputils.registry.ModBlockEntities;
import rabid.uputils.blockentity.SpeedScaledFurnace;
public class NetheriteBlastFurnaceBlockEntity extends AbstractFurnaceBlockEntity implements SpeedScaledFurnace {
	private static final Component DEFAULT_NAME = Component.translatable("container.upgradeable-utilities.netherite_blast_furnace");

	public NetheriteBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.NETHERITE_BLAST_FURNACE, pos, state, RecipeType.BLASTING);
	}

	@Override
	protected Component getDefaultName() {
		return DEFAULT_NAME;
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new BlastFurnaceMenu(containerId, inventory, this, this.dataAccess);
	}

	@Override
	public double upgradeableUtilities$getCookSpeedMultiplier() {
		return 4.0;
	}
}


