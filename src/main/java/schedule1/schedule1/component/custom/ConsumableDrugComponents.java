package schedule1.schedule1.component.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemConvertible;
import schedule1.schedule1.effect.ModEffects;
import schedule1.schedule1.item.ModItems;

public class ConsumableDrugComponents {
    public static final ConsumableDrugComponent GRANDADDY_PURPLE_BLUNT =
            new ConsumableDrugComponent.Builder()
                    .statusEffect(new StatusEffectInstance(ModEffects.HIGH, 200), 1f)
                    .build();
}
