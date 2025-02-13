package dev.lieonlion.goggleplacement.config;

import dev.lieonlion.goggleplacement.GogglePlacement;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screens.Screen;

@Config(name = GogglePlacement.MOD_ID)
public class GpConfig implements ConfigData, GpUtil {
    @ConfigEntry.Gui.Tooltip
    public boolean disabledMovingGogglesWithHelmet = DEFAULT.disabledMovingGogglesWithHelmet();
    @ConfigEntry.Gui.Tooltip
    public double gogglePossition = DEFAULT.gogglePossition();

    @Override
    public boolean disabledMovingGogglesWithHelmet() {
        return disabledMovingGogglesWithHelmet;
    }

    @Override
    public double gogglePossition() {
        return gogglePossition;
    }

    public static GpConfig createConfig() {
        return AutoConfig.register(GpConfig.class, GsonConfigSerializer::new).getConfig();
    }

    public static Screen createConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(GpConfig.class, parent).get();
    }
}
