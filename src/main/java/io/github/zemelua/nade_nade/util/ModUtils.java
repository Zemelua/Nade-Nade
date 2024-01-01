package io.github.zemelua.nade_nade.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;

public final class ModUtils {
	public static boolean isThirdPersonView() {
		Perspective perspective = MinecraftClient.getInstance().options.getPerspective();
		return perspective == Perspective.THIRD_PERSON_BACK || perspective.isFrontView();
	}
}
