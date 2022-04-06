package betterterrain;

public enum BTAEnumClimate {
	SNOWY(-1, 0, true),
	COLD(-2, 95, true),
	TEMPERATE(-3, 256, true),
	TROPICAL(-4, 256, true),
	ARID(-5, 256, true),
	HELL(-6, 256, false);
	
	public final int id;
	public final int minHeightForSnow;
	public final boolean isOverworld;
	
	private BTAEnumClimate(int id, int minHeightForSnow, boolean isOverworld) {
		this.id = id;
		this.minHeightForSnow = minHeightForSnow;
		this.isOverworld = isOverworld;
	}
	
	public static BTAEnumClimate fromID(int id) {
		for (BTAEnumClimate c : BTAEnumClimate.values()) {
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