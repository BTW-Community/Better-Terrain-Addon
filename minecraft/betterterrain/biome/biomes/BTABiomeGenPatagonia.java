package betterterrain.biome.biomes;

import betterterrain.BTAEnumClimate;

public class BTABiomeGenPatagonia extends BTABiomeGenBase {
	public BTABiomeGenPatagonia(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 15064968;
    }
}