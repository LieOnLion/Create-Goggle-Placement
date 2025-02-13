package dev.lieonlion.goggleplacement.fabric;

import dev.lieonlion.goggleplacement.GogglePlacement;
import dev.lieonlion.goggleplacement.config.GpConfig;
import dev.lieonlion.goggleplacement.config.GpUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class GogglePlacementFabric implements ModInitializer {
    private static GpConfig CONFIG;

    @Override
    public void onInitialize() {
        GogglePlacement.init();
    }

    public static GpUtil getConfig() {
        if (CONFIG == null && FabricLoader.getInstance().isModLoaded("cloth-config")) {
            GogglePlacement.LOGGER.info("Cloth config found");
            CONFIG = GpConfig.createConfig();
        }
        if (CONFIG != null) {
            return CONFIG;
        } return GpUtil.DEFAULT;
    }
}
