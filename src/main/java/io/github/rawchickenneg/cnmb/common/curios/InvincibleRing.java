package io.github.rawchickenneg.cnmb.common.curios;

import io.github.rawchickenneg.cnmb.common.curios.CuriosItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class InvincibleRing extends CuriosItem {
    public InvincibleRing(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onEquippedCurio(String identifier, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            player.getAbilities().invulnerable = true;
            player.onUpdateAbilities();
        }
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            if(!player.getAbilities().invulnerable) {
                player.getAbilities().invulnerable = true;
                player.onUpdateAbilities();
            }
        }
    }

    @Override
    public void onUnequippedCurio(String identifier, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            player.getAbilities().invulnerable = false;
            player.getAbilities().invulnerable = false;
            player.onUpdateAbilities();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.invincible.tip").withStyle(ChatFormatting.DARK_PURPLE));
    }

}
