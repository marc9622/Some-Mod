package somemod.frost.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.world.World;
import somemod.frost.entity.effect.FrostStatusEffects;
import somemod.frost.item.FrostItems;
import somemod.frost.potion.FrostPotions;

public class FrostbiteArrowEntity extends ArrowEntity {

    public FrostbiteArrowEntity(EntityType<? extends ArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public FrostbiteArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public FrostbiteArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    protected boolean canApplyEffects() {
        return false;
    }

    // Instead of making the arrow burn, being 'on fire' simply
    // cancels out the freezing effect.
    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void initFromStack(ItemStack arrows) {
        if (canApplyEffects()) super.initFromStack(arrows);
    }

    @Override
    public int getColor() {
        if (canApplyEffects())
            return super.getColor();
        else
            return PotionUtil.getColor(FrostPotions.FROSTBITE);
    }

    @Override
    public void addEffect(StatusEffectInstance effect) {
        if (canApplyEffects()) super.addEffect(effect);
    }

    @Override
    protected void onHit(LivingEntity entity) {
        super.onHit(entity);

        if (!super.isOnFire())
            entity.addStatusEffect(new StatusEffectInstance(FrostStatusEffects.FROSTBITE, 100));
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(FrostItems.FROSTBITE_ARROW);
    }

    @Override
    public void handleStatus(byte status) {
        if (canApplyEffects()) super.handleStatus(status);
    }
}

