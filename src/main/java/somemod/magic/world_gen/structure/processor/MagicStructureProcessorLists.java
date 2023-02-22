package somemod.magic.world_gen.structure.processor;

import java.util.function.Function;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import static net.minecraft.block.Blocks.*;
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
import static net.minecraft.world.biome.BiomeKeys.*;
import somemod.SomeMod;

public class MagicStructureProcessorLists {

    public static final RegistryKey<StructureProcessorList> ENCHANTING_TOWER = SomeMod.keyOf(RegistryKeys.PROCESSOR_LIST, "enchanting_tower");

    public static void bootstrap(Registerable<StructureProcessorList> registerable) {

        Function<RegistryKey<Biome>, RegistryEntry<Biome>> lookup = key -> registerable.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(key);

        SomeMod.registerStructureProcessorList(registerable, ENCHANTING_TOWER,
            // First round of rules: (Keeps some block; marks others for removal)
            //   Light Gray Concrete  -> Gray Concrete  % 0.50
            //   Orange Concrete      -> Brown Concrete % 0.50
            //   Light Blue Concrete  -> Blue Concrete  % 0.50
            new RuleStructureProcessor(ImmutableList.of(
                createRule(LIGHT_GRAY_CONCRETE,  GRAY_CONCRETE, 0.5f),
                createRule(ORANGE_CONCRETE,      BROWN_CONCRETE, 0.5f),
                createRule(LIGHT_BLUE_CONCRETE,  BLUE_CONCRETE, 0.5f)
            )),
            // Second round of rules: (Removes marked blocks)
            //   Light Gray Concrete -> Air
            //   Orange Concrete     -> Air
            //   Light Blue Concrete -> Air
            //   Lime Concrete       -> Air % 0.50
            new RuleStructureProcessor(ImmutableList.of(
                createRule(LIGHT_GRAY_CONCRETE,  AIR),
                createRule(ORANGE_CONCRETE,      AIR),
                createRule(LIGHT_BLUE_CONCRETE,  AIR),
                createRule(LIME_CONCRETE,        AIR, 0.5f)
            )),
            // Third round of rules: (Replaces with actual block types)
            //   Gray Concrete  -> Cobblestone
            //   Brown Concrete -> Planks
            //   Blue Concrete  -> Smooth Stone
            //   Lime Concrete  -> Leaves
            new RuleStructureProcessor(ImmutableList.of(
                createRule(GRAY_CONCRETE,    COBBLESTONE),
                createRule(BROWN_CONCRETE,   OAK_PLANKS),
                createRule(BLUE_CONCRETE,    SMOOTH_STONE),
                createRule(LIME_CONCRETE,    OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true))
            )),
            // Fourth round of rules: (Replaces wood with biome-appropriate wood)
            //   Dark Forst         -> Dark Oak
            //   Flower Forest      -> Oak
            //   Old Growth Birch Forest    -> Birch
            //   Old Growth Pine Taiga      -> Spruce
            //   Old Growth Spruce Taiga    -> Spruce
            //   Frozen Peaks       -> Spruce
            //   Jagged Peaks       -> Spruce
            //   Grove              -> Spruce
            //   Ice Spikes         -> Spruce
            // 
            //   Birch Forest       -> Birch 
            //   Forest             -> Oak
            //   Snowy Taiga        -> Spruce
            //   Taiga              -> Spruce
            //   Windswept Forest   -> Oak
            //   Meadow             -> Oak
            //   Stony Peaks        -> Spruce
            //   Sparse Jungle      -> Jungle
            //   Windswept Savanna  -> Acacia
            new BiomeRuleStructureProcessor(new StructureProcessorBiomeRule.Builder()
                .add(lookup.apply(DARK_FOREST),   createRule(OAK_PLANKS, DARK_OAK_PLANKS))
                .add(lookup.apply(FLOWER_FOREST), createRule(OAK_PLANKS, OAK_PLANKS))
                .add(lookup.apply(OLD_GROWTH_BIRCH_FOREST),createRule(OAK_PLANKS, BIRCH_PLANKS))
                .add(lookup.apply(OLD_GROWTH_PINE_TAIGA),  createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(OLD_GROWTH_SPRUCE_TAIGA),createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(FROZEN_PEAKS),  createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(JAGGED_PEAKS),  createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(GROVE),         createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(ICE_SPIKES),    createRule(OAK_PLANKS, SPRUCE_PLANKS))

                .add(lookup.apply(BIRCH_FOREST),  createRule(OAK_PLANKS, BIRCH_PLANKS))
                .add(lookup.apply(FOREST),        createRule(OAK_PLANKS, OAK_PLANKS))
                .add(lookup.apply(SNOWY_TAIGA),   createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(TAIGA),         createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(WINDSWEPT_FOREST),createRule(OAK_PLANKS, OAK_PLANKS))
                .add(lookup.apply(MEADOW),        createRule(OAK_PLANKS, OAK_PLANKS))
                .add(lookup.apply(STONY_PEAKS),   createRule(OAK_PLANKS, SPRUCE_PLANKS))
                .add(lookup.apply(SPARSE_JUNGLE), createRule(OAK_PLANKS, JUNGLE_PLANKS))
                .add(lookup.apply(WINDSWEPT_SAVANNA),createRule(OAK_PLANKS, ACACIA_PLANKS))
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
                createRule(COBBLESTONE,  STONE_BRICKS, 0.3f),
                createRule(COBBLESTONE,  ANDESITE, 0.1f),
                createRule(COBBLESTONE,  STONE, 0.1f),
                createRule(COBBLESTONE,  SMOOTH_STONE, 0.1f),

                createRule(OAK_PLANKS,   STRIPPED_OAK_WOOD, 0.2f),

                createRule(SMOOTH_STONE, POLISHED_ANDESITE, 0.2f),
                createRule(SMOOTH_STONE, ANDESITE, 0.15f),
                createRule(SMOOTH_STONE, STONE, 0.1f),
                createRule(SMOOTH_STONE, POLISHED_DIORITE, 0.1f)
            )),
            // Sixth round of rules: (Replaces some blocks with mossy variants)
            //   Cobblestone    -> Mossy Cobblestone    % 0.50
            //   Stone Bricks   -> Mossy Stone Bricks   % 0.50
            //   Stone Bricks   -> Cracked Stone Bricks % 0.50
            new RuleStructureProcessor(ImmutableList.of(
                createRule(COBBLESTONE,          MOSSY_COBBLESTONE, 0.5f),
                createRule(STONE_BRICKS,         MOSSY_STONE_BRICKS, 0.5f),
                createRule(STONE_BRICKS,         CRACKED_STONE_BRICKS, 0.5f)
            )),
            // Seventh round of rules: (Replaces some planks with stripped wood)
            //   Oak Planks         -> Stripped Oak Wood    % 0.20
            //   Birch Planks       -> Stripped Birch Wood  % 0.20
            //   Dark Oak Planks    -> Stripped Dark Oak    % 0.20
            //   Spruce Planks      -> Stripped Spruce Wood % 0.20
            new RuleStructureProcessor(ImmutableList.of(
                createRule(OAK_PLANKS,   STRIPPED_OAK_WOOD, 0.2f),
                createRule(BIRCH_PLANKS, STRIPPED_BIRCH_WOOD, 0.2f),
                createRule(DARK_OAK_PLANKS, STRIPPED_DARK_OAK_WOOD, 0.2f),
                createRule(SPRUCE_PLANKS, STRIPPED_SPRUCE_WOOD, 0.2f)
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
