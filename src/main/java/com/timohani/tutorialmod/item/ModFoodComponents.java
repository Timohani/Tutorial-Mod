package com.timohani.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().nutrition(12).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200), 0.3f).build();

    public static final FoodComponent HONEY_BERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.15f).snack().build();
}
