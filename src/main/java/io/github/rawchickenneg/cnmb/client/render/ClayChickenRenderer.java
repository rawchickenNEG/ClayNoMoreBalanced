package io.github.rawchickenneg.cnmb.client.render;

import io.github.rawchickenneg.cnmb.common.entity.ClayChicken;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClayChickenRenderer extends MobRenderer<ClayChicken, ChickenModel<ClayChicken>> {

    public ClayChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    public ResourceLocation getTextureLocation(ClayChicken entity) {
        return new ResourceLocation("clay_no_more_balanced:textures/entity/clay_chicken.png");
    }

    protected float getBob(ClayChicken chicken, float partialTicks) {
        float f = Mth.lerp(partialTicks, chicken.oFlap, chicken.flap);
        float f1 = Mth.lerp(partialTicks, chicken.oFlapSpeed, chicken.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }

}