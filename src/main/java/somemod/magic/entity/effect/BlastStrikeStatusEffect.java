package somemod.magic.entity.effect;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World.ExplosionSourceType;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import somemod.common.entity.effect.DamageDealtStatusEffect;

public class BlastStrikeStatusEffect extends DamageDealtStatusEffect {

    protected BlastStrikeStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onDamageApplied(DamageSource source, float amount, LivingEntity victim, LivingEntity attacker, StatusEffectInstance effectInstance) {
        if(amount > 0f && !victim.isInvulnerable() && victim.isDead()) {
            ExplosionBehavior behavior = new ExplosionBehavior() {
                @Override
                public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                    return false;
                }
            };
            float power = 1.5f + effectInstance.getAmplifier() * 0.5f;
            
            victim.world.createExplosion(victim, source, behavior, victim.getPos(), power, false, ExplosionSourceType.MOB);
        }
    }
    
}
