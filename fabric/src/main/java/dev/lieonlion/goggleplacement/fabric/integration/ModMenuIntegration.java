package dev.lieonlion.goggleplacement.fabric.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.lieonlion.goggleplacement.GogglePlacement;
import dev.lieonlion.goggleplacement.config.GpConfig;
import dev.lieonlion.goggleplacement.fabric.GogglePlacementFabric;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, GogglePlacement.MOD_ID);
    }
}