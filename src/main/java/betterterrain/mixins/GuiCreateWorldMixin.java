package betterterrain.mixins;

import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.gui.GeneratorOptionsGui;
import betterterrain.gui.GuiCreateWorldInterface;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiCreateWorld.class)
public abstract class GuiCreateWorldMixin extends GuiScreen implements GuiCreateWorldInterface {
    private boolean isDeco;

    @Shadow
    private int worldTypeId;
    @Shadow
    public String generatorOptionsToUse;
    @Shadow
    private boolean moreOptions;
    @Shadow
    private GuiButton buttonCustomize;

    @Inject(method = "initGui", at = @At("HEAD"))
    public void initBTAGUI(CallbackInfo ci) {
        this.worldTypeId = ((WorldType) BTAMod.BTAWorldType).getWorldTypeID();
        this.isDeco = BTAMod.isDecoInstalled();
    }

    @Inject(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EnumGameType;getByName(Ljava/lang/String;)Lnet/minecraft/src/EnumGameType;", shift = At.Shift.AFTER))
    public void initGeneratorOptions(GuiButton guiButton, CallbackInfo ci) {
        if (((WorldTypeInterface) WorldType.worldTypes[this.worldTypeId]).isBTA() && this.generatorOptionsToUse.equals("")) {
            this.generatorOptionsToUse = WorldConfigurationInfo.createDefaultConfiguration(isDeco).toString();

            System.out.println(this.generatorOptionsToUse);

            SurfaceBuilder.defaultBuilder.hasBeenInit = false;
            SurfaceBuilder.legacyBuilder.hasBeenInit = false;

            for (BiomeGenBase b : WorldConfigurationInfo.createInfoFromString(this.generatorOptionsToUse).getBiomesForGeneration()) {
                if (b instanceof BTABiome) {
                    SurfaceBuilder builder = ((BTABiome) b).getSurfaceBuilder();

                    if (builder != null) {
                        builder.hasBeenInit = false;
                    }
                }
            }
        }
    }

    @ModifyArg(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;launchIntegratedServer(Ljava/lang/String;Ljava/lang/String;Lnet/minecraft/src/WorldSettings;)V"), index = 2)
    public WorldSettings modifyWorldSettingsOnLaunch(WorldSettings settings) {
        WorldType type = WorldType.worldTypes[this.worldTypeId];

        if (this.isDeco && ((WorldTypeInterface) type).hasDeco()) {
            type = WorldType.worldTypes[this.worldTypeId + 1];
        }

        WorldSettings newSettings = new WorldSettings(settings.getSeed(), settings.getGameType(), settings.isMapFeaturesEnabled(), settings.getHardcoreEnabled(), type);
        newSettings.func_82750_a(this.generatorOptionsToUse);

        return newSettings;
    }

    @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
    public void handleBTAButtons(GuiButton guiButton, CallbackInfo ci) {
        if (guiButton.enabled && guiButton.id == this.buttonCustomize.id && ((WorldTypeInterface) WorldType.worldTypes[this.worldTypeId]).isBTA()) {
            this.mc.displayGuiScreen(new GeneratorOptionsGui((GuiCreateWorld) (Object) this, this.generatorOptionsToUse));
            ci.cancel();
        }
    }

    @Inject(method = "func_82288_a", at = @At("TAIL"))
    public void setBTAButtons(boolean par1, CallbackInfo ci) {
        this.buttonCustomize.drawButton = this.moreOptions &&
                (WorldType.worldTypes[this.worldTypeId] == WorldType.FLAT ||
                ((WorldTypeInterface) WorldType.worldTypes[this.worldTypeId]).isBTA());
    }

    @Inject(method = "func_82286_a", at = @At("TAIL"))
    public void setBTAValuesOnRecreate(WorldInfo worldInfo, CallbackInfo ci) {
        WorldType currentType = WorldType.worldTypes[this.worldTypeId];
        this.isDeco = ((WorldTypeInterface) currentType).isDeco();
        this.worldTypeId = ((WorldType) ((WorldTypeInterface) currentType).getParent()).getWorldTypeID();
    }

    @Override
    public boolean isDeco() {
        return isDeco;
    }

    @Override
    public void setDeco(boolean isDeco) {
        this.isDeco = isDeco;
    }

    @Override
    public int getWorldTypeId() {
        return worldTypeId;
    }
}
