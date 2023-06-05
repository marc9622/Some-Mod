package somemod.common.world_gen.feature;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import somemod.SomeMod;

public class SingleChestFeature extends Feature<SingleChestFeatureConfig> {
    
    public SingleChestFeature(Codec<SingleChestFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<SingleChestFeatureConfig> context) {

        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
                
        Optional<Text> optionalCustomName = context.getConfig().name();
        ChestBlock chestBlock = context.getConfig().chestBlock();
        Identifier lootTable = context.getConfig().lootTable();

        final BlockPos blockPos; setBlockPos: {
            final BlockPos[] offsets = new BlockPos[] {
                origin, //( 0,  0,  0),
                origin.add( 3,  0,  0),
                origin.add(-3,  0,  0),
                origin.add( 0,  0,  3),
                origin.add( 3,  0,  3),
                origin.add(-3,  0,  3),
                origin.add( 0,  0, -3),
                origin.add( 3,  0, -3),
                origin.add(-3,  0, -3),
            };

            // Find a non-solid block
            for(BlockPos offset : offsets) {

                // Continue if pos is solid
                if(isSolidBlock(offset, world))
                    continue;

                // Find a solid block underneath. Going down in steps of 5.
                for(int downOffset = 0; downOffset < 20; downOffset += 5) {

                    // Continue if pos is non-solid
                    if(!isSolidBlock(offset.down(downOffset + 1), world))
                        continue;

                    // Because we stepped over some blocks. Go up until a non-solid block is found.
                    for(int i = 0; i < 5; i++) {

                        // Continue if pos is solid
                        if(isSolidBlock(offset.down(downOffset - i), world))
                            continue;

                        // Break when non-solid position found.
                        blockPos = offset.down(downOffset - i);
                        break setBlockPos;
                    }
                }
            }
            SomeMod.LOGGER.info("Failed to generate chest feature at " + origin + " (no valid position found)");

            return false; // No valid position found
        }

        final BlockState blockState; {
            BlockState defaultState = chestBlock.getDefaultState();

            findDirection: {
                for (Direction direction : Direction.Type.HORIZONTAL.getShuffled(random)) {
                    if (!isSolidBlock(blockPos.offset(direction), world)) {
                        defaultState = defaultState.with(Properties.HORIZONTAL_FACING, direction);
                        break findDirection;
                    }
                }
                SomeMod.LOGGER.info("Failed to generate chest feature at " + origin + " (no valid direction found)");

                return false; // No valid direction found
            }

            if(world.isWater(blockPos))
                blockState = defaultState.with(Properties.WATERLOGGED, true);
            else
                blockState = defaultState;
        }

        world.setBlockState(blockPos, blockState, Block.NOTIFY_LISTENERS);

        LootableContainerBlockEntity.setLootTable(world, random, blockPos, lootTable);

        if (optionalCustomName.isPresent()) {
            Text customName = context.getConfig().name().get();

            if (world.getBlockEntity(blockPos) instanceof ChestBlockEntity chestBlockEntity)
                chestBlockEntity.setCustomName(customName);
            else
                SomeMod.LOGGER.warn("Block entity at " + blockPos + " is not a chest block entity");

            SomeMod.LOGGER.info("Chest feature of chest: " + chestBlock + " with name: " + customName + " generating at: " + blockPos + ".");
        }
        else {
            SomeMod.LOGGER.info("Chest feature of chest: " + chestBlock + " generating at: " + blockPos + ".");
        }

        return true;
    }

    private static boolean isSolidBlock(BlockPos pos, StructureWorldAccess world) {
        return !world.isAir(pos) && !world.containsFluid(Box.from(Vec3d.of(pos)));
    }

}
