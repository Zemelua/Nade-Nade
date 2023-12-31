package io.github.zemelua.nade_nade.client;

import io.github.zemelua.nade_nade.client.event.ClientCallbacks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class NadeNadeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register(ClientCallbacks::onStartTick);
	}
}
