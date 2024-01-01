package io.github.zemelua.nade_nade.component.headpatted;

import io.github.zemelua.nade_nade.entity.effect.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

public class TameableHeadpattedComponent<T extends LivingEntity & Tameable> extends AbstractHeadpattedComponent<T> {
	private int headpattedTicks;

	public TameableHeadpattedComponent(T provider) {
		super(provider);

		this.headpattedTicks = 0;
	}

	@Override
	public boolean isHeadpattable(PlayerEntity owner) {
		return Optional.ofNullable(this.provider.getOwner())
				.filter(p -> p.equals(owner))
				.isPresent();
	}

	@Override
	public void serverTick() {
		if (this.isHeadpatted()) {
			this.headpattedTicks++;

			if (this.headpattedTicks % 100 == 0) {
				this.provider.addStatusEffect(new StatusEffectInstance(ModStatusEffects.UNLONELY, 24000, 0, false, false, true));
			}
		} else if (this.headpattedTicks > 0) {
			this.headpattedTicks = 0;
		}
	}
}
