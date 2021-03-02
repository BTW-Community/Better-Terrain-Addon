package net.minecraft.src;

import java.util.Random;

public class BTAHorizonsNoiseGeneratorTerrain extends BTAHorizonsNoiseFieldGenerator {
	private Random rand;
	
	private BTAHorizonsNoiseOctaves biomeHeightNoiseGen;
	private BTAHorizonsNoiseOctaves blockModifierNoiseGen;
	private BTAHorizonsNoiseOctaves blockNoiseGen1;
	private BTAHorizonsNoiseOctaves blockNoiseGen2;
	
	private double[] biomeHeightNoise;
	private double[] blockModifierNoise;
	private double[] blockNoise1;
	private double[] blockNoise2;
	
	//Used for smoothing between biomes
	private float[] parabolicField;
	private int parabolicRadius;
	
	public BTAHorizonsNoiseGeneratorTerrain(Random rand) {
		this.rand = rand;
		
		this.biomeHeightNoiseGen = new BTAHorizonsNoiseOctaves(this.rand, 16);
		this.blockModifierNoiseGen = new BTAHorizonsNoiseOctaves(this.rand, 8);
		this.blockNoiseGen1 = new BTAHorizonsNoiseOctaves(this.rand, 16);
		this.blockNoiseGen2 = new BTAHorizonsNoiseOctaves(this.rand, 16);
	}
	
