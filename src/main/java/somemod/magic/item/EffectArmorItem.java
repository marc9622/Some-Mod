package somemod.magic.item;

import java.util.function.Function;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

// TODO: Turn this into StaticEffectArmorItem, and create EffectArmorItem as an abstract super class that has an overrideable method called applyEffect instead of storing the effect directly.
// TODO: Add a RandomEffectArmorItem that gives specified effects at random.
public class EffectArmorItem extends ArmorItem {

    private final StatusEffectInstance effect;
    private final ArmorItem[] extraRequirements;

    public EffectArmorItem(ArmorMaterial material, Type type, Settings settings, StatusEffect effect, int amplifier, ArmorItem... extraRequirements) {
        this(material, type, settings, new StatusEffectInstance(effect, 20, amplifier, false, false, true), extraRequirements);
    }

    public EffectArmorItem(ArmorMaterial material, Type type, Settings settings, StatusEffectInstance effect, ArmorItem... extraRequirements) {
        super(material, type, settings);
        this.effect = effect;
        this.extraRequirements = extraRequirements;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (entity instanceof PlayerEntity player) {

                if (!playerHasEquipped(player, this.material, this.type)) return;
                for (ArmorItem requirement : extraRequirements) {
                    if (!playerHasEquipped(player, requirement.getMaterial(), requirement.getType())) return;
                }

                player.addStatusEffect(new StatusEffectInstance(effect));
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    protected static boolean playerHasEquipped(PlayerEntity player, ArmorMaterial material, Type type) {
        int slot = switch (type) {
            case HELMET     -> 3;
            case CHESTPLATE -> 2;
            case LEGGINGS   -> 1;
            case BOOTS      -> 0;
        };
        ItemStack stack = player.getInventory().getArmorStack(slot);

        if (!stack.isEmpty()) {
            if (stack.getItem() instanceof ArmorItem item) {
                return item.getMaterial() == material;
            }
        }

        return false;
    }

}
