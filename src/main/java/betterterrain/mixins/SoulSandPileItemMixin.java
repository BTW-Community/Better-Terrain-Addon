package betterterrain.mixins;

import betterterrain.world.generate.provider.AbstractChunkProvider;
import btw.client.fx.BTWEffectManager;
import btw.entity.SoulSandEntity;
import btw.item.items.SoulSandPileItem;
import btw.util.hardcorespawn.SpawnLocation;
import btw.util.hardcorespawn.SpawnLocationList;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SoulSandPileItem.class)
public abstract class SoulSandPileItemMixin extends Item {
    public SoulSandPileItemMixin(int itemID) {
        super(itemID);
    }

    /**
     * @author Dawnraider00 / The Lady Dawn
     * @reason Would be complex mixin
     */
    @Overwrite
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            boolean hasTarget = false;

            double targetXPos = player.posX;
            double targetZPos = player.posZ;

            if (world.provider.dimensionId == 0) {
                SpawnLocationList spawnList = world.getSpawnLocationList();

                SpawnLocation closestSpawnLoc = spawnList.getClosestSpawnLocationForPosition(player.posX, player.posZ);

                if (closestSpawnLoc != null) {
                    targetXPos = closestSpawnLoc.posX;
                    targetZPos = closestSpawnLoc.posZ;

                    hasTarget = true;
                }
            }
            else if (world.provider.dimensionId == -1) {
                IChunkProvider provider = world.getChunkProvider();

                if (provider != null && provider instanceof ChunkProviderServer) {
                    ChunkProviderServer serverProvider = (ChunkProviderServer)provider;

                    provider = serverProvider.getCurrentProvider();

                    if (provider != null) {
                        StructureStart closestFortress = null;

                        if (provider instanceof ChunkProviderHell) {
                            ChunkProviderHell hellProvider = (ChunkProviderHell) provider;
                            closestFortress = hellProvider.genNetherBridge.getClosestStructureWithinRangeSq(player.posX, player.posZ, 90000); // 300 block range
                        }
                        else if (provider instanceof AbstractChunkProvider) {
                            AbstractChunkProvider btaProvider = (AbstractChunkProvider) provider;

                            if (btaProvider.isNether()) {
                                closestFortress = btaProvider.getNetherBridgeGenerator().GetClosestStructureWithinRangeSq(player.posX, player.posZ, 90000); // 300 block range
                            }
                        }

                        if (closestFortress != null) {
                            targetXPos = (double)closestFortress.getBoundingBox().getCenterX();
                            targetZPos = (double)closestFortress.getBoundingBox().getCenterZ();

                            hasTarget = true;
                        }
                    }
                }
            }

            SoulSandEntity sandEntity = (SoulSandEntity) EntityList.createEntityOfType(SoulSandEntity.class, world, player.posX, player.posY + 2.0D - (double)player.yOffset, player.posZ);

            sandEntity.moveTowards(targetXPos, targetZPos);

            world.spawnEntityInWorld(sandEntity);

            if (hasTarget) {
                world.playAuxSFX(BTWEffectManager.GHAST_MOAN_EFFECT_ID, (int) Math.round(sandEntity.posX), (int) Math.round(sandEntity.posY), (int) Math.round(sandEntity.posZ), 0);
            }

            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
        }

        return stack;
    }
}
