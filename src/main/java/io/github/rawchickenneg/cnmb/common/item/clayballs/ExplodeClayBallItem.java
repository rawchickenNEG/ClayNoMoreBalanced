package io.github.rawchickenneg.cnmb.common.item.clayballs;

import io.github.rawchickenneg.cnmb.common.entity.ThrownExplodeClayBall;
import io.github.rawchickenneg.cnmb.config.Config;
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

public class ExplodeClayBallItem extends Item {
    public ExplodeClayBallItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        float pitch = 1F / (level.random.nextFloat() * 0.4F + 0.8F);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 1.0F, pitch);
        if (!level.isClientSide) {
            ThrownExplodeClayBall clayBall = new ThrownExplodeClayBall(level, player);
            clayBall.setItem(heldStack);
            clayBall.setExplosionPower(4);
            clayBall.setCanBreak(Config.CONFIG.EXPLODE.get());
            clayBall.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(clayBall);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            heldStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(heldStack, level.isClientSide());
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.advanced_explode_clay_ball.tip").withStyle(ChatFormatting.GOLD));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.explode_clay_ball.tip2").withStyle(ChatFormatting.GRAY));
    }

}
