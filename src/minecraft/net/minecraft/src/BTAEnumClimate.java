package net.minecraft.src;

public enum BTAEnumClimate {
	SNOWY(-1, 0),
	COLD(-2, 95),
	TEMPERATE(-3, 256),
	TROPICAL(-4, 256),
	ARID(-5, 256);
	
	public final int id;
	public final int minHeightForSnow;
	
	private BTAEnumClimate(int id, int minHeightForSnow) {
		this.id = id;
		this.minHeightForSnow = minHeightForSnow;
	}
	
	public static BTAEnumClimate fromID(int id) {
		for (BTAEnumClimate c : BTAEnumClimate.values()) {
			if (c.id == id) {
				return c;
			}
		}
		
		return null;
	}
}