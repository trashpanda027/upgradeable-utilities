package rabid.uputils.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rabid.uputils.blockentity.SpeedScaledFurnace;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
	@Inject(method = "getTotalCookTime", at = @At("RETURN"), cancellable = true)
	private static void upgradeableUtilities$scaleCookTime(ServerLevel level, AbstractFurnaceBlockEntity entity, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(upgradeableUtilities$getScaledCookTime(entity, cir.getReturnValue()));
	}

	@Redirect(
		method = "serverTick",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/AbstractCookingRecipe;cookingTime()I")
	)
	private static int upgradeableUtilities$scaleCookTimeAfterSmelt(AbstractCookingRecipe recipe, ServerLevel level, net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state, AbstractFurnaceBlockEntity entity) {
		return upgradeableUtilities$getScaledCookTime(entity, recipe.cookingTime());
	}

	private static int upgradeableUtilities$getScaledCookTime(AbstractFurnaceBlockEntity entity, int baseCookTime) {
		if (!(entity instanceof SpeedScaledFurnace speedScaledFurnace)) {
			return baseCookTime;
		}

		double speedMultiplier = Math.max(0.01, speedScaledFurnace.upgradeableUtilities$getCookSpeedMultiplier());
		return (int) Math.max(1, Math.round(baseCookTime / speedMultiplier));
	}
}
