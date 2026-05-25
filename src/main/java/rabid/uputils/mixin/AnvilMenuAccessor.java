package rabid.uputils.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilMenu.class)
public interface AnvilMenuAccessor {
	@Accessor("repairItemCountCost")
	int upgradeableUtilities$getRepairItemCountCost();

	@Accessor("cost")
	DataSlot upgradeableUtilities$getCost();

	@Accessor("onlyRenaming")
	boolean upgradeableUtilities$isOnlyRenaming();

	@Accessor("itemName")
	String upgradeableUtilities$getItemName();
}
