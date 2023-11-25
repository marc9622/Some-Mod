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
            factories.add((entity, random) -> {
                ItemStack enchantedBlizzardBoots = new ItemStack(FrostItems.BLIZZARD_BOOTS);
                EnchantmentHelper.set(Map.of(FrostEnchantments.WINTER_WALKER, 2), new ItemStack(FrostItems.BLIZZARD_BOOTS));
                return new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(FrostItems.BLIZZARD_BOOTS),
                    enchantedBlizzardBoots,
                    12, 10, 0.05f
                );
            });
        });
    }
}
