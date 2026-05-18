package tanner.farmersparadise.block;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import tanner.farmersparadise.FarmersParadise;

public final class BlockRegister {
	public static final ResourceKey<CreativeModeTab> FARMERS_PARADISE_BLOCKS_TAB_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Identifier.fromNamespaceAndPath(FarmersParadise.MOD_ID, "blocks")
	);

	public static final Block FERTILIZED_FARMLAND = register(
			"fertilized_farmland",
			FertilizedFarmlandBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND),
			true
	);

	public static final Block AMARANTH_CROP = register(
			"amaranth_crop",
			AmaranthCropBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT),
			false
	);

	private static final CreativeModeTab FARMERS_PARADISE_BLOCKS_TAB = FabricCreativeModeTab.builder()
			.icon(() -> new ItemStack(FERTILIZED_FARMLAND))
			.title(Component.translatable("creativeTab.farmersparadise.blocks"))
			.displayItems((parameters, output) -> {
				output.accept(FERTILIZED_FARMLAND);
			})
			.build();

	private BlockRegister() {
	}

	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FARMERS_PARADISE_BLOCKS_TAB_KEY, FARMERS_PARADISE_BLOCKS_TAB);
		FarmersParadise.LOGGER.info("Registering blocks for {}", FarmersParadise.MOD_ID);
	}

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		ResourceKey<Block> blockKey = keyOfBlock(name);
		Block block = blockFactory.apply(settings.setId(blockKey));

		if (shouldRegisterItem) {
			ResourceKey<Item> itemKey = keyOfItem(name);
			BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FarmersParadise.MOD_ID, name));
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FarmersParadise.MOD_ID, name));
	}
}
