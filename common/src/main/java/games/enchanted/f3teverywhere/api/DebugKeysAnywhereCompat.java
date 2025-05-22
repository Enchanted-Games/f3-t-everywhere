package games.enchanted.f3teverywhere.api;

public interface DebugKeysAnywhereCompat {
    /**
     * Returning true here will fully disable Debug Keys Anywhere if this screen is open
     *
     * @return boolean whether to disable Debug Keys Anywhere or not
     */
    default boolean forceDisableDebugKeys() {
        return false;
    }

    /**
     * A list of key codes that should not be used for debug combinations and will always be passed to this screen.
     * This is useful if you want a custom key combination, like F3 + T for example, to work on your screen without
     * Minecraft's debug key combination being run
     *
     * @return array of key codes
     */
    default int[] debugKeysToSkip() {
        return new int[]{};
    }
}
