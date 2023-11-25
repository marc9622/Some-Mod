package somemod.magic.village;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import somemod.magic.item.MagicItems;

public final class MagicTradeOffers {

    public static void registerTradeOffers() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(MagicItems.ELVEN_STEEL, 3),
                new ItemStack(Items.EMERALD),
                12, 2, 0.05f
            ));
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(MagicItems.ALCHEMIST_BOOTS),
                6, 2, 0.2f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(MagicItems.ALCHEMIST_PANTS),
                6, 5, 0.2f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(MagicItems.ALCHEMIST_JACKET),
                6, 10, 0.2f
            ));
        });

        // TODO: Maybe make a pirate villager type?
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 2),
                new ItemStack(MagicItems.PIRATE_HAT),
                12, 1, 0.2f
            ));
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 2),
                new ItemStack(MagicItems.PIRATE_BOOTS),
                12, 1, 0.2f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(MagicItems.PIRATE_SHIRT),
                12, 2, 0.2f
            ));
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(MagicItems.PIRATE_PANTS),
                12, 2, 0.2f
            ));
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(MagicItems.OCEANIC_LEGGINGS),
                6, 5, 0.2f
            ));
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(MagicItems.OCEANIC_SHOES),
                6, 5, 0.2f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 7),
                new ItemStack(MagicItems.OCEANIC_MASK),
                6, 10, 0.2f
            ));
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 7),
                new ItemStack(MagicItems.OCEANIC_SUIT),
                6, 10, 0.2f
            ));
        });
    }

}
