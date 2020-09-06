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
			Class decoDefs = null;
			
			try {
				decoDefs = Class.forName("DecoDefs");
			} catch (ClassNotFoundException e) {
				try {
					decoDefs = Class.forName("net.minecraft.src.DecoDefs");
				} catch (ClassNotFoundException e1) {
					
				}
			}
			
			if (decoDefs != null) {
				isDecoInstalled = true;
				
				redSand = (Block) getDecoField(decoDefs, "redSand");
				redSandStone = (Block) getDecoField(decoDefs, "redSandStone");
				terracotta = (Block) getDecoField(decoDefs, "terracotta");
				stainedTerracotta = (Block) getDecoField(decoDefs, "stainedTerracotta");
				cherryLog = (Block) getDecoField(decoDefs, "cherryLog");
				cherryStump = (Block) getDecoField(decoDefs, "cherryStump");
				cherryLeaves = (Block) getDecoField(decoDefs, "cherryLeaves");
				flower = (Block) getDecoField(decoDefs, "flower");
				flower2 = (Block) getDecoField(decoDefs, "flower2");
				tulip = (Block) getDecoField(decoDefs, "tulip");
				stoneTypes = (Block) getDecoField(decoDefs, "stoneTypes");
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