package tanner.farmersbounty.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import tanner.farmersbounty.block.BlockRegister;

public class FertilizerItem extends Item {
	public FertilizerItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);

		if (!state.is(Blocks.FARMLAND)) {
			return InteractionResult.PASS;
		}

		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		BlockState fertilizedState = BlockRegister.FERTILIZED_FARMLAND.defaultBlockState();

		if (state.hasProperty(FarmlandBlock.MOISTURE)) {
			fertilizedState = fertilizedState.setValue(FarmlandBlock.MOISTURE, state.getValue(FarmlandBlock.MOISTURE));
		}

		level.setBlockAndUpdate(pos, fertilizedState);
		level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();

		if (player == null || !player.isCreative()) {
			stack.shrink(1);
		}

		return InteractionResult.SUCCESS_SERVER;
	}
}
