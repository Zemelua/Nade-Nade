package io.github.zemelua.nade_nade.component.headpatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerHeadpattingComponent extends AbstractHeadpattingComponent<PlayerEntity> {
	public PlayerHeadpattingComponent(PlayerEntity provider) {
		super(provider);
	}

	@Override
	public boolean canHeadpat(Entity target) {
		return true;
	}

	@Override
	public void clientTick() {}
}
