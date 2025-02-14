package dev.lieonlion.goggleplacement.forge;

import dev.lieonlion.goggleplacement.GogglePlacement;
import dev.lieonlion.goggleplacement.config.GpConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(GogglePlacement.MOD_ID)
public class GoggleplacementForge {
    // The mod loading context
    private static ModLoadingContext modLoadingContext;

    public GoggleplacementForge() {
        MinecraftForge.EVENT_BUS.register(GogglePlacement.class);
        GoggleplacementForge.modLoadingContext = ModLoadingContext.get();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GoggleConfigForgeClient::setup);

        GogglePlacement.init();
    }

    private static final class GoggleConfigForgeClient {
        public static void setup() {
            MinecraftForge.EVENT_BUS.register(GogglePlacement.class);

            loadModConfig(modLoadingContext);
        }

        public static void loadModConfig(final ModLoadingContext modLoadingContext) {
            modLoadingContext.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MidnightConfig.getScreen(parent, GogglePlacement.MOD_ID)));
        }
    }
}
