package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.BigTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

import java.util.Random;

public class HugeTreeGrower extends BigTreeGrower {
	protected HugeTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
		this.setScale(1, 1, 1);
		this.trunkSize = 2;
	}

	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		if (super.growTree(world, rand, x, y, z, isWorldGen)) {
			if (this.trunkSize == 2) {
				this.setBlockAndMetadata(world, x + 1, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x, y, z + 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
				this.setBlockAndMetadata(world, x + 1, y, z + 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
			}

			return true;
		}

		return false;
	}
}
