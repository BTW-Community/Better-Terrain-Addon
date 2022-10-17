package betterterrain.mixins;

import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.WorldType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldType.class)
public class WorldTypeMixin implements WorldTypeInterface {
    private boolean isDeco;
    private WorldTypeInterface parent = this;

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
