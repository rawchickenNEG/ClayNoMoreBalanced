package io.github.rawchickenneg.cnmb.common.events;

import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import io.github.rawchickenneg.cnmb.common.registry.MobEffectRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber
public class UndyingEvent {
    @SubscribeEvent
    public static void LivingDeathEvent(LivingDeathEvent event) {
        if(event.getEntity() instanceof LivingEntity livingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(ItemRegistry.GUARDIAN_RING.get(), livingEntity).isPresent()){
            if(livingEntity.getLevel().dimension()== Level.END || livingEntity.getLevel().dimension()== Level.NETHER || livingEntity.getLevel().dimension()== Level.OVERWORLD){
                event.setCanceled(true);
                livingEntity.setHealth(1.0F);
            }
        }
        if(event.getEntity() instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffectRegistry.UNDYING.get())) {
            event.setCanceled(true);
            livingEntity.setHealth(1.0F);
        }
    }
}
