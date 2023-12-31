package io.github.zemelua.nade_nade.network;

import io.github.zemelua.nade_nade.NadeNade;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public final class NetworkHandler {
	public static final Identifier CHANNEL_CLIENT_HEADPATTING_START = NadeNade.identifier("headpatting_start");
	public static final Identifier CHANNEL_CLIENT_HEADPATTING_END = NadeNade.identifier("headpatting_end");

	public static void initialize() {
		ServerPlayNetworking.registerGlobalReceiver(CHANNEL_CLIENT_HEADPATTING_START, (server, player, handler, packet, sender) -> {
			int targetID = packet.readInt();

			server.execute(() -> PacketHandlers.handleStartHeadpatting(player, player.getWorld(), targetID));
		});
		ServerPlayNetworking.registerGlobalReceiver(CHANNEL_CLIENT_HEADPATTING_END, (server, player, handler, packet, sender)
				-> server.execute(() -> PacketHandlers.handleEndHeadpatting(player)));
	}

	private NetworkHandler() {}
}
