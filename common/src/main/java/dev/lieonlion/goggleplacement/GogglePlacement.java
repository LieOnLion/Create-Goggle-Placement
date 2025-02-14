package dev.lieonlion.goggleplacement;

import dev.lieonlion.goggleplacement.config.GpConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GogglePlacement {
    public static final String MOD_ID = "goggleplacement";
    public static final String NAME = "Create: Goggle Placement";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static void init() {
        LOGGER.info("{} initializing!", NAME);
        MidnightConfig.init(MOD_ID, GpConfig.class);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
