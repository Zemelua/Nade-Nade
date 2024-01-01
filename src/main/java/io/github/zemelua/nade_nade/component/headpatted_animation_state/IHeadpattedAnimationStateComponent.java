package io.github.zemelua.nade_nade.component.headpatted_animation_state;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public interface IHeadpattedAnimationStateComponent extends ComponentV3 {
	void start();
	void stop();
	AnimationState get();

	class Impl implements IHeadpattedAnimationStateComponent {
		private final Entity provider;
		private final AnimationState animationState = new AnimationState();

		public Impl(Entity provider) {
			this.provider = provider;
		}

		@Override
		public void start() {
			this.animationState.start(this.provider.age);
		}

		@Override
		public void stop() {
			this.animationState.stop();
		}

		@Override
		public AnimationState get() {
			return this.animationState;
		}

		@Override
		public void readFromNbt(@NotNull NbtCompound tag) {}

		@Override
		public void writeToNbt(@NotNull NbtCompound tag) {}
	}
}
