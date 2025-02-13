package dev.lieonlion.goggleplacement.fabric.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.trinkets.GoggleTrinketRenderer;
import dev.lieonlion.goggleplacement.fabric.GogglePlacementFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GoggleTrinketRenderer.class)
public class GoggleTrinketRendererMixin {
    // Helmet On
    @ModifyReturnValue(method = "headOccupied", at = @At(value = "RETURN"))
    private static boolean disabledMovingGoggles(boolean original) {
        return original && !GogglePlacementFabric.getConfig().disabledMovingGogglesWithHelmet();
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 1))
    private void positionGoggles(PoseStack instance, double d, double e, double f, Operation<Void> original) {
        instance.translate(d, GogglePlacementFabric.getConfig().gogglePossition(), f);
    }
}
