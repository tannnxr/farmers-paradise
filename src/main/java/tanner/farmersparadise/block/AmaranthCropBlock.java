package tanner.farmersparadise.block;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import tanner.farmersparadise.item.ItemRegister;

public class AmaranthCropBlock extends CropBlock {
	public AmaranthCropBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return ItemRegister.AMARANTH_SEEDS;
	}
}
