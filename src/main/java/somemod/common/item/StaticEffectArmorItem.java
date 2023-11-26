package somemod.common.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

/**
 * An armor item that applies a list of effects to the player wearing it.
 * <p>
 * Use {@link #of(ArmorMaterial, ArmorItem.Type)} to create a new instance.
 */
public class StaticEffectArmorItem extends EffectArmorItem {

    private final StatusEffectInstance[] effects;

    protected StaticEffectArmorItem(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, StatusEffectInstance[] effects, ArmorItem... otherArmorRequired) {
        super(material, type, settings, otherArmorRequired);
        this.effects = effects;
    }

    @Override
    protected StatusEffectInstance[] effectsToApply(PlayerEntity player) {
        return effects;
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

        public Require gives(StatusEffect type) {
            return gives(type, 0);
        }

        public Require gives(StatusEffect type, int amplifier) {
            return gives(new StatusEffectInstance(type, 75, amplifier));
        }

        public Require gives(StatusEffectInstance effect) {
            return new Require(new ArmorItem[]{}).gives(effect);
        }

        public final class Require {
            private final ArmorItem[] otherArmorRequired;
            private final List<StatusEffectInstance> effects;

            private Require(ArmorItem[] otherArmorRequired) {
                this.otherArmorRequired = otherArmorRequired;
                this.effects = new ArrayList<>();
            }

            public Require gives(StatusEffect type) {
                return gives(type, 0);
            }

            public Require gives(StatusEffect type, int amplifier) {
                return gives(new StatusEffectInstance(type, 75, amplifier));
            }

            public Require gives(StatusEffectInstance effect) {
                this.effects.add(effect);
                return this;
            }

            public Function<Item.Settings, StaticEffectArmorItem> build() {
                return settings -> build(settings);
            }

            public StaticEffectArmorItem build(Item.Settings settings) {
                return new StaticEffectArmorItem(Builder.this.material, Builder.this.type, settings, this.effects.toArray(StatusEffectInstance[]::new), this.otherArmorRequired);
            }
        }
    }

}

