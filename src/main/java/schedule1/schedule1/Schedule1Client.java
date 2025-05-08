package schedule1.schedule1;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.screen.ModScreenHandlers;
import schedule1.schedule1.screen.custom.PackingStationScreen;

public class Schedule1Client implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRANDADDY_PURPLE_BUSH, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.PACKING_STATION_SCREEN_HANDLER, PackingStationScreen::new);
    }
}