	public double[] initializeNoiseField(double[] noiseArray, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ, BiomeGenBase[] biomesForGeneration) {
		if (noiseArray == null) {
			noiseArray = new double[sizeX * sizeY * sizeZ];
		}

		//Parabolic field is used for smoothing between biomes
		int parabolicDiameter = parabolicRadius * 2 + 1;
		
		if (this.parabolicField == null) {
			this.parabolicField = new float[(int) Math.pow(parabolicDiameter, 2)];

			for (int i = -parabolicRadius; i <= parabolicRadius; ++i) {
				for (int k = -parabolicRadius; k <= parabolicRadius; ++k) {
					float parabolicValue = 10.0F / MathHelper.sqrt_float((float)(i * i + k * k) + 0.2F);
					this.parabolicField[i + parabolicRadius + (k + parabolicRadius) * parabolicDiameter] = parabolicValue;
				}
			}
		}

		double octaveScalarXZ = 684.412D;
		double octaveScalarY = 684.412D;
		this.biomeHeightNoise = this.biomeHeightNoiseGen.generateNoiseOctaves(this.biomeHeightNoise, posX, posZ, sizeX, sizeZ, 200.0D, 200.0D, 0.5D);
		this.blockModifierNoise = this.blockModifierNoiseGen.generateNoiseOctaves(this.blockModifierNoise, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ / 80.0D, octaveScalarY / 160.0D, octaveScalarXZ / 80.0D);
		this.blockNoise1 = this.blockNoiseGen1.generateNoiseOctaves(this.blockNoise1, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		this.blockNoise2 = this.blockNoiseGen2.generateNoiseOctaves(this.blockNoise2, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		int biomeHeightNoiseIndex = 0;
		int blockNoiseIndex = 0;

		//Iterates through the entire noise array for population
		//Loops first through x and z, getting biome height data, then iterates through y values
		for (int i = 0; i < sizeX; ++i) {
			for (int k = 0; k < sizeZ; ++k) {
				float biomeMaxHeightSample = 0.0F;
				float biomeMinHeightSample = 0.0F;
				float biomeModifierTotal = 0.0F;
				BiomeGenBase biome = biomesForGeneration[i + parabolicRadius + (k + parabolicRadius) * (sizeX + parabolicDiameter)];

				//Gets biome height values influenced by neighboring biomes
				//Weights provided by the parabolic field
				for (int c = -parabolicRadius; c <= parabolicRadius; ++c) {
					for (int d = -parabolicRadius; d <= parabolicRadius; ++d) {
						BiomeGenBase biomeNeighbor = biomesForGeneration[i + c + parabolicRadius + (k + d + parabolicRadius) * (sizeX + parabolicDiameter)];
						float biomeHeightModifierValue = this.parabolicField[c + parabolicRadius + (d + parabolicRadius) * parabolicDiameter] / (biomeNeighbor.minHeight + 2.0F);

						if (biomeNeighbor.minHeight > biome.minHeight) {
							biomeHeightModifierValue /= 2.0F;
						}

						biomeMaxHeightSample += biomeNeighbor.maxHeight * biomeHeightModifierValue;
						biomeMinHeightSample += biomeNeighbor.minHeight * biomeHeightModifierValue;
						biomeModifierTotal += biomeHeightModifierValue;
					}
				}

				biomeMaxHeightSample /= biomeModifierTotal;
				biomeMinHeightSample /= biomeModifierTotal;
				biomeMaxHeightSample = biomeMaxHeightSample * 0.9F + 0.1F;
				biomeMinHeightSample = (biomeMinHeightSample * 4.0F - 1.0F) / 8.0F;
				
				//Gets the value from the biome noise array to modify the biome height values above
				double biomeHeightNoiseModifier = this.biomeHeightNoise[biomeHeightNoiseIndex] / 8000.0D;
				biomeHeightNoiseIndex++;
				
				if (biomeHeightNoiseModifier < 0.0D) {
					biomeHeightNoiseModifier = -biomeHeightNoiseModifier * 0.3D;
				}

				biomeHeightNoiseModifier = biomeHeightNoiseModifier * 3.0D - 2.0D;

				if (biomeHeightNoiseModifier < 0.0D) {
					biomeHeightNoiseModifier /= 2.0D;

					if (biomeHeightNoiseModifier < -1.0D){
						biomeHeightNoiseModifier = -1.0D;
					}

					biomeHeightNoiseModifier /= 2.8D;
				}
				else {
					if (biomeHeightNoiseModifier > 1.0D) {
						biomeHeightNoiseModifier = 1.0D;
					}

					biomeHeightNoiseModifier /= 8.0D;
				}

				//After biome height information has been processed, iterates through y values
				for (int j = 0; j < sizeY; ++j) {
					//Modifies biome height for x/z based on the current y value
					double biomeMinHeightDouble = (double) biomeMinHeightSample;
					double biomeMaxHeightDouble = (double) biomeMaxHeightSample;
					biomeMinHeightDouble += biomeHeightNoiseModifier * 0.2D;
					biomeMinHeightDouble = biomeMinHeightDouble * (double) sizeY / 16.0D;
					double biomeMinHeightModified = (double) sizeY / 2.0D + biomeMinHeightDouble * 4.0D;
					double noiseSample = 0.0D;
					double biomeHeightValue = ((double) j - biomeMinHeightModified) * 12.0D / biomeMaxHeightDouble;

					if (biomeHeightValue < 0.0D) {
						biomeHeightValue *= 4.0D;
					}

					//Samples the noise fields at the current location
					double blockNoiseModifierSample = (this.blockModifierNoise[blockNoiseIndex] / 10.0D + 1.0D) / 2.0D;
					
					double blockNoiseSample1 = this.blockNoise1[blockNoiseIndex] / 512.0D;
					double blockNoiseSample2 = this.blockNoise2[blockNoiseIndex] / 512.0D;
					
					//Combines block noise fields depending on the value of the modifier sample
					if (blockNoiseModifierSample < 0.0D) {
						noiseSample = blockNoiseSample1;
					}
					else if (blockNoiseModifierSample > 1.0D) {
						noiseSample = blockNoiseSample2;
					}
					else {
						noiseSample = blockNoiseSample1 + (blockNoiseSample2 - blockNoiseSample1) * blockNoiseModifierSample;
					}

					//Factors biome height calculations into final noise values
					noiseSample -= biomeHeightValue;

					//Modifies noise at the top of the section
					if (j > sizeY - 4) {
						double sectionHeightModifier = (double)((float)(j - (sizeY - 4)) / 3.0F);
						noiseSample = noiseSample * (1.0D - sectionHeightModifier) + -10.0D * sectionHeightModifier;
					}

					noiseArray[blockNoiseIndex] = noiseSample;
					blockNoiseIndex++;
				}
			}
		}

		return noiseArray;
	}
}
