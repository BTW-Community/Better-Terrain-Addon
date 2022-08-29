package betterterrain.world.generate;

public enum TerrainGenerator {
	CLASSIC(0, "classic"),
	SIMPLEX(1, "simplex"),
	BETA(2, "beta"),
	SKYLANDS(3, "skylands");
	
	public final int id;
	public final String name;

	TerrainGenerator(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static TerrainGenerator fromId(int id) {
		for (TerrainGenerator gen : values()) {
			if (gen.id == id) {
				return gen;
			}
		}
		
		return null;
	}
	
	public static TerrainGenerator fromName(String name) {
		for (TerrainGenerator gen : values()) {
			if (gen.name.equals(name)) {
				return gen;
			}
		}
		
		return null;
	}
}
