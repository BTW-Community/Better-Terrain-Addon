package betterterrain.mixins;

import betterterrain.block.util.ClayHelper;
import btw.block.blocks.ClayBlock;
import btw.item.BTWItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ClayBlock.class)
public class ClayBlockMixin extends Block {
    protected ClayBlockMixin(int blockID, Material material) {
        super(blockID, material);
    }

    @Inject(method = "dropBlockAsItemWithChance", at = @At("HEAD"), cancellable = true)
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortuneModifier, CallbackInfo ci) {
        switch (metadata) {
            default: // Dirt and grass
                return;
            case ClayHelper.SAND_TYPE:
                this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, 0, chance);
                ci.cancel();
                break;
            case ClayHelper.RED_SAND_TYPE:
                this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, 1, chance);
                ci.cancel();
                break;
        }

        this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.idDropped(metadata, world.rand, fortuneModifier), 1, this.damageDropped(metadata)));
    }

    @Inject(method = "dropComponentItemsOnBadBreak", at = @At("HEAD"), cancellable = true)
    public void dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chance, CallbackInfoReturnable<Boolean> cir) {
        switch (metadata) {
            default: // Dirt and grass
                return;
            case ClayHelper.SAND_TYPE:
                this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, 0, chance);
                cir.setReturnValue(true);
                break;
            case ClayHelper.RED_SAND_TYPE:
                this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, 1, chance);
                cir.setReturnValue(true);
                break;
        }

        this.dropItemsIndividually(world, x, y, z, BTWItems.clayPile.itemID, 1, 0, chance);
    }

    @Override
    public float getMovementModifier(World world, int x, int y, int z) {
        int metadata = world.getBlockId(x, y, z);

        if (metadata == ClayHelper.SAND_TYPE || metadata == ClayHelper.RED_SAND_TYPE) {
            return 0.8F;
        }
        else {
            return super.getMovementModifier(world, x, y, z);
        }
    }

    @Override
    public boolean canReedsGrowOnBlock(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        return metadata == ClayHelper.GRASS_TYPE || metadata == ClayHelper.DIRT_TYPE;
    }

    @Override
    public boolean canSaplingsGrowOnBlock(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        return metadata == ClayHelper.GRASS_TYPE || metadata == ClayHelper.DIRT_TYPE;
    }

    @Override
    public boolean canWildVegetationGrowOnBlock(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        return metadata == ClayHelper.GRASS_TYPE || metadata == ClayHelper.DIRT_TYPE;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    Icon sandyClayIcon;
    @Environment(EnvType.CLIENT)
    Icon redSandyClayIcon;
    @Environment(EnvType.CLIENT)
    Icon grassyClayOverlay;

    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister var1) {
        super.registerIcons(var1);
        sandyClayIcon = var1.registerIcon("btaBlockSandyClay");
        redSandyClayIcon = var1.registerIcon("btaBlockRedSandyClay");
        grassyClayOverlay = var1.registerIcon("btaOverlayGrassyClay");
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
        if (meta == 1) {
            return sandyClayIcon;
        }
        else if (meta == 2) {
            return redSandyClayIcon;
        }
        else {
            return super.getIcon(side, meta);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean renderBlock(RenderBlocks render, int x, int y, int z) {
        int meta = render.blockAccess.getBlockMetadata(x, y, z);

        if (meta == 0)
            super.renderBlock(render, x, y, z);
        else if (meta == 1)
            this.renderBlockWithTexture(render, x, y, z, sandyClayIcon);
        else if (meta == 2)
            this.renderBlockWithTexture(render, x, y, z, redSandyClayIcon);
        else
            Block.grass.renderBlock(render, x, y, z);

        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (blockAccess.getBlockMetadata(x, y, z) == 3 && side == 1) {
            return false;
        }

        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void renderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5) {
        if (var1.blockAccess.getBlockMetadata(var2, var3, var4) == 3) {
            Block.grass.renderBlockSecondPass(var1, var2, var3, var4, var5);

            var1.setRenderAllFaces(false);
            this.renderBlockWithTexture(var1, var2, var3, var4, grassyClayOverlay);
            var1.setRenderAllFaces(true);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        // used only by pick block
        return world.getBlockMetadata(x, y, z);
    }

    @Environment(EnvType.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < 4; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
}