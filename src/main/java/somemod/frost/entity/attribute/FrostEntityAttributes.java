package somemod.frost.entity.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import somemod.SomeMod;

public final class FrostEntityAttributes {

    // TODO: ItemStack.getTooltip
    public static final EntityAttribute WARMTH = SomeMod.registerEntityAttribute("warmth", new ClampedEntityAttribute("attribute.name.warmth", 0.0f, -16.0f, 16.0f));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}
}
