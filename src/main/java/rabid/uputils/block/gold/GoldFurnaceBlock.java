package rabid.uputils.block.gold;

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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import rabid.uputils.registry.ModBlockEntities;
import rabid.uputils.blockentity.gold.*;

public class GoldFurnaceBlock extends AbstractFurnaceBlock {
    public static final MapCodec<GoldFurnaceBlock> CODEC = simpleCodec(GoldFurnaceBlock::new);

    public GoldFurnaceBlock(Properties properties) {
        super(properties);
    }

    @NonNull
    @Override
    public MapCodec<GoldFurnaceBlock> codec() {return CODEC;}

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GoldFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createFurnaceTicker(level, type, ModBlockEntities.GOLD_FURNACE);
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof GoldFurnaceBlockEntity) {
            player.openMenu((MenuProvider) blockEntity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
}
