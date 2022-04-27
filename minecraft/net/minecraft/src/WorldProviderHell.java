package net.minecraft.src;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.BTANetherBiome;

public class WorldProviderHell extends WorldProvider
{
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
    	WorldType worldType = this.worldObj.worldInfo.getTerrainType();
    	
    	System.out.println(worldType.getTranslateName());
    	
    	if (worldType.isDeco()) {
        	this.worldChunkMgr = new WorldChunkManagerHell(BetterBiomesConfiguration.netherWastes, 1.0F, 0.0F);
        	//this.worldChunkMgr = new WorldChunkManagerHell(BTABiomeConfiguration.crystalCaverns, 1.0F, 0.0F);
    	}
    	else {
    		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
    	}
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    public Vec3 getFogColor(float par1, float par2, int x, int y, int z)
    {
        BiomeGenBase biome =  this.worldObj.getBiomeGenForCoords(x, z);
        
        if (biome instanceof BTANetherBiome) {
        	Vec3[][] biomeColors = new Vec3[5][5];
        	
        	for (int i = 0; i < 5; i++) {
        		for (int j = 0; j < 5; j++) {
        			biomeColors[i][j] = ((BTANetherBiome) this.worldObj.getBiomeGenForCoords(x - 2 + i, z - 2 + j)).getFogColor(this.worldObj);
        		}
        	}
        	
        	Vec3 color = this.worldObj.getWorldVec3Pool().getVecFromPool(0, 0, 0);
        	
        	for (int i = 0; i < 5; i++) {
        		for (int j = 0; j < 5; j++) {
        			color.xCoord += biomeColors[i][j].xCoord / 25;
        			color.yCoord += biomeColors[i][j].yCoord / 25;
        			color.zCoord += biomeColors[i][j].zCoord / 25;
        		}
        	}
        	
        	return color;
        }
        else {
        	return this.worldObj.getWorldVec3Pool().getVecFromPool(0.2, 0.03, 0.03);
        }
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
        float var1 = 0.1F;

        for (int var2 = 0; var2 <= 15; ++var2)
        {
            float var3 = 1.0F - (float)var2 / 15.0F;
            this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
    	return this.terrainType.getChunkProviderNether(this.worldObj, this.worldObj.getSeed(), this.generatorOptions);
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    public boolean doesXZShowFog(int par1, int par2)
    {
        return true;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Nether";
    }
}
