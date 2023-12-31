package io.github.zemelua.nade_nade.client.event;

import io.github.zemelua.nade_nade.component.HeadpatManager;
import net.minecraft.client.MinecraftClient;

public final class ClientCallbacks {
	public static void onStartTick(MinecraftClient client) {
		HeadpatManager.clientTick(client);
	}
}
