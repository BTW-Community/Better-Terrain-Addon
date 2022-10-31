package betterterrain.mixins;

import net.minecraft.src.GenLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GenLayer.class)
public interface GenLayerAccessor {
    @Accessor
    long getWorldGenSeed();

    @Accessor
    long getChunkSeed();
}
