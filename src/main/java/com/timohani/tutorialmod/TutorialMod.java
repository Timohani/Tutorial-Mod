package com.timohani.tutorialmod;

import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.block.entity.ModBlockEntities;
import com.timohani.tutorialmod.component.ModDataComponentTypes;
import com.timohani.tutorialmod.effect.ModEffects;
import com.timohani.tutorialmod.enchantment.ModEnchantmentEffects;
import com.timohani.tutorialmod.entity.ModEntities;
import com.timohani.tutorialmod.entity.custom.MantisEntity;
import com.timohani.tutorialmod.item.ModItemGroups;
import com.timohani.tutorialmod.item.ModItems;
import com.timohani.tutorialmod.particle.ModParticles;
import com.timohani.tutorialmod.potions.ModPotions;
import com.timohani.tutorialmod.screen.ModScreenHandlers;
import com.timohani.tutorialmod.sound.ModSounds;
import com.timohani.tutorialmod.util.HammerUsageEvent;
import com.timohani.tutorialmod.util.ModLootTableModifiers;
import com.timohani.tutorialmod.villager.ModVillagerTrades;
import com.timohani.tutorialmod.villager.ModVillagers;
import com.timohani.tutorialmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
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

        ModEffects.registerEffects();
        ModPotions.registerPotions();

        ModEnchantmentEffects.registerEnchantmentEffects();

        ModDataComponentTypes.registerDataComponentTypes();
        ModWorldGeneration.generateModWorldGen();

        ModEntities.registerModEntities();
        ModVillagers.registerVillagers();
        ModVillagerTrades.createTrades();
        ModParticles.registerParticles();

        ModLootTableModifiers.modifyLootTables();

        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();

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

        FabricBrewingRecipeRegistryBuilder.BUILD.register(
                builder -> builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION));

        CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.5f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.25f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.HONEY_BERRIES, 0.15f);

        StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_LOG, ModBlocks.STRIPPED_DRIFTWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_WOOD, ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LEAVES, 30, 60);


        FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
    }
}