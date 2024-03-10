package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.layer.PMM2_EyesLayer;
import site.dodoneko.peoplemobsmod2.layer.PMM2_HumanHeldBlockLayer;

/**
 * @see net.minecraft.client.model.EndermanModel
 * @see net.minecraft.client.renderer.entity.EndermanRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_EndermanRenderer extends PMM2_HumanoidMobRenderer<EnderMan, PMM2_HumanoidModel<EnderMan>> {

    private final RandomSource random = RandomSource.create();

    @SuppressWarnings("null")
    public PMM2_EndermanRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_TWINKLED_HUMANOID_LAYER)), modelScale);
        this.addLayer(new PMM2_EyesLayer<>(this));
        this.addLayer(new PMM2_HumanHeldBlockLayer<>(this, entity.getBlockRenderDispatcher()));
    }

    @SuppressWarnings("null")
    public Vec3 getRenderOffset(EnderMan entity, float partialTick) {
        if (entity.isCreepy()) {
            double d0 = 0.02D;
            return new Vec3(this.random.nextGaussian() * d0, 0.0D, this.random.nextGaussian() * d0);
        } else {
            return super.getRenderOffset(entity, partialTick);
        }
    }
}