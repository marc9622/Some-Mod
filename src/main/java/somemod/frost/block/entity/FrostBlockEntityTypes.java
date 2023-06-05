package somemod.frost.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.Factory;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import somemod.SomeMod;
import somemod.common.block.entity.CustomChestBlockEntity;
import somemod.frost.block.FrostBlocks;

public class FrostBlockEntityTypes {

    public static final BlockEntityType<CustomChestBlockEntity> SPRUCE_CHEST_ENTITY =
        register("spruce_chest", SpruceChestBlockEntity::new, FrostBlocks.SPRUCE_CHEST);

    public static final BlockEntityType<CustomChestBlockEntity> ICE_CHEST_ENTITY =
        register("ice_chest", IceChestBlockEntity::new, FrostBlocks.ICE_CHEST);

    private static <T extends BlockEntity> BlockEntityType<T> register(String path, Factory<T> factory, Block... blocks) {
        return SomeMod.register(Registries.BLOCK_ENTITY_TYPE, path, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
