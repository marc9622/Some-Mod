package somemod.magic.enchantment;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class BlastStrikeEnchantment extends Enchantment {

    protected BlastStrikeEnchantment(Rarity weight, EquipmentSlot... slots) {
        super(weight, EnchantmentTarget.WEAPON, slots);
    }

    @Override
    public int getMinPower(int level) {
        return level * 10 + 10;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return 0f;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem ? true : super.isAcceptableItem(stack);
    }

    @Override
    public void onTargetDamaged(LivingEntity attacker, Entity target, int level) {
        if (target instanceof LivingEntity victim) {
            if (level > 0 && !victim.isInvulnerable()) {
                World world = victim.getWorld();
                DamageSource source = world.getDamageSources().create(DamageTypes.PLAYER_EXPLOSION, attacker);
                ExplosionBehavior behavior = new ExplosionBehavior() {
                    @Override
                    public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                        return false;
                    }
                };
                float power = (victim.isDead() ? 0.5f : 0) + level * 0.5f;
                world.createExplosion(victim, source, behavior, victim.getPos(), power, false, ExplosionSourceType.MOB);
            }
        }
    }
}

