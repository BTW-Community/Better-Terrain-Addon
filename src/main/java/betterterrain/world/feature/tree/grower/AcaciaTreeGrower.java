package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class AcaciaTreeGrower extends AbstractTreeGrower {
	protected AcaciaTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int baseHeight = this.minTreeHeight + rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1);
		
		//Base tree
		for(int i = 1; i < baseHeight + 4; i++) {
			int blockID = world.getBlockId(x, y + i, z);
			
			//Checks trunk space
			if (!(this.isReplaceable(world, x, y + i, z) || blockID == DecoBlocks.acaciaLeaves.blockID)) {
				return false;
			}
		}
		
		for(int i = 0; i < baseHeight + 3; i++) {
			this.setBlockAndMetadata(world, x, y + i, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 3, z - 0, 3, isWorldGen);
		createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 4, z - 0, 2, isWorldGen);
		
		this.setBlockAndMetadata(world, x, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		
		//Branches
		if(rand.nextInt(4) == 0 &&
				this.isReplaceable(world, x + 0, y + baseHeight + 1, z + 1) &&
				this.isReplaceable(world, x + 1, y + baseHeight + 2, z + 2))
		{
			this.setBlockAndMetadata(world, x + 0, y + baseHeight + 1, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, y + baseHeight + 2, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z + 2, 3, isWorldGen);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 3, z + 2, 2, isWorldGen);
		}
		
		if(rand.nextInt(4) == 0 &&
				this.isReplaceable(world, x + 1, y + baseHeight + 0, z + 0) &&
				this.isReplaceable(world, x + 2, y + baseHeight + 1, z + 0) &&
				this.isReplaceable(world, x + 3, y + baseHeight + 2, z + 1))
		{
			this.setBlockAndMetadata(world, x + 1, y + baseHeight + 0, z + 0, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 2, y + baseHeight + 1, z + 0, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 3, y + baseHeight + 2, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 1, 3, isWorldGen);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 1, 2, isWorldGen);
		}
		
		if(rand.nextInt(4) == 0 &&
				this.isReplaceable(world, x - 1, y + baseHeight + 0, z + 0) &&
				this.isReplaceable(world, x - 2, y + baseHeight + 1, z + 0) &&
				this.isReplaceable(world, x - 3, y + baseHeight + 2, z - 1) &&
				this.isReplaceable(world, x - 4, y + baseHeight + 3, z - 2))
		{
			this.setBlockAndMetadata(world, x - 1, y + baseHeight + 0, z + 0, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 2, y + baseHeight + 1, z + 0, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 3, y + baseHeight + 2, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x - 4, y + baseHeight + 3, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 4, z - 2, 3, isWorldGen);
			createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 5, z - 2, 2, isWorldGen);
		}
		
		if(rand.nextInt(4) == 0 &&
				this.isReplaceable(world, x + 0, y + baseHeight + 0, z - 1) &&
				this.isReplaceable(world, x + 1, y + baseHeight + 1, z - 1) &&
				this.isReplaceable(world, x + 2, y + baseHeight + 2, z - 2) &&
				this.isReplaceable(world, x + 3, y + baseHeight + 3, z - 2))
		{
			this.setBlockAndMetadata(world, x + 0, y + baseHeight + 0, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, y + baseHeight + 1, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 2, y + baseHeight + 2, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 3, y + baseHeight + 3, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 2, 3, isWorldGen);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 2, 2, isWorldGen);
		}
		
		if(rand.nextInt(4) == 0 &&
				this.isReplaceable(world, x + 0, y + baseHeight + 0, z - 1) &&
				this.isReplaceable(world, x + 0, y + baseHeight + 0, z - 2) &&
				this.isReplaceable(world, x + 1, y + baseHeight + 1, z - 3))
		{
			this.setBlockAndMetadata(world, x + 0, y + baseHeight + 0, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 0, y + baseHeight + 0, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, y + baseHeight + 1, z - 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 1, z - 3, 3, isWorldGen);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z - 3, 2, isWorldGen);
		}
		
		return true;
	}
	
	private void createAcaciaLeaves(World world, Random rand, int x, int y, int z, int size, boolean isWorldGen) {
		for (int i = -size + x; i < size + 1 + x; i++) {
			for (int k = -size + z; k < size + 1 + z; k++) {
				int currentID = world.getBlockId(i, y, k);
				
				if (currentID == 0) {
					if (!((i == -size + x && k == -size + z) ||
							(i == -size + x && k == size + z) ||
							(i == size + x && k == -size + z) ||
							(i == size + x && k == size + z)))
					{
						this.setBlockAndMetadata(world, i, y, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					}
				}
			}
		}
		
		if (size == 3) {
			this.setBlockAndMetadata(world, x, y, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
	}
}
