package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.opensimplex2.OpenSimplex2;
import net.minecraft.src.opensimplex2.OpenSimplex2S;
import net.minecraft.src.opensimplex2.OpenSimplex2S;

public class BTAOpenSimplexOctavesSmooth {
	private OpenSimplex2S[] generators;
	
	public BTAOpenSimplexOctavesSmooth(long seed, int numOctaves) {
		Random rand = new Random();
		rand.setSeed(seed);
		
		this.generators = new OpenSimplex2S[numOctaves];
		
		for (int i = 0; i < numOctaves; i++) {
			this.generators[i] = new OpenSimplex2S(rand.nextLong());
		}
	}
	
	public double noise2(double x, double y) {
		float octaveScale = 1;
		double noise = 0;
		
		for (int i = 0; i < generators.length; i++) {
			noise += generators[i].noise2(x / octaveScale, y / octaveScale) * octaveScale;
			octaveScale /= 2;
		}
		
		return noise;
	}
	
	public double noise2(double x, double y, double treeNoiseScale) {
		float octaveScale = 1;
		double noise = 0;
		
		for (int i = 0; i < generators.length; i++) {
			noise += generators[i].noise2(x * treeNoiseScale / octaveScale, y * treeNoiseScale / octaveScale) * octaveScale;
			octaveScale /= 2;
		}
		
		return noise;
	}
	
	public double noise3(double x, double y, double z) {
		float octaveScale = 1;
		double noise = 0;
		
		for (int i = 0; i < generators.length; i++) {
			noise += generators[i].noise3_XZBeforeY(x / octaveScale, y / octaveScale, z / octaveScale) * octaveScale;
			octaveScale /= 2;
		}
		
		return noise;
	}
	
	public double noise3(double x, double y, double z, double treeNoiseScale) {
		float octaveScale = 1;
		double noise = 0;
		
		for (int i = 0; i < generators.length; i++) {
			noise += generators[i].noise3_XZBeforeY(x * treeNoiseScale / octaveScale, y * treeNoiseScale / octaveScale, z * treeNoiseScale / octaveScale) * octaveScale;
			octaveScale /= 2;
		}
		
		return noise;
	}
}