package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_SkeletonRenderer<T extends Skeleton> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    SkeletonRenderer refR;
    SkeletonModel<Skeleton> refM;

    @SuppressWarnings("null")
    public PMM2_SkeletonRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.8F);
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_SkeletonRenderer(EntityRendererProvider.Context entity)");
        this.addArmorLayers(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)));
    }
}