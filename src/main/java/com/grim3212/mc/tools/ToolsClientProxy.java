package com.grim3212.mc.tools;

import com.grim3212.mc.core.client.RenderHelper;
import com.grim3212.mc.core.proxy.ClientProxy;
import com.grim3212.mc.tools.blocks.ToolsBlocks;
import com.grim3212.mc.tools.client.entity.RenderBallisticKnife.BallisticKnifeFactory;
import com.grim3212.mc.tools.client.model.BetterBucketModel;
import com.grim3212.mc.tools.entity.EntityBallisticKnife;
import com.grim3212.mc.tools.items.ToolsItems;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ToolsClientProxy extends ClientProxy {

	@Override
	public void registerModels() {
		// Register custom model
		ModelLoaderRegistry.registerLoader(BetterBucketModel.LoaderDynBucket.instance);

		// BLOCKS
		RenderHelper.renderBlock(ToolsBlocks.black_diamond_ore);
		RenderHelper.renderBlock(ToolsBlocks.black_diamond_block);

		// ITEMS
		RenderHelper.renderItem(ToolsItems.backpack);
		RenderHelper.renderItem(ToolsItems.portable_workbench);
		RenderHelper.renderItem(ToolsItems.loaded_knife);
		RenderHelper.renderItem(ToolsItems.unloaded_knife);
		RenderHelper.renderItem(ToolsItems.ammo_part);
		RenderHelper.renderItem(ToolsItems.button_part);
		RenderHelper.renderItem(ToolsItems.spring_part);
		RenderHelper.renderItem(ToolsItems.casing_part);
		RenderHelper.renderItem(ToolsItems.rod_part);
		RenderHelper.renderItem(ToolsItems.black_diamond);
		RenderHelper.renderItem(ToolsItems.black_diamond_helmet);
		RenderHelper.renderItem(ToolsItems.black_diamond_chestplate);
		RenderHelper.renderItem(ToolsItems.black_diamond_leggings);
		RenderHelper.renderItem(ToolsItems.black_diamond_boots);
		RenderHelper.renderItem(ToolsItems.black_diamond_sword);
		RenderHelper.renderItem(ToolsItems.black_diamond_hoe);
		RenderHelper.renderItem(ToolsItems.black_diamond_axe);
		RenderHelper.renderItem(ToolsItems.black_diamond_shovel);
		RenderHelper.renderItem(ToolsItems.black_diamond_pickaxe);

		setBucketModelDefinition(ToolsItems.wooden_bucket, false);
		setBucketModelDefinition(ToolsItems.stone_bucket, false);
		setBucketModelDefinition(ToolsItems.golden_bucket, false);
		setBucketModelDefinition(ToolsItems.diamond_bucket, false);
		setBucketModelDefinition(ToolsItems.obsidian_bucket, true);

		// ENTITYS
		RenderingRegistry.registerEntityRenderingHandler(EntityBallisticKnife.class, new BallisticKnifeFactory());
	}

	public void setBucketModelDefinition(Item item, boolean pickupFire) {
		final ModelResourceLocation LOCATION = new ModelResourceLocation(new ResourceLocation(GrimTools.modID, item.getUnlocalizedName().substring(5)), "inventory");

		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return LOCATION;
			}
		});
		ModelBakery.registerItemVariants(item, LOCATION);
	}
}
