package betterterrain.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiScreen.class)
@Environment(EnvType.CLIENT)
public interface GuiScreenAccessor {
    @Accessor("mc")
    Minecraft getMinecraft();

    @Accessor
    FontRenderer getFontRenderer();
}
