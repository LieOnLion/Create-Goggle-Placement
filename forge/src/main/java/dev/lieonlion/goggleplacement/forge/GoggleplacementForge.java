package dev.lieonlion.goggleplacement.forge;

import dev.lieonlion.goggleplacement.GogglePlacement;
import dev.lieonlion.goggleplacement.forge.integration.ForgeMenuIntegration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(GogglePlacement.MOD_ID)
public class GoggleplacementForge {
    public GoggleplacementForge() {
        MinecraftForge.EVENT_BUS.register(GogglePlacement.class);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GogglePlacementForgeClient::initializeClient);
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = GogglePlacement.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class GogglePlacementForgeClient {
        public static void initializeClient() {
            GogglePlacement.clientInit();
            ForgeMenuIntegration.getModConfigScreen();
        }
    }

}
