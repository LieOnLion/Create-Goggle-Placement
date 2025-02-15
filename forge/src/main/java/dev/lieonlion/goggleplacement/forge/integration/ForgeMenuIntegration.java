package dev.lieonlion.goggleplacement.forge.integration;

import dev.lieonlion.goggleplacement.GogglePlacement;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public class ForgeMenuIntegration {
    public static void getModConfigScreen() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> MidnightConfig.getScreen(parent, GogglePlacement.MOD_ID)));
    }
}
