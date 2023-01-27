package somemod.enchanting.world_gen.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class TowerStructure extends Structure {

    public static final Codec<TowerStructure> CODEC = 
        RecordCodecBuilder.<TowerStructure>mapCodec(
            (Function<RecordCodecBuilder.Instance<TowerStructure>, ? extends App<RecordCodecBuilder.Mu<TowerStructure>,TowerStructure>>) // Lol, I have no idea what this means.
            instance -> instance.group(
                TowerStructure.configCodecBuilder(instance),
                StructurePool.REGISTRY_CODEC.fieldOf("ground_floor_pool").forGetter(towerStructure -> towerStructure.groundFloorPool),
                Codec.compoundList(StructurePool.REGISTRY_CODEC, RotationStyle.CODEC).fieldOf("extra_floor_pools_and_rotation_styles").forGetter(towerStructure -> towerStructure.floorPools))
            .apply(instance, TowerStructure::new))
        .flatXmap(TowerStructure.createValidator(), TowerStructure.createValidator()).codec();

    public static enum RotationStyle {
        RANDOM("random_any"),
        RANDOM_NOT_PREVIOUS("random_not_previous"),
        COPY_PREVIOUS("copy_previous"),
        COPY_GROUND("copy_ground");
        public static final Codec<RotationStyle> CODEC = Codec.STRING.xmap(RotationStyle::fromString, RotationStyle::toString);
        private final String name;
        private RotationStyle(String name) {
            this.name = name;
        }
        private static RotationStyle fromString(String name) {
            name = name.toLowerCase();
            for(RotationStyle rotationStyle : RotationStyle.values()) {
                if(rotationStyle.name.equals(name))
                    return rotationStyle;
            }
            return null;
        }
        public String toString() {
            return name;
        }
    };
    
    private final RegistryEntry<StructurePool> groundFloorPool;
    private final List<Pair<RegistryEntry<StructurePool>, RotationStyle>> floorPools;

    private static Function<TowerStructure, DataResult<TowerStructure>> createValidator() {
        return towerStructure -> {
            // for(RegistryEntry<StructurePool> floorPoolEntry : towerStructure.floorPools) {
            //     StructurePool floorPool = floorPoolEntry.value();
            // 
            //     if(floorPool.getElementCount() <= 0)
            //         return DataResult.error("Tower floor pool " + floorPoolEntry.toString() + " has no templates");
            // }
            return DataResult.success(towerStructure);
        };
    }

    protected TowerStructure(Config config, RegistryEntry<StructurePool> groundFloorPool, List<Pair<RegistryEntry<StructurePool>, RotationStyle>> floorPools) {
        super(config);
        this.groundFloorPool = groundFloorPool;
        this.floorPools = floorPools;
    }

    @Override
    protected Optional<StructurePosition> getStructurePosition(Structure.Context context) {
        
        Random random = context.random();
        
        List<Pair<StructurePoolElement, BlockRotation>> poolElements = new ArrayList<>(floorPools.size() + 1);
        poolElements.add(new Pair<>(groundFloorPool.value().getRandomElement(random), BlockRotation.random(random)));

        for(int i = 1; i < floorPools.size() + 1; i++) {
            Pair<RegistryEntry<StructurePool>, RotationStyle> pair = floorPools.get(i - 1);
            StructurePool floorPool = pair.getFirst().value();
            RotationStyle rotationStyle = pair.getSecond();

            StructurePoolElement floorPoolElement = floorPool.getRandomElement(random);
            BlockRotation rotation = switch(rotationStyle) {
                case RANDOM -> BlockRotation.random(random);
                case RANDOM_NOT_PREVIOUS -> {
                    BlockRotation previousRotation = poolElements.get(i - 1).getSecond();
                    List<BlockRotation> list = Arrays.stream(BlockRotation.values()).filter(r -> r != previousRotation).collect(Collectors.toList());
                    yield list.get(random.nextInt(list.size()));
                }
                case COPY_PREVIOUS -> poolElements.get(i - 1).getSecond();
                case COPY_GROUND -> poolElements.get(0).getSecond();
            };

            poolElements.add(Pair.of(floorPoolElement, rotation));
        }

        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getStartX();
        int z = chunkPos.getStartZ();
        BlockPos blockPos = new BlockPos(x, Structure.getMinCornerHeight(context, x, z, 8, 8) + 1, z);

        Consumer<StructurePiecesCollector> pieceCollectorConsumer =
            pieceCollector -> pieceCollector.addPiece(TowerGenerator.addPiece(context.structureTemplateManager(), blockPos, poolElements, random));

        return Optional.of(new Structure.StructurePosition(blockPos, pieceCollectorConsumer));
    }

    @Override
    public StructureStart createStructureStart(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, BiomeSource biomeSource, NoiseConfig noiseConfig, StructureTemplateManager structureTemplateManager, long seed, ChunkPos chunkPos, int references, HeightLimitView world, Predicate<RegistryEntry<Biome>> validBiomes) {
        @SuppressWarnings("unused")
        StructurePiecesCollector structurePiecesCollector;
        StructureStart structureStart;

        Context context = new Context(dynamicRegistryManager, chunkGenerator, biomeSource, noiseConfig, structureTemplateManager, seed, chunkPos, world, validBiomes);
        Optional<StructurePosition> optional = this.getValidStructurePosition(context);
        if (optional.isPresent() && (structureStart = new StructureStart(this, chunkPos, references, (structurePiecesCollector = optional.get().generate()).toList())).hasChildren()) {
            return structureStart;
        }
        return StructureStart.DEFAULT;
    }

    @Override
    public StructureType<?> getType() {
        return EnchantingStructureTypes.TOWER;
    }
    
}
