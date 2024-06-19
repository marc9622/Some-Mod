package somemod.frost.entity.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import somemod.SomeMod;

public final class FrostEntityAttributes {

    // This is tracked so that the client can calculate the player's warmth and display it in the F3 debug menu.
    public static final EntityAttribute WARMTH = SomeMod.registerEntityAttribute("warmth", new ClampedEntityAttribute("attribute.name.warmth", 0.0f, -16.0f, 16.0f).setTracked(true));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}
}
