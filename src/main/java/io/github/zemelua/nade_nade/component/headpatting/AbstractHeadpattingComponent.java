package io.github.zemelua.nade_nade.component.headpatting;

import io.github.zemelua.nade_nade.component.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AbstractHeadpattingComponent<T extends Entity> implements IHeadpattingComponent {
	protected final T provider;
	@Nullable protected Entity target;

	protected AbstractHeadpattingComponent(T provider) {
		this.provider = provider;
		this.target = null;
	}

	protected void onStartHeadpatting(Entity newTarget) {}
	protected void onEndHeadpatting() {}

	@Override
	public void startHeadpatting(Entity target) {
		this.onStartHeadpatting(target);
		this.target = target;
		this.sync();
	}

	@Override
	public void endHeadpatting() {
		this.onEndHeadpatting();
		this.target = null;
		this.sync();
	}

	@Override
	public Optional<Entity> getTarget() {
		return Optional.ofNullable(this.target);
	}

	protected void sync() {
		ModComponents.HEADPATTING.sync(this.provider);
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		if (tag.contains("target")) {
			this.target = this.provider.getWorld().getEntityById(tag.getInt("target"));
		} else {
			this.target = null;
		}
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		this.getTarget().ifPresent(t -> tag.putInt("target", t.getId()));
	}
}
