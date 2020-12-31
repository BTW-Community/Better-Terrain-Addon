package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenPalmTreeSmall extends WorldGenerator {
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** True if this tree should grow Vines. */
	private final boolean vinesGrow;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;
	
	private final boolean spawnCocoa;

	public BTAWorldGenPalmTreeSmall(boolean par1, boolean spawnCocoa) {
		this(par1, 6, 3, 3, false, spawnCocoa);
	}

	public BTAWorldGenPalmTreeSmall(boolean par1, int par2, int par3, int par4, boolean par5, boolean spawnCocoa)
	{
		super(par1);
		this.minTreeHeight = par2;
		this.metaWood = par3;
		this.metaLeaves = par4;
		this.vinesGrow = par5;
		this.spawnCocoa = spawnCocoa;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;

		int treeHeight = this.minTreeHeight + rand.nextInt(3);
		//How many times the trunk shifts to the side, controls the angle of the trunk
		int trunkShifts = 1 + rand.nextInt(2);
		//Which direction the trunk leans
		int leanDirection = rand.nextInt(4);
		int leanX = 0;
		int leanZ = 0;
		int[] trunkShiftHeights;

		switch (leanDirection) {
		//North
		case 0:
			leanX = 0;
			leanZ = -1;
			break;
			//West
		case 1:
			leanX = -1;
			leanZ = 0;
			break;
			//South
		case 2:
			leanX = 0;
			leanZ = 1;
			break;
			//East
		case 3:
			leanX = 1;
			leanZ = 0;
			break;
		default:
		}
		
		if (!checkForValidTreeSpawn(world, x, y, z, treeHeight, trunkShifts, leanX, leanZ))
			return false;

		//Gets the heights at which to split the trunk
		if (trunkShifts == 1) {
			trunkShiftHeights = new int[2];

			if (treeHeight == 6) {
				trunkShiftHeights[0] = 3;
				trunkShiftHeights[1] = 3;
			}
			else if (treeHeight == 7) {
				trunkShiftHeights[0] = 4;
				trunkShiftHeights[1] = 3;
			}
			else if (treeHeight == 8) {
				trunkShiftHeights[0] = 4;
				trunkShiftHeights[1] = 4;
			}
		}
		else {
			trunkShiftHeights = new int[3];

			if (treeHeight == 6) {
				trunkShiftHeights[0] = 2;
				trunkShiftHeights[1] = 2;
				trunkShiftHeights[2] = 2;
			}
			else if (treeHeight == 7) {
				trunkShiftHeights[0] = 3;
				trunkShiftHeights[1] = 2;
				trunkShiftHeights[2] = 2;
			}
			else if (treeHeight == 8) {
				trunkShiftHeights[0] = 3;
				trunkShiftHeights[1] = 3;
				trunkShiftHeights[2] = 2;
			}
		}

		int height = y;

		for (int i = 0; i < trunkShifts + 1; i++) {
			for (int j = 0; j < trunkShiftHeights[i]; j++) {
				world.setBlockAndMetadata(x + leanX * i, height, z + leanZ * i, Block.wood.blockID, metaWood);
				
				if (rand.nextInt(5) == 0) {
					int facing = rand.nextInt(4) + 2;
					
					FCUtilsBlockPos pos = new FCUtilsBlockPos(x + leanX * i, height, z + leanZ * i);
					pos.AddFacingAsOffset(facing);
					
					int meta = 0;
					
					if (facing - 2 == 1) {
						meta = 2;
					}
					else if (facing - 2 == 2) {
						meta = 3;
					}
					else if (facing - 2 == 3) {
						meta = 1;
					}
					
					world.setBlockAndMetadata(pos.i, pos.j, pos.k, Block.cocoaPlant.blockID, meta);
				}
				
				height++;
			}
		}

		//Places a stump
		world.setBlockMetadataWithClient(x, y, z, metaWood | 12);

		generatePalmLeaves(world, x + leanX * trunkShifts, height, z + leanZ * trunkShifts);

		return true;
	}

	public void generatePalmLeaves(World world, int x, int y, int z) {
		//Central leaves
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				world.setBlockAndMetadata(x + i, y, z+ j, Block.leaves.blockID, metaLeaves);
			}
		}
		
		//Lower leaves around log
		world.setBlockAndMetadata(x + 1, y - 1, z, Block.leaves.blockID, metaLeaves);
		world.setBlockAndMetadata(x, y - 1, z + 1, Block.leaves.blockID, metaLeaves);
		world.setBlockAndMetadata(x - 1, y - 1, z, Block.leaves.blockID, metaLeaves);
		world.setBlockAndMetadata(x, y - 1, z - 1, Block.leaves.blockID, metaLeaves);
		
		//Hanging leaves - uses metadata to not despawn
		for (int i = 2; i < 5; i++) {
			int j = 0;
			int k = 0;
			
			if (i == 3)
				k = 1;
			if (i == 4)
				j = 1;
			
			//Sides
			world.setBlockAndMetadata(x + i, y - j, z, Block.leaves.blockID, metaLeaves | 4);
			world.setBlockAndMetadata(x, y - j, z + i, Block.leaves.blockID, metaLeaves | 4);
			world.setBlockAndMetadata(x - i, y - j, z, Block.leaves.blockID, metaLeaves | 4);
			world.setBlockAndMetadata(x, y - j, z - i, Block.leaves.blockID, metaLeaves | 4);
			
			//Corners
			if (i < 4) {
				world.setBlockAndMetadata(x + i, y - k, z + i, Block.leaves.blockID, metaLeaves | 4);
				world.setBlockAndMetadata(x - i, y - k, z + i, Block.leaves.blockID, metaLeaves | 4);
				world.setBlockAndMetadata(x + i, y - k, z - i, Block.leaves.blockID, metaLeaves | 4);
				world.setBlockAndMetadata(x - i, y - k, z - i, Block.leaves.blockID, metaLeaves | 4);
			}
		}
	}
	
	//Checks to make sure the space is clear for a tree to spawn
	private boolean checkForValidTreeSpawn(World world, int x, int y, int z, int treeHeight, int trunkShifts, int leanX, int leanZ) {
		leanX *= trunkShifts;
		leanZ *= trunkShifts;
		
		//Checks trunk
		
		
		//Checks leaves
		for (int i = -4 + leanX; i < 5 + leanZ; i++) {
			for (int j = -1; j < 1; j++) {
				for (int k = -4 + leanZ; k < 5 + leanZ; k++) {
					if (!world.isAirBlock(i + x, j + y + treeHeight, k + z)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}