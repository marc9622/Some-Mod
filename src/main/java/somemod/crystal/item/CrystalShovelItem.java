package somemod.crystal.item;

import net.minecraft.item.ShovelItem;

public class CrystalShovelItem extends ShovelItem {
    
    public CrystalShovelItem(Settings settings) {
        super(CrystalToolMaterial.INSTANCE, 0.5F, -3F, settings);
    }
}
