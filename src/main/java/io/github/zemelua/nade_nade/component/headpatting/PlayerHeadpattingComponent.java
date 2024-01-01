package io.github.zemelua.nade_nade.component.headpatting;

import io.github.zemelua.nade_nade.entity.effect.ModStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerHeadpattingComponent extends AbstractHeadpattingComponent<PlayerEntity> {
	private int headpattedTicks;

	public PlayerHeadpattingComponent(PlayerEntity provider) {
		super(provider);

		this.headpattedTicks = 0;
	}

	@Override
	public boolean canHeadpat(Entity target) {
		return true;
	}

	@Override
	public void serverTick() {
		if (this.isHeadpatting()) {
			this.headpattedTicks++;

			if (this.headpattedTicks % 100 == 0) {
				this.provider.addStatusEffect(new StatusEffectInstance(ModStatusEffects.UNLONELY, 24000, 0, false, false, true));
			}
		} else if (this.headpattedTicks > 0) {
			this.headpattedTicks = 0;
		}
	}
}
