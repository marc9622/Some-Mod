package somemod.enchanting.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import somemod.SomeMod;
import somemod.enchanting.block.EnchantingBlocks;

public class EnchantingBlockEntities {
    
    public static final BlockEntityType<EnchantedBookshelfBlockEntity> ENCHANTED_BOOKSHELF_ENTITY = SomeMod.register(
        Registries.BLOCK_ENTITY_TYPE,
        "enchanted_bookshelf_entity",
        FabricBlockEntityTypeBuilder.create(EnchantedBookshelfBlockEntity::new, EnchantingBlocks.ENCHANTED_BOOKSHELF).build()
    );

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
