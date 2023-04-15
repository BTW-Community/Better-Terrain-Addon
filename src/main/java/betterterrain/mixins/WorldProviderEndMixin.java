package betterterrain.mixins;

import betterterrain.world.util.WorldProviderInterface;
import betterterrain.world.util.WorldTypeInterface;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.Vec3;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderEnd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldProviderEnd.class)
public abstract class WorldProviderEndMixin extends WorldProvider implements WorldProviderInterface {
    @Inject(method = "createChunkGenerator", at = @At("RETURN"), cancellable = true)
    public void createChunkGenerator(CallbackInfoReturnable<IChunkProvider> cir) {
        cir.setReturnValue(((WorldTypeInterface) this.terrainType).getChunkProviderEnd(this.worldObj, this.worldObj.getSeed()));
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    @Shadow public abstract Vec3 getFogColor(float par1, float par2);

    @Environment(EnvType.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2, int x, int y, int z) {
        return this.getFogColor(par1, par2);
    }
}
