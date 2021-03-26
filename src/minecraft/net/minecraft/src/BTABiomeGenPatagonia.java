package net.minecraft.src;

public class BTABiomeGenPatagonia extends BTABiomeGenBase {
	protected BTABiomeGenPatagonia(int id) {
		super(id);
		this.btaiomeDecorator.grassPerChunk = 30;
		this.btaiomeDecorator.sandPerChunk = 0;
		this.btaiomeDecorator.sandPerChunk2 = 0;
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 15064968;
    }
}