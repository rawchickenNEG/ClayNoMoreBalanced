package io.github.rawchickenneg.cnmb.common.item.clayballs;

import io.github.rawchickenneg.cnmb.common.registry.SoundEventRegistry;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EggClayBall extends Item {

    public EggClayBall(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResultHolder<ItemStack> use(Level p_43142_, Player p_43143_, InteractionHand p_43144_) {
        ItemStack itemstack = p_43143_.getItemInHand(p_43144_);
        p_43142_.playSound(null, p_43143_.getX(), p_43143_.getY(), p_43143_.getZ(), SoundEventRegistry.EGG_LAUNCH.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
        if (!p_43142_.isClientSide) {
            ThrownEgg egg = new ThrownEgg(p_43142_, p_43143_);
            egg.setItem(itemstack);
            egg.shootFromRotation(p_43143_, p_43143_.getXRot(), p_43143_.getYRot(), 0.0F, 1.5F, 1.0F);
            p_43142_.addFreshEntity(egg);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, p_43142_.isClientSide());
    }
}
