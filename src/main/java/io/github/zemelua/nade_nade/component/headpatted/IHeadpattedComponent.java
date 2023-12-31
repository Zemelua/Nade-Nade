package io.github.zemelua.nade_nade.component.headpatted;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

public interface IHeadpattedComponent extends ComponentV3, CommonTickingComponent, AutoSyncedComponent {
	boolean isHeadpattable(PlayerEntity owner);
	void startHeadpatted(PlayerEntity owner);
	void endHeadpatted();
	Optional<PlayerEntity> getOwner();

	default boolean isHeadpatted() {
		return this.getOwner().isPresent();
	}
}
