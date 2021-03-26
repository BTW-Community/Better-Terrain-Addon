package net.minecraft.src;

public class BTABiomeInfo {
	private int id;
    private boolean enabled;
    private boolean decoOnly;

    public BTABiomeInfo(int id, boolean enabled) {
        this(id, enabled, false);
    }

    public BTABiomeInfo(int id, boolean enabled, boolean decoOnly) {
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

    public BTABiomeInfo setEnabled(boolean enabled) {
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
}
