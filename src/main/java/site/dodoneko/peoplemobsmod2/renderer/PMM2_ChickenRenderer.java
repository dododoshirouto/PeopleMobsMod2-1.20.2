package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_ChickenRenderer<T extends Chicken> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    ChickenRenderer refR;
    ChickenModel<Chicken> refM;

    @SuppressWarnings("null")
    public PMM2_ChickenRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.7F);
        this.getModel().useChildModel = true;
        this.getModel().flyFlap = true;
    }
}