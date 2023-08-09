package io.github.rawchickenneg.cnmb.common.events;

import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber
public class UnhurtEvent {
    @SubscribeEvent
    public static void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getEntity() instanceof LivingEntity livingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(ItemRegistry.RUNE_RING.get(), livingEntity).isPresent()){
            if(event.getSource().isFire() || event.getSource().isFall()){
                event.setCanceled(true);
            }
        }
    }
}
