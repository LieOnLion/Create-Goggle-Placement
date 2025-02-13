package dev.lieonlion.goggleplacement.forge.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.curios.GogglesCurioRenderer;
import dev.lieonlion.goggleplacement.forge.GoggleplacementForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GogglesCurioRenderer.class)
public class GogglesCurioRendererMixin {
    // Helmet On
    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"))
    private boolean disabledMovingGoggles(boolean original) {
        return original || GoggleplacementForge.getConfig().disabledMovingGogglesWithHelmet();
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 2))
    private void possitionGoggles(PoseStack instance, double d, double e, double f, Operation<Void> original) {
        instance.translate(d, GoggleplacementForge.getConfig().gogglePossition(), f);
    }
}
