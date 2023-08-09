package io.github.rawchickenneg.cnmb.common.curios;

import io.github.rawchickenneg.cnmb.common.curios.CuriosItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class Foxtail extends CuriosItem {
    public Foxtail(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {

    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

}
