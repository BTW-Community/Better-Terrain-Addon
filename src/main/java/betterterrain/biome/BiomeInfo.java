package betterterrain.biome;

import net.minecraft.src.BiomeGenBase;

public class BiomeInfo {
	private int id;
    private boolean enabled;
    private boolean decoOnly;

    public BiomeInfo(int id, boolean enabled) {
        this(id, enabled, false);
    }

    public BiomeInfo(int id, boolean enabled, boolean decoOnly) {
        this.id = id;
        this.enabled = enabled;
        this.decoOnly = decoOnly;
    }

    public int getID() {
		return id;
	}

	public String getName() {
        return BiomeGenBase.biomeList[id].biomeName;
    }
	
	public BiomeGenBase getBiome() {
		return BiomeGenBase.biomeList[this.id];
	}

    public boolean isDecoOnly() {
		return decoOnly;
	}

	public boolean getEnabled() {
        return this.enabled;
    }

    public BiomeInfo setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String toString() {
        String var1 = "";

        if (this.enabled) {
            var1 = this.id + "=true";
        }
        else {
            var1 = this.id + "=false";
        }

        return var1;
    }
    
    public BiomeInfo copy() {
    	return new BiomeInfo(this.id, this.enabled, this.decoOnly);
    }
}
