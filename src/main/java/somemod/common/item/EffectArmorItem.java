package somemod.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * An armor item that applies a list of effects to the player wearing it.
 * <p>
 * The effects are applied every tick, as long as the player is wearing the
 * armor and {@link #extraRequirementsMet(PlayerEntity, int)} returns true.
 */
public abstract class EffectArmorItem extends ArmorItem {

    protected final ArmorItem[] otherArmorRequired;

    public EffectArmorItem(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, ArmorItem... otherArmorRequired) {
        super(material, type, settings);
        this.otherArmorRequired = otherArmorRequired;
    }

    @Override
    public final void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            inventoryTickBeforeEffect(stack, world, player, slot, selected);

            if (!world.isClient) {
                if (!playerHasEquipped(player, this.material, this.type))
                    return;

                for (ArmorItem requirement : otherArmorRequired) {
                    if (!EffectArmorItem.playerHasEquipped(player, requirement.getMaterial(), requirement.getType()))
                        return;
                }

                if (!extraRequirementsMet(player, world, slot))
                    return;

                for (StatusEffectInstance effect : effectsToApply(player))
                    player.addStatusEffect(new StatusEffectInstance(effect));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    protected void inventoryTickBeforeEffect(ItemStack stack, World world, PlayerEntity player, int slot, boolean selected) {
    }

    protected boolean extraRequirementsMet(PlayerEntity player, World world, int slot) {
        return true;
    }

    /**
     * Should return an array of {@link StatusEffectInstance}s to apply to the
     * player wearing this armor.
     * <p>
     * The {@link StatusEffectInstance}s will be copied before being applied,
     * so you don't have to worry about them being modified.
     */
    protected abstract StatusEffectInstance[] effectsToApply(PlayerEntity player);

    public static boolean playerHasEquipped(PlayerEntity player, ArmorMaterial material, ArmorItem.Type type) {
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

