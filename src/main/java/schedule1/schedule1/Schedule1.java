package schedule1.schedule1;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.block.entity.ModBlockEntities;
import schedule1.schedule1.component.ModDataComponentTypes;
import schedule1.schedule1.effect.ModEffects;
import schedule1.schedule1.item.ModItemGroups;
import schedule1.schedule1.item.ModItems;
import schedule1.schedule1.screen.ModScreenHandlers;

public class Schedule1 implements ModInitializer {
	public static final String MOD_ID = "schedule1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModDataComponentTypes.registerModComponents();
		ModEffects.registerEffects();
		ModScreenHandlers.registerScreenHandlers();
	}
}