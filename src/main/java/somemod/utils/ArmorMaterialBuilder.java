package somemod.utils;

import java.util.function.Supplier;
import java.util.stream.Stream;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import somemod.SomeMod;

public final class ArmorMaterialBuilder {

    private static final int BASE_DURABILITY_HEAD = 11;
    private static final int BASE_DURABILITY_CHEST = 16;
    private static final int BASE_DURABILITY_LEGS = 15;
    private static final int BASE_DURABILITY_FEET = 13;

    @SafeVarargs
    public static ArmorMaterial of(String name, int durabilityMultiplier, int protectionHead, int protectionChest, int protectionLegs, int protectionFeet, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<ItemConvertible>... repairIngredients) {
        return new CustomArmorMaterial(name, durabilityMultiplier, protectionHead, protectionChest, protectionLegs, protectionFeet, enchantability, equipSound, toughness, knockbackResistance, repairIngredients);
    }

    private static class CustomArmorMaterial implements ArmorMaterial {

        private final String name;
        private final int durabilityMultiplier;
        private final int protectionHead;
        private final int protectionChest;
        private final int protectionLegs;
        private final int protectionFeet;
        private final int enchantability;
        private final SoundEvent equipSound;
        private final float toughness;
        private final float knockbackResistance;
        private final Supplier<Ingredient> repairIngredients;

        @SafeVarargs
        public CustomArmorMaterial(String name, int durabilityMultiplier, int protectionHead, int protectionChest, int protectionLegs, int protectionFeet, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<ItemConvertible>... repairIngredients) {
            this.name = name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.protectionHead = protectionHead;
            this.protectionChest = protectionChest;
            this.protectionLegs = protectionLegs;
            this.protectionFeet = protectionFeet;
            this.enchantability = enchantability;
            this.equipSound = equipSound;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredients = new Memoized<>(() -> Ingredient.ofItems(Stream.of(repairIngredients).map(Supplier::get).toArray(ItemConvertible[]::new)));
        }

        @Override
        public int getDurability(Type slot) {
            return switch (slot) {
                case HELMET -> BASE_DURABILITY_HEAD;
                case CHESTPLATE -> BASE_DURABILITY_CHEST;
                case LEGGINGS -> BASE_DURABILITY_LEGS;
                case BOOTS -> BASE_DURABILITY_FEET;
            } * this.durabilityMultiplier;
        }

        @Override
        public int getProtection(Type slot) {
            return switch (slot) {
                case HELMET -> this.protectionHead;
                case CHESTPLATE -> this.protectionChest;
                case LEGGINGS -> this.protectionLegs;
                case BOOTS -> this.protectionFeet;
            };
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getEquipSound() {
            return this.equipSound;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairIngredients.get();
        }

        @Override
        public String getName() {
            return SomeMod.idString(this.name);
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }

    private static class Memoized<T> implements Supplier<T> {

        private T result;
        private Supplier<T> supplier;

        public Memoized(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public final T get() {
            if (result == null) {
                result = supplier.get();
                supplier = null;
            }
            return result;
        }
    }
}

