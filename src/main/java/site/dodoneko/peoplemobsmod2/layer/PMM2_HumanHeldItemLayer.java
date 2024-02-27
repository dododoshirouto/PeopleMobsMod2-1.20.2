package site.dodoneko.peoplemobsmod2.layer;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_HumanHeldItemLayer<E extends Mob, M extends PMM2_HumanoidModel<E> & ArmedModel>
        extends RenderLayer<E, M> {
    private final ItemInHandRenderer itemRenderer;

    public PMM2_HumanHeldItemLayer(RenderLayerParent<E, M> renderParent, ItemInHandRenderer itemRenderer) {
        super(renderParent);
        this.itemRenderer = itemRenderer;
    }

    @SuppressWarnings("null")
    public void render(PoseStack matrix, MultiBufferSource buffer, int i1, E entity, float f1, float f2, float f3,
            float f4, float f5, float f6) {
        boolean isMainR = entity.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemStackMain = isMainR ? entity.getOffhandItem() : entity.getMainHandItem();
        ItemStack itemStackOff = isMainR ? entity.getMainHandItem() : entity.getOffhandItem();
        if (itemStackMain.isEmpty() && itemStackOff.isEmpty())
            return;

        matrix.pushPose();
        if (this.getParentModel().young) {
            float f = 0.5F;
            matrix.translate(0.0F, 0.75F, 0.0F);
            matrix.scale(f, f, f);
        }

        this.renderArmWithItem(entity, itemStackOff, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT,
                matrix, buffer, i1);
        this.renderArmWithItem(entity, itemStackMain, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT,
                matrix,
                buffer, i1);

        matrix.popPose();
    }

    @SuppressWarnings("null")
    protected void renderArmWithItem(E entity, ItemStack itemStack, ItemDisplayContext itemDisp,
            HumanoidArm arm, PoseStack matrix, MultiBufferSource buffer, int i1) {
        if (itemStack.isEmpty())
            return;

        PMM2_HumanoidModel<E> model = (PMM2_HumanoidModel<E>) this.getParentModel();
        float modelScale = model.modelScale;

        matrix.pushPose();
        matrix.translate(0, (1F - modelScale) * 26F / 16F, 0);
        matrix.scale(modelScale, modelScale, modelScale);

        matrix.translate(model.pBody.z / 16F, model.pBody.y / 16F, model.pBody.x / 16F);
        matrix.mulPose((new Quaternionf()).rotationZYX(model.pBody.zRot, model.pBody.yRot, model.pBody.xRot));

        matrix.translate(model.pArmR.z / 16F, model.pArmR.y / 16F, model.pArmR.x / 16F);
        matrix.mulPose((new Quaternionf()).rotationZYX(model.pArmR.zRot, model.pArmR.yRot, model.pArmR.xRot));

        // this.getParentModel().translateToHand(arm, matrix);
        matrix.mulPose(Axis.XP.rotationDegrees(-90.0F));
        matrix.mulPose(Axis.YP.rotationDegrees(180.0F));
        boolean flag = arm == HumanoidArm.LEFT;

        matrix.translate((float) (flag ? -1F : 1F) / 16.0F, 0.03F, -0.625F);
        // matrix.translate((float) (flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
        // matrix.mulPose((new Quaternionf()).rotationZYX(PMath.toRad(-10F), PMath.toRad(-35F), 10F));

        if (modelScale != 0F) matrix.scale(1F/modelScale, 1F/modelScale, 1F/modelScale);
        this.itemRenderer.renderItem(entity, itemStack, itemDisp, flag, matrix, buffer, i1);

        matrix.popPose();

    }
}