package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.model.PMM2_ZombieModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_ZombieRenderer<T extends Zombie> extends PMM2_HumanoidMobRenderer<T, PMM2_ZombieModel<T>> {

    ZombieRenderer ref;

    @SuppressWarnings("null")
    public PMM2_ZombieRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_ZombieModel<>(entity.bakeLayer(ModelLayers.ZOMBIE)), 0.8F);
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_ZombieRenderer(EntityRendererProvider.Context entity)");
        this.addArmorLayers(entity, new PMM2_ZombieModel<>(entity.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new PMM2_ZombieModel<>(entity.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
    }
}