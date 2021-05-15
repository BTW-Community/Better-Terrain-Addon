package net.minecraft.src;

public enum BTAEnumTerrainGenerator {
	CLASSIC(0, "Classic"),
	SIMPLEX(1, "BTA"),
	BETA(2, "Beta"),
	SKYLANDS(3, "Skylands");
	
	public final int id;
	public final String name;

	BTAEnumTerrainGenerator(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static BTAEnumTerrainGenerator fromId(int id) {
		for (BTAEnumTerrainGenerator gen : values()) {
			if (gen.id == id) {
				return gen;
			}
		}
		
		return null;
	}
}
