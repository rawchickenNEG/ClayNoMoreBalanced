package io.github.rawchickenneg.cnmb.common.item;

import io.github.rawchickenneg.cnmb.common.entity.ThrownVehicleClayBall;
import io.github.rawchickenneg.cnmb.common.registry.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class VehicleLever extends Item {

    public VehicleLever(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 1F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            ThrownVehicleClayBall clayball = new ThrownVehicleClayBall(level, player);
            clayball.setItem(ItemRegistry.VEHICLE_CLAY_BALL.get().getDefaultInstance());
            clayball.setType(0);
            clayball.setLever(true);
            clayball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(clayball);
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResultHolder.sidedSuccess(heldStack, level.isClientSide());
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.levers.tip").withStyle(ChatFormatting.YELLOW));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.vehicle_clay_ball.tip").withStyle(ChatFormatting.GOLD));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.vehicle_clay_ball.tip2").withStyle(ChatFormatting.GRAY));
    }
}
