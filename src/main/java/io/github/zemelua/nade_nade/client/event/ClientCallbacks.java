package io.github.zemelua.nade_nade.client.event;

import io.github.zemelua.nade_nade.component.HeadpatManager;
import io.github.zemelua.nade_nade.component.ModComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public final class ClientCallbacks {
	public static void onStartTick(MinecraftClient client) {
		HeadpatManager.clientTick(client);
	}

	public static boolean preAttackInput(ClientPlayerEntity player) {
		return player.getComponent(ModComponents.HEADPATTING).isHeadpatting();
	}
}
