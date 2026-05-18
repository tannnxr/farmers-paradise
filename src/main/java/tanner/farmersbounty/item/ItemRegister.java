package tanner.farmersbounty.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import tanner.farmersbounty.FarmersParadise;
import tanner.farmersbounty.block.BlockRegister;

public final class ItemRegister {
	public static final ResourceKey<CreativeModeTab> FARMERS_PARADISE_SEEDS_TAB_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Identifier.fromNamespaceAndPath(FarmersParadise.MOD_ID, "seeds")
	);

	public static final ResourceKey<CreativeModeTab> FARMERS_PARADISE_ITEMS_TAB_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Identifier.fromNamespaceAndPath(FarmersParadise.MOD_ID, "items")
	);

	public static final Item AMARANTH_SEEDS = register(
			"amaranth_seeds",
			new FarmersParadiseSeedItem(BlockRegister.AMARANTH_CROP, new Item.Properties().setId(keyOfItem("amaranth_seeds")))
	);

	public static final Item FERTILIZER = register(
			"fertilizer",
			new FertilizerItem(new Item.Properties().setId(keyOfItem("fertilizer")))
	);

	private static final CreativeModeTab FARMERS_PARADISE_SEEDS_TAB = FabricCreativeModeTab.builder()
			.icon(() -> new ItemStack(AMARANTH_SEEDS))
			.title(Component.translatable("creativeTab.farmersparadise.seeds"))
			.displayItems((parameters, output) -> {
				output.accept(AMARANTH_SEEDS);
			})
			.build();

	private static final CreativeModeTab FARMERS_PARADISE_ITEMS_TAB = FabricCreativeModeTab.builder()
			.icon(() -> new ItemStack(FERTILIZER))
			.title(Component.translatable("creativeTab.farmersparadise.items"))
			.displayItems((parameters, output) -> {
				output.accept(FERTILIZER);
			})
			.build();

	private ItemRegister() {
	}

	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FARMERS_PARADISE_SEEDS_TAB_KEY, FARMERS_PARADISE_SEEDS_TAB);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FARMERS_PARADISE_ITEMS_TAB_KEY, FARMERS_PARADISE_ITEMS_TAB);
		FarmersParadise.LOGGER.info("Registering items for {}", FarmersParadise.MOD_ID);
	}

	private static Item register(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, keyOfItem(name), item);
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FarmersParadise.MOD_ID, name));
	}
}
