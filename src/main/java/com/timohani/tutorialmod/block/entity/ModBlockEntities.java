package com.timohani.tutorialmod.block.entity;

import com.timohani.tutorialmod.TutorialMod;
import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "pedestal_be"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static void registerBlockEntities() {
        TutorialMod.LOGGER.info("Registering Block Entities for: " + TutorialMod.MOD_ID);
    }
}
