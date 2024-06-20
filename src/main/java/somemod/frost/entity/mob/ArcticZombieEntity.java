package somemod.frost.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import somemod.SomeMod;
import somemod.frost.entity.EntityFreezing;
import somemod.frost.entity.attribute.FrostEntityAttributes;
import somemod.frost.entity.effect.FrostStatusEffects;
import somemod.frost.item.FrostItems;
import somemod.frost.tag.FrostBiomeTags;

/**
 * TODO: These should wear arctic armor and, importantly, drop leather.
 * TODO: Maybe make it throw snowballs, ... or icycles?
 */
public class ArcticZombieEntity extends ZombieEntity {

    public ArcticZombieEntity(EntityType<? extends ArcticZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);

        ItemStack[] armorSet = {new ItemStack(FrostItems.ARCTIC_HAT), new ItemStack(FrostItems.ARCTIC_COAT), new ItemStack(FrostItems.ARCTIC_PANTS), new ItemStack(FrostItems.ARCTIC_BOOTS)};
        for (ItemStack armor : armorSet) {
            if (world.getRandom().nextFloat() < 0.20f * difficulty.getLocalDifficulty()) {
                EquipmentSlot slot = LivingEntity.getPreferredEquipmentSlot(armor);
                 
                if (this.getEquippedStack(slot).isEmpty()) {
                    this.equipStack(slot, armor);
                    this.setEquipmentDropChance(slot, 0.10f);
                }
            }
        }

        return entityData;
    }

    public static DefaultAttributeContainer.Builder createArcticZombieAttributes() {
        return ZombieEntity.createZombieAttributes()
            .add(FrostEntityAttributes.WARMTH, 16.0f);
    }

	public static boolean canSpawn(EntityType<ArcticZombieEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        RegistryEntry<Biome> registryEntry = world.getBiome(pos);

        if (world.getChunkManager() instanceof ServerChunkManager chunkManager) {
            ChunkGenerator chunkGenerator = chunkManager.getChunkGenerator();
            NoiseConfig noiseConfig = chunkManager.getNoiseConfig();

            float localTemperature = EntityFreezing.getLocalTemperature(chunkGenerator, noiseConfig, pos);
            boolean isColdBiome = registryEntry.isIn(FrostBiomeTags.IS_COLD);

            if (HostileEntity.canSpawnInDark(type, world, spawnReason, pos, random)) {
                if (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(pos) || isColdBiome) {
                    if (localTemperature >= 0.00f) { // TODO: Look for better temperature cutoff?
                        if (!isColdBiome)
                            return false;

                        return random.nextInt(10) == 0;
                    }

                    if (localTemperature >= 0.50f) {
                        if (!isColdBiome)
                            return random.nextInt(5) == 0;

                        return random.nextInt(3) == 0;
                    }

                    if (!isColdBiome)
                        return random.nextInt(2) == 0;

                    return true;
                }
            }

            return false;
        }
        else {
            SomeMod.logError("My hypothesis was wrong :(");
            throw new RuntimeException("My hypothesis was wrong :(");
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_HUSK_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_HUSK_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_HUSK_STEP;
    }

    @Override
    public boolean tryAttack(Entity target) {
		boolean attacked = super.tryAttack(target);

        if (attacked && this.getMainHandStack().isEmpty() && target instanceof LivingEntity livingEntity) {
            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            livingEntity.addStatusEffect(new StatusEffectInstance(FrostStatusEffects.FROSTBITE, 20 * (int) f), this);
        }

		return attacked;
    }

    @Override
    protected boolean canConvertInWater() {
        return false;
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
