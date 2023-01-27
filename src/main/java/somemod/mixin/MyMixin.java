package somemod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Enchantment.class)
public abstract class MyMixin {

	@Inject(method = "getName(I)Lnet/minecraft/text/Text;", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void getName(int level, CallbackInfoReturnable<Text> info, MutableText mutableText) {

		// mutableText.append(" Hello from MyMixin!");
		
	}

}
