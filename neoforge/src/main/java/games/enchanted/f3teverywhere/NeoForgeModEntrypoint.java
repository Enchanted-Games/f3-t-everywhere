package games.enchanted.f3teverywhere;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NeoForgeModEntrypoint {
    public NeoForgeModEntrypoint(IEventBus eventBus) {
        CommonEntrypoint.initBeforeRegistration();
    }
}