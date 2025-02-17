package dev.lieonlion.goggleplacement.fabric;

import dev.lieonlion.goggleplacement.GogglePlacement;
import net.fabricmc.api.ClientModInitializer;

public class GogglePlacementFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GogglePlacement.clientInit();
    }
}
