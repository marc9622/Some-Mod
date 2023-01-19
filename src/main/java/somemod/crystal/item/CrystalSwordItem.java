package somemod.crystal.item;

import net.minecraft.item.SwordItem;

public class CrystalSwordItem extends SwordItem {
    
    public CrystalSwordItem(Settings settings) {
        super(CrystalToolMaterial.INSTANCE, 3, -2.4F, settings);
    }
}
