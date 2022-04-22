// FCMOD

package betterterrain.item;

import net.minecraft.src.BTAChunkProviderNether;
import net.minecraft.src.BTASkyChunkProvider;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.ChunkProviderServer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.FCEntitySoulSand;
import net.minecraft.src.FCSpawnLocation;
import net.minecraft.src.FCSpawnLocationList;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StructureStart;
import net.minecraft.src.World;

public class BTAItemPileSoulSand extends Item
{
    public BTAItemPileSoulSand(int itemID) {
        super(itemID);
        
        SetBellowsBlowDistance(1);
		SetFilterableProperties(m_iFilterable_Fine);
        
        setUnlocalizedName("fcItemPileSoulSand");
        
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
        	boolean hasTarget = false;
        	
        	double targetXPos = player.posX;
        	double targetZPos = player.posZ;
        	
        	if (world.provider.dimensionId == 0) {
        		FCSpawnLocationList spawnList = world.GetSpawnLocationList();
        		
        		FCSpawnLocation closestSpawnLoc = spawnList.GetClosestSpawnLocationForPosition(player.posX, player.posZ);
        		
                if (closestSpawnLoc != null) {
                	targetXPos = closestSpawnLoc.m_iIPos;
                	targetZPos = closestSpawnLoc.m_iKPos;
                	
                	hasTarget = true;
                }                
        	}
        	else if (world.provider.dimensionId == -1) {
        		IChunkProvider provider = world.getChunkProvider();
        		
        		if (provider != null && provider instanceof ChunkProviderServer) {
        			ChunkProviderServer serverProvider = (ChunkProviderServer)provider;
        			
        			provider = serverProvider.GetCurrentProvider();
        			
        			if (provider != null) {
        				StructureStart closestFortress = null;
        				
	        			if (provider instanceof ChunkProviderHell) {
	        				ChunkProviderHell hellProvider = (ChunkProviderHell) provider;
	        				closestFortress = hellProvider.genNetherBridge.GetClosestStructureWithinRangeSq(player.posX, player.posZ, 90000); // 300 block range
	        			}
	        			else if (provider instanceof BTAChunkProviderNether) {
	        				BTAChunkProviderNether hellProvider = (BTAChunkProviderNether) provider;
	        				closestFortress = hellProvider.genNetherBridge.GetClosestStructureWithinRangeSq(player.posX, player.posZ, 90000); // 300 block range
	        			}
	        			else if (provider instanceof BTASkyChunkProvider) {
	        				BTASkyChunkProvider hellProvider = (BTASkyChunkProvider) provider;
	        				closestFortress = hellProvider.genNetherBridge.GetClosestStructureWithinRangeSq(player.posX, player.posZ, 90000); // 300 block range
	        			}
	        			
	        			if (closestFortress != null) {
                        	targetXPos = (double)closestFortress.getBoundingBox().getCenterX();
                        	targetZPos = (double)closestFortress.getBoundingBox().getCenterZ();
                        	
                        	hasTarget = true;
        				}
        			}
        		}
        	}
        	
            FCEntitySoulSand sandEntity = (FCEntitySoulSand) EntityList.createEntityOfType(FCEntitySoulSand.class, world, player.posX, player.posY + 2.0D - (double)player.yOffset, player.posZ);

        	sandEntity.MoveTowards(targetXPos, targetZPos);
                
            world.spawnEntityInWorld(sandEntity);

            if (hasTarget) {
	        	world.playAuxSFX(FCBetterThanWolves.m_iGhastMoanSoundAuxFXID, (int) Math.round(sandEntity.posX), (int) Math.round(sandEntity.posY), (int) Math.round(sandEntity.posZ), 0);
            }
                
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
        }

        return stack;
    }
}
