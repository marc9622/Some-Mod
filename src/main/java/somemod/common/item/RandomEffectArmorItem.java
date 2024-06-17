package somemod.common.item;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Multimap;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.math.random.Random;
import somemod.utils.ItemBuilder.AttributeItemConstructor;

/**
 * TODO
 */
public class RandomEffectArmorItem extends EffectArmorItem {
    protected record WeightedGroup(StatusEffect[] effects, int[] amplifiers, int duration, float chance) {}

    private final WeightedGroup[] groups;
    private final List<StatusEffectInstance> tempInstances = new ArrayList<>();

    protected RandomEffectArmorItem(
        ArmorMaterial material, ArmorItem.Type type, Item.Settings settings,
        Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers,
        WeightedGroup[] groups, ArmorItem... otherArmorRequired
    ) {
        super(material, type, settings, attributeModifiers, otherArmorRequired);
        this.groups = groups;
    }

    @Override
    protected StatusEffectInstance[] effectsToApply(PlayerEntity player) {
        Random random = player.getRandom();

        for (WeightedGroup group : groups) {
            if (random.nextFloat() < group.chance) {
                for (int i = 0; i < group.effects.length; i++) {
                    StatusEffectInstance instance = new StatusEffectInstance(group.effects[i], group.duration, group.amplifiers[i], true, true, true);
                    tempInstances.add(instance);
                }
            }
        }

        StatusEffectInstance[] array = tempInstances.toArray(StatusEffectInstance[]::new);
        tempInstances.clear();
        return array;
    }

    public static Builder of(ArmorMaterial material, ArmorItem.Type type) {
        return new Builder(material, type);
    }

    public static final class Builder {
        private final ArmorMaterial material;
        private final ArmorItem.Type type;

        private Builder(ArmorMaterial material, ArmorItem.Type type) {
            this.material = material;
            this.type = type;
        }

        public Require requires(ArmorItem... otherArmorRequired) {
            return new Require(otherArmorRequired);
        }

        public final class Require {
            private final ArmorItem[] otherArmorRequired;
            private final List<WeightedGroup> groups;

            private Require(ArmorItem[] otherArmorRequired) {
                this.otherArmorRequired = otherArmorRequired;
                this.groups = new ArrayList<>();
            }

            public Single addGroupSingle(StatusEffect effect) {
                return addGroupSingle(effect, 0);
            }

            public Single addGroupSingle(StatusEffect effect, int amplifier) {
                return new Single(effect, amplifier);
            }

            public Require copyGroupSingle(StatusEffect effect) {
                return copyGroupSingle(effect, 0);
            }

            public Require copyGroupSingle(StatusEffect effect, int amplifier) {
                if (groups.size() <= 0) throw new IllegalStateException("Tried to copy previous group, but there were none.");
                WeightedGroup previous = groups.get(groups.size() - 1);
                this.groups.add(new WeightedGroup(
                    new StatusEffect[]{effect},
                    new int[]{amplifier},
                    previous.duration,
                    previous.chance
                ));
                return this;
            }

            public Multi addGroup(StatusEffect effect) {
                return addGroup(effect, 0);
            }

            public Multi addGroup(StatusEffect effect, int amplifier) {
                return new Multi(effect, amplifier);
            }

            public AttributeItemConstructor<RandomEffectArmorItem> build() {
                return (settings, attributeModifiers) -> build(settings, attributeModifiers);
            }

            public RandomEffectArmorItem build(Item.Settings settings, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
                return new RandomEffectArmorItem(Builder.this.material, Builder.this.type, settings, attributeModifiers, this.groups.toArray(WeightedGroup[]::new), this.otherArmorRequired);
            }

            public final class Single {
                private final StatusEffect effect;
                private final int amplifier;

                private Single(StatusEffect effect, int amplifier) {
                    this.effect = effect;
                    this.amplifier = amplifier;
                }

                public Require durationAndChance(int duration, float chance) {
                    Require.this.groups.add(new WeightedGroup(
                        new StatusEffect[]{Single.this.effect},
                        new int[]{Single.this.amplifier},
                        duration,
                        chance
                    ));
                    return Require.this;
                }
            }

            public final class Multi {
                private final List<StatusEffect> effects;
                private final List<Integer> amplifiers;

                private Multi(StatusEffect effect, int amplifier) {
                    this.effects = new ArrayList<>();
                    this.effects.add(effect);
                    this.amplifiers = new ArrayList<>();
                    this.amplifiers.add(amplifier);
                }

                public Multi and(StatusEffect effect) {
                    return and(effect, 0);
                }

                public Multi and(StatusEffect effect, int amplifier) {
                    this.effects.add(effect);
                    this.amplifiers.add(amplifier);
                    return this;
                }

                public Require durationAndChance(int duration, float chance) {
                    Require.this.groups.add(new WeightedGroup(
                        this.effects.toArray(StatusEffect[]::new),
                        this.amplifiers.stream().mapToInt(Integer::intValue).toArray(),
                        duration,
                        chance
                    ));
                    return Require.this;
                }
            }
        }
    }

}

