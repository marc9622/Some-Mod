package somemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

import somemod.SomeMod;
import somemod.crystal.block.CrystalBlocks;
import static somemod.crystal.item.CrystalItems.*;
import somemod.frost.block.FrostBlocks;
import static somemod.frost.item.FrostItems.*;
import somemod.magic.block.MagicBlocks;
import static somemod.magic.item.MagicItems.*;

import java.util.Optional;
import java.util.stream.Stream;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        
        //#region CRYSTAL
        generator.registerSimpleCubeAll(CrystalBlocks.CRYSTAL_GLASS);
        generator.registerSimpleCubeAll(CrystalBlocks.CRYSTAL_BLOCK);

        generator.registerSimpleCubeAll(CrystalBlocks.CITRINE_ORE);
        generator.registerSimpleCubeAll(CrystalBlocks.RUBY_ORE);
        generator.registerSimpleCubeAll(CrystalBlocks.SAPPHIRE_ORE);
        //#endregion

        //#region MAGIC
        generator.registerSingleton(
            MagicBlocks.ENCHANTED_BOOKSHELF,
            TextureMap.sideEnd(TextureMap.getId(MagicBlocks.ENCHANTED_BOOKSHELF), TextureMap.getId(Blocks.GOLD_BLOCK)),
            Models.CUBE_COLUMN);

        generator.registerSingleton(
            MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF,
            TextureMap.sideEnd(TextureMap.getId(MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF), TextureMap.getId(Blocks.OBSIDIAN)),
            Models.CUBE_COLUMN);

        generator.registerBuiltin(SomeMod.id("block/forgotten_chest"), Blocks.OAK_PLANKS).includeWithoutItem(MagicBlocks.FORGOTTEN_CHEST);
        //#endregion

        //#region FROST
        generator.registerBuiltin(SomeMod.id("block/spruce_chest"), Blocks.SPRUCE_PLANKS).includeWithoutItem(FrostBlocks.SPRUCE_CHEST);
        generator.registerBuiltin(SomeMod.id("block/ice_chest"), Blocks.ICE).includeWithoutItem(FrostBlocks.ICE_CHEST);
        //#endregion

    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        Stream.of(
            // Crystal
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

            // Magic
            ELVEN_STEEL,

            // Frost
            FROSTBITE_ARROW
        ).forEach(item -> generator.register(item, Models.GENERATED));

        Stream.of(
            // Crystal
            CRYSTAL_HELMET,
            CRYSTAL_CHESTPLATE,
            CRYSTAL_LEGGINGS,
            CRYSTAL_BOOTS,

            DRAGON_SCALE_HELMET,
            DRAGON_SCALE_CHESTPLATE,
            DRAGON_SCALE_LEGGINGS,
            DRAGON_SCALE_BOOTS,

            // Magic
            ARCANE_HAT,
            ARCANE_ROBE,
            ARCANE_PANTS,

            HONEY_MASK,
            HONEY_CHESTPIECE,
            HONEY_LEGPIECE,
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
            SHADOW_LEGPIECE,

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
            GUARDIAN_BOOTS,

            // Frost
            ARCTIC_HAT,
            ARCTIC_COAT,
            ARCTIC_PANTS,
            ARCTIC_BOOTS,

            GLACIER_HELMET,
            GLACIER_CHESTPLATE,
            GLACIER_LEGGINGS,
            GLACIER_BOOTS,

            BLIZZARD_BOOTS,

            FROSTBITE_CHESTPLATE,
            FROSTBITE_LEGGINGS,

            ICE_QUEEN_CROWN
        ).forEach(armorItem -> generator.registerArmor(armorItem));

        // I believe the JSONs for the chest items in the real game are hand-written for some reason.
        // But to reuse the model transformations from the chest item, we can just use 'item/chest' as the parent.
        Stream.of(
            FORGOTTEN_CHEST,

            SPRUCE_CHEST,
            ICE_CHEST
        ).forEach(chestItem -> generator.register(chestItem, CHEST_MODEL));

        Stream.of(
            GHOST_SPAWN_EGG,

            ARCTIC_ZOMBIE_SPAWN_EGG
        ).forEach(spawnEggItem -> generator.register(spawnEggItem, SPAWN_EGG_MODEL));
    }
    
    private static final Model CHEST_MODEL = new Model(Optional.of(new Identifier("minecraft", "item/chest")), Optional.empty());
    private static final Model SPAWN_EGG_MODEL = new Model(Optional.of(new Identifier("minecraft", "item/template_spawn_egg")), Optional.empty());

}
