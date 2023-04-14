package betterbiomes.biome.biomes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TaigaGen5;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class AlpineBiome extends BTABiome {
	public AlpineBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 20;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    public Map<AbstractTreeGrower, Integer> aspenTreeGrowers;
    public Map<AbstractTreeGrower, Integer> decoAspenTreeGrowers;

    public void initTreeGrowerMap() {
        aspenTreeGrowers = new HashMap<>();
        decoAspenTreeGrowers = new HashMap<>();

        this.treeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 1);
        this.treeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 1);

        this.aspenTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 2);
        this.aspenTreeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 2);
        this.aspenTreeGrowers.put(BTATreeGrowers.BIRCH_ASPEN_TREE, 16);

        this.decoTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.BIG_FIR_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.MEDIUM_FIR_TREE, 1);

        this.decoAspenTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 1);
        this.decoAspenTreeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 1);
        this.decoAspenTreeGrowers.put(BTATreeGrowers.BIG_FIR_TREE, 1);
        this.decoAspenTreeGrowers.put(BTATreeGrowers.MEDIUM_FIR_TREE, 1);
        this.decoAspenTreeGrowers.put(BTATreeGrowers.BIRCH_ASPEN_TREE, 10);
        this.decoAspenTreeGrowers.put(BTATreeGrowers.ASPEN_TREE, 6);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new TaigaGen5(false) : new WorldGenTaiga2(false));
    }

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}