package io.github.zemelua.nade_nade.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.zemelua.nade_nade.NadeNade;
import io.github.zemelua.nade_nade.component.headpatted.IHeadpattedComponent;
import io.github.zemelua.nade_nade.component.headpatted.TameableHeadpattedComponent;
import io.github.zemelua.nade_nade.component.headpatting.IHeadpattingComponent;
import io.github.zemelua.nade_nade.component.headpatting.PlayerHeadpattingComponent;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.WolfEntity;

public class ModComponents implements EntityComponentInitializer {
	public static final ComponentKey<IHeadpattingComponent> HEADPATTING = ComponentRegistryV3.INSTANCE.getOrCreate(NadeNade.identifier("headpatting"), IHeadpattingComponent.class);
	public static final ComponentKey<IHeadpattedComponent> HEADPATTED = ComponentRegistryV3.INSTANCE.getOrCreate(NadeNade.identifier("headpatted"), IHeadpattedComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(HEADPATTING, PlayerHeadpattingComponent::new, RespawnCopyStrategy.NEVER_COPY);
		registry.registerFor(WolfEntity.class, HEADPATTED, TameableHeadpattedComponent::new);
		registry.registerFor(CatEntity.class, HEADPATTED, TameableHeadpattedComponent::new);
	}
}
