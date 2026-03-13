package com.timohani.tutorialmod;

import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.entity.ModEntities;
import com.timohani.tutorialmod.entity.client.MantisModel;
import com.timohani.tutorialmod.entity.client.MantisRender;
import com.timohani.tutorialmod.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

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
    }

    private static void cutOut(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }
}
