package betterterrain.mixins;

import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldType.class)
public class WorldTypeMixin implements WorldTypeInterface {
    private boolean isDeco;
    private WorldTypeInterface parent;

    @Inject(method = "<init>(ILjava/lang/String;I)V", at = @At("TAIL"))
    public void initBTAValues(int par1, String par2Str, int par3, CallbackInfo ci) {
        this.parent = this;
    }

    public WorldTypeInterface setIsDeco() {
        this.isDeco = true;
        return this;
    }

    public boolean isDeco() {
        return isDeco;
    }

    public WorldTypeInterface getParent() {
        return parent;
    }

    public WorldTypeInterface setParent(WorldTypeInterface parent) {
        this.parent = parent;
        return this;
    }
}
