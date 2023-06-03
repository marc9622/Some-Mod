package somemod.magic.world_gen.feature;

import java.util.function.Predicate;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import somemod.SomeMod;
import somemod.magic.block.MagicBlocks;
import somemod.magic.block.entity.MagicBlockEntityTypes;

public class ForgottenChestFeature extends Feature<ForgottenChestFeatureConfig> {
    
    public ForgottenChestFeature(Codec<ForgottenChestFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<ForgottenChestFeatureConfig> context) {

        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        
        Predicate<BlockPos> isSolidBlock = pos -> !world.isAir(pos) && !world.containsFluid(Box.from(Vec3d.of(pos)));
                
        // Find a position to place the block in
        final BlockPos blockPos; blockPos: {
            BlockPos[] offsets = new BlockPos[]{
                origin,//.add(0,  0,  0),
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
                if(isSolidBlock.test(offset))
                    continue;

                // Find a solid block underneath. Going down in steps of 5.
                for(int downOffset = 0; downOffset < 20; downOffset += 5) {

                    // Continue if pos is non-solid
                    if(!isSolidBlock.test(offset.down(downOffset + 1)))
                        continue;

                    // Because we stepped over some blocks. Go up until a non-solid block is found.
                    for(int i = 0; i < 5; i++) {

                        // Continue if pos is solid
                        if(isSolidBlock.test(offset.down(downOffset - i)))
                            continue;

                        // Break if when non-solid position found.
                        blockPos = offset.down(downOffset - i);
                        break blockPos;
                    }
                }
            }
            SomeMod.LOGGER.info("Failed to generate Forgotten Chest at " + origin + " (no valid position found)");

            return false;
        }
        
        // Sets rotation and waterlogged
        BlockState blockState; {
            final BlockState blockStateDefault = MagicBlocks.FORGOTTEN_CHEST.getDefaultState(); // Must be final for lambda

            blockState = Direction.Type.HORIZONTAL.getShuffled(random).stream()
                    .filter(direction -> {
                        BlockPos offset = blockPos.offset(direction);
                        return !isSolidBlock.test(offset);
                    })
                    .findFirst()
                    .map(direction -> blockStateDefault.with(Properties.HORIZONTAL_FACING, direction))
                    .orElse(blockStateDefault);
                    
            if(world.isWater(blockPos))
                blockState = blockState.with(Properties.WATERLOGGED, true);
        }
        
        // Set block state in world
        world.setBlockState(blockPos, blockState, Block.NOTIFY_LISTENERS);
        // Set loot table
        LootableContainerBlockEntity.setLootTable(world, random, blockPos, context.getConfig().lootTable());
        // Set custom name for block entity
        context.getConfig().name().ifPresent(customName ->
            world.getBlockEntity(blockPos, MagicBlockEntityTypes.FORGOTTEN_CHEST_ENTITY).ifPresent(blockEntity ->
                blockEntity.setCustomName(customName)));
        
        SomeMod.LOGGER.info("Forgotten chest generating at " + blockPos + " with name: " + context.getConfig().name().orElse(null));

        return true;
    }

}
