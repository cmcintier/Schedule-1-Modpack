package schedule1.schedule1.component.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import schedule1.schedule1.component.ModDataComponentTypes;

import java.util.List;
import java.util.Optional;

/**
 * Represents the components that make up the properties of a food item.
 */
public record ConsumableDrugComponent(
    String strain, float consumeSeconds, Optional<ItemStack> usingConvertsTo, List<ConsumableDrugComponent.StatusEffectEntry> effects
) {
    private static final float DEFAULT_CONSUME_SECONDS = 1.6F;
    public static final Codec<ConsumableDrugComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.STRING.fieldOf("strain").forGetter(ConsumableDrugComponent::strain),
                            Codecs.POSITIVE_FLOAT.optionalFieldOf("consume_seconds", DEFAULT_CONSUME_SECONDS).forGetter(ConsumableDrugComponent::consumeSeconds),
                            ItemStack.UNCOUNTED_CODEC.optionalFieldOf("using_converts_to").forGetter(ConsumableDrugComponent::usingConvertsTo),
                            ConsumableDrugComponent.StatusEffectEntry.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(ConsumableDrugComponent::effects)
                    )
                    .apply(instance, ConsumableDrugComponent::new)
    );

    public static final PacketCodec<RegistryByteBuf, ConsumableDrugComponent> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            ConsumableDrugComponent::strain,
            PacketCodecs.FLOAT,
            ConsumableDrugComponent::consumeSeconds,
            ItemStack.PACKET_CODEC.collect(PacketCodecs::optional),
            ConsumableDrugComponent::usingConvertsTo,
            ConsumableDrugComponent.StatusEffectEntry.PACKET_CODEC.collect(PacketCodecs.toList()),
            ConsumableDrugComponent::effects,
            ConsumableDrugComponent::new
    );

    public int getConsumeTicks() {
        return (int) (this.consumeSeconds * 20.0F);
    }


    public static class Builder {
        private String strain;
        private float consumeSeconds = 1.6F;
        private Optional<ItemStack> usingConvertsTo = Optional.empty();
        private final ImmutableList.Builder<ConsumableDrugComponent.StatusEffectEntry> effects = ImmutableList.builder();


        public ConsumableDrugComponent.Builder strain(String strainName){
            this.strain = strainName;
            return this;
        }


        /**
         * Specifies that a food item is snack-like and is eaten quickly.
         */
        public ConsumableDrugComponent.Builder snack() {
            this.consumeSeconds = 0.8F;
            return this;
        }

        /**
         * Specifies a status effect to apply to an entity when a food item is consumed.
         * This method may be called multiple times to apply several status effects when food is consumed.
         *
         * @param chance the chance the status effect is applied, on a scale of {@code 0.0F} to {@code 1.0F}
         * @param effect the effect instance to apply
         */
        public ConsumableDrugComponent.Builder statusEffect(StatusEffectInstance effect, float chance) {
            this.effects.add(new ConsumableDrugComponent.StatusEffectEntry(effect, chance));
            return this;
        }

        public ConsumableDrugComponent.Builder usingConvertsTo(ItemConvertible item) {
            this.usingConvertsTo = Optional.of(new ItemStack(item));
            return this;
        }

        public ConsumableDrugComponent build() {
            return new ConsumableDrugComponent(this.strain, this.consumeSeconds, this.usingConvertsTo, this.effects.build());
        }
    }

    public record StatusEffectEntry(StatusEffectInstance effect, float probability) {
        public static final Codec<ConsumableDrugComponent.StatusEffectEntry> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                StatusEffectInstance.CODEC.fieldOf("effect").forGetter(ConsumableDrugComponent.StatusEffectEntry::effect),
                                Codec.floatRange(0.0F, 1.0F).optionalFieldOf("probability", 1.0F).forGetter(ConsumableDrugComponent.StatusEffectEntry::probability)
                        )
                        .apply(instance, ConsumableDrugComponent.StatusEffectEntry::new)
        );
        public static final PacketCodec<RegistryByteBuf, ConsumableDrugComponent.StatusEffectEntry> PACKET_CODEC = PacketCodec.tuple(
                StatusEffectInstance.PACKET_CODEC,
                ConsumableDrugComponent.StatusEffectEntry::effect,
                PacketCodecs.FLOAT,
                ConsumableDrugComponent.StatusEffectEntry::probability,
                ConsumableDrugComponent.StatusEffectEntry::new
        );

        public StatusEffectInstance effect() {
            return new StatusEffectInstance(this.effect);
        }
    }
}



