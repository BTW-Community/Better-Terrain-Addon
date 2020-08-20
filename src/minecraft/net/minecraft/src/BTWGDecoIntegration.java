package net.minecraft.src;

public class BTWGDecoIntegration {
	private static boolean isDecoInstalled = false;

	public static Block redSand;
	public static Block redSandStone;
	public static Block cherryLog;
	public static Block cherryStump;
	public static Block cherryLeaves;
	public static Block flower;
	public static Block flower2;
	
	public static void init() {
		try {
			Class addonManager = Class.forName("AddonManager");
			
			if (addonManager != null) {
				isDecoInstalled = true;
				
				redSand = (Block) getDecoField(addonManager, "redSand");
				redSandStone = (Block) getDecoField(addonManager, "redSandStone");
				cherryLog = (Block) getDecoField(addonManager, "cherryLog");
				cherryStump = (Block) getDecoField(addonManager, "cherryStump");
				cherryLeaves = (Block) getDecoField(addonManager, "cherryLeaves");
				flower = (Block) getDecoField(addonManager, "flower");
				flower2 = (Block) getDecoField(addonManager, "flower2");
			}
		} catch (ClassNotFoundException e) {
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private static Object getDecoField(Class addonManager, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return addonManager.getDeclaredField(fieldName).get(null);
	}
	
	public static boolean isDecoInstalled() {
		return isDecoInstalled;
	}
}