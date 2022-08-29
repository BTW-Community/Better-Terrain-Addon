package betterterrain.structure.component;

import java.util.Random;

import betterterrain.DecoIntegration;
import net.minecraft.src.Block;
import net.minecraft.src.ComponentScatteredFeature;
import net.minecraft.src.Direction;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.FCUtilsHardcoreSpawn;
import net.minecraft.src.Item;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.WeightedRandomChestContent;
import net.minecraft.src.World;

public class BTAComponentScatteredFeatureRedDesertPyramid extends ComponentScatteredFeature
{
    private boolean[] field_74940_h = new boolean[4];
    private static final WeightedRandomChestContent[] m_LootListArray = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.helmetGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateGold.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.legsGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsGold.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.swordGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.emerald.itemID, 0, 1, 5, 15), new WeightedRandomChestContent(Item.bone.itemID, 0, 4, 6, 20), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 3, 7, 11), new WeightedRandomChestContent(Item.skull.itemID, 0, 1, 1, 5)};
    private static final WeightedRandomChestContent[] m_LootedLootListArray = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.bone.itemID, 0, 4, 6, 20), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 3, 7, 11), new WeightedRandomChestContent(Item.skull.itemID, 0, 1, 1, 5)};

    public BTAComponentScatteredFeatureRedDesertPyramid(Random par1Random, int par2, int par3)
    {
        super(par1Random, par2, 64, par3, 21, 15, 21);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        boolean var4 = FCUtilsHardcoreSpawn.IsInLootedTempleRadius(par1World, par3StructureBoundingBox.getCenterX(), par3StructureBoundingBox.getCenterZ());
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        int var5;

        for (var5 = 1; var5 <= 9; ++var5)
        {
            this.fillWithBlocks(par1World, par3StructureBoundingBox, var5, var5, var5, this.scatteredFeatureSizeX - 1 - var5, var5, this.scatteredFeatureSizeZ - 1 - var5, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, var5 + 1, var5, var5 + 1, this.scatteredFeatureSizeX - 2 - var5, var5, this.scatteredFeatureSizeZ - 2 - var5, 0, 0, false);
        }

        int var6;

        for (var5 = 0; var5 < this.scatteredFeatureSizeX; ++var5)
        {
            for (var6 = 0; var6 < this.scatteredFeatureSizeZ; ++var6)
            {
                this.fillCurrentPositionBlocksDownwards(par1World, DecoIntegration.redSandStone.blockID, 0, var5, -5, var6, par3StructureBoundingBox);
            }
        }

        var5 = this.getMetadataWithOffset(DecoIntegration.redSandStoneStairs.blockID, 3);
        var6 = this.getMetadataWithOffset(DecoIntegration.redSandStoneStairs.blockID, 2);
        int var7 = this.getMetadataWithOffset(DecoIntegration.redSandStoneStairs.blockID, 0);
        int var8 = this.getMetadataWithOffset(DecoIntegration.redSandStoneStairs.blockID, 1);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 9, 4, DecoIntegration.redSandStone.blockID, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 10, 1, 3, 10, 3, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var5, 2, 10, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var6, 2, 10, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var7, 0, 10, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var8, 4, 10, 2, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 5, 0, 0, this.scatteredFeatureSizeX - 1, 9, 4, DecoIntegration.redSandStone.blockID, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 4, 10, 1, this.scatteredFeatureSizeX - 2, 10, 3, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var5, this.scatteredFeatureSizeX - 3, 10, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var6, this.scatteredFeatureSizeX - 3, 10, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var7, this.scatteredFeatureSizeX - 5, 10, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var8, this.scatteredFeatureSizeX - 1, 10, 2, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 0, 0, 12, 4, 4, DecoIntegration.redSandStone.blockID, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, 0, 11, 3, 4, 0, 0, false);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 9, 1, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 9, 2, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 9, 3, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 10, 3, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 11, 3, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 11, 2, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 11, 1, 1, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 8, 3, 3, DecoIntegration.redSandStone.blockID, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 2, 8, 2, 2, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 12, 1, 1, 16, 3, 3, DecoIntegration.redSandStone.blockID, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 12, 1, 2, 16, 2, 2, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 4, 5, this.scatteredFeatureSizeX - 6, 4, this.scatteredFeatureSizeZ - 6, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 4, 9, 11, 4, 11, 0, 0, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 8, 8, 3, 8, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 8, 12, 3, 8, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 12, 8, 3, 12, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 12, 12, 3, 12, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 4, 4, 11, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 5, 1, 5, this.scatteredFeatureSizeX - 2, 4, 11, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 7, 9, 6, 7, 11, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 7, 7, 9, this.scatteredFeatureSizeX - 7, 7, 11, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 5, 9, 5, 7, 11, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 6, 5, 9, this.scatteredFeatureSizeX - 6, 7, 11, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 5, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 6, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 6, 6, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, this.scatteredFeatureSizeX - 6, 5, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, this.scatteredFeatureSizeX - 6, 6, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, this.scatteredFeatureSizeX - 7, 6, 10, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 4, 4, 2, 6, 4, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 3, 4, 4, this.scatteredFeatureSizeX - 3, 6, 4, 0, 0, false);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var5, 2, 4, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var5, 2, 3, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var5, this.scatteredFeatureSizeX - 3, 4, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var5, this.scatteredFeatureSizeX - 3, 3, 4, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 3, 2, 2, 3, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 3, 1, 3, this.scatteredFeatureSizeX - 2, 2, 3, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, 0, 1, 1, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, 0, this.scatteredFeatureSizeX - 2, 1, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 1, 1, 2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 1, this.scatteredFeatureSizeX - 2, 2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var8, 2, 1, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStoneStairs.blockID, var7, this.scatteredFeatureSizeX - 3, 1, 2, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 5, 4, 3, 18, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 5, 3, 5, this.scatteredFeatureSizeX - 5, 3, 17, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 5, 4, 2, 16, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, this.scatteredFeatureSizeX - 6, 1, 5, this.scatteredFeatureSizeX - 5, 2, 16, 0, 0, false);
        int var9;

        for (var9 = 5; var9 <= 17; var9 += 2)
        {
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 4, 1, var9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, 4, 2, var9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, this.scatteredFeatureSizeX - 5, 1, var9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, this.scatteredFeatureSizeX - 5, 2, var9, par3StructureBoundingBox);
        }

        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 10, 0, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 10, 0, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 9, 0, 9, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 11, 0, 9, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 8, 0, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 12, 0, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 7, 0, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 13, 0, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 9, 0, 11, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 11, 0, 11, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 10, 0, 12, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 10, 0, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 10, 0, 10, par3StructureBoundingBox);

        for (var9 = 0; var9 <= this.scatteredFeatureSizeX - 1; var9 += this.scatteredFeatureSizeX - 1)
        {
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 2, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 2, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 2, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 3, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 3, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 4, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, var9, 4, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 4, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 5, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 5, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 5, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 6, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, var9, 6, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 6, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 7, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 7, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 7, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 8, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 8, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 8, 3, par3StructureBoundingBox);
        }

        for (var9 = 2; var9 <= this.scatteredFeatureSizeX - 3; var9 += this.scatteredFeatureSizeX - 3 - 2)
        {
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 - 1, 2, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 2, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 + 1, 2, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 - 1, 3, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 3, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 + 1, 3, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9 - 1, 4, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, var9, 4, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9 + 1, 4, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 - 1, 5, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 5, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 + 1, 5, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9 - 1, 6, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, var9, 6, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9 + 1, 6, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9 - 1, 7, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9, 7, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, var9 + 1, 7, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 - 1, 8, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9, 8, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, var9 + 1, 8, 0, par3StructureBoundingBox);
        }

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 4, 0, 12, 6, 0, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 6, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, 6, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 9, 5, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, 10, 5, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.obsidian.blockID, 0, 11, 5, 0, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, -14, 8, 12, -11, 12, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, -10, 8, 12, -10, 12, DecoIntegration.redSandStone.blockID, 1, DecoIntegration.redSandStone.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, -9, 8, 12, -9, 12, DecoIntegration.redSandStone.blockID, 2, DecoIntegration.redSandStone.blockID, 2, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, -8, 8, 12, -1, 12, DecoIntegration.redSandStone.blockID, DecoIntegration.redSandStone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, -11, 9, 11, -1, 11, 0, 0, false);
        this.placeBlockAtCurrentPosition(par1World, Block.pressurePlatePlanks.blockID, 0, 10, -11, 10, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, -13, 9, 11, -13, 11, Block.tnt.blockID, 0, false);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, -11, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, -10, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, 7, -10, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 7, -11, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, -11, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, -10, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, 13, -10, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 13, -11, 10, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, -11, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, -10, 8, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, 10, -10, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 10, -11, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, -11, 12, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, -10, 12, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 1, 10, -10, 13, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, DecoIntegration.redSandStone.blockID, 2, 10, -11, 13, par3StructureBoundingBox);
        int var10;
        int var11;

        for (var9 = 0; var9 < 4; ++var9)
        {
            if (!this.field_74940_h[var9])
            {
                var10 = Direction.offsetX[var9] * 2;
                var11 = Direction.offsetZ[var9] * 2;
                WeightedRandomChestContent[] var12 = m_LootListArray;
                int var13 = 2 + par2Random.nextInt(5);

                if (var4)
                {
                    var12 = m_LootedLootListArray;
                    var13 /= 2;
                }

                WeightedRandomChestContent[] var14 = WeightedRandomChestContent.func_92080_a(var12, new WeightedRandomChestContent[] {Item.enchantedBook.func_92114_b(par2Random)});
                this.field_74940_h[var9] = this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 10 + var10, -11, 10 + var11, var14, var13);
            }
        }

        if (var4)
        {
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 0, 9, 10, 0, 10, 0, 0, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, -13, 9, 11, -11, 11, 0, 0, false);
            var10 = this.getMetadataWithOffset(Block.ladder.blockID, 5);
            var11 = FCBetterThanWolves.fcBlockLadder.SetFacing(0, var10);

            for (int var15 = -13; var15 <= 0; ++var15)
            {
                this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockLadder.blockID, var11, 9, var15, 9, par3StructureBoundingBox);
            }
        }
        else
        {
            this.placeBlockAtCurrentPosition(par1World, Block.enchantmentTable.blockID, 0, 10, 1, 10, par3StructureBoundingBox);
        }

        return true;
    }
}
