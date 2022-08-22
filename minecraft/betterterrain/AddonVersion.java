package betterterrain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import betterterrain.BTAVersion;

public class AddonVersion {
	private static final Map<BTAAddon, Set<AddonVersion>> versions = new HashMap();
	
	private final int major;
	private final int minor;
	private final int patch;
	
	public AddonVersion(int major, int minor, int patch, BTAAddon addon) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		
		if (versions.get(addon) == null) {
			versions.put(addon, new HashSet());
		}
		
		versions.get(addon).add(this);
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
			for (AddonVersion version : versions.get(addon)) {
				if (version.major == Integer.parseInt(infoSplit[0]) && version.minor == Integer.parseInt(infoSplit[1]) && version.patch == Integer.parseInt(infoSplit[2]) ) {
					return version;
				}
			}
			
			throw new IllegalArgumentException("Specified version " + info + " not found for addon " + addon.getName());
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Incorrect format for version compatibility string. String must be in the format of <X.X.X> where X are integers.");
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
