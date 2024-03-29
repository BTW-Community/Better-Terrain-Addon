package betterterrain;

public enum BTAVersion {
	V1_1_3(1, 1, 3),
	
	V1_2_0(1, 2, 0),
	V1_2_1(1, 2, 1),
	
	V1_3_0(1, 3, 0),
	V1_3_1(1, 3, 1),
	V1_3_2(1, 3, 2),
	V1_3_3(1, 3, 3),
	V1_3_4(1, 3, 4),
	
	V1_4_0(1, 4, 0),
	V1_4_1(1, 4, 1),
	V1_4_2(1, 4, 2),
	V1_4_3(1, 4, 3),
	V1_4_4(1, 4, 4),
	
	V2_0_0(2, 0, 0),
	V2_0_1(2, 0, 1),
	V2_0_2(2, 0, 2),
	V2_0_3(2, 0, 3),
	V2_0_4(2, 0, 4),
	V2_0_5(2, 0, 5),
	V2_0_6(2, 0, 6),
	V2_0_7(2, 0, 7),
	V2_0_8(2, 0, 8),
	
	V3_0_0(3, 0, 0),

	V3_1_0(3, 1, 0),

	V3_2_0(3, 2, 0),
	V3_2_1(3, 2, 1),
	V3_2_2(3, 2, 2);
	
	private final int major;
	private final int minor;
	private final int patch;
	
	BTAVersion(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
	
	public String toString() {
		return this.major + "." + this.minor + "." + this.patch;
	}
	
	public static BTAVersion fromString(String info) {
		String[] infoSplit = info.split("\\.");
		
		if (infoSplit.length != 3) {
			throw new IllegalArgumentException("Incorrect format for version comnpatibility string. String must be in the format of <X.X.X> where X are integers.");
		}
		
		try {
			for (BTAVersion version : BTAVersion.values()) {
				if (version.major == Integer.parseInt(infoSplit[0]) && version.minor == Integer.parseInt(infoSplit[1]) && version.patch == Integer.parseInt(infoSplit[2]) ) {
					return version;
				}
			}
			
			throw new IllegalArgumentException("Specified version " + info + " not found.");
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Incorrect format for version comnpatibility string. String must be in the format of <X.X.X> where X are integers.");
		}
	}
	
	public boolean isVersionAtOrBelow(BTAVersion version) {
		if (this.major > version.major) {
			return false;
		}
		else if (this.major == version.major) {
			if (this.minor > version.minor) {
				return false;
			}
			else if (this.minor == version.minor) {
				if (this.patch > version.patch) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean isVersionAtLeast(BTAVersion version) {
		if (this.major < version.major) {
			return false;
		}
		else if (this.major == version.major) {
			if (this.minor < version.minor) {
				return false;
			}
			else if (this.minor == version.minor) {
				if (this.patch < version.patch) {
					return false;
				}
			}
		}
		
		return true;
	}
}
