package somemod.frost.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import somemod.frost.entity.EntityFreezing;
import somemod.frost.entity.effect.FrostStatusEffects;
import somemod.frost.tag.FrostBiomeTags;

/**
 * TODO: These should wear arctic armor and, importantly, drop leather.
 * TODO: Maybe make it throw snowballs, ... or icycles?
 */
public class ArcticZombieEntity extends ZombieEntity {

    public ArcticZombieEntity(EntityType<? extends ArcticZombieEntity> entityType, World world) {
        super(entityType, world);
    }

 //    @Override
	// public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
 //        entityData = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
 //        if (this.getEquippedStack(EquipmentSlot.OFFHAND).isEmpty() && world.getRandom().nextFloat() < 0.03f) {
 //            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
 //            this.updateDropChances(EquipmentSlot.OFFHAND);
 //        }
 //        return entityData;
 //    }

	public static boolean canSpawn(EntityType<ArcticZombieEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        RegistryEntry<Biome> registryEntry = world.getBiome(pos);

        float localTemperature = EntityFreezing.getLocalTemperature(world, pos);
        boolean isColdBiome = registryEntry.isIn(FrostBiomeTags.HAS_ICE_CHEST) || registryEntry.isIn(FrostBiomeTags.HAS_SPRUCE_CHEST_SNOWY);

        if (HostileEntity.canSpawnInDark(type, world, spawnReason, pos, random)) {
            if (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(pos) || localTemperature < 0.0f || isColdBiome) { // TODO: Look for better temperature cutoff
                float spawnChanceRecip = 10.0f;
                spawnChanceRecip += localTemperature * 10.0f;
                if (!isColdBiome) spawnChanceRecip += 10.0f;

                return random.nextInt((int) spawnChanceRecip) == 0;
            }
        }

        return false;
    }

    // @Override
    // protected boolean burnsInDaylight() {
    //     return true;
    // }

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

    public boolean tryAttack(Entity target) {
		boolean attacked = super.tryAttack(target);
		if (attacked && this.getMainHandStack().isEmpty() && target instanceof LivingEntity livingEntity) {
			float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
			livingEntity.addStatusEffect(new StatusEffectInstance(FrostStatusEffects.FROSTBITE, 20 * (int) f), this);
		}

		return attacked;
    }

  //   @Override
  //   protected boolean canConvertInWater() {
  //       return true;
  //   }
		//
  //   @Override
  //   protected void convertInWater() {
		// this.convertTo(EntityType.ZOMBIE);
		// if (!this.isSilent()) {
		// 	this.getWorld().syncWorldEvent(null, WorldEvents.HUSK_CONVERTS_TO_ZOMBIE, this.getBlockPos(), 0);
		// }
  //   }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
