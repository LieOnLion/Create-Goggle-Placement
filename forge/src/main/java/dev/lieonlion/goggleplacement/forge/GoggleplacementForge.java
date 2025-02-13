package dev.lieonlion.goggleplacement.forge;

import dev.lieonlion.goggleplacement.GogglePlacement;
import dev.lieonlion.goggleplacement.config.GpConfig;
import dev.lieonlion.goggleplacement.config.GpUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(GogglePlacement.MOD_ID)
public class GoggleplacementForge {
    private static GpConfig CONFIG;
    // The mod loading context
    private static ModLoadingContext modLoadingContext;

    public GoggleplacementForge() {
        MinecraftForge.EVENT_BUS.register(GogglePlacement.class);
        GoggleplacementForge.modLoadingContext = ModLoadingContext.get();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GoggleConfigForgeClient::setup);

        GogglePlacement.init();
    }

    public static GpUtil getConfig() {
        if (CONFIG == null && ModList.get().isLoaded("cloth_config")) {
            GogglePlacement.LOGGER.info("Cloth config found");
            CONFIG = GpConfig.createConfig();
        }
        if (CONFIG != null) {
            return CONFIG;
        } return GpUtil.DEFAULT;
    }

    private static final class GoggleConfigForgeClient {
        public static void setup() {
            MinecraftForge.EVENT_BUS.register(GogglePlacement.class);

            if (ModList.get().isLoaded("cloth_config")) {
                loadModConfig(modLoadingContext);
            }
        }

        public static void loadModConfig(final ModLoadingContext modLoadingContext) {
            if (GoggleplacementForge.getConfig() instanceof GpConfig) {
                modLoadingContext.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                        () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> GpConfig.createConfigScreen(parent)));
            }
        }
    }
}
