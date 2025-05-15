package games.enchanted.f3teverywhere;

import net.fabricmc.api.ModInitializer;

public class FabricModEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonEntrypoint.initBeforeRegistration();
    }
}
