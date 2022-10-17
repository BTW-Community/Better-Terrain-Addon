package betterterrain.mixins;

import net.minecraft.src.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WorldType.class)
public interface WorldTypeAccessor {
    @Invoker("setCanBeCreated")
    WorldType setCanBeCreated(boolean canBeCreated);
}
