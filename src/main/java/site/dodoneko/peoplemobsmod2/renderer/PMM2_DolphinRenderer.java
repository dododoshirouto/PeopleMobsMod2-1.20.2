package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.renderer.entity.DolphinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_DolphinRenderer<T extends Dolphin> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    DolphinRenderer refR;
    DolphinModel<Dolphin> refM;

    @SuppressWarnings("null")
    public PMM2_DolphinRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.8F);
        this.getModel().bHeight = 0.82F;
    }
}