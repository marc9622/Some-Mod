package somemod.frost.village;

import java.util.Map;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import somemod.frost.enchantment.FrostEnchantments;
import somemod.frost.item.FrostItems;

public final class FrostTradeOffers {

    public static void registerTradeOffers() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD),
                new ItemStack(FrostItems.BLIZZARD_BOOTS),
                blizzardBoots(1),
                6, 10, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 2),
                new ItemStack(FrostItems.BLIZZARD_BOOTS),
                blizzardBoots(2),
                6, 15, 0.2f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(FrostItems.BLIZZARD_BOOTS),
                blizzardBoots(3),
                6, 30, 0.2f
            ));
        });
    }

    private static ItemStack blizzardBoots(int level) {
        ItemStack stack = new ItemStack(FrostItems.BLIZZARD_BOOTS);
        EnchantmentHelper.set(Map.of(FrostEnchantments.WINTER_WALKER, level), stack);
        return stack;
    }
}
