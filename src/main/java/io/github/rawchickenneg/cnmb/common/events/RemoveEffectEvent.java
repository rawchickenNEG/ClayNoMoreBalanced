package io.github.rawchickenneg.cnmb.common.events;

import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber
public class RemoveEffectEvent {
    @SubscribeEvent
    public static void onApplyPotion(PotionEvent.PotionApplicableEvent event) {
        if(event.getEntity() instanceof LivingEntity livingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(ItemRegistry.RUNE_RING.get(), livingEntity).isPresent()){
            if (event.getPotionEffect().getEffect().getCategory() == MobEffectCategory.HARMFUL){
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
