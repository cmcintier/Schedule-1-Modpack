package schedule1.schedule1.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;
import schedule1.schedule1.block.ModBlocks;
import schedule1.schedule1.block.entity.custom.PackingStationBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<PackingStationBlockEntity> PACKING_STATION_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Schedule1.MOD_ID, "packing_station_be"),
                    BlockEntityType.Builder.create(PackingStationBlockEntity::new, ModBlocks.PACKING_STATION_BLOCK).build(null));

    public static void registerBlockEntities(){
        Schedule1.LOGGER.info("Registering Block Entities for " + Schedule1.MOD_ID
        );
    }
}
