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
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import rabid.uputils.blockentity.CopperBlastFurnaceBlockEntity;
import rabid.uputils.blockentity.IronBlastFurnaceBlockEntity;
import rabid.uputils.registry.ModBlockEntities;

public class IronBlastFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<IronBlastFurnaceBlock> CODEC = simpleCodec(IronBlastFurnaceBlock::new);

    public IronBlastFurnaceBlock(Properties properties) {super(properties);}

    @Override
    public MapCodec<IronBlastFurnaceBlock> codec() {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof IronBlastFurnaceBlockEntity) {
            player.openMenu((MenuProvider) blockEntity);
            player.awardStat(Stats.INTERACT_WITH_BLAST_FURNACE);
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new IronBlastFurnaceBlockEntity(worldPosition, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createFurnaceTicker(level, type, ModBlockEntities.IRON_BLAST_FURNACE);
    }
}
