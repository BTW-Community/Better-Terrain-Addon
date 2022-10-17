package betterterrain.mixins;

import betterterrain.biome.BTABiomeConfiguration;
import betterterrain.biome.BTANetherBiome;
import betterterrain.world.util.WorldProviderInterface;
import betterterrain.world.util.WorldTypeInterface;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldProviderHell.class)
public abstract class WorldProviderHellMixin extends WorldProvider implements WorldProviderInterface {
    @Inject(method = "registerWorldChunkManager", at = @At("TAIL"))
    public void setChunkManager(CallbackInfo ci) {
        WorldType worldType = this.worldObj.worldInfo.getTerrainType();

        if (((WorldTypeInterface) worldType).isDeco()) {
            this.worldChunkMgr = new WorldChunkManagerHell(BTABiomeConfiguration.netherWastes, 1.0F, 0.0F);
        }
    }

    @Inject(method = "createChunkGenerator", at = @At("RETURN"), cancellable = true)
    public void createChunkGenerator(CallbackInfoReturnable<IChunkProvider> cir) {
        cir.setReturnValue(((WorldTypeInterface) this.terrainType).getChunkProviderNether(this.worldObj, this.worldObj.getSeed(), this.getGeneratorOptions()));
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2, int x, int y, int z) {
        BiomeGenBase biome =  this.worldObj.getBiomeGenForCoords(x, z);

        if (biome instanceof BTANetherBiome) {
            Vec3[][] biomeColors = new Vec3[5][5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    biomeColors[i][j] = ((BTANetherBiome) this.worldObj.getBiomeGenForCoords(x - 2 + i, z - 2 + j)).getFogColor(this.worldObj);
                }
            }

            Vec3 color = this.worldObj.getWorldVec3Pool().getVecFromPool(0, 0, 0);

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    color.xCoord += biomeColors[i][j].xCoord / 25;
                    color.yCoord += biomeColors[i][j].yCoord / 25;
                    color.zCoord += biomeColors[i][j].zCoord / 25;
                }
            }

            return color;
        }
        else {
            return this.worldObj.getWorldVec3Pool().getVecFromPool(0.2, 0.03, 0.03);
        }
    }
}
