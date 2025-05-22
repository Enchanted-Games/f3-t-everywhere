package games.enchanted.f3teverywhere;

import games.enchanted.f3teverywhere.api.DebugKeysAnywhereCompat;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class TestScreen extends Screen implements DebugKeysAnywhereCompat {
    protected TestScreen(Component title) {
        super(title);
    }

    public static TestScreen create() {
        return new TestScreen(Component.literal("TEST"));
    }

    @Override
    public boolean forceDisableDebugKeys() {
        return false;
    }

    @Override
    public int[] debugKeysToSkip() {
        return new int[]{GLFW.GLFW_KEY_T};
    }
}
