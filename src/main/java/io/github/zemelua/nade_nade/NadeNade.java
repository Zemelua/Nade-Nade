package io.github.zemelua.nade_nade;

import io.github.zemelua.nade_nade.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NadeNade implements ModInitializer {
	public static final String MOD_ID = "nade_nade";
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		NetworkHandler.initialize();
	}

	public static Identifier identifier(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
