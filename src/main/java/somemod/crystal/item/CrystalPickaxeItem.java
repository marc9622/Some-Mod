package somemod.crystal.item;

import net.minecraft.item.PickaxeItem;

public class CrystalPickaxeItem extends PickaxeItem {
    
    public CrystalPickaxeItem(Settings settings) {
        super(CrystalToolMaterial.INSTANCE, 0, -2.8F, settings);
    }
}
