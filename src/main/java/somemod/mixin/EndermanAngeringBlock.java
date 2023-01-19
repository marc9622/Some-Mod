package somemod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import somemod.crystal.block.CrystalBlockTags;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class EndermanAngeringBlock {
    
    @Inject(method = "onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At("HEAD"))
    private void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo info) {

        if(state.isIn(CrystalBlockTags.GUARDED_BY_ENDERMEN)) {

            ArrayList<EndermanEntity> endermen = new ArrayList<>(); // In vanilla, endermen can be provoked from 64 blocks away, when looking at them.
            world.collectEntitiesByType(EntityType.ENDERMAN, new Box(pos).expand(24), enderman -> !enderman.isAngry() && !enderman.isProvoked(), endermen, 4);

            int angerCount = world.random.nextInt(Math.min(4, endermen.size()));

            for(int i = 0; i < angerCount; i++) {
                EndermanEntity enderman = endermen.remove(world.random.nextInt(endermen.size()));
                enderman.setTarget(player);
                enderman.setProvoked();
            }
        }
    }
}
