package io.github.zemelua.nade_nade.component.headpatted;

import io.github.zemelua.nade_nade.NadeNade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

public class TameableHeadpattedComponent<T extends Entity & Tameable> extends AbstractHeadpattedComponent<T> {
	public TameableHeadpattedComponent(T provider) {
		super(provider);
	}

	@Override
	public boolean isHeadpattable(PlayerEntity owner) {
		return Optional.ofNullable(this.provider.getOwner())
				.filter(p -> p.equals(owner))
				.isPresent();
	}

	@Override
	public void tick() {
		NadeNade.LOGGER.info(this.getOwner().isPresent());
	}
}
