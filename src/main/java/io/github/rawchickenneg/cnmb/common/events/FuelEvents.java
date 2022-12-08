package io.github.rawchickenneg.cnmb.common.events;

import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FuelEvents {
        @SubscribeEvent
        public void fuelEvent(FurnaceFuelBurnTimeEvent e) {
            Item item = e.getItemStack().getItem();
            if (item == ItemRegistry.netheriteSpongeAbsorbed.get()) {
                e.setBurnTime(20000);
            }
        }
    }

