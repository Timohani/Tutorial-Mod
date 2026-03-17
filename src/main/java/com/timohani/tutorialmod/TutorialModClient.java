package com.timohani.tutorialmod;

import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.block.entity.ModBlockEntities;
import com.timohani.tutorialmod.block.entity.renderer.PedestalBlockEntityRenderer;
import com.timohani.tutorialmod.entity.ModEntities;
import com.timohani.tutorialmod.entity.client.*;
import com.timohani.tutorialmod.particle.ModParticles;
import com.timohani.tutorialmod.particle.PinkGarnetParticle;
import com.timohani.tutorialmod.screen.ModScreenHandlers;
import com.timohani.tutorialmod.screen.custom.GrowthChamberScreen;
import com.timohani.tutorialmod.screen.custom.PedestalScreen;
import com.timohani.tutorialmod.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        cutOut(ModBlocks.PINK_GARNET_TRAPDOOR);
        cutOut(ModBlocks.PINK_GARNET_DOOR);

        cutOut(ModBlocks.CAULIFLOWER_CROP);
        cutOut(ModBlocks.HONEY_BERRY_BUSH);

        cutOut(ModBlocks.DRIFTWOOD_SAPLING);

        ModModelPredicates.registerModelPredicates();

        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRender::new);

        EntityModelLayerRegistry.registerModelLayer(TomahawkProjectileModel.TOMAHAWK, TomahawkProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TOMAHAWK, TomahawkProjectileRenderer::new);

        EntityRendererRegistry.register(ModEntities.CHAIR, ChairRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.PINK_GARNET_PARTICLE, PinkGarnetParticle.Factory::new);

        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);

        HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
        HandledScreens.register(ModScreenHandlers.GROWTH_CHAMBER_SCREEN_HANDLER, GrowthChamberScreen::new);
    }

    private static void cutOut(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }
}
