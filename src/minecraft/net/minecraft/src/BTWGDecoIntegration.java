package net.minecraft.src;

public class BTWGDecoIntegration {
	private static boolean isDecoInstalled = false;

	public static Block redSand;
	public static Block redSandStone;
	public static Block terracotta;
	public static Block stainedTerracotta;
	public static Block cherryLog;
	public static Block cherryStump;
	public static Block cherryLeaves;
	public static Block flower;
	public static Block flower2;
	public static Block tulip;
	public static Block stoneTypes;
	
	public static void init() {
		try {
			Class addonDefs = null;
			
			try {
				addonDefs = Class.forName("AddonDefs");
			} catch (ClassNotFoundException e) {
				try {
					addonDefs = Class.forName("net.minecraft.src.AddonDefs");
				} catch (ClassNotFoundException e1) {
					
				}
			}
			
			if (addonDefs != null) {
				isDecoInstalled = true;
				
				redSand = (Block) getDecoField(addonDefs, "redSand");
				redSandStone = (Block) getDecoField(addonDefs, "redSandStone");
				terracotta = (Block) getDecoField(addonDefs, "terracotta");
				stainedTerracotta = (Block) getDecoField(addonDefs, "stainedTerracotta");
				cherryLog = (Block) getDecoField(addonDefs, "cherryLog");
				cherryStump = (Block) getDecoField(addonDefs, "cherryStump");
				cherryLeaves = (Block) getDecoField(addonDefs, "cherryLeaves");
				flower = (Block) getDecoField(addonDefs, "flower");
				flower2 = (Block) getDecoField(addonDefs, "flower2");
				tulip = (Block) getDecoField(addonDefs, "tulip");
				stoneTypes = (Block) getDecoField(addonDefs, "stoneTypes");
			}
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
	
	private static Object getDecoField(Class addonDefs, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return addonDefs.getDeclaredField(fieldName).get(null);
	}
	
	public static boolean isDecoInstalled() {
		return isDecoInstalled;
	}
}