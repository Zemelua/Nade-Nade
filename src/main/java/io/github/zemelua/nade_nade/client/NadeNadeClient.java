package io.github.zemelua.nade_nade.client;

import io.github.zemelua.nade_nade.client.event.ClientCallbacks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class NadeNadeClient implements ClientModInitializer {
	public static final KeyBinding KEY_HEADPAT = new KeyBinding("key.nade_nade.headpat", GLFW.GLFW_KEY_P, KeyBinding.GAMEPLAY_CATEGORY);

	@Override
	public void onInitializeClient() {
		KeyBindingHelper.registerKeyBinding(KEY_HEADPAT);

		ClientTickEvents.START_CLIENT_TICK.register(ClientCallbacks::onStartTick);
		ClientPreAttackCallback.EVENT.register((client, player, clickCount) -> ClientCallbacks.preAttackInput(player));
	}
}
