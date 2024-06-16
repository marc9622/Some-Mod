package somemod.magic.entity.mob;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import somemod.SomeMod;
import somemod.magic.entity.effect.MagicStatusEffects;

/**
 * Ghosts are hostile mobs that spawn in the Overworld.
 * They are transparent and can fly through blocks.
 * They only spawn at night, and flee from the sun.
 * TODO: Maybe make them flee all forms of light?
 * TODO: Maybe make them spawn during full moon?
 * They cannot be damaged by any means, and they cannot do damage either.
 * Instead, when they get close to a player, they scare them and disappear.
 * Scaring a player currently gives them slowness, weakness, and magic fragility.
 * TODO: Make Scared it's own status effect that does something more interesting.
 */
public class GhostEntity extends HostileEntity {

    public GhostEntity(EntityType<? extends GhostEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = Monster.ZERO_XP;
        this.moveControl = new GhostMoveControl();
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height - 0.28125f;
    }

    @Override
    public void move(MovementType movementType, Vec3d movement) {
        super.move(movementType, movement);
        this.checkBlockCollision();
    }

    @Override
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);

        if (this.getY() < this.getWorld().getBottomY()) {
            this.remove(RemovalReason.DISCARDED);
            return;
        }
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.isSourceCreativePlayer())
            return false;

        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))
            return false;

        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public final boolean tryAttack(Entity target) {
        if (target instanceof LivingEntity livingTarget)
            this.scare(livingTarget);

        this.onAttacking(target);

        if (this.disappear())
            this.remove(RemovalReason.DISCARDED);

        return true;
    }

    protected void scare(LivingEntity target) {
        if (target instanceof PlayerEntity player) {
            // Local Difficulty is between 0.0 and 6.75.
            float difficulty = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();

            int duration = 20 + (int) (difficulty * 3.0f);

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, duration));
            player.addStatusEffect(new StatusEffectInstance(MagicStatusEffects.MAGIC_FRAGILITY,  duration));
        }
    }

    protected boolean disappear() {
        World world = this.getWorld();
        world.addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_GHAST_DEATH, this.getSoundCategory(), 1.0f, 1.0f);
        return true;
    }

    public static DefaultAttributeContainer.Builder createGhostAttributes() {
        return HostileEntity.createHostileAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0f)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0f)
            .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.5f);
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_GHAST_AMBIENT;
	}

    @Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_GHAST_HURT;
	}

    @Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GHAST_DEATH;
	}

    @Override
    protected float getSoundVolume() {
        return 5.0f;
    }

    @Override
    public float getBrightnessAtEyes() {
        return 1.0f;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.initEquipment(random, difficulty);
        this.updateEnchantments(random, difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public double getHeightOffset() {
        return 0.4f;
    }

    private static final TargetPredicate TARGET_PREDICATE = TargetPredicate.createNonAttackable().ignoreVisibility().setBaseMaxDistance(24.0f);

    // TODO: Maybe add a RunFromGhostGoal to Villagers?
    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new GhostFlyAwayGoal(this));
        this.goalSelector.add(1, new GhostAttackGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, MobEntity.class, 8.0f));
        this.goalSelector.add(3, new FlyRandomlyGoal(this));
        this.goalSelector.add(3, new LookAroundGoal(this));
        // TODO: Don't use ActiveTargetGoal because it does not respect the ignoreVisibility.
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, false, false, target -> TARGET_PREDICATE.test(this, target)));
    }

    private static double EPSILON = 2.500000277905201E-7;

    public final class GhostMoveControl extends MoveControl {

        public GhostMoveControl() {
            super(GhostEntity.this);
        }

        @Override
        public void moveTo(double x, double y, double z, double speed) {
            this.targetX = x;
            this.targetY = y;
            this.targetZ = z;
            this.speed = speed;
            this.state = MoveControl.State.MOVE_TO;
        }

        @Override
        public void strafeTo(float forward, float sideways) {}

        @Override
        public void tick() {
            GhostEntity ghost = GhostEntity.this;

            if (this.state == MoveControl.State.MOVE_TO) {
                Vec3d dif = new Vec3d(this.targetX - ghost.getX(), this.targetY - ghost.getY(), this.targetZ - ghost.getZ());
                double dis = dif.length();

                if (dis < EPSILON) {
                    ghost.setVelocity(new Vec3d(0, 0, 0));
                    return;
                }

                Vec3d target_vel = dif.normalize().multiply(this.speed);
                Vec3d new_vel = ghost.getVelocity().lerp(target_vel, 0.1f);
                ghost.setVelocity(new_vel);

                if (ghost.getTarget() == null) {
                    ghost.setYaw(-((float)MathHelper.atan2(new_vel.x, new_vel.z)) * 57.295776f);
                    ghost.bodyYaw = ghost.getYaw();
                }
                else {
                    double dx = ghost.getTarget().getX() - ghost.getX();
                    double dz = ghost.getTarget().getZ() - ghost.getZ();
                    ghost.setYaw(-((float)MathHelper.atan2(dx, dz)) * 57.295776f);
                    ghost.bodyYaw = ghost.getYaw();
                }

            }
        }
    }

    public static class GhostFlyAwayGoal extends Goal {

        private final GhostEntity ghost;

        public GhostFlyAwayGoal(GhostEntity ghost) {
            this.ghost = ghost;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return ghost.getWorld().isDay();
        }

        @Override
        public boolean shouldContinue() {
            return canStart();
        }

        @Override
        public void tick() {
            ghost.isInsideWall();

            BlockPos blockPos = ghost.getBlockPos();

            if (ghost.getBlockStateAtPos().isOpaqueFullCube(ghost.getWorld(), blockPos)) {
                Box ghostBox = ghost.getBoundingBox();
                Box blockBox = new Box(blockPos);

                if (ghostBox.minX + EPSILON >= blockBox.minX && ghostBox.maxX - EPSILON <= blockBox.maxX &&
                    ghostBox.minY + EPSILON >= blockBox.minY && ghostBox.maxY - EPSILON <= blockBox.maxY &&
                    ghostBox.minZ + EPSILON >= blockBox.minZ && ghostBox.maxZ - EPSILON <= blockBox.maxZ) {
                    ghost.remove(RemovalReason.DISCARDED);
                    return;
                }
            }

            Vec3d target = ghost.getBlockPos().down(10).toCenterPos();
            ghost.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 0.4f);
        }

    }

    public static class GhostAttackGoal extends Goal {

        private final GhostEntity ghost;

        public GhostAttackGoal(GhostEntity ghost) {
            this.ghost = ghost;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            LivingEntity target = ghost.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public boolean shouldContinue() {
            LivingEntity target = ghost.getTarget();

            if (target == null || !target.isAlive() || !target.canTakeDamage())
                return false;

            if (ghost.squaredDistanceTo(target) > 225.0f)
                return false;

            return ghost.getMoveControl().isMoving();
        }

        @Override
        public void tick() {
            LivingEntity target = ghost.getTarget();
            if (target == null) return;

            if (ghost.getBoundingBox().intersects(target.getBoundingBox())) {
                ghost.tryAttack(target);
                return;
            }

            Vec3d pos = target.getEyePos();
            if (ghost.squaredDistanceTo(target) < 10.0f)
                ghost.moveControl.moveTo(pos.x, pos.y, pos.z, 0.8f);
            else
                ghost.moveControl.moveTo(pos.x, pos.y, pos.z, 0.4f);

            ghost.getLookControl().lookAt(target, 30.0f, 30.0f);
        }
    }

    public static class FlyRandomlyGoal extends Goal {

        private final GhostEntity ghost;

        public FlyRandomlyGoal(GhostEntity ghost) {
            this.ghost = ghost;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            MoveControl moveControl = ghost.getMoveControl();

            if (!moveControl.isMoving()) {
                return true;
            }
            else {
                double dx = moveControl.getTargetX() - ghost.getX();
                double dy = moveControl.getTargetY() - ghost.getY();
                double dz = moveControl.getTargetZ() - ghost.getZ();
                double distance = dx * dx + dy * dy + dz * dz;
                return distance < 1.0f || distance > 3600.0f;
            }
        }

        @Override
        public boolean shouldContinue() {
            return false;
        }

        @Override
        public void start() {
            Random random = ghost.getRandom();
            double x = ghost.getX() + (random.nextFloat() * 2.0f - 1.0f) * 6.0f;
            double y = ghost.getY() + (random.nextFloat() * 2.0f - 1.0f) * 3.0f;
            double z = ghost.getZ() + (random.nextFloat() * 2.0f - 1.0f) * 6.0f;
            if (ghost.getWorld().getBlockState(BlockPos.ofFloored(x, y, z)).isAir()) {
                ghost.getMoveControl().moveTo(x, y, z, 0.1f);
            }
        }
    }

}
