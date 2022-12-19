package io.github.rawchickenneg.cnmb.client.render;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;

public class ClayChickenRenderer extends ChickenRenderer {
    public ClayChickenRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_);
    }

    public ResourceLocation getTextureLocation(Chicken pEntity) {
        return new ResourceLocation("clay_no_more_balanced:textures/entity/clay_chicken.png");
    }
}
