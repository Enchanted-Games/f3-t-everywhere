package games.enchanted.f3teverywhere.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
	@Shadow
	MinecraftClient client;
	boolean switchF3State;

	@Shadow
	abstract public boolean processF3(int key);

	@Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/Keyboard;onKey(JIIII)V")
	public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
		if (window == this.client.window.getHandle()) {
			boolean bl2;
			if (action != 0 && this.client.currentScreen != null) {
				try {
					bl2 = InputUtil.isKeyPressed(this.client.window.getHandle(), 292) && this.processF3(key);
				} catch (Exception e) {
					bl2 = true;
				}
				this.switchF3State |= bl2;
			}
		}
	}
}