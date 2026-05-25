package rabid.uputils.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringUtil;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rabid.uputils.registry.ModAnvilDurability;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {
	@Inject(method = "onTake", at = @At("HEAD"), cancellable = true)
	private void upgradeableUtilities$onTakeWithScaledDurability(Player player, ItemStack carried, CallbackInfo ci) {
		AnvilMenuAccessor anvil = (AnvilMenuAccessor) this;
		ItemCombinerMenuAccessor combiner = (ItemCombinerMenuAccessor) this;
		Container inputSlots = combiner.upgradeableUtilities$getInputSlots();
		DataSlot cost = anvil.upgradeableUtilities$getCost();

		if (!player.hasInfiniteMaterials()) {
			player.giveExperienceLevels(-cost.get());
		}

		int repairItemCountCost = anvil.upgradeableUtilities$getRepairItemCountCost();
		if (repairItemCountCost > 0) {
			ItemStack addition = inputSlots.getItem(1);
			if (!addition.isEmpty() && addition.getCount() > repairItemCountCost) {
				addition.shrink(repairItemCountCost);
				inputSlots.setItem(1, addition);
			} else {
				inputSlots.setItem(1, ItemStack.EMPTY);
			}
		} else if (!anvil.upgradeableUtilities$isOnlyRenaming()) {
			inputSlots.setItem(1, ItemStack.EMPTY);
		}

		cost.set(0);

		String itemName = anvil.upgradeableUtilities$getItemName();
		if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
			if (!StringUtil.isBlank(itemName) && !inputSlots.getItem(0).getHoverName().getString().equals(itemName)) {
				serverPlayer.getTextFilter().processStreamMessage(itemName);
			}
		}

		inputSlots.setItem(0, ItemStack.EMPTY);
		ContainerLevelAccess access = combiner.upgradeableUtilities$getAccess();
		access.execute((level, pos) -> {
			BlockState state = level.getBlockState((BlockPos) pos);
			float multiplier = ModAnvilDurability.getDamageChanceMultiplier(state.getBlock());
			float effectiveChance = Math.max(0.0F, 0.12F * multiplier);

			if (!player.hasInfiniteMaterials() && state.is(net.minecraft.tags.BlockTags.ANVIL) && player.getRandom().nextFloat() < effectiveChance) {
				BlockState newBlockState = AnvilBlock.damage(state);
				if (newBlockState == null) {
					level.removeBlock((BlockPos) pos, false);
					level.levelEvent(1029, (BlockPos) pos, 0);
				} else {
					level.setBlock((BlockPos) pos, newBlockState, 2);
					level.levelEvent(1030, (BlockPos) pos, 0);
				}
			} else {
				level.levelEvent(1030, (BlockPos) pos, 0);
			}
		});

		ci.cancel();
	}
}
