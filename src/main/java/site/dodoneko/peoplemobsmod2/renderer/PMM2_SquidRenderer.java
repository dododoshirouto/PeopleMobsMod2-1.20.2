package site.dodoneko.peoplemobsmod2.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.SquidModel;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Squid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_SquidRenderer<T extends Squid> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    SquidRenderer<Squid> refR;
    SquidModel<Squid> refM;

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
    public PMM2_SquidRenderer(EntityRendererProvider.Context entity) {
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



    @SuppressWarnings("null")
    protected void setupRotations(T entity, PoseStack pose, float p_116037_, float p_116038_, float p_116039_) {
        float f = Mth.lerp(p_116039_, entity.xBodyRotO, entity.xBodyRot);
        float f1 = Mth.lerp(p_116039_, entity.zBodyRotO, entity.zBodyRot);
        pose.translate(0.0F, 0.5F, 0.0F);
        pose.mulPose(Axis.YP.rotationDegrees(180.0F - p_116038_));
        pose.mulPose(Axis.XP.rotationDegrees(f));
        pose.mulPose(Axis.YP.rotationDegrees(f1));
        pose.translate(0.0F, -0.5F, -0.1F);
    }

    @SuppressWarnings("null")
    protected float getBob(T entity, float p_116033_) {
        return Mth.lerp(p_116033_, entity.oldTentacleAngle, entity.tentacleAngle);
    }
}