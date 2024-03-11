package site.dodoneko.peoplemobsmod2.renderer;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.layer.PMM2_FrogTongueLayer;
import site.dodoneko.peoplemobsmod2.model.PMM2_FrogModel;

/**
 * @see net.minecraft.client.model.FrogModel
 * @see net.minecraft.client.renderer.entity.FrogRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_FrogRenderer extends PMM2_HumanoidMobRenderer<Frog, PMM2_FrogModel> {

    @SuppressWarnings({ "null" })
    private static final Map<FrogVariant, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(),
            (entity) -> {
                entity.put(FrogVariant.TEMPERATE,
                        new ResourceLocation("textures/entity/frog/temperate_frog-chan.png"));
                entity.put(FrogVariant.COLD,
                        new ResourceLocation("textures/entity/frog/cold_frog-chan.png"));
                entity.put(FrogVariant.WARM,
                        new ResourceLocation("textures/entity/frog/warm_frog-chan.png"));
            });

    @SuppressWarnings("null")
    public PMM2_FrogRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_FrogModel(entity.bakeLayer(PeopleMobsMod2.PMM2_TWINKLED_HUMANOID_LAYER)), modelScale);
        this.addLayer(new PMM2_FrogTongueLayer(this, entity.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(Frog entity) {
        return TEXTURES.get(entity.getVariant());
    }
}