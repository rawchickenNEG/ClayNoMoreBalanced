package io.github.rawchickenneg.cnmb.common.curios;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
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

public class RepulsionRing extends CuriosItem {

    public RepulsionRing(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        final Vec3 center = new Vec3(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        double r = 8;
        List<Entity> entities = livingEntity.getLevel().getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(r*2), e -> true).stream()
                .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
        for (Entity target : entities) {
            double dx = target.getX() - livingEntity.getX();
            double dy = target.getY() - livingEntity.getY();
            double dz = target.getZ() - livingEntity.getZ();
            if (Math.pow(dx,2) + Math.pow(dy,2) + Math.pow(dz,2) <= Math.pow(r,2)){
                if (target instanceof Monster || target instanceof Mob mob && mob.getTarget()==livingEntity){
                    Vec3 vec3 = new Vec3(dx, dy, dz);
                    double d0 = vec3.lengthSqr();
                    double d1 = - Math.sqrt(d0) / 8.0D;
                    target.setDeltaMovement(target.getDeltaMovement().add(vec3.normalize().scale(d1 * d1)));
                }
                if (target instanceof Projectile projectile){
                    if (!(projectile.getOwner() == livingEntity) && projectile.getOwner() != null){
                        if (projectile instanceof AbstractHurtingProjectile fireball){
                            fireball.xPower = 0;
                            fireball.yPower = 0;
                            fireball.zPower = 0;
                        }
                        projectile.setDeltaMovement(-projectile.getDeltaMovement().x(),-projectile.getDeltaMovement().y(),-projectile.getDeltaMovement().z());
                        projectile.setOwner(livingEntity);
                        livingEntity.getLevel().playSound(null, projectile.getX(), projectile.getY(), projectile.getZ(), SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, SoundSource.NEUTRAL, 1F, 0.4F / (livingEntity.getLevel().random.nextFloat() * 0.4F + 0.8F));
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.repulsion_ring.tip").withStyle(ChatFormatting.DARK_AQUA));
        tooltip.add(new TranslatableComponent("item.clay_no_more_balanced.repulsion_ring.tip2").withStyle(ChatFormatting.DARK_AQUA));
    }

}
