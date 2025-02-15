package dev.lieonlion.goggleplacement.fabric.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.trinkets.GoggleTrinketRenderer;
import dev.emi.trinkets.api.SlotReference;
import dev.lieonlion.goggleplacement.config.GpConfig;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GoggleTrinketRenderer.class)
public abstract class GoggleTrinketRendererMixin {
    @Shadow
    public static boolean headOccupied(LivingEntity entity) {
        return false;
    }

    // Both
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    private void hideGoggles(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> model, PoseStack matrices, MultiBufferSource multiBufferSource, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        boolean headOccupied = headOccupied(entity);
        if ((GpConfig.whHideGoggles && headOccupied) || (GpConfig.wohHideGoggles && !headOccupied)) {
            ci.cancel();
        }
    }

    // Helmet Off (woh)
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 0))
    private void wohGogglePlacement(PoseStack instance, double d, double e, double f, Operation<Void> original) {
        instance.translate(
                d,
                0 - ((GpConfig.wohGogglePlacement - 0.5) / 2),
                f
        );
    }

    // Helmet On (wh)
    @ModifyReturnValue(method = "headOccupied", at = @At(value = "RETURN"))
    private static boolean whDisabledGogglesMovingDown(boolean original) {
        return original && !GpConfig.whDisableGogglesMovingDown;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 1))
    private void whGogglePlacement(PoseStack instance, double d, double e, double f, Operation<Void> original) {
        boolean flip = GpConfig.whFlipUpsideDown;
        instance.translate(
            d,
           (flip ? -0.368 : -0.25) - (GpConfig.whGogglePlacement * (flip ? -1 : 1)),
            f
        );
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;", ordinal = 1))
    private Quaternionf whFlipUpsideDown(Axis instance, float f, Operation<Quaternionf> original) {
        return instance.rotationDegrees(180f * (GpConfig.whFlipUpsideDown ? 2 : 1));
    }
}
