package io.github.rawchickenneg.cnmb.common.curios;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class RuneRing extends CuriosItem {
    public RuneRing(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            //List<MobEffectInstance> effects = Lists.newArrayList(player.getActiveEffects());
            //for (MobEffectInstance effectInstance : effects) {
            //    MobEffect effect = effectInstance.getEffect();
            //    if (effect.getCategory() == MobEffectCategory.HARMFUL) {
            //        player.removeEffect(effect);
            //    }
            //}
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 62, 1, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 62, 1, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 62, 1, false, false, true));
            player.clearFire();
            player.setTicksFrozen(0);
            player.setAirSupply(player.getMaxAirSupply());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.rune_ring.tip").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.rune_ring.tip2").withStyle(ChatFormatting.DARK_GREEN));
    }

}
