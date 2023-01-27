package somemod.enchanting.world_gen.structure.processor;

import java.util.function.Function;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import somemod.SomeMod;

public class EnchantingStructureProcessorLists {

    public static final RegistryKey<StructureProcessorList> ENCHANTING_TOWER = SomeMod.keyOf(RegistryKeys.PROCESSOR_LIST, "enchanting_tower");

    public static void bootstrap(Registerable<StructureProcessorList> registerable) {

        Function<RegistryKey<Biome>, RegistryEntry<Biome>> lookup = key -> registerable.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(key);

        SomeMod.registerStructureProcessorList(registerable, ENCHANTING_TOWER,
            // First round of rules: (Keeps some block; marks others for removal)
            //   Light Gray Concrete  -> Gray Concrete  % 0.50
            //   Orange Concrete      -> Brown Concrete % 0.50
            //   Light Blue Concrete  -> Blue Concrete  % 0.50
            new RuleStructureProcessor(ImmutableList.of(
                createRule(Blocks.LIGHT_GRAY_CONCRETE,  Blocks.GRAY_CONCRETE, 0.5f),
                createRule(Blocks.ORANGE_CONCRETE,      Blocks.BROWN_CONCRETE, 0.5f),
                createRule(Blocks.LIGHT_BLUE_CONCRETE,  Blocks.BLUE_CONCRETE, 0.5f)
            )),
            // Second round of rules: (Removes marked blocks)
            //   Light Gray Concrete -> Air
            //   Orange Concrete     -> Air
            //   Light Blue Concrete -> Air
            //   Lime Concrete       -> Air % 0.50
            new RuleStructureProcessor(ImmutableList.of(
                createRule(Blocks.LIGHT_GRAY_CONCRETE,  Blocks.AIR),
                createRule(Blocks.ORANGE_CONCRETE,      Blocks.AIR),
                createRule(Blocks.LIGHT_BLUE_CONCRETE,  Blocks.AIR),
                createRule(Blocks.LIME_CONCRETE,        Blocks.AIR, 0.5f)
            )),
            // Third round of rules: (Replaces with actual block types)
            //   Gray Concrete  -> Cobblestone
            //   Brown Concrete -> Planks
            //   Blue Concrete  -> Smooth Stone
            //   Lime Concrete  -> Leaves
            new RuleStructureProcessor(ImmutableList.of(
                createRule(Blocks.GRAY_CONCRETE,    Blocks.COBBLESTONE),
                createRule(Blocks.BROWN_CONCRETE,   Blocks.OAK_PLANKS),
                createRule(Blocks.BLUE_CONCRETE,    Blocks.SMOOTH_STONE),
                createRule(Blocks.LIME_CONCRETE,    Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true))
            )),
            // Fourth round of rules: (Replaces wood with biome-appropriate wood)
            //   #IS_FOREST:
            //   Forest             -> Oak
            //   Flower Forest      -> Oak
            //   Birch Forest       -> Birch
            //   Old Birch Forest   -> Birch
            //   Dark Forst         -> Dark Oak
            //   Grove              -> Spruce
            // 
            //   #IS_MOUNTAIN:
            //   Meadow             -> Oak
            //   Frozen Peaks       -> Spruce
            //   Jagged Peaks       -> Spruce
            //   Stony Peaks        -> Spruce
            //   Snowy Slopes       -> Spruce
            new BiomeRuleStructureProcessor(new StructureProcessorBiomeRule.Builder()
                .add(lookup.apply(BiomeKeys.FOREST),        createRule(Blocks.OAK_PLANKS, Blocks.OAK_PLANKS))
                .add(lookup.apply(BiomeKeys.FLOWER_FOREST), createRule(Blocks.OAK_PLANKS, Blocks.OAK_PLANKS))
                .add(lookup.apply(BiomeKeys.BIRCH_FOREST),  createRule(Blocks.OAK_PLANKS, Blocks.BIRCH_PLANKS))
                .add(lookup.apply(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),createRule(Blocks.OAK_PLANKS, Blocks.BIRCH_PLANKS))
                .add(lookup.apply(BiomeKeys.DARK_FOREST),   createRule(Blocks.OAK_PLANKS, Blocks.DARK_OAK_PLANKS))
                .add(lookup.apply(BiomeKeys.GROVE),         createRule(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS))
                .add(lookup.apply(BiomeKeys.MEADOW),        createRule(Blocks.OAK_PLANKS, Blocks.OAK_PLANKS))
                .add(lookup.apply(BiomeKeys.FROZEN_PEAKS),  createRule(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS))
                .add(lookup.apply(BiomeKeys.JAGGED_PEAKS),  createRule(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS))
                .add(lookup.apply(BiomeKeys.STONY_PEAKS),   createRule(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS))
                .add(lookup.apply(BiomeKeys.SNOWY_SLOPES),  createRule(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS))
                .build()
            ),
            // Fifth round of rules: (Replaces some blocks with variants)
            //   Cobblestone    -> Stone Bricks    % 0.30 = 0.7    of total cobblestone
            //   Cobblestone    -> Andesite        % 0.10 = 0.07   of total cobblestone
            //   Cobblestone    -> Stone           % 0.10 = 0.063  of total cobblestone
            //   Cobblestone    -> Smooth Stone    % 0.10 = 0.0567 of total cobblestone
            //                                              0.5103 will remain cobblestone
            // 
            //   Smooth Stone   -> Polished Andesite % 0.20 = 0.2    of total smooth stone
            //   Smooth Stone   -> Andesite          % 0.15 = 0.12   of total smooth stone
            //   Smooth Stone   -> Stone             % 0.10 = 0.068  of total smooth stone
            //   Smooth Stone   -> Polished Diorite  % 0.10 = 0.0612 of total smooth stone
            new RuleStructureProcessor(ImmutableList.of(
                createRule(Blocks.COBBLESTONE,  Blocks.STONE_BRICKS, 0.3f),
                createRule(Blocks.COBBLESTONE,  Blocks.ANDESITE, 0.1f),
                createRule(Blocks.COBBLESTONE,  Blocks.STONE, 0.1f),
                createRule(Blocks.COBBLESTONE,  Blocks.SMOOTH_STONE, 0.1f),

                createRule(Blocks.OAK_PLANKS,   Blocks.STRIPPED_OAK_WOOD, 0.2f),

                createRule(Blocks.SMOOTH_STONE, Blocks.POLISHED_ANDESITE, 0.2f),
                createRule(Blocks.SMOOTH_STONE, Blocks.ANDESITE, 0.15f),
                createRule(Blocks.SMOOTH_STONE, Blocks.STONE, 0.1f),
                createRule(Blocks.SMOOTH_STONE, Blocks.POLISHED_DIORITE, 0.1f)
            )),
            // Sixth round of rules: (Replaces some blocks with mossy variants)
            //   Cobblestone    -> Mossy Cobblestone    % 0.50
            //   Stone Bricks   -> Mossy Stone Bricks   % 0.50
            //   Stone Bricks   -> Cracked Stone Bricks % 0.50
            new RuleStructureProcessor(ImmutableList.of(
                createRule(Blocks.COBBLESTONE,          Blocks.MOSSY_COBBLESTONE, 0.5f),
                createRule(Blocks.STONE_BRICKS,         Blocks.MOSSY_STONE_BRICKS, 0.5f),
                createRule(Blocks.STONE_BRICKS,         Blocks.CRACKED_STONE_BRICKS, 0.5f)
            )),
            // Seventh round of rules: (Replaces some planks with stripped wood)
            //   Oak Planks         -> Stripped Oak Wood    % 0.20
            //   Birch Planks       -> Stripped Birch Wood  % 0.20
            //   Dark Oak Planks    -> Stripped Dark Oak    % 0.20
            //   Spruce Planks      -> Stripped Spruce Wood % 0.20
            new RuleStructureProcessor(ImmutableList.of(
                createRule(Blocks.OAK_PLANKS,   Blocks.STRIPPED_OAK_WOOD, 0.2f),
                createRule(Blocks.BIRCH_PLANKS, Blocks.STRIPPED_BIRCH_WOOD, 0.2f),
                createRule(Blocks.DARK_OAK_PLANKS, Blocks.STRIPPED_DARK_OAK_WOOD, 0.2f),
                createRule(Blocks.SPRUCE_PLANKS, Blocks.STRIPPED_SPRUCE_WOOD, 0.2f)
            ))
        );

    }

    private static StructureProcessorRule createRule(Block from, Block to) {
        return createRule(from, to.getDefaultState());
    }

    private static StructureProcessorRule createRule(Block from, Block to, float chance) {
        return createRule(from, to.getDefaultState(), chance);
    }

    private static StructureProcessorRule createRule(Block from, BlockState to) {
        return new StructureProcessorRule(
            new BlockMatchRuleTest(from),
            AlwaysTrueRuleTest.INSTANCE,
            to
        );
    }

    private static StructureProcessorRule createRule(Block from, BlockState to, float chance) {
        return new StructureProcessorRule(
            new RandomBlockMatchRuleTest(from, chance),
            AlwaysTrueRuleTest.INSTANCE,
            to
        );
    }
    
}
