package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class AspenTreeGrower extends AbstractTreeGrower {
	protected AspenTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		while (world.isAirBlock(x, y, z) && y > 2) {
			--y;
		}
		
		int var6 = world.getBlockId(x, y, z);
		Block blockBelow = Block.blocksList[var6];
		
		if (blockBelow == null || !blockBelow.canSaplingsGrowOnBlock(world, x, y, z)) {
			return false;
		}
		else {
			for (int var7 = -2; var7 <= 2; ++var7) {
				for (int var8 = -2; var8 <= 2; ++var8) {
					if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8) &&
							!world.isAirBlock(x + var7, y, z + var8)) {
						return false;
					}
				}
			}
			
			this.setBlockAndMetadata(world, x, y + 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 2, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 3, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 4, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 5, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 6, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 7, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 8, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 9, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 10, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 11, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			
			this.setBlockAndMetadata(world, x, y + 1, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
			
			this.setBlockAndMetadata(world, x + 1, y + 10, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 1, y + 10, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 10, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 10, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, y + 10, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, y + 10, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 1, y + 10, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 1, y + 10, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			
			this.setBlockAndMetadata(world, x + 1, y + 11, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 1, y + 11, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 11, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, y + 11, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			
			this.setBlockAndMetadata(world, x, y + 12, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			
			int var99 = rand.nextInt(2);
			
			if (var99 == 0) {
				this.setBlockAndMetadata(world, x + 1, y + 6, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 6, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 6, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 6, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				
				this.setBlockAndMetadata(world, x + 1, y + 8, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 8, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 8, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 8, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			}
			
			if (var99 == 1) {
				this.setBlockAndMetadata(world, x + 1, y + 6, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				
				this.setBlockAndMetadata(world, x + 1, y + 6, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 6, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 6, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 6, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				
				this.setBlockAndMetadata(world, x + 1, y + 8, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 1, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				
				this.setBlockAndMetadata(world, x - 1, y + 8, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 8, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x - 2, y + 8, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y + 8, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 8, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 2, y + 8, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
			}
			
			return true;
		}
	}
}
