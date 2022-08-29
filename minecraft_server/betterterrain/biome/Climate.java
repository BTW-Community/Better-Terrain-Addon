package betterterrain.biome;

public enum Climate {
	SNOWY(-1, 0, true),
	COLD(-2, 95, true),
	TEMPERATE(-3, 256, true),
	TROPICAL(-4, 256, true),
	ARID(-5, 256, true),
	HELL(-6, 256, false);
	
	public final int id;
	public final int minHeightForSnow;
	public final boolean isOverworld;
	
	private Climate(int id, int minHeightForSnow, boolean isOverworld) {
		this.id = id;
		this.minHeightForSnow = minHeightForSnow;
		this.isOverworld = isOverworld;
	}
	
	public static Climate fromID(int id) {
		for (Climate c : Climate.values()) {
			if (c.id == id) {
				return c;
			}
		}
		
		return null;
	}
	
	public boolean hasSnowVariance() {
		return this.minHeightForSnow != 0 && this.minHeightForSnow != 256;
	}
}