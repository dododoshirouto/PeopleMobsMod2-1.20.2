package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.EndermiteModel;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EndermiteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_EndermiteRenderer<T extends Endermite> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    EndermiteRenderer refR;
    EndermiteModel<Endermite> refM;

    @SuppressWarnings("null")
    public PMM2_EndermiteRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.4F);
        this.getModel().useChildModel = true;
        this.getModel().forwardArm = true;
    }


    

   @SuppressWarnings("null")
protected float getFlipDegrees(Endermite p_114352_) {
    return 180.0F;
 }
}