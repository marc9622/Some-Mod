package somemod.crystal.block;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import somemod.SomeMod;

public class CrystalBlockTags {
    
    public static final TagKey<Block> GUARDED_BY_ENDERMEN = TagKey.of(RegistryKeys.BLOCK, SomeMod.id("guarded_by_endermen"));

}
