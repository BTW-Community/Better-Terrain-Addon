package betterterrain.mixins;

import betterterrain.world.util.WorldProviderInterface;
import betterterrain.world.util.WorldTypeInterface;
import com.prupe.mcpatcher.cc.ColorizeWorld;
import com.prupe.mcpatcher.cc.Colorizer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldProvider.class)
public abstract class WorldProviderMixin implements WorldProviderInterface {
    @Shadow
    public World worldObj;
    @Shadow
    public WorldType terrainType;
    @Shadow
    public String field_82913_c; // generator options

    @Inject(method = "createChunkGenerator", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    public void createChunkGenerator(CallbackInfoReturnable<IChunkProvider> cir) {
        cir.setReturnValue((IChunkProvider) ((WorldTypeInterface) this.terrainType).getChunkManager(this.worldObj, this.field_82913_c));
    }

    @Inject(method = "getCloudHeight", at = @At("RETURN"), cancellable = true)
    public void getCloudHeight(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(((WorldTypeInterface) this.terrainType).getCloudHeight());
    }

    @Inject(method = "getAverageGroundLevel", at = @At("RETURN"), cancellable = true)
    public void getAverageGroundLevel(CallbackInfoReturnable<Integer> cir) {
        if (this.terrainType != WorldType.FLAT) {
            cir.setReturnValue(((WorldTypeInterface) this.terrainType).getAverageGroundLevel());
        }
    }

    @Override
    public String getGeneratorOptions() {
        return this.field_82913_c;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    @Inject(method = "getFogColor", at = @At("HEAD"), cancellable = true)
    public void redirectFogColor(float par1, float par2, CallbackInfoReturnable<Vec3> cir) {
        cir.setReturnValue(this.getFogColor(par1, par2, 0, 0, 0));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2, int x, int y, int z) {
        float var3 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        float var4 = 0.7529412F;
        float var5 = 0.84705883F;
        float var6 = 1.0F;

        if (ColorizeWorld.computeFogColor((WorldProvider) (Object) this, par1))
        {
            var4 = Colorizer.setColor[0];
            var5 = Colorizer.setColor[1];
            var6 = Colorizer.setColor[2];
        }

        var4 *= var3 * 0.94F + 0.06F;
        var5 *= var3 * 0.94F + 0.06F;
        var6 *= var3 * 0.91F + 0.09F;
        return this.worldObj.getWorldVec3Pool().getVecFromPool(var4, var5, var6);
    }
}
