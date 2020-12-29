package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTAWorldGenAcacia extends WorldGenerator
{
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** True if this tree should grow Vines. */
	private final boolean vinesGrow;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;

	public BTAWorldGenAcacia(boolean par1)
	{
		this(par1, 4, 0, 0, false);
	}

	public BTAWorldGenAcacia(boolean par1, int par2, int par3, int par4, boolean par5)
	{
		super(par1);
		minTreeHeight = par2;
		metaWood = par3;
		metaLeaves = par4;
		vinesGrow = par5;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z)
	{
		int var5 = par1World.getBlockId(x, y - 1, z);
		if (var5 != Block.grass.blockID)
			return false;
		int rand = 4 + par2Random.nextInt(3);
		for(int i = 0; i < rand; i++) { par1World.setBlock(x, y + i, z, Block.wood.blockID); }

        par1World.setBlockMetadataWithClient(x, y, z, metaWood | 12); // place stump

		if(par2Random.nextInt(4) == 0) { //branch1
			par1World.setBlock(x + 0, y + rand + 1, z + 1, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 2, z + 2, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 2, z + 2, 3);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 3, z + 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch2
			par1World.setBlock(x + 1, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x + 2, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x + 3, y + rand + 2, z - 1, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 1, 3);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 1, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch3
			par1World.setBlock(x - 1, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 2, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 3, y + rand + 2, z - 1, Block.wood.blockID);
			par1World.setBlock(x - 4, y + rand + 3, z - 2, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x - 4, y + rand + 4, z - 2, 3);
			createAcaciaLeaves(par1World, par2Random, x - 4, y + rand + 5, z - 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch4
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 1, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 2, y + rand + 2, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 3, y + rand + 3, z - 2, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 2, 3);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch5
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Block.wood.blockID);
			par1World.setBlock(x + 0, y + rand + 0, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 1, z - 3, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 1, z - 3, 3);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 2, z - 3, 2);
		}

		//branch6
		par1World.setBlock(x - 0, y + rand + 0, z + 0, Block.wood.blockID);
		par1World.setBlock(x - 0, y + rand + 1, z + 0, Block.wood.blockID);
		par1World.setBlock(x - 0, y + rand + 2, z - 0, Block.wood.blockID);
		createAcaciaLeaves(par1World, par2Random, x + 0, y + rand + 3, z - 0, 3);
		createAcaciaLeaves(par1World, par2Random, x + 0, y + rand + 4, z - 0, 2);

		return true;
	}

	private void createAcaciaLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
	{
		for(int x1 = -size + x; x1 < size + 1 + x; x1++)
		{
			for(int z1 = -size + z; z1 < size + 1 + z; z1++)
			{
				int var5 = par1World.getBlockId(x1, y, z1);
				if (var5 == 0)
				{
					if(x1 == -size + x && z1 == -size + z ){} else if(x1 == -size + x && z1 == size + z ){} else if(x1 == size + x && z1 == -size + z ){} else if(x1 == size + x && z1 == size + z ){}
					else { par1World.setBlock(x1, y, z1, Block.leaves.blockID); }
				}
			}
		}
		if(size==3){par1World.setBlock(x, y, z, Block.wood.blockID);}
	}
}
