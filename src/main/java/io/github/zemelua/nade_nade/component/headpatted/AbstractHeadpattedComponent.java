package io.github.zemelua.nade_nade.component.headpatted;

import io.github.zemelua.nade_nade.component.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AbstractHeadpattedComponent<T extends Entity> implements IHeadpattedComponent {
	protected final T provider;
	@Nullable protected PlayerEntity owner;

	protected AbstractHeadpattedComponent(T provider) {
		this.provider = provider;
		this.owner = null;
	}

	protected void onStartHeadpatted(PlayerEntity newOwner) {}
	protected void onEndHeadpatted() {}

	@Override
	public void startHeadpatted(PlayerEntity owner) {
		this.onStartHeadpatted(owner);
		this.owner = owner;
		this.sync();
	}

	@Override
	public void endHeadpatted() {
		this.onEndHeadpatted();
		this.owner = null;
		this.sync();
	}

	@Override
	public Optional<PlayerEntity> getOwner() {
		return Optional.ofNullable(this.owner);
	}

	protected void sync() {
		ModComponents.HEADPATTED.sync(this.provider);
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		if (tag.contains("owner")) {
			this.owner = this.provider.getWorld().getPlayerByUuid(tag.getUuid("owner"));
		} else {
			this.owner = null;
		}
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		this.getOwner().ifPresent(t -> tag.putUuid("owner", t.getUuid()));
	}
}
