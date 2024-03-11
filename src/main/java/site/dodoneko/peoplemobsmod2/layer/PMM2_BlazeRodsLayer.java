package site.dodoneko.peoplemobsmod2.layer;

import java.util.Arrays;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;

/**
 * @see BlazeRenderer
 * @see net.minecraft.client.model.BlazeModel
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_BlazeRodsLayer extends RenderLayer<Blaze, PMM2_HumanoidModel<Blaze>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/blaze.png");
    public static final ModelLayerLocation PMM2_BLAZE_RODS_LAYER = new ModelLayerLocation(
            new ResourceLocation(PeopleMobsMod2.MODID, "blaze_rods_layer"), "main");

    private final PMM2_BlazeRodsLayer.PMM2_BlazeRodsModel model;

    @SuppressWarnings("null")
    public PMM2_BlazeRodsLayer(RenderLayerParent<Blaze, PMM2_HumanoidModel<Blaze>> renderParent,
            EntityModelSet modelSet) {
        super(renderParent);
        this.model = new PMM2_BlazeRodsLayer.PMM2_BlazeRodsModel(modelSet.bakeLayer(PMM2_BLAZE_RODS_LAYER));
        this.model.parentModel = this.getParentModel();
    }

    @SuppressWarnings("null")
    public void render(PoseStack matrix, MultiBufferSource buffer, int i3, Blaze entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getParentModel().copyPropertiesTo(this.model);

        if (entity.isInvisible()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean glowing = minecraft.shouldEntityAppearGlowing(entity);
            if (glowing) {
                this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, f4);
                this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
                        headPitch);
                VertexConsumer vertexconsumer = buffer
                        .getBuffer(RenderType.outline(getTextureLocation(entity)));
                this.model.renderToBuffer(matrix, vertexconsumer, i3,
                        LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 0.0F, 0.0F, 0.0F,
                        1.0F);
            }
        } else {
            renderRods(matrix, buffer, i3, entity, limbSwing, limbSwingAmount, f4, ageInTicks, netHeadYaw,
                    headPitch);
        }
    }

    @Override
    @SuppressWarnings("null")
    public ResourceLocation getTextureLocation(Blaze entity) {
        return TEXTURE;
    }

    @SuppressWarnings({ "null" })
    private void renderRods(PoseStack matrix, MultiBufferSource buffer, int i3, Blaze entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, getTextureLocation(entity), matrix,
                buffer, i3, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
                1f, 1f, 1f, 1f);
    }

    public class PMM2_BlazeRodsModel extends PMM2_HumanoidModel<Blaze> {
        public final ModelPart rodsParent;
        public final ModelPart[] rods;
        public PMM2_HumanoidModel<Blaze> parentModel;

        static String getRodsName(int i) {
            return "rod" + i;
        }

        @SuppressWarnings("null")
        public PMM2_BlazeRodsModel(ModelPart root) {
            super(root);

            this.rodsParent = root.getChild("rodsParent");
            this.rods = new ModelPart[12];
            Arrays.setAll(this.rods, (i) -> {
                return this.rodsParent.getChild(getRodsName(i));
            });
        }

        @SuppressWarnings("null")
        public static LayerDefinition createLayerModelParts() {
            float yOffset = 0F;
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();

            root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
            root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
            root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
            root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

            PartDefinition pHead = root.addOrReplaceChild("pHead", CubeListBuilder.create(), PartPose.ZERO);
            pHead.addOrReplaceChild("pFace", CubeListBuilder.create(), PartPose.ZERO);
            pHead.addOrReplaceChild("pFace_twinkled", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pBody = root.addOrReplaceChild("pBody", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pArmL = pBody.addOrReplaceChild("pArmL", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pArmR = pBody.addOrReplaceChild("pArmR", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pLegL = pBody.addOrReplaceChild("pLegL", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pLegR = pBody.addOrReplaceChild("pLegR", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pBUpper = pBody.addOrReplaceChild("pBUpper", CubeListBuilder.create(), PartPose.ZERO);
            PartDefinition pBLower = pBUpper.addOrReplaceChild("pBLower", CubeListBuilder.create(), PartPose.ZERO);

            pHead.addOrReplaceChild("pHeadWear", CubeListBuilder.create(), PartPose.ZERO);
            pBody.addOrReplaceChild("pBodyWear", CubeListBuilder.create(), PartPose.ZERO);
            pArmL.addOrReplaceChild("pArmLWear", CubeListBuilder.create(), PartPose.ZERO);
            pArmR.addOrReplaceChild("pArmRWear", CubeListBuilder.create(), PartPose.ZERO);
            pLegL.addOrReplaceChild("pLegLWear", CubeListBuilder.create(), PartPose.ZERO);
            pLegR.addOrReplaceChild("pLegRWear", CubeListBuilder.create(), PartPose.ZERO);
            pBUpper.addOrReplaceChild("pBUpperWear", CubeListBuilder.create(), PartPose.ZERO);
            pBLower.addOrReplaceChild("pBLowerWear", CubeListBuilder.create(), PartPose.ZERO);
            pHead.addOrReplaceChild("pAhoge", CubeListBuilder.create(), PartPose.ZERO);
            pHead.addOrReplaceChild("pKemomimi", CubeListBuilder.create(), PartPose.ZERO);
            pBody.addOrReplaceChild("pShippo", CubeListBuilder.create(), PartPose.ZERO);

            PartDefinition rodsParent = root.addOrReplaceChild("rodsParent", CubeListBuilder.create(), PartPose.ZERO);

            CubeListBuilder rodCubeBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F,
                    8.0F, 2.0F);
            float rodsRotateDist = 0.0F;

            for (int i = 0; i < 4; ++i) {
                float xOff = PMath.cos(rodsRotateDist) * 9.0F;
                float yOff = -2.0F + PMath.cos((float) i * 2 * 0.25F);
                float zOff = PMath.sin(rodsRotateDist) * 9.0F;
                rodsParent.addOrReplaceChild(getRodsName(i), rodCubeBuilder,
                        PartPose.offset(xOff, yOff + yOffset, zOff));
                ++rodsRotateDist;
            }

            rodsRotateDist = PMath.toRad(45F);

            for (int i = 4; i < 8; ++i) {
                float xOff = PMath.cos(rodsRotateDist) * 7.0F;
                float yOff = 2.0F + PMath.cos((float) i * 2 * 0.25F);
                float zOff = PMath.sin(rodsRotateDist) * 7.0F;
                rodsParent.addOrReplaceChild(getRodsName(i), rodCubeBuilder,
                        PartPose.offset(xOff, yOff + yOffset, zOff));
                ++rodsRotateDist;
            }

            rodsRotateDist = PMath.toRad(27F);

            for (int i = 8; i < 12; ++i) {
                float xOff = PMath.cos(rodsRotateDist) * 5.0F;
                float yOff = 11.0F + PMath.cos((float) i * 1.5F * 0.5F);
                float zOff = PMath.sin(rodsRotateDist) * 5.0F;
                rodsParent.addOrReplaceChild(getRodsName(i), rodCubeBuilder,
                        PartPose.offset(xOff, yOff + yOffset, zOff));
                ++rodsRotateDist;
            }

            return LayerDefinition.create(mesh, 64, 32);
        }

        @Override
        @SuppressWarnings("null")
        protected Iterable<ModelPart> bodyParts() {
            return ImmutableList.of(this.pBody, this.rodsParent);
        }

        @Override
        public void resetPartsPosAndRot() {
            super.resetPartsPosAndRot();
        }

        public void setupAnim(Blaze entity, float swing, float swingAmount, float age, float headRotY,
                float headRotX) {
            super.setupAnim(entity, swing, swingAmount, age, headRotY, headRotX);
        }

        @Override
        protected void setPostAnimations() {
            float parentModelScale = this.parentModel.modelScale + (this.entityId % 628752F / 628752F
                    + this.entityId % 199872F / 199872F + this.entityId % 36872F / 36872F) / 3 * 2F / 16F;
            float antiScale = 1f / PMath.max(0.01F, parentModelScale);
            float f = this.ageInTicks * PMath.PI * -0.1F;

            this.rodsParent.xScale = this.rodsParent.yScale = this.rodsParent.zScale = antiScale;
            this.rodsParent.y = -24F * (antiScale - 1F);

            for (int i = 0; i < 4; ++i) {
                this.rods[i].y = -2.0F + PMath.cos(((float) (i * 2) + this.ageInTicks) * 0.25F);
                this.rods[i].x = PMath.cos(f) * 9.0F;
                this.rods[i].z = PMath.sin(f) * 9.0F;
                ++f;
            }

            f = ((float) Math.PI / 4F) + this.ageInTicks * (float) Math.PI * 0.03F;

            for (int i = 4; i < 8; ++i) {
                this.rods[i].y = 2.0F + PMath.cos(((float) (i * 2) + this.ageInTicks) * 0.25F);
                this.rods[i].x = PMath.cos(f) * 7.0F;
                this.rods[i].z = PMath.sin(f) * 7.0F;
                ++f;
            }

            f = 0.47123894F + this.ageInTicks * (float) Math.PI * -0.05F;

            for (int i = 8; i < 12; ++i) {
                this.rods[i].y = 11.0F + PMath.cos(((float) i * 1.5F + this.ageInTicks) * 0.5F);
                this.rods[i].x = PMath.cos(f) * 5.0F;
                this.rods[i].z = PMath.sin(f) * 5.0F;
                ++f;
            }
        }
    }
}