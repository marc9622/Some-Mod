package somemod.crystal.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CrystalToolMaterial implements ToolMaterial {
    
    public static final CrystalToolMaterial INSTANCE = new CrystalToolMaterial();

    private CrystalToolMaterial() {}

    @Override
    public int getDurability() {
        return 2031;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7F;
    }

    @Override
    public float getAttackDamage() {
        return 5F;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(CrystalItems.CRYSTAL_DUST);
    }

}