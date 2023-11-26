package somemod.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Util;
import somemod.SomeMod;
import somemod.crystal.block.CrystalBlocks;
import somemod.crystal.item.CrystalItems;
import somemod.frost.item.FrostItems;
import somemod.magic.block.entity.EnchantedBookshelfBlockEntity;
import somemod.magic.enchantment.MagicEnchantments;
import somemod.magic.potion.MagicPotions;
import somemod.magic.screen.EnchantedBookshelfScreen;

public class LanguageProviders {

    public static class English extends FabricLanguageProvider {

        private static final Map<Object, Consumer<TranslationBuilder>> translations = new HashMap<>();

        public English(FabricDataOutput dataGenerator) {
            super(dataGenerator, "en_us");
        }

        @Override
        public void generateTranslations(TranslationBuilder builder) {
            addItem(CrystalItems.CRYSTAL_DUST, "End Stone Dust");
            addBlock(CrystalBlocks.CRYSTAL_BLOCK, "Block of Crystal");
            addBlock(CrystalBlocks.CRYSTAL_GLASS, "End Stone Crystal");

            addEnchantment(MagicEnchantments.HERMES, "Hermes Sprint");
            addEnchantment(MagicEnchantments.DULLNESS_CURSE, "Curse of Dullness");
            addPotion(MagicPotions.WOUNDED, "Wounding");
            addPotion(MagicPotions.MIGHTY_MINERS, "Mighty Miner's Elixir", "Mighty Miner's Splash Elixir", "Mighty Miner's Lingering Elixir", "Mighty Miner's Tipped Arrow");
            addPotion(MagicPotions.MEDITATION, "Meditation Brew", "Meditation Splash Brew", "Meditation Lingering Brew", "Meditation Tipped Arrow");

            addItem(FrostItems.ICE_QUEEN_CROWN, "The Ice Queen's Crown");

            builder.add(AdvancementProvider.END_BREAK_END_STONE_CRYSTAL_TITLE, "Ohhh Shiny!");
            builder.add(AdvancementProvider.END_BREAK_END_STONE_CRYSTAL_DESCRIPTION, "Break a block of end stone crystal");
            builder.add(AdvancementProvider.JEWELLERY_ROOT_TITLE, "Jewellery");
            builder.add(AdvancementProvider.JEWELLERY_ROOT_DESCRIPTION, "The next tier of equipment.");
            builder.add(AdvancementProvider.JEWELLERY_CRAFT_CRYSTAL_TITLE, "Crystal Clear");
            builder.add(AdvancementProvider.JEWELLERY_CRAFT_CRYSTAL_DESCRIPTION, "Craft a piece of crystal with crystal dust");
            builder.add(AdvancementProvider.JEWELLERY_CRAFT_CRYSTAL_ARMOR_TITLE, "Crystal Power");
            builder.add(AdvancementProvider.JEWELLERY_CRAFT_CRYSTAL_ARMOR_DESCRIPTION, "Use some crystals to make yourself a new set of armor.");
            builder.add(AdvancementProvider.JEWELLERY_CRAFT_CRYSTAL_TOOLS_TITLE, "Tools of Crystals");
            builder.add(AdvancementProvider.JEWELLERY_CRAFT_CRYSTAL_TOOLS_DESCRIPTION, "Use some crystals to make yourself some new tools.");

            builder.add(EnchantedBookshelfBlockEntity.ENCHANT_BOOK, "Enchant Book");
            builder.add(EnchantedBookshelfScreen.ENCHANTING_COST, "Enchanting Cost: %1$s");
            builder.add(EnchantedBookshelfScreen.ENCHANTING_TOO_EXPENSIVE, "Too Expensive");

            for (Consumer<TranslationBuilder> consumer : translations.values())
                consumer.accept(builder);
        }

        public static void addItem(Item item, String value) {
            translations.put(item, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor item: " + item.getTranslationKey());
                builder.add(Util.createTranslationKey("item", Registries.ITEM.getId(item)), value);
            });
        }

        public static void addBlock(Block block, String value) {
            translations.put(block, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor block: " + block.getTranslationKey());
                builder.add(block, value);
            });
        }

        public static void addEnchantment(Enchantment enchantment, String value) {
            translations.put(enchantment, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor enchantment: " + enchantment.getTranslationKey());
                builder.add(enchantment, value);
            });
        }

        public static void addStatusEffect(StatusEffect statusEffect, String value) {
            translations.put(statusEffect, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor status effect: " + statusEffect.getTranslationKey());
                builder.add(statusEffect, value);
            });
        }

        public static void addPotion(Potion potion, String value) {
            translations.put(potion, builder -> {
                String potionKey = potion.finishTranslationKey("potion.effect");
                String potionValue = "Potion of " + value;
                SomeMod.logDebug("Adding translation: " + potionValue + "\tfor potion: " + potionKey);
                builder.add(potionKey, potionValue);

                String splashKey = potion.finishTranslationKey("splash_potion.effect");
                String splashValue = "Splash Potion of " + value;
                SomeMod.logDebug("Adding translation: " + splashValue + "\tfor potion: " + splashKey);
                builder.add(splashKey, splashValue);

                String lingeringKey = potion.finishTranslationKey("lingering_potion.effect");
                String lingeringValue = "Lingering Potion of " + value;
                SomeMod.logDebug("Adding translation: " + lingeringValue + "\tfor potion: " + lingeringKey);
                
                String arrowKey = potion.finishTranslationKey("tipped_arrow.effect");
                String arrowValue = "Arrow of " + value;
                SomeMod.logDebug("Adding translation: " + arrowValue + "\tfor potion: " + arrowKey);
                builder.add(arrowKey, arrowValue);
            });
        }

        public static void addPotion(Potion potion, String potionValue, String splashValue, String lingeringValue, String arrowValue) {
            translations.put(potion, builder -> {
                String potionKey = potion.finishTranslationKey("potion.effect");
                SomeMod.logDebug("Adding translation: " + potionValue + "\tfor potion: " + potionKey);
                builder.add(potionKey, potionValue);

                String splashKey = potion.finishTranslationKey("splash_potion.effect");
                SomeMod.logDebug("Adding translation: " + splashValue + "\tfor potion: " + splashKey);
                builder.add(splashKey, splashValue);

                String lingeringKey = potion.finishTranslationKey("lingering_potion.effect");
                SomeMod.logDebug("Adding translation: " + lingeringValue + "\tfor potion: " + lingeringKey);
                
                String arrowKey = potion.finishTranslationKey("tipped_arrow.effect");
                SomeMod.logDebug("Adding translation: " + arrowValue + "\tfor potion: " + arrowKey);
                builder.add(arrowKey, arrowValue);
            });
        }

        public static void addEntityType(EntityType<?> entityType, String value) {
            translations.put(entityType, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor entity type: " + entityType.getTranslationKey());
                builder.add(entityType, value);
            });
        }

        public static void addEntityAttribute(EntityAttribute entityAttribute, String value) {
            translations.put(entityAttribute, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor entity type: " + entityAttribute.getTranslationKey());
                builder.add(entityAttribute, value);
            });
        }

        public static void addItemGroup(RegistryKey<ItemGroup> itemGroup, String value) {
            translations.put(itemGroup, builder -> {
                SomeMod.logDebug("Adding translation: " + value + "\tfor item group");
                builder.add(itemGroup, value);
            });
        }

    }

}

