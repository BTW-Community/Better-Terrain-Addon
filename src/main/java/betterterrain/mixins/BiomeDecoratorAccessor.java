package betterterrain.mixins;

import net.minecraft.src.BiomeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BiomeDecorator.class)
public interface BiomeDecoratorAccessor {
    @Accessor
    int getTreesPerChunk();
}
