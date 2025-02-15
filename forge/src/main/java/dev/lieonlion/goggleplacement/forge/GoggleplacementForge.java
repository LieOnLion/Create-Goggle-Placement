package dev.lieonlion.goggleplacement.forge;

import dev.lieonlion.goggleplacement.GogglePlacement;
import dev.lieonlion.goggleplacement.forge.integration.ForgeMenuIntegration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(GogglePlacement.MOD_ID)
public class GoggleplacementForge {
    // The mod loading context
    private static ModLoadingContext modLoadingContext;

    public GoggleplacementForge() {
        MinecraftForge.EVENT_BUS.register(GogglePlacement.class);
        GoggleplacementForge.modLoadingContext = ModLoadingContext.get();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ForgeMenuIntegration::getModConfigScreen);

        GogglePlacement.init();
    }
}
