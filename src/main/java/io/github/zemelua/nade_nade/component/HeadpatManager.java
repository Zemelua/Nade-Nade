package io.github.zemelua.nade_nade.component;

import io.github.zemelua.nade_nade.component.headpatting.IHeadpattingComponent;
import io.github.zemelua.nade_nade.network.NetworkHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.EntityHitResult;

import java.util.Optional;

public final class HeadpatManager {
	@Environment(EnvType.CLIENT)
	public static void clientTick(MinecraftClient client) {
		if (client.player == null) return;

		IHeadpattingComponent component = client.player.getComponent(ModComponents.HEADPATTING);
		Optional<Entity> crosshairTarget = Optional.ofNullable(client.crosshairTarget)
				.filter(h -> h instanceof EntityHitResult)
				.map(h -> ((EntityHitResult) h).getEntity())
				.filter(e -> ModComponents.HEADPATTED.maybeGet(e).isPresent());

		// if (component.isHeadpatting()) NadeNade.LOGGER.info("nade");

		if (true) {
			if (crosshairTarget.isPresent()) {
				if (component.getTarget().isPresent()) {
					if (!component.getTarget().get().equals(crosshairTarget.get())) {
						sendFinishHeadpatting();
					}
				} else if (component.canHeadpat(crosshairTarget.get())) {
					sendStartHeadpatting(crosshairTarget.get());
				}
			} else {
				if (component.getTarget().isPresent()) {
					sendFinishHeadpatting();
				}
			}
		} else {
			if (component.getTarget().isPresent()) {
				sendFinishHeadpatting();
			}
		}
	}

	@Environment(EnvType.CLIENT)
	public static void sendStartHeadpatting(Entity target) {
		PacketByteBuf packet = PacketByteBufs.create();
		packet.writeInt(target.getId());

		ClientPlayNetworking.send(NetworkHandler.CHANNEL_CLIENT_HEADPATTING_START, packet);
	}

	@Environment(EnvType.CLIENT)
	public static void sendFinishHeadpatting() {
		ClientPlayNetworking.send(NetworkHandler.CHANNEL_CLIENT_HEADPATTING_END, PacketByteBufs.create());
	}

	private HeadpatManager() {}
}
