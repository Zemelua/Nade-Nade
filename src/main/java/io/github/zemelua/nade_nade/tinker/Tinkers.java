package io.github.zemelua.nade_nade.tinker;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.client.render.entity.model.BipedEntityModel;

public final class Tinkers {
	public static final class ArmPose {
		public static final BipedEntityModel.ArmPose HEADPATTING = ClassTinkerers.getEnum(BipedEntityModel.ArmPose.class, EarlyRiser.ARM_POSE_HEADPATTING);

		private ArmPose() {}
	}

	private Tinkers() {}
}
