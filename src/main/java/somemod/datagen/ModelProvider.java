package somemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;

import static somemod.crystal.block.CrystalBlocks.*;
import static somemod.crystal.item.CrystalItems.*;
import static somemod.frost.item.FrostItems.*;
import static somemod.magic.block.MagicBlocks.*;
import static somemod.magic.item.MagicItems.*;

import java.util.stream.Stream;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        
        //#region CRYSTAL
        generator.registerSimpleCubeAll(CRYSTAL_GLASS);
        generator.registerSimpleCubeAll(CRYSTAL_BLOCK);

        generator.registerSimpleCubeAll(CITRINE_ORE);
        generator.registerSimpleCubeAll(RUBY_ORE);
        generator.registerSimpleCubeAll(SAPPHIRE_ORE);
        //#endregion

        //#region MAGIC
        generator.registerSingleton(
            ENCHANTED_BOOKSHELF,
            TextureMap.sideEnd(TextureMap.getId(ENCHANTED_BOOKSHELF), TextureMap.getId(Blocks.GOLD_BLOCK)),
            Models.CUBE_COLUMN);

        generator.registerSingleton(
            OBSIDIAN_ENCHANTED_BOOKSHELF,
            TextureMap.sideEnd(TextureMap.getId(OBSIDIAN_ENCHANTED_BOOKSHELF), TextureMap.getId(Blocks.OBSIDIAN)),
            Models.CUBE_COLUMN);
        //#endregion

    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        
        Stream.of(
            //#region CRYSTAL
            CRYSTAL_DUST,
            CRYSTAL,
            CITRINE,
            RUBY,
            SAPPHIRE,

            CRYSTAL_SHOVEL,
            CRYSTAL_PICKAXE,
            CRYSTAL_AXE,
            CRYSTAL_HOE,
            CRYSTAL_SWORD,

            CRYSTAL_HELMET,
            CRYSTAL_CHESTPLATE,
            CRYSTAL_LEGGINGS,
            CRYSTAL_BOOTS,

            DRAGON_SCALE_HELMET,
            DRAGON_SCALE_CHESTPLATE,
            DRAGON_SCALE_LEGGINGS,
            DRAGON_SCALE_BOOTS,
            //#endregion
            
            //#region FROST
            ARCTIC_HAT,
            ARCTIC_JACKET,
            ARCTIC_PANTS,
            ARCTIC_BOOTS,

            GLACIER_HELMET,
            GLACIER_CHESTPLATE,
            GLACIER_LEGGINGS,
            GLACIER_BOOTS,

            BLIZZARD_BOOTS,

            FROSTBITE_CHESTPLATE,
            FROSTBITE_LEGGINGS,

            ICE_QUEEN_CROWN,
            //#endregion
            
            //#region MAGIC
            ARCANE_HAT,
            ARCANE_ROBE,
            ARCANE_LEGGINGS,

            HONEY_MASK,
            HONEY_CHESTPIECE,
            HONEY_LEGGINGS,
            HONEY_BOOTS,
            
            PIRATE_HAT,
            PIRATE_SHIRT,
            PIRATE_PANTS,
            PIRATE_BOOTS,

            ALCHEMIST_JACKET,
            ALCHEMIST_PANTS,
            ALCHEMIST_BOOTS,
            
            ELVEN_HELMET,
            ELVEN_CHESTPLATE,
            ELVEN_LEGGINGS,
            ELVEN_BOOTS,
            
            OCEANIC_MASK,
            OCEANIC_SUIT,
            OCEANIC_LEGGINGS,
            OCEANIC_SHOES,
            
            DESERT_HELMET,
            DESERT_CHESTPLATE,
            DESERT_LEGGINGS,
            DESERT_BOOTS,
            
            LUNAR_HELMET,
            LUNAR_CHESTPLATE,
            LUNAR_LEGGINGS,
            LUNAR_BOOTS,
            
            PHANTOM_MASK,
            PHANTOM_CHESTPLATE,
            PHANTOM_LEGGINGS,
            
            DIVINE_HELMET,
            DIVINE_CHESTPLATE,
            DIVINE_LEGGINGS,
            DIVINE_BOOTS,
            
            NECROTIC_MASK,
            NECROTIC_CHESTPLATE,
            NECROTIC_LEGGINGS,
            NECROTIC_BOOTS,
            
            LIVING_MASK,
            LIVING_SUIT,
            LIVING_LEGGINGS,
            LIVING_BOOTS,
            
            SHADOW_MASK,
            SHADOW_CHESTPIECE,
            SHADOW_LEGGINGS,

            ANGELIC_CHESTPLATE,
            ANGELIC_LEGGINGS,
            
            DEEP_SEA_MASK,
            DEEP_SEA_CHESTPLATE,
            DEEP_SEA_LEGGINGS,
            DEEP_SEA_BOOTS,
            
            MAGMA_CHESTPLATE,
            MAGMA_LEGGINGS,
            MAGMA_BOOTS,
            
            GUARDIAN_HELMET,
            GUARDIAN_CHESTPLATE,
            GUARDIAN_LEGGINGS,
            GUARDIAN_BOOTS
            //#endregion
        ).forEach(item -> generator.register(item, Models.GENERATED));

    }

}