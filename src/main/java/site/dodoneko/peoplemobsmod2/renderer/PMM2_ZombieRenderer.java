package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;

@OnlyIn(Dist.CLIENT)
public class PMM2_ZombieRenderer<T extends Zombie> extends PMM2_HumanoidMobRenderer<T, ZombieModel<T>> {

    ZombieRenderer ref;

    public PMM2_ZombieRenderer(EntityRendererProvider.Context entity) {
        this(entity, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_ZombieRenderer(EntityRendererProvider.Context entity)");
    }

    @SuppressWarnings("null")
    public PMM2_ZombieRenderer(EntityRendererProvider.Context entity, ModelLayerLocation modelLayer_1, ModelLayerLocation modelLayer_2, ModelLayerLocation modelLayer_3) {
        super(entity, new ZombieModel<>(entity.bakeLayer(modelLayer_1)), new ZombieModel<>(entity.bakeLayer(modelLayer_2)), new ZombieModel<>(entity.bakeLayer(modelLayer_3)), 0.5F);
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_ZombieRenderer(EntityRendererProvider.Context entity, ModelLayerLocation modelLayer_1, ModelLayerLocation modelLayer_2, ModelLayerLocation modelLayer_3)");
    }
}