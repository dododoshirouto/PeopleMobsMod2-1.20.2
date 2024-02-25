package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_CaveSpiderRenderer<T extends CaveSpider> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    SpiderRenderer<Spider> refR;
    SpiderModel<Spider> refM;

    @SuppressWarnings("null")
    public PMM2_CaveSpiderRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 1F);
        this.getModel().useChildModel = true;
        this.getModel().forwardArm = true;
    }



    

   @SuppressWarnings("null")
protected float getFlipDegrees(T p_116011_) {
    return 180.0F;
 }
}