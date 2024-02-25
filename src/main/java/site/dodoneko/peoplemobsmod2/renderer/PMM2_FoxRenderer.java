package site.dodoneko.peoplemobsmod2.renderer;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.Util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.FoxModel;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Fox;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_FoxRenderer<T extends Fox> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    FoxRenderer refR;
    FoxModel<Fox> refM;

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
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), 0.8F);
        // this.getModel().bHeight = 0.95F;
    }

    @SuppressWarnings("null")
    protected void setupRotations(T entity, PoseStack pose, float p_114740_, float p_114741_,
            float p_114742_) {
        super.setupRotations(entity, pose, p_114740_, p_114741_, p_114742_);
        if (entity.isPouncing() || entity.isFaceplanted()) {
            float f = -Mth.lerp(p_114742_, entity.xRotO, entity.getXRot());
            pose.mulPose(Axis.XP.rotationDegrees(f));
        }

    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURES.get(entity.getVariant());
    }
}