package games.enchanted.f3teverywhere;

import net.fabricmc.api.ModInitializer;

public class FabricModEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        Logging.info("Hello Fabric world!");
        CommonEntrypoint.initBeforeRegistration();
    }
}
