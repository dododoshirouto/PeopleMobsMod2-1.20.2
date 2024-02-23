package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_PigRenderer<T extends Pig> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    PigRenderer refR;
    PigModel<Pig> refM;

    @SuppressWarnings("null")
    public PMM2_PigRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.8F);
        // this.getModel().bHeight = 0.95F;
    }
}