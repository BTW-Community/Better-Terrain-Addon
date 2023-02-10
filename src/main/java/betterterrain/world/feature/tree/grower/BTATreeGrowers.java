package betterterrain.world.feature.tree.grower;

import betterterrain.util.DecoWoodTypeHelper;
import btw.world.feature.trees.grower.*;

public class BTATreeGrowers {
    //------ Oak ------//

    public static final AbstractTreeGrower OAK_BUSH = new BushGrower("betterterrain:oak_bush", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower SMALL_OAK_SHRUB = new SmallShrubGrower("betterterrain:small_oak_shrub", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower TINY_OAK_SHRUB = new TinyShrubGrower("betterterrain:tiny_oak_shrub", TreeGrowers.OAK_WOOD_TYPE);

    public static final AbstractTreeGrower TALL_OAK_TREE = new StandardTreeGrower("betterterrain:tall_oak", 6, 8, TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower HUGE_OAK_TREE = new HugeTreeGrower("betterterrain:huge_oak", 12, 21, TreeGrowers.OAK_WOOD_TYPE);

    public static final AbstractTreeGrower TALL_SWAMP_OAK_TREE = new SwampTreeGrower("betterterrain:tall_swamp_oak", 8, 13, TreeGrowers.OAK_WOOD_TYPE);

    public static final AbstractTreeGrower FALLEN_OAK_TREE = new FallenLogGrower("betterterrain:fallen_oak", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower BIG_DEAD_OAK_TREE = new BigDeadTreeGrower("betterterrain:big_dead_oak", 5, 16, TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower HUGE_DEAD_OAK_TREE = new HugeDeadTreeGrower("betterterrain:huge_dead_oak", 12, 21, TreeGrowers.OAK_WOOD_TYPE);

    public static final AbstractTreeGrower OAK_ACACIA_TREE = new AcaciaTreeGrower("betterterrain:oak_acacia", 4, 6, TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower OAK_ASPEN = new AspenTreeGrower("betterterrain:oak_aspen", 6, 10, TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower HUGE_OAK_WILLOW_TREE = new HugeWillowTreeGrower("betterterrain:huge_oak_willow", 9, 12, TreeGrowers.OAK_WOOD_TYPE, false);
    public static final AbstractTreeGrower OAK_REDWOOD_TREE = new TallTaigaTreeGrower("betterterrain:oak_redwood", 13, 21, TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower HUGE_OAK_REDWOOD_TREE = new HugeRedwoodTreeGrower("betterterrain:huge_oak_redwood", 20, 35, TreeGrowers.OAK_WOOD_TYPE);

    //------ Spruce ------//

    public static final AbstractTreeGrower SPRUCE_BUSH = new BushGrower("betterterrain:spruce_bush", TreeGrowers.SPRUCE_WOOD_TYPE);
    public static final AbstractTreeGrower SMALL_SPRUCE_SHRUB = new SmallShrubGrower("betterterrain:small_spruce_shrub", TreeGrowers.SPRUCE_WOOD_TYPE);

    public static final AbstractTreeGrower MEDIUM_SPRUCE_TREE = new MediumTaigaTreeGrower("betterterrain:medium_spruce", 9, 17, TreeGrowers.SPRUCE_WOOD_TYPE); // BTA Taiga 5
    public static final AbstractTreeGrower TALL_SPRUCE_TREE = new TallTaigaTreeGrower("betterterrain:tall_spruce", 13, 21, TreeGrowers.SPRUCE_WOOD_TYPE);      // BTA Taiga 6
    public static final AbstractTreeGrower BIG_SPRUCE_TREE = new BigTaigaTreeGrower("betterterrain:big_spruce", 10, 19, TreeGrowers.SPRUCE_WOOD_TYPE);		   // BTA Taiga 7
    public static final AbstractTreeGrower HUGE_SPRUCE_TREE = new HugeTaigaTreeGrower("betterterrain:huge_spruce", 20, 45, TreeGrowers.SPRUCE_WOOD_TYPE);

    public static final AbstractTreeGrower MEADOW_SPRUCE_TREE = new TemperateTreeGrower("betterterrain:meadow_spruce", 7, 9, TreeGrowers.SPRUCE_WOOD_TYPE);

    //------ Birch ------//

    public static final AbstractTreeGrower TALL_BIRCH_TREE = new StandardTreeGrower("betterterrain:tall_birch", 6, 8, TreeGrowers.BIRCH_WOOD_TYPE);
    public static final AbstractTreeGrower BIG_BIRCH_TREE = new BranchingTreeGrower("betterterrain:big_birch", 10, 20, TreeGrowers.BIRCH_WOOD_TYPE);

    public static final AbstractTreeGrower TEMPERATE_BIRCH_TREE = new TemperateTreeGrower("betterterrain:temperate_birch", 7, 12, TreeGrowers.BIRCH_WOOD_TYPE);

    public static final AbstractTreeGrower BIRCH_ASPEN_TREE = new AspenTreeGrower("betterterrain:birch_aspen", 6, 10, TreeGrowers.BIRCH_WOOD_TYPE);

    //------ Jungle ------//

    public static final AbstractTreeGrower JUNGLE_BUSH = new BushGrower("betterterrain:jungle_bush", TreeGrowers.JUNGLE_WOOD_TYPE);

    public static final AbstractTreeGrower PALM_TREE = new PalmTreeGrower("betterterrain:palm", 6, 8, TreeGrowers.JUNGLE_WOOD_TYPE, false);
    public static final AbstractTreeGrower PALM_TREE_COCOA = new PalmTreeGrower("betterterrain:palm_cocoa", 6, 8, TreeGrowers.JUNGLE_WOOD_TYPE, true);

    public static final AbstractTreeGrower JUNGLE_MAHOGANY_TREE = new MahoganyTreeGrower("betterterrain:jungle_mahogany", 12, 17, TreeGrowers.JUNGLE_WOOD_TYPE);

    //------ Cherry ------//

    public static final AbstractTreeGrower CHERRY_TREE = new StandardTreeGrower("betterterrain:cherry", 5, 7, DecoWoodTypeHelper.cherryWoodType());
    public static final AbstractTreeGrower BIG_CHERRY_TREE = new BigTreeGrower("betterterrain:big_cherry", 5, 16, DecoWoodTypeHelper.cherryWoodType());
    public static final AbstractTreeGrower HUGE_CHERRY_TREE = new HugeTreeGrower("betterterrain:huge_cherry", 12, 21, DecoWoodTypeHelper.cherryWoodType());

    public static final AbstractTreeGrower WHITE_CHERRY_TREE = new StandardTreeGrower("betterterrain:white_cherry", 5, 7, DecoWoodTypeHelper.whiteCherryWoodType());
    public static final AbstractTreeGrower BIG_WHITE_CHERRY_TREE = new BigTreeGrower("betterterrain:big_white_cherry", 5, 16, DecoWoodTypeHelper.whiteCherryWoodType());
    public static final AbstractTreeGrower HUGE_WHITE_CHERRY_TREE = new HugeTreeGrower("betterterrain:huge_white_cherry", 12, 21, DecoWoodTypeHelper.whiteCherryWoodType());

    //------ Acacia ------//

    public static final AbstractTreeGrower ACACIA_TREE = new AcaciaTreeGrower("betterterrain:acacia", 4, 6, DecoWoodTypeHelper.acaciaWoodType());

    //------ Mahogany ------//

    public static final AbstractTreeGrower MAHOGANY_TREE = new MahoganyTreeGrower("betterterrain:mahogany", 12, 17, DecoWoodTypeHelper.mahoganyWoodType());

    //------ Mangrove ------//

    public static final AbstractTreeGrower OAK_MANGROVE_TREE = new MangroveTreeGrower("betterterrain:oak_mangrove", 9, 13, TreeGrowers.OAK_WOOD_TYPE);

    public static final AbstractTreeGrower MANGROVE_TREE = new MangroveTreeGrower("betterterrain:mangrove", 9, 13, DecoWoodTypeHelper.mangroveWoodType());

    //------ Hazel ------//

    public static final AbstractTreeGrower HAZEL_TREE = new StandardTreeGrower("betterterrain:hazel", 5, 7, DecoWoodTypeHelper.hazelWoodType());
    public static final AbstractTreeGrower BIG_HAZEL_TREE = new BigTreeGrower("betterterrain:big_hazel", 5, 16, DecoWoodTypeHelper.hazelWoodType());
    public static final AbstractTreeGrower HUGE_HAZEL_TREE = new HugeTreeGrower("betterterrain:huge_hazel", 12, 21, DecoWoodTypeHelper.hazelWoodType());

    //------ Fir ------//

    public static final AbstractTreeGrower SMALL_FIR_SHRUB = new SmallShrubGrower("betterterrain:small_fir_shrub", DecoWoodTypeHelper.firWoodType());

    public static final AbstractTreeGrower FIR_TREE = new TaigaTreeGrower("betterterrain:fir", 6, 9, DecoWoodTypeHelper.firWoodType());
    public static final AbstractTreeGrower MEDIUM_FIR_TREE = new MediumTaigaTreeGrower("betterterrain:medium_fir", 9, 17, DecoWoodTypeHelper.firWoodType());
    public static final AbstractTreeGrower TALL_FIR_TREE = new TallTaigaTreeGrower("betterterrain:tall_fir", 13, 21, DecoWoodTypeHelper.firWoodType());
    public static final AbstractTreeGrower BIG_FIR_TREE = new BigTaigaTreeGrower("betterterrain:big_fir", 10, 19, DecoWoodTypeHelper.firWoodType());
    public static final AbstractTreeGrower HUGE_FIR_TREE = new HugeTaigaTreeGrower("betterterrain:huge_fir", 20, 45, DecoWoodTypeHelper.firWoodType());

    //------ Aspen ------//

    public static final AbstractTreeGrower ASPEN_TREE = new AspenTreeGrower("betterterrain:aspen", 6, 10, DecoWoodTypeHelper.aspenWoodType());

    //------ Willow ------//

    public static final AbstractTreeGrower WILLOW_TREE = new SwampTreeGrower("betterterrain:willow", 5, 8, DecoWoodTypeHelper.willowWoodType());
    public static final AbstractTreeGrower HUGE_WILLOW_TREE = new HugeWillowTreeGrower("betterterrain:huge_willow", 9, 12, DecoWoodTypeHelper.willowWoodType(), true);

    //------ Dark Oak ------//

    public static final AbstractTreeGrower DARK_OAK_TREE = new BranchingTreeGrower("betterterrain:dark_oak", 10, 20, DecoWoodTypeHelper.darkOakWoodType());
    public static final AbstractTreeGrower HUGE_DARK_OAK_TREE = new HugeDarkOakTreeGrower("betterterrain:huge_dark_oak", 7, 18, DecoWoodTypeHelper.darkOakWoodType());

    public static final AbstractTreeGrower MEADOW_DARK_OAK_TREE = new TemperateTreeGrower("betterterrain:meadow_dark_oak", 7, 12, DecoWoodTypeHelper.darkOakWoodType());
    public static final AbstractTreeGrower TALL_DARK_OAK_TREE = new TallTaigaTreeGrower("betterterrain:tall_dark_oak", 13, 21, DecoWoodTypeHelper.darkOakWoodType());

    public static final AbstractTreeGrower DARK_OAK_HIGHLANDS_TREE = new BigTaigaTreeGrower("betterterrain:dark_oak_highlands", 10, 19, DecoWoodTypeHelper.darkOakWoodType());

    //------ Redwood ------//

    public static final AbstractTreeGrower SMALL_REDWOOD_SHRUB = new SmallShrubGrower("betterterrain:small_redwood_shrub", DecoWoodTypeHelper.redwoodWoodType());

    public static final AbstractTreeGrower REDWOOD_TREE = new TallTaigaTreeGrower("betterterrain:redwood", 13, 21, DecoWoodTypeHelper.redwoodWoodType());
    public static final AbstractTreeGrower HUGE_REDWOOD_TREE = new HugeRedwoodTreeGrower("betterterrain:huge_redwood", 20, 35, DecoWoodTypeHelper.redwoodWoodType());

    //------ Autumn ------//

    public static final AbstractTreeGrower RED_AUTUMN_TREE = new StandardTreeGrower("betterterrain:red_autumn", 5, 7, DecoWoodTypeHelper.redAutumnWoodType());
    public static final AbstractTreeGrower BIG_RED_AUTUMN_TREE = new BigTreeGrower("betterterrain:big_red_autumn", 5, 16, DecoWoodTypeHelper.redAutumnWoodType());
    public static final AbstractTreeGrower HUGE_RED_AUTUMN_TREE = new HugeTreeGrower("betterterrain:huge_red_autumn", 12, 21, DecoWoodTypeHelper.redAutumnWoodType());

    public static final AbstractTreeGrower ORANGE_AUTUMN_TREE = new StandardTreeGrower("betterterrain:orange_autumn", 5, 7, DecoWoodTypeHelper.orangeAutumnWoodType());
    public static final AbstractTreeGrower BIG_ORANGE_AUTUMN_TREE = new BigTreeGrower("betterterrain:big_orange_autumn", 5, 16, DecoWoodTypeHelper.orangeAutumnWoodType());
    public static final AbstractTreeGrower HUGE_ORANGE_AUTUMN_TREE = new HugeTreeGrower("betterterrain:huge_orange_autumn", 12, 21, DecoWoodTypeHelper.orangeAutumnWoodType());

    public static final AbstractTreeGrower YELLOW_AUTUMN_TREE = new StandardTreeGrower("betterterrain:yellow_autumn", 5, 7, DecoWoodTypeHelper.yellowAutumnWoodType());
    public static final AbstractTreeGrower BIG_YELLOW_AUTUMN_TREE = new BigTreeGrower("betterterrain:big_yellow_autumn", 5, 16, DecoWoodTypeHelper.yellowAutumnWoodType());
    public static final AbstractTreeGrower HUGE_YELLOW_AUTUMN_TREE = new HugeTreeGrower("betterterrain:huge_yellow_autumn", 12, 21, DecoWoodTypeHelper.yellowAutumnWoodType());

    public static final AbstractTreeGrower RED_HIGHLANDS_TREE = new BigTaigaTreeGrower("betterterrain:red_highlands", 10, 19, DecoWoodTypeHelper.redAutumnWoodType());
    public static final AbstractTreeGrower ORANGE_HIGHLANDS_TREE = new BigTaigaTreeGrower("betterterrain:orange_highlands", 10, 19, DecoWoodTypeHelper.orangeAutumnWoodType());
}
