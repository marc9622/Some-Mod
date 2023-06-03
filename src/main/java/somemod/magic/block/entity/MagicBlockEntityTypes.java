package somemod.magic.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.Factory;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import somemod.SomeMod;
import somemod.magic.block.MagicBlocks;

public class MagicBlockEntityTypes {
    
    public static final BlockEntityType<EnchantedBookshelfBlockEntity> ENCHANTED_BOOKSHELF_ENTITY =
        register("enchanted_bookshelf_entity", EnchantedBookshelfBlockEntity::new, MagicBlocks.ENCHANTED_BOOKSHELF);

    public static final BlockEntityType<CustomChestBlockEntity> FORGOTTEN_CHEST_ENTITY =
        register("forgotten_chest", ForgottenChestBlockEntity::new, MagicBlocks.FORGOTTEN_CHEST);

    private static <T extends BlockEntity> BlockEntityType<T> register(String path, Factory<T> factory, Block... blocks) {
        return SomeMod.register(Registries.BLOCK_ENTITY_TYPE, path, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
