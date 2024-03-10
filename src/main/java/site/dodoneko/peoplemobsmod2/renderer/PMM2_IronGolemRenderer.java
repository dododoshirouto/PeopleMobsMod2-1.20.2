package site.dodoneko.peoplemobsmod2.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

/**
 * @see net.minecraft.client.model.IronGolemModel
 * @see net.minecraft.client.renderer.entity.IronGolemRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_IronGolemRenderer extends PMM2_HumanoidMobRenderer<IronGolem, PMM2_HumanoidModel<IronGolem>> {

    @SuppressWarnings("null")
    public PMM2_IronGolemRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_TWINKLED_HUMANOID_LAYER)), modelScale);
    }

   @SuppressWarnings("null")
   protected void setupRotations(IronGolem entity, PoseStack matrix, float p1, float p2, float partialTick) {
      super.setupRotations(entity, matrix, p1, p2, partialTick);
      if (!((double)entity.walkAnimation.speed() < 0.01D)) {
         float f1 = entity.walkAnimation.position(partialTick) + 6.0F;
         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
         matrix.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
      }
   }
}