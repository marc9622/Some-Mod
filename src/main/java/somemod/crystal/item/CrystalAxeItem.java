package somemod.crystal.item;

import net.minecraft.item.AxeItem;

public class CrystalAxeItem extends AxeItem {
    
    public CrystalAxeItem(Settings settings) {
        super(CrystalToolMaterial.INSTANCE, 5, -3F, settings);
    }
}
