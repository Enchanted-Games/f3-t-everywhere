package games.enchanted.f3teverywhere;

import games.enchanted.f3teverywhere.platform.Services;

public class CommonEntrypoint {
    public static void initBeforeRegistration() {
        Logging.info("Mod is loading on a {} environment", Services.PLATFORM.getPlatformName());
    }
}