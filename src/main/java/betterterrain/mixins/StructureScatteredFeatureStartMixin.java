package betterterrain.mixins;

import betterterrain.structure.component.BTAComponentScatteredFeatureRedDesertPyramid;
import betterterrain.world.util.StructureScatteredFeatureStartInterface;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.StructureScatteredFeatureStart;
import net.minecraft.src.StructureStart;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(StructureScatteredFeatureStart.class)
public class StructureScatteredFeatureStartMixin extends StructureStart implements StructureScatteredFeatureStartInterface {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void setRedDesertPyramid(World world, Random rand, int chunkX, int chunkZ, CallbackInfo ci) {
        BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);

        if (redDesertBiomeList.contains(biome)) {
            BTAComponentScatteredFeatureRedDesertPyramid pyramid = new BTAComponentScatteredFeatureRedDesertPyramid(rand, chunkX * 16, chunkZ * 16);
            this.components.add(pyramid);
            this.updateBoundingBox();
        }
    }
}
