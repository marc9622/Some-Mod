package somemod.crystal.item;

import net.minecraft.item.HoeItem;

public class CrystalHoeItem extends HoeItem {
    
    public CrystalHoeItem(Settings settings) {
        super(CrystalToolMaterial.INSTANCE, -5, 0F, settings);
    }
}
