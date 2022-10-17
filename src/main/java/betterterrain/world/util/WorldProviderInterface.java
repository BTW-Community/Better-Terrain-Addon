package betterterrain.world.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public interface WorldProviderInterface {
    String getGeneratorOptions();

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    Vec3 getFogColor(float par1, float par2, int x, int y, int z);
}
