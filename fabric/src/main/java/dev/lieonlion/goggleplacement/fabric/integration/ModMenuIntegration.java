package dev.lieonlion.goggleplacement.fabric.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.lieonlion.goggleplacement.config.GpConfig;
import dev.lieonlion.goggleplacement.fabric.GogglePlacementFabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (GogglePlacementFabric.getConfig() instanceof GpConfig) {
            return GpConfig::createConfigScreen;
        } return ModMenuApi.super.getModConfigScreenFactory();
    }
}