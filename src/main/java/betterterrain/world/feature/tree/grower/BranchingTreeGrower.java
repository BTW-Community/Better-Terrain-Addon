package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.BigTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public class BranchingTreeGrower extends BigTreeGrower {
	protected BranchingTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
		this.setScale(1.5, 1, 0.25);
	}
	
	@Override
	public void generateLeafNode(int x, int y, int z, boolean isWorldGen) {
		int height = y;
		
		for (int maxHeight = y + this.leafDistanceLimit - 2; height < maxHeight; ++height) {
			float leafSize = this.leafSize(height - y);
			this.genTreeLayer(x, height, z, leafSize, (byte) 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
		}
	}
	
	@Override
	public float leafSize(int height) {
		return height >= 0 && height < this.leafDistanceLimit - 2 ? (height != 0 && height != this.leafDistanceLimit - 3 ? 4.0F : 2.75F) : -1.0F;
	}
	
	@Override
	public boolean leafNodeNeedsBase(int height) {
		return true;
	}
}
