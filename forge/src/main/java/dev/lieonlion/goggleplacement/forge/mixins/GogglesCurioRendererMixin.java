package dev.lieonlion.goggleplacement.forge.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.curios.GogglesCurioRenderer;
import dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.injection.Inject;
import dev.lieonlion.goggleplacement.config.GpConfig;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotContext;

@Mixin(GogglesCurioRenderer.class)
public class GogglesCurioRendererMixin {
    // Both
    @Inject(method = "render", at = @dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.injection.At(value = "HEAD"), cancellable = true)
    private <T extends LivingEntity, M extends EntityModel<T>> void hideGoggles(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        boolean headOccupied = !slotContext.entity().getItemBySlot(EquipmentSlot.HEAD).isEmpty();
        if ((GpConfig.whHideGoggles && headOccupied) || (GpConfig.wohHideGoggles && !headOccupied)) {
            ci.cancel();
        }
    }

    // Helmet Off (woh)
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 1))
    private void wohGogglePlacement(PoseStack instance, double d, double e, double f, Operation<Void> original) {
        boolean flip = GpConfig.wohFlipUpsideDown;
        instance.translate(
                d,
                (flip ? -0.39 : -0.25) - ((GpConfig.wohGogglePlacement - 0.5) / 2),
                f
        );
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;", ordinal = 0))
    private Quaternionf wohFlipUpsideDown(Axis instance, float f, Operation<Quaternionf> original) {
        return instance.rotationDegrees(180f * (GpConfig.wohFlipUpsideDown ? 2 : 1));
    }

    // Helmet On (wh)
    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"))
    private boolean whDisabledGogglesMovingDown(boolean original) {
        return original || GpConfig.whDisableGogglesMovingDown;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 2))
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
