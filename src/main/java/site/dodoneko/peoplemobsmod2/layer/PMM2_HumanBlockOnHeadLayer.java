package site.dodoneko.peoplemobsmod2.layer;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_HumanBlockOnHeadLayer<E extends Mob, M extends PMM2_HumanoidModel<E>> extends RenderLayer<E, M> {
    private final BlockRenderDispatcher blockRenderer;

    public PMM2_HumanBlockOnHeadLayer(RenderLayerParent<E, M> renderParent, BlockRenderDispatcher blockRenderer) {
        super(renderParent);
        this.blockRenderer = blockRenderer;
    }

    @SuppressWarnings({ "null", "deprecation" })
    public void render(PoseStack matrix, MultiBufferSource buffer, int i1, E entity, float f1, float f2, float f3,
            float f4, float f5, float f6) {
        if (entity.isBaby())
            return;
        Minecraft minecraft = Minecraft.getInstance();
        boolean invisible = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();
        if (!(!entity.isInvisible() || invisible))
            return;
        BlockState blockStatus = null;
        if (entity instanceof MushroomCow)
            blockStatus = ((MushroomCow) entity).getVariant().getBlockState();
        if (blockStatus == null)
            return;

        PMM2_HumanoidModel<E> model = (PMM2_HumanoidModel<E>) this.getParentModel();
        float modelScale = model.modelScale;
        int coords = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
        BakedModel bakedModel = this.blockRenderer.getBlockModel(blockStatus);

        matrix.pushPose();
        matrix.translate(0, (1F - modelScale) * 26F / 16F, 0);
        matrix.scale(modelScale, modelScale, modelScale);

        matrix.translate(model.pHead.x / 16F, model.pHead.y / 16F, model.pHead.z / 16F);
        matrix.mulPose((new Quaternionf()).rotationZYX(model.pHead.zRot, model.pHead.yRot, model.pHead.xRot));

        matrix.translate(0F, -8F / 16F, 0F);
        matrix.mulPose(Axis.XP.rotation(model.pHead.xRot * 0.4F));

        if (modelScale != 0F)
            matrix.scale(1F / modelScale, 1F / modelScale, 1F / modelScale);
        matrix.translate(0.25F, 0F, -0.25F);
        matrix.scale(-0.5F, -0.5F, 0.5F);

        if (invisible) {
            this.blockRenderer.getModelRenderer().renderModel(matrix.last(),
                    buffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockStatus, bakedModel, 0.0F,
                    0.0F, 0.0F, i1, coords);
        } else {
            this.blockRenderer.renderSingleBlock(blockStatus, matrix, buffer, i1, coords);
        }
        matrix.popPose();

    }
}