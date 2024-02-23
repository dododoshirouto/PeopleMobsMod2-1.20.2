package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.BatModel;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_BatRenderer<T extends Bat> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    BatRenderer refR;
    BatModel refM;

    @SuppressWarnings("null")
    public PMM2_BatRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.7F);
        this.getModel().useChildModel = true;
        this.getModel().flyFlap = true;
    }
}