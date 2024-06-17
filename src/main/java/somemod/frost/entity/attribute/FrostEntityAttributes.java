package somemod.frost.entity.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import somemod.SomeMod;
import somemod.mixin.LivingEntityAttributes;

public final class FrostEntityAttributes {

    // TODO: ItemStack.getTooltip
    public static final EntityAttribute WARMTH = SomeMod.registerEntityAttribute("warmth", new ClampedEntityAttribute("attribute.name.warmth", 0.0f, -16.0f, 16.0f));

    static {
        LivingEntityAttributes.addAttribute(WARMTH);
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}
}
