package rabid.uputils.block.copper;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class CopperAnvilBlock extends AnvilBlock {
	public static final MapCodec<AnvilBlock> CODEC = simpleCodec(CopperAnvilBlock::new);

	public CopperAnvilBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<AnvilBlock> codec() {
		return CODEC;
	}
}
