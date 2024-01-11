package io.github.zemelua.nade_nade.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.zemelua.nade_nade.client.event.ClientCallbacks;
import io.github.zemelua.umu_config.api.config.IConfigProvider;
import io.github.zemelua.umu_config.api.config.container.IConfigContainer;
import io.github.zemelua.umu_config.config.ConfigFileManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class NadeNadeClient implements ClientModInitializer, IConfigProvider, ModMenuApi {
	public static final ModClientConfig CONFIG = new ModClientConfig();
	public static final KeyBinding KEY_HEADPAT = new KeyBinding("key.nade_nade.headpat", GLFW.GLFW_KEY_UNKNOWN, KeyBinding.GAMEPLAY_CATEGORY);

	@Override
	public void onInitializeClient() {
		KeyBindingHelper.registerKeyBinding(KEY_HEADPAT);

		ClientTickEvents.START_CLIENT_TICK.register(ClientCallbacks::onStartTick);
		ClientPreAttackCallback.EVENT.register((client, player, clickCount) -> ClientCallbacks.preAttackInput(player));
	}

	@Override
	public List<IConfigContainer> getConfigs() {
		return List.of(CONFIG.getContainer());
	}

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
				ConfigBuilder builder = ConfigBuilder.create();

				CONFIG.asScreen(builder, builder.getOrCreateCategory(CONFIG.getContainer().getName()));

				builder.setParentScreen(parent)
						.setTitle(Text.literal("Nade-Nade Config"))
						.setSavingRunnable(() -> ConfigFileManager.saveFrom(CONFIG.getContainer()));

				return builder.build();
		};
	}
}
