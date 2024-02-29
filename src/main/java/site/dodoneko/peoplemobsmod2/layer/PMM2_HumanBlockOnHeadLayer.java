package site.dodoneko.peoplemobsmod2.layer;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

// TODO: change block pos to on the head.

@OnlyIn(Dist.CLIENT)
public class PMM2_HumanBlockOnHeadLayer<E extends Mob, M extends PMM2_HumanoidModel<E> & ArmedModel>
        extends RenderLayer<E, M> {
    private final BlockRenderDispatcher blockRenderer;

    public PMM2_HumanBlockOnHeadLayer(RenderLayerParent<E, M> renderParent, BlockRenderDispatcher blockRenderer) {
        super(renderParent);
        this.blockRenderer = blockRenderer;
    }

    @SuppressWarnings("null")
    public void render(PoseStack matrix, MultiBufferSource buffer, int i1, E entity, float f1, float f2, float f3,
            float f4, float f5, float f6) {

        matrix.pushPose();
        if (this.getParentModel().young) {
            float f = 0.5F;
            matrix.translate(0.0F, 0.75F, 0.0F);
            matrix.scale(f, f, f);
        }

        this.renderArmWithItem(entity, matrix, buffer, i1);

        matrix.popPose();
    }

    @SuppressWarnings({ "null", "deprecation" })
    protected void renderArmWithItem(E entity, PoseStack matrix, MultiBufferSource buffer, int i1) {
        BlockState blockState = null;

        if (entity instanceof EnderMan) {
            blockState = ((EnderMan) entity).getCarriedBlock();
        }

        if (blockState == null) return;

        PMM2_HumanoidModel<E> model = (PMM2_HumanoidModel<E>) this.getParentModel();
        float modelScale = model.modelScale;

        matrix.pushPose();
        matrix.translate(0, (1F - modelScale) * 26F / 16F, 0);
        matrix.translate(0.4F, 0, 0);
        matrix.scale(modelScale, modelScale, modelScale);

        matrix.translate(model.pBody.x / 16F, model.pBody.y / 16F, model.pBody.z / 16F);
        matrix.mulPose((new Quaternionf()).rotationZYX(model.pBody.zRot, model.pBody.yRot, model.pBody.xRot));

        matrix.translate(0 / 16F, model.pArmR.y / 16F, model.pArmR.z / 16F);
        matrix.mulPose((new Quaternionf()).rotationZYX(0, 0, (model.pArmR.xRot+model.pArmL.xRot)/2));

        matrix.translate(0, 0.95F, 0);

        if (modelScale != 0F) matrix.scale(1F/modelScale, 1F/modelScale, 1F/modelScale);
        matrix.scale(-0.5F, -0.5F, 0.5F);

        matrix.mulPose(Axis.XP.rotation(model.pArmR.xRot));
        matrix.mulPose(Axis.XP.rotation(model.pBody.xRot));

        matrix.translate(0, 0.01F, 0);

        // matrix.mulPose(Axis.XP.rotationDegrees(20.0F));
        matrix.mulPose(Axis.YP.rotationDegrees(45.0F));
        this.blockRenderer.renderSingleBlock(blockState, matrix, buffer, i1, OverlayTexture.NO_OVERLAY);

        matrix.popPose();

    }
}