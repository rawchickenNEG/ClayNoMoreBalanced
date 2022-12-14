package io.github.rawchickenneg.cnmb.common.item;

import io.github.rawchickenneg.cnmb.common.entity.ThrownClayBallFromBow;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class ClayWand extends ProjectileWeaponItem implements Vanishable {
    public ClayWand(Properties p_40660_) {
        super(p_40660_);
    }

    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        if (p_40669_ instanceof Player) {
            Player player = (Player)p_40669_;
            int i = this.getUseDuration(p_40667_) - p_40670_;
                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    if (!p_40668_.isClientSide) {
                        int count = 1;
                        if (f > 0.2F) {
                            count = Math.round(f * 15 + 1);

                            for (int j = 0; j < count; ++j) {
                                ThrownClayBallFromBow clayball = new ThrownClayBallFromBow(p_40668_, player);
                                clayball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 4.0F, 10.0F);
                                p_40668_.addFreshEntity(clayball);
                            }
                        }

                        p_40667_.hurtAndBreak(1, player, (p_40665_) -> {
                            p_40665_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                    }

                    p_40668_.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.0F / (p_40668_.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    player.awardStat(Stats.ITEM_USED.get(this));
                }

        }
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);
        p_40673_.startUsingItem(p_40674_);
        return InteractionResultHolder.consume(itemstack);

    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }


    public int getDefaultProjectileRange() {
        return 15;
    }
}