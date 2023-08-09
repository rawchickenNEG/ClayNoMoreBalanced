package io.github.rawchickenneg.cnmb.common.item;

import io.github.rawchickenneg.cnmb.common.registry.MobEffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class EnchantedSclameApple extends Item {

    public EnchantedSclameApple(Properties pProperties) {
        super(pProperties);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.enchanted_sclame_apple.tip").withStyle(ChatFormatting.DARK_AQUA));
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity livingEntity) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, livingEntity);
        livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.UNDYING.get(), 600, 0, false, true, true));
        return itemstack;
    }

    @Override
    public boolean isFoil(ItemStack p_43138_) {
        return true;
    }
}
