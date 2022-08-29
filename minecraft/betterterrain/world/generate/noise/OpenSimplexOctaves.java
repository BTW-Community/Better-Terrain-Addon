package betterterrain.world.generate.noise;

import java.util.Map;
import java.util.Random;

import opensimplex2.OpenSimplex2;
import opensimplex2.OpenSimplex2F;
import opensimplex2.OpenSimplex2S;

public class OpenSimplexOctaves {
	private OpenSimplex2F[] generators;

	//Cache for previously calculated noise values.
	//All coordinates are local to their chunk
	//1st dimension is x, 2nd is z, 3rd is y
	//4th dimension holds cached information
	//	1st entry is chunkX
	//	2nd entry is chunkZ
	//	3rd entry is noise value
	private double[][][][] noiseCache = new double[16][16][256][3];
	private double lastXZScale = 0;
	private double lastYScale = 0;

	public OpenSimplexOctaves(long seed, int numOctaves) {
		Random rand = new Random();
		rand.setSeed(seed);

		this.generators = new OpenSimplex2F[numOctaves];

		for (int i = 0; i < numOctaves; i++) {
			this.generators[i] = new OpenSimplex2F(rand.nextLong());
		}
	}

	//Included for legacy reasons
	public double noise2(double x, double y) {
		return this.noise2((int) x, (int) y, 1);
	}

	public double noise2(int x, int y) {
		return this.noise2(x, y, 1);
	}

	public double noise2(int x, int y, double noiseScale) {
		float octaveScale = 1;
		double noise = 0;

		for (int i = 0; i < generators.length; i++) {
			noise += generators[i].noise2(x * noiseScale / octaveScale, y * noiseScale / octaveScale) * octaveScale;
			octaveScale /= 2;
		}

		return noise;
	}

	public double noise3(int x, int y, int z, double noiseScale) {
		return this.noise3(x, y, z, noiseScale, noiseScale, true);
	}

	public double noise3(int x, int y, int z, double noiseScaleXZ, double noiseScaleY) {
		return this.noise3(x, y, z, noiseScaleXZ, noiseScaleY, true);
	}

	public double noise3(int x, int y, int z, double noiseScale, boolean useCache) {
		return this.noise3(x, y, z, noiseScale, noiseScale, true);
	}

	public double noise3(int x, int y, int z, double noiseScaleXZ, double noiseScaleY, boolean useCache) {
		float octaveScale = 1;
		double noise = 0;

		//Cache is invalid if parameters change
		if (lastXZScale != noiseScaleXZ || lastYScale != noiseScaleY) {
			flushCache();

			lastXZScale = noiseScaleXZ;
			lastYScale = noiseScaleY;
		}

		if (isValueCached(x, y, z) && useCache) {
			noise = this.getCachedValue(x, y, z);
		}
		else {
			for (int i = 0; i < generators.length; i++) {
				noise += generators[i].noise3_XZBeforeY(x * noiseScaleXZ / octaveScale, y * noiseScaleY / octaveScale, z * noiseScaleXZ / octaveScale) * octaveScale;
				octaveScale /= 2;
			}
			
			if (useCache)
				cacheValue(x, y, z, noise);
		}

		return noise;
	}

	private boolean isValueCached(int x, int y, int z) {
		int i = modIgnoreNegative(x, 16);
		int k = modIgnoreNegative(z, 16);

		double[] cacheMap = noiseCache[i][k][y];

		int chunkX = x < 0 ? x / 16 - 1 : x / 16;
		int chunkZ = z < 0 ? z / 16 - 1 : z / 16;

		return cacheMap[0] == chunkX && cacheMap[1] == chunkZ;
	}

	private void cacheValue(int x, int y, int z, double noise) {
		int i = modIgnoreNegative(x, 16);
		int k = modIgnoreNegative(z, 16);

		int chunkX = x < 0 ? x / 16 - 1 : x / 16;
		int chunkZ = z < 0 ? z / 16 - 1 : z / 16;

		noiseCache[i][k][y] = new double[] {chunkX, chunkZ, noise};
	}

	private double getCachedValue(int x, int y, int z) {
		int i = modIgnoreNegative(x, 16);
		int k = modIgnoreNegative(z, 16);

		return noiseCache[i][k][y][2];
	}

	public void flushCache() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 256; k++) {
					for (int c = 0; c < 3; c++) {
						noiseCache[i][j][k][c] = 0;
					}
				}
			}	
		}
	}

	/**
	 * Same as n % m except cannot produce negative numbers
	 * @param n
	 * @param m
	 * @return
	 */
	private int modIgnoreNegative(int n, int m) {
		return (n % m + m) % m;
	}
}