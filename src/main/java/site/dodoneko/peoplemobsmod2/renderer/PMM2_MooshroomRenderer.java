package site.dodoneko.peoplemobsmod2.renderer;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.Util;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.layer.PMM2_HumanBlockOnHeadLayer;

/**
 * @see net.minecraft.client.renderer.entity.MushroomCowRenderer
 * @see net.minecraft.client.model.CowModel
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_MooshroomRenderer extends PMM2_HumanoidMobRenderer<MushroomCow, PMM2_HumanoidModel<MushroomCow>> {

    @SuppressWarnings("null")
    private static final Map<MushroomCow.MushroomType, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(),
            (entity) -> {
                entity.put(MushroomCow.MushroomType.BROWN,
                        new ResourceLocation("textures/entity/cow/brown_mooshroom-chan.png"));
                entity.put(MushroomCow.MushroomType.RED,
                        new ResourceLocation("textures/entity/cow/red_mooshroom-chan.png"));
            });

    @SuppressWarnings("null")
    public PMM2_MooshroomRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        this.addLayer(new PMM2_HumanBlockOnHeadLayer<>(this, entity.getBlockRenderDispatcher()));
    }

    @Override
    public ResourceLocation getTextureLocation(MushroomCow entity) {
        return TEXTURES.get(entity.getVariant());
    }
}