package schedule1.schedule1.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import schedule1.schedule1.Schedule1;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<BlockPos> COORDINATES =
            register("coordinates", builder -> builder.codec(BlockPos.CODEC));

    public static final ComponentType<Integer> CLICK_COUNT_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Schedule1.MOD_ID, "click_count"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<String> CONTENTS_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Schedule1.MOD_ID, "contents"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    //Strain component

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Schedule1.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerModComponents(){
        Schedule1.LOGGER.info("Registering Mod Components for " + Schedule1.MOD_ID);
    }
}
