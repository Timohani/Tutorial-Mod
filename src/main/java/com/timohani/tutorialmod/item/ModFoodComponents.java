package com.timohani.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200), 0.3f).build();
}
