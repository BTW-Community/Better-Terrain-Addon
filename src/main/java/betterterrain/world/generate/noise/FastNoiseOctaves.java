package betterterrain.world.generate.noise;

import java.util.Random;

import opensimplex2.OpenSimplex2;
import opensimplex2.OpenSimplex2F;
import opensimplex2.OpenSimplex2S;

public class FastNoiseOctaves {
	private FastNoise[] generators;
	
	public FastNoiseOctaves(long seed, int numOctaves) {
		Random rand = new Random();
		rand.setSeed(seed);
		
		this.generators = new FastNoise[numOctaves];
		
		for (int i = 0; i < numOctaves; i++) {
			this.generators[i] = new FastNoise(rand.nextInt());
		}
	}
	
	public double noise3(double x, double y, double z, double noiseScale) {
		return this.noise3(x, y, z, noiseScale, noiseScale);
	}
	
	public double noise3(double x, double y, double z, double noiseScaleXZ, double noiseScaleY) {
		float octaveScale = 1;
		double noise = 0;
		
		for (int i = 0; i < generators.length; i++) {
			noise += generators[i].SinglePerlin(generators[i].GetSeed(), (float) (x * noiseScaleXZ / octaveScale), (float) (z * noiseScaleXZ / octaveScale), (float) (y * noiseScaleY / octaveScale));
			octaveScale /= 2;
		}
		
		//Perlin noise has output range of (-sqrt(n / 4), sqrt(n/4)) where n is the number of dimensions.
		//Here the value is normalized to the range (-1, 1).
		return noise;// / (Math.sqrt(3) / 2);
	}
}