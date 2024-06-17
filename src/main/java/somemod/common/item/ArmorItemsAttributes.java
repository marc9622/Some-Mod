package somemod.common.item;

import java.util.ArrayList;
import java.util.Collection;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public abstract class ArmorItemsAttributes {

    public record AttributePair(EntityAttribute attribute, EntityAttributeModifier modifier) {}

    @FunctionalInterface
    public static interface AttributeModifierAdder {
        @Nullable
        public AttributePair apply(ArmorMaterial material, ArmorItem.Type type);
    }

    public static final Collection<AttributeModifierAdder> customAttributes = new ArrayList<>();
    
}

