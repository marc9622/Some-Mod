package somemod.crystal.world_gen.feature;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import somemod.crystal.block.CrystalBlocks;

public class EndCrystalGlassPillar extends Feature<EndCrystalGlassPillarConfig> {

    public EndCrystalGlassPillar(Codec<EndCrystalGlassPillarConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<EndCrystalGlassPillarConfig> context) {
        
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin(); // The origin is the position of the block that triggered the feature
        Random random = context.getRandom();

        int minHeight = context.getConfig().minHeight();
        int maxHeight = context.getConfig().maxHeight();

        // Get the position of the highest block in the world at the origin
        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, origin);
      
        // If the block below the top position is not end stone, return false
        if (Blocks.END_STONE.getDefaultState() != world.getBlockState(topPos.down()))
            return false;

        // Build the center pillar
        int heightCenter = random.nextInt(maxHeight - minHeight + 1) + minHeight; // minHeight to maxHeight

        for (int y = 0; y <= heightCenter; y++)
            setCrystalGlassBlock(world, topPos.up(y));

        // Build the adjacent pillars
        Direction offset = Direction.NORTH; // Start with the north pillar

        for (int i = 0; i < 4; i++) {
            int heightSide = random.nextInt(heightCenter + 1); // 0 to heightCenter (inclusive)

            // If the block below the top position is end stone, build the pillar
            if (Blocks.END_STONE.getDefaultState() == world.getBlockState(topPos.down().offset(offset)))
                for (int y = 0; y <= heightSide; y++)
                    world.setBlockState(topPos.up(y).offset(offset), CrystalBlocks.CRYSTAL_GLASS.getDefaultState(), 3);

            offset = offset.rotateYClockwise();
        }

        return true;
    }

    private void setCrystalGlassBlock(StructureWorldAccess world, BlockPos pos) {
        world.setBlockState(pos, CrystalBlocks.CRYSTAL_GLASS.getDefaultState(), 3);
    }

}
