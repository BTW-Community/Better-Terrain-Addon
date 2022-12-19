package betterterrain.mixins;

import betterterrain.world.generate.provider.BTAChunkProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.DecimalFormat;

@Mixin(GuiIngame.class)
@Environment(EnvType.CLIENT)
public abstract class GuiInGameMixin extends Gui {
    @Shadow @Final
    private Minecraft mc;

    @Shadow
    protected abstract void addCurrentBiomeDisplay(int iYPos);

    @Inject(method = "renderModDebugOverlay", at = @At("TAIL"), remap = false)
    public void renderModDebugOverlay(CallbackInfo ci) {
        addCurrentBiomeDisplay(124);
        renderDebugTerrainValues(134);
    }

    public void renderDebugTerrainValues(int pos) {
        FontRenderer fontRenderer = mc.fontRenderer;
        ChunkProviderServer serverProvider = (ChunkProviderServer) MinecraftServer.getServer().worldServerForDimension(mc.thePlayer.dimension).getChunkProvider();

        if (serverProvider.getCurrentProvider() instanceof BTAChunkProvider) {
            BTAChunkProvider provider = (BTAChunkProvider) serverProvider.getCurrentProvider();

            DecimalFormat format = new DecimalFormat("0.000");

            int x = (int) mc.thePlayer.posX;
            int z = (int) mc.thePlayer.posZ;

            double squash = provider.getSquashFactor(x, z);
            double lift = provider.getLiftFactor(x, z);

            double continentalness = provider.getContinentalness(x, z);
            double interpolatedContinentalness = provider.getInterpolatedContinentalness(x, z);

            double erosion = provider.getErosion(x, z);

            double peakValley = provider.getPeakValley(x, z);

            drawString(fontRenderer,
                    "SQ: " + format.format(squash) + " " +
                    "L: " + format.format(lift),
                    2, pos, 0xe0e0e0);

            drawString(fontRenderer,
                    "C: " + format.format(continentalness) + " " +
                    "CI: " + format.format(interpolatedContinentalness),
                    2, pos + 10, 0xe0e0e0);

            drawString(fontRenderer,
                    "E: " + format.format(erosion),
                    2, pos + 20, 0xe0e0e0);

            drawString(fontRenderer,
                    "PV: " + format.format(peakValley),
                    2, pos + 30, 0xe0e0e0);
        }
    }
}
