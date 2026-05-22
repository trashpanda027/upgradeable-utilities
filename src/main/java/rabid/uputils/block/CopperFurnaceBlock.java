package rabid.uputils.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import rabid.uputils.blockentity.CopperFurnaceBlockEntity;
import rabid.uputils.registry.ModBlockEntities;

public final class CopperFurnaceBlock extends AbstractFurnaceBlock {
	public static final MapCodec<CopperFurnaceBlock> CODEC = simpleCodec(CopperFurnaceBlock::new);

	public CopperFurnaceBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<CopperFurnaceBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CopperFurnaceBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createFurnaceTicker(level, type, ModBlockEntities.COPPER_FURNACE);
	}

	@Override
	protected void openContainer(Level level, BlockPos pos, Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof CopperFurnaceBlockEntity) {
			player.openMenu((MenuProvider) blockEntity);
			player.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}
}
