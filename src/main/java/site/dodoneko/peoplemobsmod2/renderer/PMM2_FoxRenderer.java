package site.dodoneko.peoplemobsmod2.renderer;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.Util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Fox;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.model.PMM2_FoxModel;

/**
 * @see net.minecraft.client.model.FoxModel
 * @see net.minecraft.client.renderer.entity.FoxRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_FoxRenderer extends PMM2_HumanoidMobRenderer<Fox, PMM2_FoxModel> {

    @SuppressWarnings({ "null" })
    private static final Map<Fox.Type, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(),
            (entity) -> {
                entity.put(Fox.Type.RED,
                        new ResourceLocation("textures/entity/fox/fox-chan.png"));
                entity.put(Fox.Type.SNOW,
                        new ResourceLocation("textures/entity/fox/snow_fox-chan.png"));
            });

    @SuppressWarnings("null")
    public PMM2_FoxRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_FoxModel(entity.bakeLayer(PeopleMobsMod2.PMM2_TWINKLED_HUMANOID_LAYER)), modelScale);
    }

    @SuppressWarnings("null")
    protected void setupRotations(Fox entity, PoseStack matrix, float p1, float p2, float p3) {
        super.setupRotations(entity, matrix, p1, p2, p3);
        if (entity.isPouncing() || entity.isFaceplanted()) {
            float f = -Mth.lerp(p3, entity.xRotO, entity.getXRot());
            matrix.mulPose(Axis.XP.rotationDegrees(f));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Fox entity) {
        return TEXTURES.get(entity.getVariant());
    }
}