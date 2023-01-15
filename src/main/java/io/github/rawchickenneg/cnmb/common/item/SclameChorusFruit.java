package io.github.rawchickenneg.cnmb.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class SclameChorusFruit extends Item {
    public SclameChorusFruit(Item.Properties p_40710_) {
        super(p_40710_);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (!pLevel.isClientSide) {
            double d0 = pEntityLiving.getX();
            double d1 = pEntityLiving.getY();
            double d2 = pEntityLiving.getZ();
            double d = Math.rint(Math.random() * 3);
            switch ((int) d) {
                case 1 -> pEntityLiving.setPos(pEntityLiving.getX() + 1024, pEntityLiving.getY(), pEntityLiving.getZ() + 1024);
                case 2 -> pEntityLiving.setPos(pEntityLiving.getX() - 1024, pEntityLiving.getY(), pEntityLiving.getZ() + 1024);
                case 3 -> pEntityLiving.setPos(pEntityLiving.getX() + 1024, pEntityLiving.getY(), pEntityLiving.getZ() - 1024);
                default -> pEntityLiving.setPos(pEntityLiving.getX() - 1024, pEntityLiving.getY(), pEntityLiving.getZ() - 1024);
            }


            for(int i = 0; i < 64; ++i) {
                double d3 = pEntityLiving.getX() + (pEntityLiving.getRandom().nextDouble() - 0.5D) * 32.0D;
                double d4 = Mth.clamp(pEntityLiving.getY() + (double)(pEntityLiving.getRandom().nextInt(16) - 8), (double)pLevel.getMinBuildHeight(), (double)(pLevel.getMinBuildHeight() + ((ServerLevel)pLevel).getLogicalHeight() - 1));
                double d5 = pEntityLiving.getZ() + (pEntityLiving.getRandom().nextDouble() - 0.5D) * 32.0D;
                if (pEntityLiving.isPassenger()) {
                    pEntityLiving.stopRiding();
                }

                net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(pEntityLiving, d3, d4, d5);
                if (event.isCanceled()) return itemstack;
                if (pEntityLiving.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    SoundEvent soundevent = pEntityLiving instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    pLevel.playSound(null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pEntityLiving.playSound(soundevent, 1.0F, 1.0F);
                    break;
                }
            }

            if (pEntityLiving instanceof Player) {
                ((Player)pEntityLiving).getCooldowns().addCooldown(this, 20);
                if (!pEntityLiving.level.getBlockState(pEntityLiving.getOnPos()).is(Blocks.AIR)){

                    for (BlockPos tmpPos : BlockPos.withinManhattan(pEntityLiving.getOnPos().offset(0,2,0), 1,1,1)) {
                        pLevel.setBlockAndUpdate(tmpPos, Blocks.AIR.defaultBlockState());
                    }
                }
                if (pEntityLiving.level.getBlockState(pEntityLiving.getOnPos().offset(0,-1,0)).is(Blocks.AIR)){
                    pLevel.setBlockAndUpdate(pEntityLiving.getOnPos().offset(0,-1,0), Blocks.END_STONE.defaultBlockState());
                }
            }
        }

        return itemstack;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.sclame_chorus_fruit.tip").withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
