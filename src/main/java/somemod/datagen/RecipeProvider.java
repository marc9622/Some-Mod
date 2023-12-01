package somemod.datagen;

import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;

import static net.minecraft.recipe.book.RecipeCategory.*;
import static net.minecraft.item.Items.*;
import static somemod.crystal.item.CrystalItems.*;
import static somemod.magic.item.MagicItems.*;
import static somemod.frost.item.FrostItems.*;

public final class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        // Crystal
        FabricRecipeProvider.offerSmelting(exporter, List.of(END_STONE), MISC, CRYSTAL_DUST, 0.35f, 200, "crystal_dust");

        shaped(MISC, CRYSTAL)
            .group("crystal")
            .pattern(" c ")
            .pattern("cxc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('x', DIAMOND)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(DIAMOND), FabricRecipeProvider.conditionsFromItem(DIAMOND))
            .offerTo(exporter, "crystal_from_diamond");

        shaped(MISC, CRYSTAL)
            .group("crystal")
            .pattern(" c ")
            .pattern("cxc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('x', EMERALD)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(EMERALD), FabricRecipeProvider.conditionsFromItem(EMERALD))
            .offerTo(exporter, "crystal_from_emerald");

        shaped(MISC, CRYSTAL)
            .group("crystal")
            .pattern(" c ")
            .pattern("cxc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('x', CITRINE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(CITRINE), FabricRecipeProvider.conditionsFromItem(CITRINE))
            .offerTo(exporter, "crystal_from_citrine");

        shaped(MISC, CRYSTAL)
            .group("crystal")
            .pattern(" c ")
            .pattern("cxc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('x', RUBY)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(RUBY), FabricRecipeProvider.conditionsFromItem(RUBY))
            .offerTo(exporter, "crystal_from_ruby");

        shaped(MISC, CRYSTAL)
            .group("crystal")
            .pattern(" c ")
            .pattern("cxc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('x', SAPPHIRE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(SAPPHIRE), FabricRecipeProvider.conditionsFromItem(SAPPHIRE))
            .offerTo(exporter, "crystal_from_sapphire");

        shaped(MISC, CRYSTAL)
            .group("crystal")
            .pattern(" c ")
            .pattern("cxc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('x', AMETHYST_SHARD)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(AMETHYST_SHARD), FabricRecipeProvider.conditionsFromItem(AMETHYST_SHARD))
            .offerTo(exporter, "crystal_from_amethyst");

        shapeless(MISC, DIAMOND)
            .input(CRYSTAL)
            .input(LIGHT_BLUE_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(LIGHT_BLUE_DYE), FabricRecipeProvider.conditionsFromItem(LIGHT_BLUE_DYE))
            .offerTo(exporter, "diamond_from_crystal");

        shapeless(MISC, EMERALD)
            .group("emerald")
            .input(CRYSTAL)
            .input(GREEN_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(GREEN_DYE), FabricRecipeProvider.conditionsFromItem(GREEN_DYE))
            .offerTo(exporter, "emerald_from_crystal_green");

        shapeless(MISC, EMERALD)
            .group("emerald")
            .input(CRYSTAL)
            .input(LIME_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(LIME_DYE), FabricRecipeProvider.conditionsFromItem(LIME_DYE))
            .offerTo(exporter, "emerald_from_crystal_lime");

        shapeless(MISC, CITRINE)
            .input(CRYSTAL)
            .input(YELLOW_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(YELLOW_DYE), FabricRecipeProvider.conditionsFromItem(YELLOW_DYE))
            .offerTo(exporter, "citrine_from_crystal");

        shapeless(MISC, RUBY)
            .input(CRYSTAL)
            .input(RED_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(RED_DYE), FabricRecipeProvider.conditionsFromItem(RED_DYE))
            .offerTo(exporter, "ruby_from_crystal");

        shapeless(MISC, SAPPHIRE)
            .input(CRYSTAL)
            .input(BLUE_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(BLUE_DYE), FabricRecipeProvider.conditionsFromItem(BLUE_DYE))
            .offerTo(exporter, "sapphire_from_crystal");

        shapeless(MISC, AMETHYST_SHARD)
            .input(CRYSTAL)
            .input(PURPLE_DYE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(PURPLE_DYE), FabricRecipeProvider.conditionsFromItem(PURPLE_DYE))
            .offerTo(exporter, "amethyst_from_crystal");

        shaped(BUILDING_BLOCKS, END_STONE)
            .pattern(" c ")
            .pattern("csc")
            .pattern(" c ")
            .input('c', CRYSTAL_DUST)
            .input('s', COBBLESTONE)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .criterion(FabricRecipeProvider.hasItem(COBBLESTONE), FabricRecipeProvider.conditionsFromItem(COBBLESTONE))
            .offerTo(exporter);

        shapeless(BUILDING_BLOCKS, CRYSTAL_BLOCK)
            .input(CRYSTAL, 9)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .offerTo(exporter);

        shaped(BUILDING_BLOCKS, CRYSTAL_GLASS)
            .pattern("cc")
            .pattern("cc")
            .input('c', CRYSTAL_DUST)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL_DUST), FabricRecipeProvider.conditionsFromItem(CRYSTAL_DUST))
            .offerTo(exporter);

        shaped(COMBAT, CRYSTAL_SWORD)
            .pattern("c")
            .pattern("c")
            .pattern("s")
            .input('c', CRYSTAL)
            .input('s', STICK)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(STICK), FabricRecipeProvider.conditionsFromItem(STICK))
            .offerTo(exporter);

        shaped(TOOLS, CRYSTAL_PICKAXE)
            .pattern("ccc")
            .pattern(" s ")
            .pattern(" s ")
            .input('c', CRYSTAL)
            .input('s', STICK)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(STICK), FabricRecipeProvider.conditionsFromItem(STICK))
            .offerTo(exporter);

        shaped(TOOLS, CRYSTAL_AXE)
            .pattern("cc")
            .pattern("cs")
            .pattern(" s")
            .input('c', CRYSTAL)
            .input('s', STICK)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(STICK), FabricRecipeProvider.conditionsFromItem(STICK))
            .offerTo(exporter);

        shaped(TOOLS, CRYSTAL_SHOVEL)
            .pattern("c")
            .pattern("s")
            .pattern("s")
            .input('c', CRYSTAL)
            .input('s', STICK)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(STICK), FabricRecipeProvider.conditionsFromItem(STICK))
            .offerTo(exporter);

        shaped(TOOLS, CRYSTAL_HOE)
            .pattern("cc")
            .pattern(" s")
            .pattern(" s")
            .input('c', CRYSTAL)
            .input('s', STICK)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .criterion(FabricRecipeProvider.hasItem(STICK), FabricRecipeProvider.conditionsFromItem(STICK))
            .offerTo(exporter);

        shaped(COMBAT, CRYSTAL_HELMET)
            .pattern("ccc")
            .pattern("c c")
            .input('c', CRYSTAL)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .offerTo(exporter);

        shaped(COMBAT, CRYSTAL_CHESTPLATE)
            .pattern("c c")
            .pattern("ccc")
            .pattern("ccc")
            .input('c', CRYSTAL)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .offerTo(exporter);

        shaped(COMBAT, CRYSTAL_LEGGINGS)
            .pattern("ccc")
            .pattern("c c")
            .pattern("c c")
            .input('c', CRYSTAL)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .offerTo(exporter);

        shaped(COMBAT, CRYSTAL_BOOTS)
            .pattern("c c")
            .pattern("c c")
            .input('c', CRYSTAL)
            .criterion(FabricRecipeProvider.hasItem(CRYSTAL), FabricRecipeProvider.conditionsFromItem(CRYSTAL))
            .offerTo(exporter);

        // Magic
        shaped(BUILDING_BLOCKS, ENCHANTED_BOOKSHELF)
            .pattern("ggg")
            .pattern("bbb")
            .pattern("ggg")
            .input('g', GOLD_BLOCK)
            .input('b', BOOK)
            .criterion(FabricRecipeProvider.hasItem(GOLD_BLOCK), FabricRecipeProvider.conditionsFromItem(GOLD_BLOCK))
            .criterion(FabricRecipeProvider.hasItem(BOOK), FabricRecipeProvider.conditionsFromItem(BOOK))
            .offerTo(exporter);

        shaped(BUILDING_BLOCKS, OBSIDIAN_ENCHANTED_BOOKSHELF)
            .pattern("ooo")
            .pattern("dbd")
            .pattern("ooo")
            .input('o', OBSIDIAN)
            .input('d', DIAMOND)
            .input('b', ENCHANTED_BOOKSHELF)
            .criterion(FabricRecipeProvider.hasItem(OBSIDIAN), FabricRecipeProvider.conditionsFromItem(OBSIDIAN))
            .criterion(FabricRecipeProvider.hasItem(DIAMOND), FabricRecipeProvider.conditionsFromItem(DIAMOND))
            .criterion(FabricRecipeProvider.hasItem(ENCHANTED_BOOKSHELF), FabricRecipeProvider.conditionsFromItem(ENCHANTED_BOOKSHELF))
            .offerTo(exporter);

        shaped(COMBAT, ELVEN_HELMET)
            .pattern("eee")
            .pattern("e e")
            .input('e', ELVEN_STEEL)
            .criterion(FabricRecipeProvider.hasItem(ELVEN_STEEL), FabricRecipeProvider.conditionsFromItem(ELVEN_STEEL))
            .offerTo(exporter);

        shaped(COMBAT, ELVEN_CHESTPLATE)
            .pattern("e e")
            .pattern("eee")
            .pattern("eee")
            .input('e', ELVEN_STEEL)
            .criterion(FabricRecipeProvider.hasItem(ELVEN_STEEL), FabricRecipeProvider.conditionsFromItem(ELVEN_STEEL))
            .offerTo(exporter);
        
        shaped(COMBAT, ELVEN_LEGGINGS)
            .pattern("eee")
            .pattern("e e")
            .pattern("e e")
            .input('e', ELVEN_STEEL)
            .criterion(FabricRecipeProvider.hasItem(ELVEN_STEEL), FabricRecipeProvider.conditionsFromItem(ELVEN_STEEL))
            .offerTo(exporter);
        
        shaped(COMBAT, ELVEN_BOOTS)
            .pattern("e e")
            .pattern("e e")
            .input('e', ELVEN_STEEL)
            .criterion(FabricRecipeProvider.hasItem(ELVEN_STEEL), FabricRecipeProvider.conditionsFromItem(ELVEN_STEEL))
            .offerTo(exporter);

        // Frost
        //shaped(BUILDING_BLOCKS, SPRUCE_CHEST)
        
        shaped(COMBAT, BLIZZARD_BOOTS)
            .pattern("b b")
            .pattern("i i")
            .input('b', ICE)
            .input('i', IRON_INGOT)
            .criterion(FabricRecipeProvider.hasItem(ICE), FabricRecipeProvider.conditionsFromItem(ICE))
            .criterion(FabricRecipeProvider.hasItem(IRON_INGOT), FabricRecipeProvider.conditionsFromItem(IRON_INGOT))
            .offerTo(exporter);

        shaped(COMBAT, FROSTBITE_ARROW, 4)
            .pattern("i")
            .pattern("b")
            .pattern("f")
            .input('i', IRON_INGOT)
            .input('b', ICE)
            .input('f', FEATHER)
            .criterion(FabricRecipeProvider.hasItem(ICE), FabricRecipeProvider.conditionsFromItem(ICE))
            .criterion(FabricRecipeProvider.hasItem(IRON_INGOT), FabricRecipeProvider.conditionsFromItem(IRON_INGOT))
            .criterion(FabricRecipeProvider.hasItem(FEATHER), FabricRecipeProvider.conditionsFromItem(FEATHER))
            .offerTo(exporter);

        shaped(COMBAT, ARCTIC_HAT)
            .pattern("lwl")
            .pattern("w w")
            .input('l', LEATHER)
            .input('w', WHITE_WOOL)
            .criterion(FabricRecipeProvider.hasItem(LEATHER), FabricRecipeProvider.conditionsFromItem(LEATHER))
            .criterion(FabricRecipeProvider.hasItem(WHITE_WOOL), FabricRecipeProvider.conditionsFromItem(WHITE_WOOL))
            .offerTo(exporter);

        shaped(COMBAT, ARCTIC_COAT)
            .pattern("l l")
            .pattern("lwl")
            .pattern("lwl")
            .input('l', LEATHER)
            .input('w', WHITE_WOOL)
            .criterion(FabricRecipeProvider.hasItem(LEATHER), FabricRecipeProvider.conditionsFromItem(LEATHER))
            .criterion(FabricRecipeProvider.hasItem(WHITE_WOOL), FabricRecipeProvider.conditionsFromItem(WHITE_WOOL))
            .offerTo(exporter);

        shaped(COMBAT, ARCTIC_PANTS)
            .pattern("lwl")
            .pattern("l l")
            .pattern("l l")
            .input('l', LEATHER)
            .input('w', WHITE_WOOL)
            .criterion(FabricRecipeProvider.hasItem(LEATHER), FabricRecipeProvider.conditionsFromItem(LEATHER))
            .criterion(FabricRecipeProvider.hasItem(WHITE_WOOL), FabricRecipeProvider.conditionsFromItem(WHITE_WOOL))
            .offerTo(exporter);

        shaped(COMBAT, ARCTIC_BOOTS)
            .pattern("l l")
            .pattern("l l")
            .input('l', LEATHER)
            .criterion(FabricRecipeProvider.hasItem(LEATHER), FabricRecipeProvider.conditionsFromItem(LEATHER))
            .offerTo(exporter);

        shaped(COMBAT, GLACIER_HELMET)
            .pattern("iii")
            .pattern("i i")
            .input('i', ICE)
            .criterion(FabricRecipeProvider.hasItem(ICE), FabricRecipeProvider.conditionsFromItem(ICE))
            .offerTo(exporter);

        shaped(COMBAT, GLACIER_CHESTPLATE)
            .pattern("i i")
            .pattern("iii")
            .pattern("iii")
            .input('i', ICE)
            .criterion(FabricRecipeProvider.hasItem(ICE), FabricRecipeProvider.conditionsFromItem(ICE))
            .offerTo(exporter);

        shaped(COMBAT, GLACIER_LEGGINGS)
            .pattern("iii")
            .pattern("i i")
            .pattern("i i")
            .input('i', ICE)
            .criterion(FabricRecipeProvider.hasItem(ICE), FabricRecipeProvider.conditionsFromItem(ICE))
            .offerTo(exporter);

        shaped(COMBAT, GLACIER_BOOTS)
            .pattern("i i")
            .pattern("i i")
            .input('i', ICE)
            .criterion(FabricRecipeProvider.hasItem(ICE), FabricRecipeProvider.conditionsFromItem(ICE))
            .offerTo(exporter);

    }

    private static ShapelessRecipeJsonBuilder shapeless(RecipeCategory category, Item output) {
        return ShapelessRecipeJsonBuilder.create(category, output);
    }

    private static ShapelessRecipeJsonBuilder shapeless(RecipeCategory category, Item output, int count) {
        return ShapelessRecipeJsonBuilder.create(category, output, count);
    }

    private static ShapedRecipeJsonBuilder shaped(RecipeCategory category, Item output) {
        return ShapedRecipeJsonBuilder.create(category, output);
    }

    private static ShapedRecipeJsonBuilder shaped(RecipeCategory category, Item output, int count) {
        return ShapedRecipeJsonBuilder.create(category, output, count);
    }

}

