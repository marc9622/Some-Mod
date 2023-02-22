package somemod.magic.world_gen.structure;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryOps;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import somemod.SomeMod;

public class TowerGenerator {

    public static TowerPiece addPiece(StructureTemplateManager templateManager, BlockPos pos, List<Pair<StructurePoolElement, BlockRotation>> floorPools, Random random) {
        List<StructurePoolElement> elements = new ArrayList<>();
        List<BlockRotation> rotations = new ArrayList<>();

        floorPools.forEach(pair -> {
            elements.add(pair.getFirst());
            rotations.add(pair.getSecond());
        });

        return new TowerPiece(templateManager, elements, pos, rotations);
    }

    public static TowerPiece addPiece(StructureTemplateManager templateManager, BlockPos pos, BlockRotation rotation, List<StructurePoolElement> floorPools, Random random) {
        return new TowerPiece(templateManager, floorPools, pos, rotation);
    }

    protected static class TowerPiece extends StructurePiece {
        
        protected final StructureTemplateManager templateManager;
        protected final List<StructurePoolElement> poolElements;
        protected final BlockPos startPos;
        protected final Either<List<BlockRotation>, BlockRotation> rotations;

        public TowerPiece(StructureTemplateManager manager, List<StructurePoolElement> poolElements, BlockPos startPos, BlockRotation rotation) {
            this(manager, poolElements, startPos, Either.right(rotation));
        }

        public TowerPiece(StructureTemplateManager manager, List<StructurePoolElement> poolElements, BlockPos startPos, List<BlockRotation> rotation) {
            this(manager, poolElements, startPos, Either.left(rotation));
            if(rotation.size() != poolElements.size())
                throw new IllegalArgumentException("The number of rotations must be equal to the number of pool elements.");
        }

        public TowerPiece(StructureTemplateManager manager, List<StructurePoolElement> poolElements, BlockPos startPos, Either<List<BlockRotation>, BlockRotation> rotations) {
            super(MagicStructurePieceTypes.TOWER, 0, TowerPiece.getBoundingBox(manager, poolElements, startPos, rotations));
            this.templateManager = manager;
            this.poolElements = poolElements;
            this.startPos = startPos;
            this.rotations = rotations;
        }

        private static BlockBox getBoundingBox(StructureTemplateManager templateManager, List<StructurePoolElement> poolElements, BlockPos startPos, Either<List<BlockRotation>, BlockRotation> rotations) {
            List<BlockBox> boundingBoxes = new ArrayList<>();
            int cumulativeHeight = 0;

            for(int i = 0; i < poolElements.size(); i++) {
                BlockPos pos = startPos.up(cumulativeHeight);

                final int index = i; // for lambda
                BlockRotation rotation = rotations.map(list -> list.get(index), r -> r);

                BlockBox boundingBox = poolElements.get(i).getBoundingBox(templateManager, pos, rotation);

                int width = boundingBox.getMinX() - boundingBox.getMaxX();
                int length = boundingBox.getMinZ() - boundingBox.getMaxZ();
                cumulativeHeight += boundingBox.getMaxY() - boundingBox.getMinY() + 1;

                switch(rotation) {
                    case NONE -> {}
                    case CLOCKWISE_90        -> boundingBox = boundingBox.offset(-length, 0, 0);
                    case CLOCKWISE_180       -> boundingBox = boundingBox.offset(-width, 0, -length);
                    case COUNTERCLOCKWISE_90 -> boundingBox = boundingBox.offset(0, 0, -width);
                }

                boundingBoxes.add(boundingBox);

            }
            return BlockBox.encompass(boundingBoxes).orElse(new BlockBox(startPos));
        }

        @Override
        public BlockBox getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            
            SomeMod.LOGGER.info("Tower structure generating at " + startPos + " in chunk " + chunkPos);
            
            int cumulativeHeight = 0;

            for(int i = 0; i < poolElements.size(); i++) {
                BlockPos pos = startPos.up(cumulativeHeight);

                StructurePoolElement poolElement = poolElements.get(i);

                final int index = i; // for lambda
                BlockRotation rotation = rotations.map(list -> list.get(index), r -> r);

                BlockBox boundingBox = poolElement.getBoundingBox(this.templateManager, pos, rotation);

                int width = boundingBox.getMinX() - boundingBox.getMaxX();
                int length = boundingBox.getMinZ() - boundingBox.getMaxZ();
                cumulativeHeight += boundingBox.getMaxY() - boundingBox.getMinY() + 1;

                switch(rotation) {
                    case NONE -> {}
                    case CLOCKWISE_90        -> pos = pos.add(-length, 0, 0);
                    case CLOCKWISE_180       -> pos = pos.add(-width, 0, -length);
                    case COUNTERCLOCKWISE_90 -> pos = pos.add(0, 0, -width);
                }

                poolElement.generate(this.templateManager, world, structureAccessor, chunkGenerator, pos, pivot, rotation, chunkBox, random, false);

            }
        }

        public TowerPiece(StructureContext context, NbtCompound nbt) {
            super(MagicStructurePieceTypes.TOWER, nbt);
            
            this.templateManager = context.structureTemplateManager();

            RegistryOps<NbtElement> dynamicOps = RegistryOps.of(NbtOps.INSTANCE, context.registryManager());
            this.poolElements = Codec.list(StructurePoolElement.CODEC).parse(dynamicOps, nbt.get("pool_elements")).resultOrPartial(SomeMod.LOGGER::error).orElseThrow(() -> new IllegalStateException("Invalid pool elements found"));

            this.startPos = new BlockPos(nbt.getInt("PosX"), nbt.getInt("PosY"), nbt.getInt("PosZ"));
            this.rotations =
                Codec.either(
                    Codec.list(BlockRotation.CODEC),
                    BlockRotation.CODEC)
                .parse(dynamicOps, nbt.get("rotations")).resultOrPartial(SomeMod.LOGGER::error).orElseThrow(() -> new IllegalStateException("Invalid rotations found"));
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            RegistryOps<NbtElement> dynamicOps = RegistryOps.of(NbtOps.INSTANCE, context.registryManager());
            Codec.list(StructurePoolElement.CODEC).encodeStart(dynamicOps, this.poolElements).resultOrPartial(SomeMod.LOGGER::error).ifPresent(nbtElement -> nbt.put("pool_elements", (NbtElement) nbtElement));

            nbt.putInt("PosX", this.startPos.getX());
            nbt.putInt("PosY", this.startPos.getY());
            nbt.putInt("PosZ", this.startPos.getZ());
            
            Codec.either(
                Codec.list(BlockRotation.CODEC),
                BlockRotation.CODEC)
            .encodeStart(dynamicOps, this.rotations).resultOrPartial(SomeMod.LOGGER::error).ifPresent(nbtElement -> nbt.put("rotations", (NbtElement) nbtElement));
        }

    }

}
