package somemod.common.world_gen.feature;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record SingleChestFeatureConfig(Optional<Text> name, ChestBlock chestBlock, Identifier lootTable) implements FeatureConfig {
    
    private static final Codec<ChestBlock> blockCodec = Codecs.withLifecycle(
        Codecs.orCompressed(
            Identifier.CODEC.flatXmap(
                identifier -> {
                    Block block = Registries.BLOCK.get(identifier);

                    if (block == null)
                        return DataResult.error(() -> "Unknown registry key in " + Registries.BLOCK.getKey() + ": " + identifier);
                    
                    if (block instanceof ChestBlock chestBlock)
                        return DataResult.success(chestBlock);
                    
                    return DataResult.error(() -> "Registry key in " + Registries.BLOCK.getKey() + " is not a chest block: " + identifier);
                },
                chestBlock -> {
                    Optional<RegistryKey<Block>> optional = Registries.BLOCK.getKey(chestBlock);

                    if (optional.isEmpty())
                        return DataResult.error(() -> "Unknown registry element in " + Registries.BLOCK.getKey() + ": " + chestBlock);

                    return DataResult.success(optional.get().getValue());
                }
            ),
            Codecs.rawIdChecked(
                chestBlock -> {
                    if (Registries.BLOCK.getKey(chestBlock).isPresent())
                        return Registries.BLOCK.getRawId(chestBlock);
                    return -1;
                },
                rawId -> {
                    Block block = Registries.BLOCK.get(rawId);
                    if (block instanceof ChestBlock chestBlock)
                        return chestBlock;
                    return null;
                },
                -1
            )
        ),
        Registries.BLOCK::getEntryLifecycle,
        Registries.BLOCK::getEntryLifecycle
    );

    public static Codec<SingleChestFeatureConfig> CODEC = RecordCodecBuilder.create(
        instance ->
            instance.group(
                Codecs.TEXT.optionalFieldOf("custom_name").forGetter(SingleChestFeatureConfig::name),
                blockCodec.fieldOf("chest_block").orElse((ChestBlock) Blocks.CHEST).forGetter(SingleChestFeatureConfig::chestBlock),
                Identifier.CODEC.fieldOf("loot_table").orElse(LootTables.EMPTY).forGetter(SingleChestFeatureConfig::lootTable))
            .apply(instance, SingleChestFeatureConfig::new));
}
