package betterbiomes.biome.biomes;

import java.util.ArrayList;
import java.util.Random;

import betterbiomes.world.feature.tree.legacy.AcaciaGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenerator;

public class IvoryHillsBiome extends BTABiome {
	private ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();

	public IvoryHillsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
		
		this.btaBiomeDecorator.treesPerChunk = 3;
		this.btaBiomeDecorator.flowersPerChunk = -999;
        this.btaBiomeDecorator.generateLakes = false;
	}

	public void initTreeGrowerMap() {
		this.decoTreeGrowers.put(BTATreeGrowers.ACACIA_TREE, 1);
	}

	@Override
	public boolean shouldConnectWithEdge(WorldConfigurationInfo generatorOptions) {
		return true;
	}

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random rand) {
    	return new AcaciaGen(false);
    }

	@Override
	public boolean canLightningStrikeInBiome() {
		return true;
	}
}