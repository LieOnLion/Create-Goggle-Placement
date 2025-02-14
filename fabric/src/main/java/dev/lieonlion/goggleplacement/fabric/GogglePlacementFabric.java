package dev.lieonlion.goggleplacement.fabric;

import dev.lieonlion.goggleplacement.GogglePlacement;
import net.fabricmc.api.ModInitializer;

public class GogglePlacementFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GogglePlacement.init();
    }
}
