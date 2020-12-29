package net.minecraft.src;

public class BTAEntitySlime extends FCEntitySlime {
    /** Chances for slimes to spawn in swamps for every moon phase. */
    private static final float[] spawnChances = new float[] {1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
    
	public BTAEntitySlime(World var1) {
		super(var1);
	}

    protected EntitySlime createInstance()
    {
        return new BTAEntitySlime(this.worldObj);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return getCanSpawnHere_do() ? (this.posY < 40.0D ? this.CanSpawnOnBlockInSlimeChunk(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)) : true) : false;
    }

    private boolean CanSpawnOnBlockInSlimeChunk(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);
        return var4 == Block.dirt.blockID || var4 == Block.stone.blockID || var4 == Block.grass.blockID || var4 == Block.gravel.blockID || var4 == Block.sand.blockID;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    private boolean getCanSpawnHere_do()
    {
        Chunk var1 = this.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));

        if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT && this.rand.nextInt(4) != 1)
        {
            return false;
        }
        else
        {
            if (this.getSlimeSize() == 1 || this.worldObj.difficultySetting > 0)
            {
                BiomeGenBase var2 = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));

                if ((var2 == BiomeGenBase.swampland || BTABiomeConfiguration.canBiomeSpawnWitchHut(var2)) && this.posY > 50.0D && this.posY < 256.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < spawnChances[this.worldObj.getMoonPhase()] && this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) <= this.rand.nextInt(8))
                {
                    return super.getCanSpawnHere();
                }

                if (this.rand.nextInt(10) == 0 && var1.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D)
                {
                    return super.getCanSpawnHere();
                }
            }

            return false;
        }
    }
}