package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.model.PMM2_ZombieModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_ZombieRenderer<T extends Zombie> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    ZombieRenderer refR;
    ZombieModel<Zombie> refM;
    PMM2_ZombieModel<Zombie> refM2;

    @SuppressWarnings("null")
    public PMM2_ZombieRenderer(EntityRendererProvider.Context entity) {
        // super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(ModelLayers.ZOMBIE)), 0.8F);
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.8F);
        this.addArmorLayers(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)));
    }
}