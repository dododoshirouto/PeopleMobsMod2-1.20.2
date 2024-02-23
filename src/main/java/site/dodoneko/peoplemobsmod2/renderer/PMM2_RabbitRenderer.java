package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_RabbitRenderer<T extends Rabbit> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    RabbitRenderer refR;
    RabbitModel<Rabbit> refM;

    @SuppressWarnings("null")
    public PMM2_RabbitRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.5F);
        // this.getModel().bHeight = 0.95F;
    }
}