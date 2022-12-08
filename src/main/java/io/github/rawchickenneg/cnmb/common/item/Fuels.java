package io.github.rawchickenneg.cnmb.common.item;

import io.github.rawchickenneg.cnmb.common.registry.BlockRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber
public class Fuels {
    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == BlockRegistry.NETHERITE_SPONGE_ABSORBED_BLOCK.get().asItem())
            event.setBurnTime(20000);
        }
    }


