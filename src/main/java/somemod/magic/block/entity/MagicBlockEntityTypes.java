package somemod.magic.block.entity;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BlockEntityType.BlockEntityFactory;
import net.minecraft.registry.Registries;
import somemod.SomeMod;
import somemod.magic.block.MagicBlocks;

public class MagicBlockEntityTypes {
    
    public static final BlockEntityType<CustomChestBlockEntity> FORGOTTEN_CHEST = register("forgotten_chest", ForgottenChestBlockEntity::new, MagicBlocks.FORGOTTEN_CHEST);

    private static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityFactory<? extends T> factory, Block... blocks) {
        return SomeMod.register(Registries.BLOCK_ENTITY_TYPE, path, new BlockEntityType<T>(factory, Set.of(blocks), null));
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
