package io.github.zemelua.nade_nade.client;

import io.github.zemelua.nade_nade.NadeNade;
import io.github.zemelua.umu_config.api.config.container.ConfigContainer;
import io.github.zemelua.umu_config.api.config.container.IConfigContainer;
import io.github.zemelua.umu_config.api.config.value.BooleanConfigValue;
import io.github.zemelua.umu_config.api.config.value.IConfigValue;
import io.github.zemelua.umu_config.api.util.UMUConfigClothUtils;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.minecraft.text.Text;

public class ModClientConfig {
	private final IConfigValue<Boolean> useAttackKey;
	private final IConfigValue<Boolean> useAdditionalKey;
	private final IConfigContainer container;

	protected ModClientConfig() {
		this.useAttackKey = BooleanConfigValue.builder(NadeNade.identifier("use_attack_key"))
				.defaultValue(true)
				.build();
		this.useAdditionalKey = BooleanConfigValue.builder(NadeNade.identifier("use_additional_key"))
				.defaultValue(false)
				.build();
		this.container = ConfigContainer.builder(NadeNade.identifier("nade_nade_client"))
				.addValue(this.useAttackKey)
				.addValue(this.useAttackKey)
				.build();
	}

	public boolean useAttackKey() {
		return this.useAttackKey.getValue();
	}

	public boolean useAdditionalKey() {
		return this.useAdditionalKey.getValue();
	}

	public IConfigContainer getContainer() {
		return this.container;
	}

	public ConfigCategory asScreen(ConfigBuilder builder, ConfigCategory category) {
		BooleanListEntry useAdditionalKeyEntry = UMUConfigClothUtils.toggle(builder, this.useAdditionalKey).build();

		return category.addEntry(UMUConfigClothUtils.toggle(builder, this.useAttackKey).build())
				.addEntry(useAdditionalKeyEntry)
				.addEntry(builder.entryBuilder().fillKeybindingField(Text.translatable(NadeNadeClient.KEY_HEADPAT.getTranslationKey()), NadeNadeClient.KEY_HEADPAT)
						.setDefaultValue(NadeNadeClient.KEY_HEADPAT.getDefaultKey())
						.setRequirement(Requirement.isTrue(useAdditionalKeyEntry))
						.build());
	}
}
