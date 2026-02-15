package com.timohani.tutorialmod;

import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.component.ModDataComponentTypes;
import com.timohani.tutorialmod.item.ModItemGroups;
import com.timohani.tutorialmod.item.ModItems;
import com.timohani.tutorialmod.sound.ModSounds;
import com.timohani.tutorialmod.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
    public static final String MOD_ID = "tutorialmod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModSounds.registerSounds();

        ModDataComponentTypes.registerDataComponentTypes();

        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 20000);

        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof SheepEntity sheepEntity && !world.isClient()) {
                if (player.getMainHandStack().getItem() == Items.END_ROD) {
                    player.sendMessage(Text.literal("THE PLAYER JUST HIT A SHEEP WITH AN END ROD!"));
                    player.getMainHandStack().decrement(1);
                    sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 6));
                }
            }

            return ActionResult.PASS;
        });
    }
}