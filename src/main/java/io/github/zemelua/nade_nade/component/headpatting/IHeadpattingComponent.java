package io.github.zemelua.nade_nade.component.headpatting;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.Entity;

import java.util.Optional;

public interface IHeadpattingComponent extends ComponentV3, ServerTickingComponent, AutoSyncedComponent {
	boolean canHeadpat(Entity target);
	void startHeadpatting(Entity target);
	void endHeadpatting();
	Optional<Entity> getTarget();

	default boolean isHeadpatting() {
		return this.getTarget().isPresent();
	}

	default boolean isHeadpattingWith(Entity target) {
		return this.getTarget()
				.filter(t -> t.equals(target))
				.isPresent();
	}
}
