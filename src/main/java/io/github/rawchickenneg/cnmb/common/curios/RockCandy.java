package io.github.rawchickenneg.cnmb.common.curios;

import io.github.rawchickenneg.cnmb.common.curios.CuriosItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

public class RockCandy extends CuriosItem {
    public RockCandy(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        final Vec3 center = new Vec3(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        double r = 20;
        List<Entity> entities = livingEntity.getLevel().getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(r*2), e -> true).stream()
                .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
        for (Entity target : entities) {
            double dx = target.getX() - livingEntity.getX();
            double dy = target.getY() - livingEntity.getY();
            double dz = target.getZ() - livingEntity.getZ();
            if (Math.pow(dx,2) + Math.pow(dy,2) + Math.pow(dz,2) <= Math.pow(r,2)){
                if (target instanceof Player player && !player.isVehicle() && player.tickCount % 1200 == 0 || target instanceof Phantom phantom && !phantom.isVehicle()){
                    Pig pig = EntityType.PIG.create(target.getLevel());
                    assert pig != null;
                    pig.setAge(-6000);
                    pig.moveTo(target.getOnPos(), 0, 0.0F);
                    pig.startRiding(target);
                    target.getLevel().addFreshEntity(pig);
                }
                if (target instanceof Phantom phantom && phantom.isVehicle()){
                    phantom.setDeltaMovement(phantom.getDeltaMovement().x(), phantom.getDeltaMovement().y() - 0.098, phantom.getDeltaMovement().z());
                    if (phantom.isOnGround()){
                        phantom.hurt(DamageSource.FALL, 100);
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.rock_candy.tip").withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.rock_candy.tip1").withStyle(ChatFormatting.GOLD));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.rock_candy.tip2").withStyle(ChatFormatting.RED));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.rock_candy.tip3").withStyle(ChatFormatting.DARK_PURPLE));
    }

}
