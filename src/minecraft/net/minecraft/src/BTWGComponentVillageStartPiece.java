package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Random;

public class BTWGComponentVillageStartPiece extends ComponentVillageStartPiece {
	public BTWGComponentVillageStartPiece(WorldChunkManager par1WorldChunkManager, int par2, Random par3Random, int par4, int par5, ArrayList par6ArrayList, int par7) {
		super(par1WorldChunkManager, par2, par3Random, par4, par5, par6ArrayList, par7);

        BiomeGenBase var8 = par1WorldChunkManager.getBiomeGenAt(par4, par5);
        setInDesert(BTWGBiomeConfiguration.canBiomeSpawnDesertTemple(var8));
	}
	
	//Uses reflection to modify final field "inDesert"
	//Second attempt is for if it is obfuscated, which changes the variable name
	private void setInDesert(boolean desert) {
        Field fieldInDesert = null;
		try {
			fieldInDesert = this.getClass().getSuperclass().getDeclaredField("inDesert");
		} catch (NoSuchFieldException e) {
			try {
				fieldInDesert = this.getClass().getSuperclass().getDeclaredField("b");
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        
        Field modifiersField;
		try {
			modifiersField = Field.class.getDeclaredField( "modifiers" );
			modifiersField.setAccessible(true);
			modifiersField.setInt(fieldInDesert, fieldInDesert.getModifiers() & ~Modifier.FINAL);
			
			fieldInDesert.set(this, desert);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
