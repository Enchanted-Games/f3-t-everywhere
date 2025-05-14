package games.enchanted.f3teverywhere;

import games.enchanted.f3teverywhere.platform.Services;

public class CommonEntrypoint {
    public static void initBeforeRegistration() {
        Logging.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
    }
}