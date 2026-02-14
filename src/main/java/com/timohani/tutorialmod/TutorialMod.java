package com.timohani.tutorialmod;

import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.component.ModDataComponentTypes;
import com.timohani.tutorialmod.item.ModItemGroups;
import com.timohani.tutorialmod.item.ModItems;
import com.timohani.tutorialmod.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
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

		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 20000);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
	}
}