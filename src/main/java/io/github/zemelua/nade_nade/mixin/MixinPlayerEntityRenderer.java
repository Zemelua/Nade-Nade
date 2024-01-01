package io.github.zemelua.nade_nade.mixin;

import io.github.zemelua.nade_nade.component.ModComponents;
import io.github.zemelua.nade_nade.tinker.Tinkers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class MixinPlayerEntityRenderer extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	@SuppressWarnings("SpellCheckingInspection")
	@Inject(method = "renderArm",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V",
					ordinal = 0))
	private void animateHeadpattingArmsOnFirstPerson(MatrixStack matrices, VertexConsumerProvider vertices, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo callback) {
		matrices.push();

		if (MinecraftClient.getInstance().options.getPerspective().isFirstPerson() && player.getComponent(ModComponents.HEADPATTING).isHeadpatting()) {
			float tickDelta = MinecraftClient.getInstance().getTickDelta();
			int ticks = player.getComponent(ModComponents.HEADPATTING).getHeadpattingTicks();
			double sin = Math.sin(Math.toRadians((ticks + tickDelta) * 18.0D + 150.0D));

			matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) (-10 + sin * 20)));
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) (-10 + sin * 20)));
		}
	}

	@SuppressWarnings("SpellCheckingInspection")
	@Inject(method = "renderArm",
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V",
					ordinal = 1,
					shift = At.Shift.AFTER))
	private void endAnimateHeadpattingArmsOnFirstPerson(MatrixStack matrices, VertexConsumerProvider vertices, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo callback) {
		matrices.pop();
	}

	@Inject(method = "getArmPose",
			at = @At("HEAD"),
			cancellable = true)
	private static void getHeadpattingArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> callback) {
		boolean isHeadpatting = player.getComponent(ModComponents.HEADPATTING).isHeadpatting();

		if (isHeadpatting) {
			if (player.getMainHandStack().isEmpty()) {
				if (hand == Hand.MAIN_HAND) {
					callback.setReturnValue(Tinkers.ArmPose.HEADPATTING);
				}
			} else if (player.getOffHandStack().isEmpty()) {
				if (hand == Hand.OFF_HAND) {
					callback.setReturnValue(Tinkers.ArmPose.HEADPATTING);
				}
			} else {
				if (hand == Hand.MAIN_HAND) {
					callback.setReturnValue(Tinkers.ArmPose.HEADPATTING);
				}
			}
		}
	}

	private MixinPlayerEntityRenderer(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
		super(ctx, model, shadowRadius);
	}
}