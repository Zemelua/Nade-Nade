package io.github.zemelua.nade_nade.mixin;

import com.google.common.collect.Ordering;
import com.mojang.datafixers.util.Pair;
import io.github.zemelua.nade_nade.entity.effect.ModStatusEffects;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.attribute.AttributeModifierCreator;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.stream.Collectors;

@Mixin(AbstractInventoryScreen.class)
public abstract class MixinAbstractInventoryScreen<T extends ScreenHandler> extends HandledScreen<T> {
	@Inject(method = "drawStatusEffects",
			at = @At("TAIL"))
	private void drawUnlonelyEffectTooltip(DrawContext context, int mouseX, int mouseY, CallbackInfo callback) {
		int i = this.x + this.backgroundWidth + 2;
		int j = this.width - i;
		Collection<StatusEffectInstance> effects = Optional.ofNullable(this.client)
				.map(c -> c.player)
				.stream()
				.flatMap(p -> p.getStatusEffects().stream())
				.collect(Collectors.toList());
		if (effects.isEmpty() || j < 32) {
			return;
		}
		boolean bl = j >= 120;
		int entryHeight = 33;
		if (effects.size() > 5) {
			entryHeight = 132 / (effects.size() - 1);
		}

		List<StatusEffectInstance> iterable = Ordering.natural().sortedCopy(effects);
		int l = this.y;
		for (StatusEffectInstance statusEffectInstance2 : iterable) {
			if (statusEffectInstance2.getEffectType() == ModStatusEffects.UNLONELY) {
				if (mouseY >= l && mouseY <= l + entryHeight) {
					if (bl) {
						if (mouseX >= i && mouseX <= i + 120) {
							this.drawUnlonelyEffectTooltip(context, mouseX, mouseY, statusEffectInstance2.getAmplifier());
						}
					} else if (mouseX >= i && mouseX <= i + 33) {
						this.drawUnlonelyEffectTooltip(context, mouseX, mouseY, statusEffectInstance2.getAmplifier());
					}
				}
			}

			l += entryHeight;
		}
	}

	@Unique
	private void drawUnlonelyEffectTooltip(DrawContext context, int mouseX, int mouseY, int amplifier) {
		List<Text> list = new ArrayList<>();
		List<Pair<EntityAttribute, EntityAttributeModifier>> list2 = new ArrayList<>();

		for (Map.Entry<EntityAttribute, AttributeModifierCreator> entry : ModStatusEffects.UNLONELY.getAttributeModifiers().entrySet()) {
			list2.add(new Pair<>(entry.getKey(), entry.getValue().createAttributeModifier(amplifier)));
		}

		if (!list2.isEmpty()) {
			list.add(Text.translatable("potion.whenDrank").formatted(Formatting.DARK_PURPLE));
			for (Pair<EntityAttribute, EntityAttributeModifier> pair : list2) {
				EntityAttributeModifier entityAttributeModifier = pair.getSecond();
				double d = entityAttributeModifier.getValue();
				double e = entityAttributeModifier.getOperation() == EntityAttributeModifier.Operation.MULTIPLY_BASE || entityAttributeModifier.getOperation() == EntityAttributeModifier.Operation.MULTIPLY_TOTAL ? entityAttributeModifier.getValue() * 100.0 : entityAttributeModifier.getValue();
				if (d > 0.0) {
					list.add(Text.translatable("attribute.modifier.plus." + entityAttributeModifier.getOperation().getId(), ItemStack.MODIFIER_FORMAT.format(e), Text.translatable(((EntityAttribute)pair.getFirst()).getTranslationKey())).formatted(Formatting.BLUE));
					continue;
				}
				if (!(d < 0.0)) continue;
				list.add(Text.translatable("attribute.modifier.take." + entityAttributeModifier.getOperation().getId(), ItemStack.MODIFIER_FORMAT.format(e *= -1.0), Text.translatable(((EntityAttribute)pair.getFirst()).getTranslationKey())).formatted(Formatting.RED));
			}
		}

		context.drawTooltip(this.textRenderer, list, Optional.empty(), mouseX, mouseY);
	}

	private MixinAbstractInventoryScreen(T handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}
}
