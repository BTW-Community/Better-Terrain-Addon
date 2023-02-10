package betterterrain.util;

import betterterrain.BTAMod;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.util.WoodTypeHelper;

public class DecoWoodTypeHelper {
    public static TreeGrowers.TreeWoodType cherryWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.CHERRY_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType whiteCherryWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType acaciaWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.ACACIA_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType mahoganyWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.MAHOGANY_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType mangroveWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.MANGROVE_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType hazelWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.HAZEL_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType firWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.FIR_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType aspenWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.ASPEN_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType willowWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.WILLOW_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType darkOakWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.DARK_OAK_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType redwoodWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.REDWOOD_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType redAutumnWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType orangeAutumnWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE;
        else return null;
    }

    public static TreeGrowers.TreeWoodType yellowAutumnWoodType() {
        if (BTAMod.isDecoInstalled())
            return WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE;
        else return null;
    }
}
