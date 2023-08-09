package io.github.rawchickenneg.cnmb.common.curios;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class CuriosItem extends Item implements ICurioItem {
    public CuriosItem(Properties pProperties) {
        super(pProperties);
    }

    public void tickCurio(String identifier, int index, LivingEntity livingEntity){}

    public void onEquippedCurio(String identifier, LivingEntity livingEntity){}

    public void onUnequippedCurio(String identifier, LivingEntity livingEntity){}

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> lazyCurio = LazyOptional.of(()-> new ICurio() {

                @Override
                public ItemStack getStack() {
                    return stack;
                }

                @Override
                public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                    tickCurio(identifier, index, livingEntity);
                }

                @Override
                public void onEquip(String identifier, int index, LivingEntity livingEntity) {
                    onEquippedCurio(identifier, livingEntity);
                }

                @Nonnull
                @Override
                public DropRule getDropRule(LivingEntity livingEntity) {
                    return DropRule.DEFAULT;
                }


                @Override
                public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                    onUnequippedCurio(identifier, livingEntity);
                }

                @Override
                public boolean canRightClickEquip() {
                    return true;
                }

            });

            @Override
            public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                return CuriosCapability.ITEM.orEmpty(capability, lazyCurio);
            }
        };
    }


























}
