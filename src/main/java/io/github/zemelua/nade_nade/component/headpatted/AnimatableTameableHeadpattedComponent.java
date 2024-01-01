package io.github.zemelua.nade_nade.component.headpatted;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.player.PlayerEntity;

// SinglePartEntityModelを実装しているバニラエンティティはこれを使用してAnimationStateを取得できます。
public class AnimatableTameableHeadpattedComponent<T extends LivingEntity & Tameable> extends TameableHeadpattedComponent<T> {
	public AnimatableTameableHeadpattedComponent(T provider) {
		super(provider);
	}

	@Override
	protected void onStartHeadpatted(PlayerEntity newOwner) {
		// ModComponents.HEADPATTED_ANIMATION_STATE.maybeGet(this.provider).ifPresent(IHeadpattedAnimationStateComponent::start);
	}

	@Override
	protected void onEndHeadpatted() {
		// ModComponents.HEADPATTED_ANIMATION_STATE.maybeGet(this.provider).ifPresent(IHeadpattedAnimationStateComponent::stop);
	}
}
