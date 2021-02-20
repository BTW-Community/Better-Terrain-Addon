package net.minecraft.src;

public class BTADecoIntegration {
	private static boolean isDecoInstalled = false;
	private static Class decoDefs = null;

	public static Block redSand;
	public static Block redSandStone;
	public static Block terracotta;
	public static Block stainedTerracotta;
	public static Block cherryLog;
	public static Block cherryStump;
	public static Block cherryLeaves;
	public static Block acaciaLog;
	public static Block acaciaStump;
	public static Block acaciaLeaves;
	public static Block flower;
	public static Block flower2;
	public static Block tulip;
	public static Block stoneTypes;
	public static Block autumnLeaves;
	public static Block barkLogStripped;
	public static Block strippedLog;
	public static Block pergola;
	public static Block gateSpruce;
	public static Block doorSpruce;
	public static Block trapdoorSpruce;
	public static Block trapdoorCherry;
	public static Block cherryStairs;
	public static Block glassStained;
	public static Block stoneTypesStoneBrick;
	public static Block stoneTypesSmooth;
	public static Block graniteBrickSidingAndCorner;
	public static Block graniteBrickMouldingAndDecorative;
	public static Block graniteSmoothSidingAndCorner;
	public static Block graniteSmoothMouldingAndDecorative;
	public static Block graniteBrickStairs;
	public static Block barrelEmpty;
	public static Block woodSlab;
	public static Block carpet;
	public static Block oakWoodChair;
	public static Block hedgeOakSidingAndCorner;
	public static Block barkLog;
	public static Block stoneSlab2;
	public static Block cherrySidingAndCorner;
	public static Block andesiteSmoothStairs;
	public static Block andesiteSmoothMouldingandDecorative;
	
	public static Item pileRedSand;
	
	public static void init() {
		try {
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
				
				redSand = (Block) getDecoField("redSand");
				redSandStone = (Block) getDecoField("redSandStone");
				terracotta = (Block) getDecoField("terracotta");
				stainedTerracotta = (Block) getDecoField("stainedTerracotta");
				cherryLog = (Block) getDecoField("cherryLog");
				cherryStump = (Block) getDecoField("cherryStump");
				cherryLeaves = (Block) getDecoField("cherryLeaves");
				acaciaLog = (Block) getDecoField("acaciaLog");
				acaciaStump = (Block) getDecoField("acaciaStump");
				acaciaLeaves = (Block) getDecoField("acaciaLeaves");
				flower = (Block) getDecoField("flower");
				flower2 = (Block) getDecoField("flower2");
				tulip = (Block) getDecoField("tulip");
				stoneTypes = (Block) getDecoField("stoneTypes");
				autumnLeaves = (Block) getDecoField("autumnLeaves");
				barkLogStripped = (Block) getDecoField("barkLogStripped");
				strippedLog = (Block) getDecoField("strippedLog");
				pergola = (Block) getDecoField("pergola");
				gateSpruce = (Block) getDecoField("gateSpruce");
				doorSpruce = (Block) getDecoField("doorSpruce");
				trapdoorSpruce = (Block) getDecoField("trapdoorSpruce");
				trapdoorCherry = (Block) getDecoField("trapdoorCherry");
				cherryStairs = (Block) getDecoField("cherryStairs");
				glassStained = (Block) getDecoField("glassStained");
				stoneTypesStoneBrick = (Block) getDecoField("stoneTypesStoneBrick");
				stoneTypesSmooth = (Block) getDecoField("stoneTypesSmooth");
				graniteBrickSidingAndCorner = ((Block[]) getDecoField("stoneTypesStoneBrickSidingAndCorner"))[0];
				graniteBrickMouldingAndDecorative = ((Block[]) getDecoField("stoneTypesStoneBrickMouldingAndDecorative"))[0];
				graniteSmoothSidingAndCorner = ((Block[]) getDecoField("stoneTypesSmoothSidingAndCorner"))[0];
				graniteSmoothMouldingAndDecorative = ((Block[]) getDecoField("stoneTypesSmoothMouldingAndDecorative"))[0];
				graniteBrickStairs = ((Block[]) getDecoField("stoneTypesStoneBrickStairs"))[0];
				barrelEmpty = (Block) getDecoField("barrelEmpty");
				woodSlab = (Block) getDecoField("woodSlab");
				carpet = (Block) getDecoField("carpet");
				oakWoodChair = (Block) getDecoField("oakWoodChair");
				hedgeOakSidingAndCorner = (Block) getDecoField("hedgeOakSidingAndCorner");
				barkLog = (Block) getDecoField("barkLog");
				stoneSlab2 = (Block) getDecoField("stoneSlab2");
				cherrySidingAndCorner = (Block) getDecoField("cherrySidingAndCorner");
				andesiteSmoothStairs = ((Block[]) getDecoField("stoneTypesSmoothStairs"))[1];
				andesiteSmoothMouldingandDecorative = ((Block[]) getDecoField("stoneTypesSmoothMouldingAndDecorative"))[1];
				
				pileRedSand = (Item) getDecoField("pileRedSand");
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
	
	private static Object getDecoField(String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return decoDefs.getDeclaredField(fieldName).get(null);
	}
	
	public static boolean isDecoInstalled() {
		return isDecoInstalled;
	}
}