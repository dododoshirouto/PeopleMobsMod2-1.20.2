package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.SilverfishModel;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SilverfishRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_SilverfishRenderer<T extends Silverfish> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    SilverfishRenderer refR;
    SilverfishModel<Silverfish> refM;

    @SuppressWarnings("null")
    public PMM2_SilverfishRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.5F);
        this.getModel().useChildModel = true;
        this.getModel().forwardArm = true;
    }
}