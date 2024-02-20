package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_CreeperRenderer<T extends Creeper> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    ZombieRenderer ref;

    @SuppressWarnings("null")
    public PMM2_CreeperRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.7F);
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_CreeperRenderer(EntityRendererProvider.Context entity)");
    }
}