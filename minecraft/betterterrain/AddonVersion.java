package betterterrain;

import java.util.Set;

import betterterrain.BTAVersion;

public class AddonVersion {
	private final int major;
	private final int minor;
	private final int patch;
	
	public AddonVersion(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
	
	public String toString() {
		return this.major + "." + this.minor + "." + this.patch;
	}
	
	public static AddonVersion fromString(String info, BTAAddon addon) {
		String[] infoSplit = info.split("\\.");
		
		if (infoSplit.length != 3) {
			throw new IllegalArgumentException("Incorrect format for version comnpatibility string. String must be in the format of <X.X.X> where X are integers.");
		}
		
		try {
			for (AddonVersion version : addon.validVersions) {
				if (version.major == Integer.parseInt(infoSplit[0]) && version.minor == Integer.parseInt(infoSplit[1]) && version.patch == Integer.parseInt(infoSplit[2]) ) {
					return version;
				}
			}
			
			throw new IllegalArgumentException("Specified version " + info + " not found for addon " + addon.getName());
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Incorrect format for version comnpatibility string. String must be in the format of <X.X.X> where X are integers.");
		}
	}
	
	public boolean isVersionAtOrBelow(AddonVersion version) {
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
	
	public boolean isVersionAtLeast(AddonVersion version) {
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