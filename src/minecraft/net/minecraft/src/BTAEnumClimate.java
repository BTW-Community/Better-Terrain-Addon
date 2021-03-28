package net.minecraft.src;

public enum BTAEnumClimate {
	SNOWY(-1),
	COLD(-2),
	TEMPERATE(-3),
	TROPICAL(-4),
	ARID(-5);
	
	public final int id;
	
	private BTAEnumClimate(int id) {
		this.id = id;
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