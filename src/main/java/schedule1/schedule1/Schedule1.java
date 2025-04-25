package schedule1.schedule1;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule1.schedule1.item.ModItems;

public class Schedule1 implements ModInitializer {
	public static final String MOD_ID = "schedule1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}