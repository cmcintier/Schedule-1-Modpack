package schedule1.schedule1.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.screen.custom.PackingStationScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<PackingStationScreenHandler> PACKING_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Schedule1.MOD_ID, "packing_station_screen_handler"),
                    new ExtendedScreenHandlerType<>(PackingStationScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        Schedule1.LOGGER.info("Registering Screen Handlers for " + Schedule1.MOD_ID);
    }
}