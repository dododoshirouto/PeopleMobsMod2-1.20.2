package site.dodoneko.peoplemobsmod2.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_IronGolemRenderer<T extends IronGolem> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    IronGolemRenderer refR;
    IronGolemModel<IronGolem> refM;

    // model options
    public static float modelScale = 0.9F;
    public static float bHeight = 0.3F;
    public static boolean useChildModel = false;
    public static boolean doFlyFlap = false;
    public static boolean forwardArm = false;
    public static boolean isFloating = false;
    public static float floatingHeight = 0.0F;
    public static boolean doWalkBounding = true;

    @SuppressWarnings("null")
    public PMM2_IronGolemRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        this.getModel().modelScale = modelScale;
        this.getModel().bHeight = bHeight;
        this.getModel().useChildModel = useChildModel;
        this.getModel().doFlyFlap = doFlyFlap;
        this.getModel().forwardArm = forwardArm;
        this.getModel().isFloating = isFloating;
        this.getModel().floatingHeight = floatingHeight;
        this.getModel().doWalkBounding = doWalkBounding;
    }

    public static void setModelScales(float scale, float height) {
        modelScale = scale;
        bHeight = height;
    }

    public static void setModelScales(float scale, float height, boolean isChild) {
        modelScale = scale;
        bHeight = height;
        useChildModel = isChild;
    }

    public static void setModelScales(float scale, float height, boolean isChild, boolean flyFlap) {
        modelScale = scale;
        bHeight = height;
        useChildModel = isChild;
        doFlyFlap = flyFlap;
    }

    public static void setForwardArm(boolean v) {
        forwardArm = v;
    }

    public static void setIsFloating(boolean v, float h) {
        isFloating = v;
        floatingHeight = h;
    }

    public static void setDoWalkBounding(boolean v) {
        doWalkBounding = v;
    }

    

   @SuppressWarnings({ "null", "unchecked" })
   protected void setupRotations(IronGolem p_115014_, PoseStack p_115015_, float p_115016_, float p_115017_, float p_115018_) {
      super.setupRotations((T)p_115014_, p_115015_, p_115016_, p_115017_, p_115018_);
      if (!((double)p_115014_.walkAnimation.speed() < 0.01D)) {
         float f1 = p_115014_.walkAnimation.position(p_115018_) + 6.0F;
         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
         p_115015_.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
      }
   }
}