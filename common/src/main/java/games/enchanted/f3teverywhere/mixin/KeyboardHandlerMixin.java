package games.enchanted.f3teverywhere.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.InputConstants;
import games.enchanted.f3teverywhere.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {
    @Shadow @Final private Minecraft minecraft;

    @Shadow protected abstract boolean handleDebugKeys(int key);

    @Inject(
        at = @At(
            value = "FIELD",
            opcode = Opcodes.GETFIELD,
            target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;",
            ordinal = 0
        ),
        method = "keyPress"
    )
    private void f3teverywhere$handleDebugKeysInScreens(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if(this.minecraft.screen instanceof PauseScreen pause && pause.showsPauseMenu()) return;

        if(action != 0 && (this.minecraft.screen != null)) {
            if(!InputConstants.isKeyDown(windowPointer, GLFW.GLFW_KEY_F3)) return;
            try {
                handleDebugKeys(key);
            } catch (NullPointerException ignored) {
            }
        }
    }

    @WrapOperation(
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyboardHandler;decorateDebugComponent(Lnet/minecraft/ChatFormatting;Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/Component;"),
        method = {"debugWarningComponent", "debugFeedbackComponent"}
    )
    private Component f3teverywhere$showDebugFeedbackToast(ChatFormatting formatting, Component message, Operation<Component> original) {
        if(this.minecraft.level != null) {
            return original.call(formatting, message);
        }
        SystemToast.addOrUpdate(this.minecraft.getToastManager(), Constants.DEBUG_FEEDBACK_TOAST, Component.translatable("debug.prefix").withStyle(formatting, ChatFormatting.BOLD), message);
        return original.call(formatting, message);
    }
}
