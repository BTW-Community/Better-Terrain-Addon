package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.opensimplex2.OpenSimplex2F;

public class BTAOpenSimplexOctaves {
	private OpenSimplex2F[] generators;

	public BTAOpenSimplexOctaves(long seed, int numOctaves) {
		Random rand = new Random();
		rand.setSeed(seed);
		
		this.generators = new OpenSimplex2F[numOctaves];
		
		for (int i = 0; i < numOctaves; i++) {
			this.generators[i] = new OpenSimplex2F(rand.nextLong());
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
}