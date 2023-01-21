package somemod.enchanting.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import somemod.enchanting.block.entity.EnchantedBookshelfBlockEntity;
import somemod.enchanting.screen.EnchantedBookshelfScreenHandler;

public class EnchantedBookshelfBlock extends BlockWithEntity {

    public EnchantedBookshelfBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        // The three coordinates for the particle offset. Their axes (axis-s) is random.
        double posA = (random.nextFloat()) * 0.85d;
        double posB = (random.nextFloat()) * 0.85d;
        double posC = (random.nextDouble() * 1d) * (random.nextBoolean() ? 1d : -1d);

        // Create the particle offset vector, based on a randomly chosen axes.
        Vec3d particleOffset = switch (random.nextInt(6)) {
            case 0 -> new Vec3d(posA, posB, posC);
            case 1 -> new Vec3d(posA, posC, posB);
            case 2 -> new Vec3d(posB, posA, posC);
            case 4 -> new Vec3d(posC, posA, posB);
            case 3 -> new Vec3d(posB, posC, posA);
            case 5 -> new Vec3d(posC, posB, posA);
            default -> throw new IllegalStateException("Should not happen");
        };

        world.addParticle(
            ParticleTypes.ENCHANT,
            // Position: (Aparently, this is the pos of the end of the particle's path, not the start.)
            pos.getX() + 0.5d,
            pos.getY() + 1.3d,
            pos.getZ() + 0.5d,
            // Velocity:
            particleOffset.getX(),
            particleOffset.getY() - 1.15d,
            particleOffset.getZ()
        );
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnchantedBookshelfBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.CONSUME;
    }

    @Override
    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EnchantedBookshelfBlockEntity bookshelfBlockEntity) {
            Text text = bookshelfBlockEntity.getDisplayName();
            return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new EnchantedBookshelfScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text);
        }
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnchantedBookshelfBlockEntity bookshelfBlockEntity)
                bookshelfBlockEntity.setCustomName(itemStack.getName());
        }
    }
    
}
