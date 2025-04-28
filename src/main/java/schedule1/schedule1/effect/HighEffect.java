package schedule1.schedule1.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import schedule1.schedule1.Schedule1;

//ctrl + h
public class HighEffect extends StatusEffect {
    protected HighEffect(StatusEffectCategory category, int color){
        super(category, color);
        Schedule1.LOGGER.info("High Effect");
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity playerEntity) {
            playerEntity.addExhaustion(0.05F * (amplifier + 1));
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
