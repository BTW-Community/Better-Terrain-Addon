package betterterrain.mixins;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.StructureScatteredFeatureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.ArrayList;

@Mixin(StructureScatteredFeatureStart.class)
public interface StructureScatteredFeatureStartAccessor {
    @Accessor
    static ArrayList<BiomeGenBase> getSwampBiomeList() {
        throw new AssertionError();
    }
}
