package io.github.zemelua.nade_nade.mixin;

import io.github.zemelua.nade_nade.component.ModComponents;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.TintableAnimalModel;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntityModel.class)
public abstract class MixinWolfEntityModel<T extends WolfEntity> extends TintableAnimalModel<T> {
	@Final @Shadow private ModelPart realHead;

	@Inject(method = "animateModel(Lnet/minecraft/entity/passive/WolfEntity;FFF)V",
			at = @At("RETURN"))
	private void animateHeadpatted(T wolfEntity, float limbAngle, float limbDistance, float tickDelta, CallbackInfo callback) {
		float roll = this.realHead.roll;

		if (wolfEntity.getComponent(ModComponents.HEADPATTED).isHeadpatted()) {
			int ticks = wolfEntity.getComponent(ModComponents.HEADPATTED).getHeadpattedTicks();
			double sin = Math.sin(Math.toRadians((ticks + tickDelta) * 18.0D));

			this.realHead.roll = (float) Math.toRadians(0.0D + sin * -25.0D);
			this.realHead.pitch = (float) Math.toRadians(30.0D);
			this.realHead.yaw = (float) Math.toRadians(0.0D + sin * 10.0D);
		} else {
			this.realHead.roll = roll;
			this.realHead.pitch = 0.0F;
			this.realHead.yaw = 0.0F;
		}
	}
}
