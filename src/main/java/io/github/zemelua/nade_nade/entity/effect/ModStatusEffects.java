package io.github.zemelua.nade_nade.entity.effect;

import io.github.zemelua.nade_nade.NadeNade;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModStatusEffects {
	public static final StatusEffect UNLONELY = new StatusEffect(StatusEffectCategory.BENEFICIAL, 33)
			.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "91C1E2D0-47AA-F2C0-76E9-A2CE9BCA7681", 0.1D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
			.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "32478D63-69BA-FD6D-E3AA-253A70D23A1B", 0.1D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
			.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, "EC632438-6701-FF22-4F3C-5F0FF02C18E3", 1.0D, EntityAttributeModifier.Operation.ADDITION);

	public static void initialize() {
		Registry.register(Registries.STATUS_EFFECT, NadeNade.identifier("unlonely"), UNLONELY);
	}
}
