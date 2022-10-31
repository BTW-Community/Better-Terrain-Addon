package betterterrain.mixins;

import btw.block.BTWBlocks;
import btw.item.items.NetherGrothSporesItem;
import btw.world.util.BlockPos;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetherGrothSporesItem.class)
public abstract class NetherGrothSporesItemMixin extends Item {
    public NetherGrothSporesItemMixin(int item) {
        super(item);
    }

    /**
     * @author Dawnraider00 / The Lady Dawn
     * @reason Would be complex mixin
     */
    @Overwrite
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
        // tests copied over from itemReed to be on the safe side
        if (player != null && !player.canPlayerEdit(x, y, z, facing, itemStack)) {
            return false;
        }

        if (itemStack.stackSize == 0) {
            return false;
        }

        BlockPos targetPos = new BlockPos(x, y, z);
        targetPos.addFacingAsOffset(facing);

        WorldChunkManager worldchunkmanager = world.getWorldChunkManager();

        if (worldchunkmanager != null) {
            BiomeGenBase biomegenbase = worldchunkmanager.getBiomeGenAt(x, z);

            if (biomegenbase instanceof BiomeGenHell) {
                int blockID = BTWBlocks.netherGroth.blockID;
                int metadata = 0;

                if (world.canPlaceEntityOnSide(blockID, targetPos.x, targetPos.y, targetPos.z,
                        false, facing, player, itemStack)) {
                    if (!world.isRemote) {
                        metadata = Block.blocksList[blockID].onBlockPlaced(world, targetPos.x, targetPos.y, targetPos.z, facing, hitX, hitY, hitZ, metadata);
                        metadata = Block.blocksList[blockID].preBlockPlacedBy(world, targetPos.x, targetPos.y, targetPos.z, metadata, player);

                        if (world.setBlockAndMetadataWithNotify(targetPos.x, targetPos.y, targetPos.z, blockID, metadata)) {
                            Block block = Block.blocksList[blockID];

                            if (world.getBlockId(targetPos.x, targetPos.y, targetPos.z) == blockID) {
                                Block.blocksList[blockID].onBlockPlacedBy(world, targetPos.x, targetPos.y, targetPos.z, player, itemStack);

                                Block.blocksList[blockID].onPostBlockPlaced(world, targetPos.x, targetPos.y, targetPos.z, metadata);
                            }

                            world.playSoundEffect((float) targetPos.x + 0.5F, (float) targetPos.y + 0.5F,
                                    (float) targetPos.z + 0.5F, block.stepSound.getPlaceSound(),
                                    (block.stepSound.getPlaceVolume() + 1.0F) / 2.0F, block.stepSound.getPlacePitch() * 0.8F);

                            angerPigmen(world, player);
                        }
                    }

                    itemStack.stackSize--;

                    return true;
                }
            }
        }

        return false;
    }

    @Shadow
    private void angerPigmen(World world, EntityPlayer entityPlayer) {}
}
