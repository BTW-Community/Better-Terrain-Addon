package betterterrain.mixins;

import betterterrain.world.util.WorldProviderInterface;
import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldType;
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
}
