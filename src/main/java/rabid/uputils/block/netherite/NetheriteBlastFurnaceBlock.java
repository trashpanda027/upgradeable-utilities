package rabid.uputils.block.netherite;

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
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import rabid.uputils.registry.ModBlockEntities;
import rabid.uputils.blockentity.netherite.*;

public class NetheriteBlastFurnaceBlock extends AbstractFurnaceBlock {
	public static final MapCodec<NetheriteBlastFurnaceBlock> CODEC = simpleCodec(NetheriteBlastFurnaceBlock::new);

	public NetheriteBlastFurnaceBlock(Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<NetheriteBlastFurnaceBlock> codec() {
		return CODEC;
	}

	@Override
	protected void openContainer(Level level, BlockPos pos, Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof NetheriteBlastFurnaceBlockEntity) {
			player.openMenu((MenuProvider) blockEntity);
			player.awardStat(Stats.INTERACT_WITH_BLAST_FURNACE);
		}
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
		return new NetheriteBlastFurnaceBlockEntity(worldPosition, blockState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createFurnaceTicker(level, type, ModBlockEntities.NETHERITE_BLAST_FURNACE);
	}
}
