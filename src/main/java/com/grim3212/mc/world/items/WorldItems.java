package com.grim3212.mc.world.items;

import java.util.List;

import com.grim3212.mc.core.part.IPartItems;
import com.grim3212.mc.core.util.RecipeHelper;
import com.grim3212.mc.world.GrimWorld;
import com.grim3212.mc.world.blocks.WorldBlocks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class WorldItems implements IPartItems {

	public static Item gunpowder_reed_item;
	public static Item fungicide;

	@Override
	public void initItems() {
		gunpowder_reed_item = (new ItemGunpowderReed(WorldBlocks.gunpowder_reed_block)).setUnlocalizedName("gunpowder_reed_item").setCreativeTab(GrimWorld.INSTANCE.getCreativeTab());
		fungicide = (new ItemFungicide()).setUnlocalizedName("fungicide");

		GameRegistry.registerItem(gunpowder_reed_item, "gunpowder_reed_item");
		GameRegistry.registerItem(fungicide, "fungicide");
	}

	public static List<IRecipe> greed;

	@Override
	public void addRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.gunpowder, 1), new Object[] { "X", 'X', gunpowder_reed_item }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(gunpowder_reed_item, 1), new Object[] { "XXX", "XWX", "XXX", 'X', "gunpowder", 'W', Items.reeds }));
		greed = RecipeHelper.getLatestIRecipes(2);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fungicide, 1, 0), new Object[] { "xkx", " x ", 'x', "ingotIron", 'k', "dyeBlack" }));
	}

}
