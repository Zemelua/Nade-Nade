package io.github.zemelua.nade_nade.network;

import io.github.zemelua.nade_nade.component.headpatted.IHeadpattedComponent;
import io.github.zemelua.nade_nade.component.headpatting.IHeadpattingComponent;
import io.github.zemelua.nade_nade.component.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;

public final class PacketHandlers {
	static void handleStartHeadpatting(ServerPlayerEntity player, World world, int targetID) {
		IHeadpattingComponent headpatter = player.getComponent(ModComponents.HEADPATTING);
		Optional<Entity> target = Optional.ofNullable(world.getEntityById(targetID));

		if (target.isPresent()) {
			IHeadpattedComponent headpattee = target.get().getComponent(ModComponents.HEADPATTED);
			if (headpatter.canHeadpat(target.get()) && headpattee.isHeadpattable(player)) {
				headpatter.startHeadpatting(target.get());
				headpattee.startHeadpatted(player);
			}
		}
	}

	static void handleEndHeadpatting(ServerPlayerEntity player) {
		IHeadpattingComponent headpatter = player.getComponent(ModComponents.HEADPATTING);
		Optional<IHeadpattedComponent> headpattee = headpatter.getTarget()
				.flatMap(ModComponents.HEADPATTED::maybeGet);
		headpatter.endHeadpatting();
		headpattee.ifPresent(IHeadpattedComponent::endHeadpatted);
	}

	private PacketHandlers() {}
}
