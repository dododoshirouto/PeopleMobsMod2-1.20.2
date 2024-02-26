package site.dodoneko.peoplemobsmod2.renderer;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_EndermanRenderer<T extends EnderMan> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    EndermanRenderer refR;
    EndermanModel<EnderMan> refM;
    private final RandomSource random = RandomSource.create();
    
    public static float modelScale = 0.9F;
    public static float bHeight = 0.3F;
    public static void setModelScales(float scale, float height) {
        modelScale = scale;
        bHeight = height;
    }

    @SuppressWarnings("null")
    public PMM2_EndermanRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        this.getModel().bHeight = bHeight;
        this.getModel().useChildModel = isChildModel;
        this.getModel().flyFlap = doFlyFlap;
    }
    public static boolean isChildModel = false;
    public static boolean doFlyFlap = false;
    public static void setModelScales(float scale, float height, boolean isChild) {
        modelScale = scale;
        bHeight = height;
        isChildModel = isChild;
    }
    public static void setModelScales(float scale, float height, boolean isChild, boolean flyFlap) {
        modelScale = scale;
        bHeight = height;
        isChildModel = isChild;
        doFlyFlap = flyFlap;
    }


    

   @SuppressWarnings("null")
   public Vec3 getRenderOffset(T p_114336_, float p_114337_) {
      if (p_114336_.isCreepy()) {
         double d0 = 0.02D;
         return new Vec3(this.random.nextGaussian() * d0, 0.0D, this.random.nextGaussian() * d0);
      } else {
         return super.getRenderOffset(p_114336_, p_114337_);
      }
   }
}