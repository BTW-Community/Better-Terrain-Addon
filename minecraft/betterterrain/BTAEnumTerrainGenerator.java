package betterterrain;

public enum BTAEnumTerrainGenerator {
	CLASSIC(0, "Classic"),
	SIMPLEX(1, "BTA"),
	SIMPLEX_OLD(2, "BTA"),
	BETA(3, "Beta"),
	SKYLANDS(4, "Skylands");
	
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
