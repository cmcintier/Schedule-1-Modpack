package schedule1.schedule1.effect;


import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import schedule1.schedule1.Schedule1;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> HIGH = registerStatusEffect("high",
            new HighEffect(StatusEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(Schedule1.MOD_ID, "high"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Schedule1.MOD_ID, name), statusEffect);
    }

    public static void registerEffects(){
        Schedule1.LOGGER.info("Registering Mod Effects for " + Schedule1.MOD_ID);


    }


}
