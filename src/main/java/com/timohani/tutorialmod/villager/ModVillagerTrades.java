package com.timohani.tutorialmod.villager;

import com.timohani.tutorialmod.block.ModBlocks;
import com.timohani.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class ModVillagerTrades {

    public static void createTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 50),
                    new ItemStack(ModItems.CAULIFLOWER, 8), 7, 2, 0.04f));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.DIAMOND, 50),
                    new ItemStack(ModItems.CAULIFLOWER_SEEDS, 1), 3, 4, 0.04f));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> factories.add((entity, random) -> new TradeOffer(
                new TradedItem(Items.OAK_PLANKS, 1),
                new ItemStack(ModItems.HONEY_BERRIES, 1), 32, 2, 0.04f)));

        TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.NETHERITE_INGOT, 10),
                    new ItemStack(ModItems.CHISEL, 1), 3, 2, 0.04f));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 10),
                    new ItemStack(ModBlocks.DRIFTWOOD_SAPLING, 1), 30, 2, 0.04f));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.DIRT, 10),
                    new ItemStack(Items.SLIME_BALL, 1), 30, 10, 0.04f));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 2, factories -> factories.add((entity, random) -> new TradeOffer(
                new TradedItem(ModItems.PINK_GARNET, 10),
                Optional.of(new TradedItem(Items.IRON_NUGGET, 2)),
                new ItemStack(ModItems.TOMAHAWK, 1), 64, 12, 0.04f)));
    }
}
