package com.grim3212.mc.pack.industry.block;

import java.util.Random;

import com.grim3212.mc.pack.core.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIceMaker extends Block {

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 15);

	protected BlockIceMaker() {
		super(Material.ROCK);
		setTickRandomly(true);
		setSoundType(SoundType.STONE);
		setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STAGE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE });
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		if (!worldIn.isRemote) {
			int l = 2;

			double temp = worldIn.getBiomeGenForCoords(pos).getFloatTemperature(pos);
			if (temp < 0.5D) {
				l = 10;
			}

			int meta = worldIn.getBlockState(pos).getValue(STAGE);
			if (meta >= 5) {
				l = (l + (15 - meta)) - l;
			}
			if (meta == 0) {
				worldIn.setBlockState(pos, state, 2);
			} else if (meta == 15) {
				worldIn.setBlockState(pos, state.withProperty(STAGE, 15), 2);
			} else {
				worldIn.setBlockState(pos, state.withProperty(STAGE, meta + l), 2);
			}
		}
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (((Integer) worldIn.getBlockState(pos).getValue(STAGE)) == 15) {
			if (!worldIn.isRemote) {
				worldIn.setBlockState(pos, getDefaultState(), 2);
				float f = 0.7F;
				double d = (double) (worldIn.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
				double d1 = (double) (worldIn.rand.nextFloat() * f) + (double) (1.0F - f) * 0.20000000000000001D + 0.59999999999999998D;
				double d2 = (double) (worldIn.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
				EntityItem entityitem = new EntityItem(worldIn, (double) pos.getX() + d, (double) pos.getY() + d1, (double) pos.getZ() + d2, new ItemStack(Blocks.ICE, worldIn.rand.nextInt(5) + 2));
				entityitem.setPickupDelay(5);
				worldIn.spawnEntityInWorld(entityitem);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (heldItem == null) {
			return false;
		}

		if (heldItem.getItem() == Items.WATER_BUCKET) {
			if (state.getValue(STAGE) == 0) {
				worldIn.setBlockState(pos, state.withProperty(STAGE, 1), 3);
				Utils.consumeInventoryItem(playerIn, heldItem.getItem());
				playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET));
			}
		}
		// TODO: Reimplement this
		// else if (heldItem.getItem() instanceof IFluidContainerItem) {
		// if (state.getValue(STAGE) == 0) {
		// IFluidContainerItem fluidBucket = (IFluidContainerItem)
		// heldItem.getItem();
		// if (fluidBucket.getFluid(heldItem).getFluid() != null) {
		// if (fluidBucket.getFluid(heldItem).getFluid() == FluidRegistry.WATER)
		// {
		// worldIn.setBlockState(pos, state.withProperty(STAGE, 1), 3);
		// fluidBucket.drain(heldItem, Fluid.BUCKET_VOLUME, true);
		// }
		// }
		// }
		// }

		return true;
	}
}