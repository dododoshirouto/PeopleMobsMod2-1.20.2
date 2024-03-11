package site.dodoneko.peoplemobsmod2.layer;

import java.util.EnumSet;
import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.definitions.FrogAnimation;
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
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import site.dodoneko.peoplemobsmod2.model.PMM2_FrogModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_FrogTongueLayer extends RenderLayer<Frog, PMM2_FrogModel> {

    @SuppressWarnings("null")
    private static final Map<FrogVariant, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(),
            (entity) -> {
                entity.put(FrogVariant.TEMPERATE,
                        new ResourceLocation("textures/entity/frog/temperate_frog.png"));
                entity.put(FrogVariant.COLD,
                        new ResourceLocation("textures/entity/frog/cold_frog.png"));
                entity.put(FrogVariant.WARM,
                        new ResourceLocation("textures/entity/frog/warm_frog.png"));
            });
    public static final ModelLayerLocation PMM2_FROG_TONGUE_LAYER = new ModelLayerLocation(
            new ResourceLocation(PeopleMobsMod2.MODID, "frog_tongue_layer"), "main");

    private final PMM2_FrogTongueLayer.PMM2_FrogTongueModel model;

    @SuppressWarnings("null")
    public PMM2_FrogTongueLayer(RenderLayerParent<Frog, PMM2_FrogModel> renderParent,
            EntityModelSet modelSet) {
        super(renderParent);
        this.model = new PMM2_FrogTongueLayer.PMM2_FrogTongueModel(
                modelSet.bakeLayer(PMM2_FROG_TONGUE_LAYER));
        setTextureMaps();
    }

    @SuppressWarnings("null")
    public void render(PoseStack matrix, MultiBufferSource buffer, int i3, Frog entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getParentModel().copyPropertiesTo(this.model);

        if (entity.isInvisible()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean glowing = minecraft.shouldEntityAppearGlowing(entity);
            if (glowing) {
                this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, f4);
                this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(getTextureLocation(entity)));
                this.model.renderToBuffer(matrix, vertexconsumer, i3,
                        LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 0.0F, 0.0F, 0.0F, 1.0F);
            }
        } else {
            renderTongue(matrix, buffer, i3, entity, limbSwing, limbSwingAmount, f4, ageInTicks, netHeadYaw, headPitch);
        }
    }

    private static void setTextureMaps() {

    }

    @Override
    @SuppressWarnings("null")
    public ResourceLocation getTextureLocation(Frog entity) {
        return TEXTURES.get(entity.getVariant());
    }

    @SuppressWarnings({ "null" })
    private void renderTongue(PoseStack matrix, MultiBufferSource buffer, int i3, Frog entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, getTextureLocation(entity), matrix,
                buffer, i3, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
                1f, 1f, 1f, 1f);
    }

    public class PMM2_FrogTongueModel extends PMM2_HumanoidModel<Frog> {
        public final ModelPart frogTongue;

        public PMM2_FrogTongueModel(ModelPart root) {
            super(root);

            this.frogTongue = this.pHead.getChild("frogTongue");
        }

        @SuppressWarnings("null")
        public static LayerDefinition createFrogTongueLayer() {
            float yOffset = 0F;
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();

            root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));

            PartDefinition pHead = root.addOrReplaceChild("pHead", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pHead.addOrReplaceChild("pFace", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pHead.addOrReplaceChild("pFace_twinkled", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pBody = root.addOrReplaceChild("pBody", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pArmL = pBody.addOrReplaceChild("pArmL", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pArmR = pBody.addOrReplaceChild("pArmR", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pLegL = pBody.addOrReplaceChild("pLegL", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pLegR = pBody.addOrReplaceChild("pLegR", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pBUpper = pBody.addOrReplaceChild("pBUpper", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            PartDefinition pBLower = pBUpper.addOrReplaceChild("pBLower", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));

            pHead.addOrReplaceChild("pHeadWear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pBody.addOrReplaceChild("pBodyWear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pArmL.addOrReplaceChild("pArmLWear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pArmR.addOrReplaceChild("pArmRWear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pLegL.addOrReplaceChild("pLegLWear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pLegR.addOrReplaceChild("pLegRWear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pBUpper.addOrReplaceChild("pBUpperWear", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pBLower.addOrReplaceChild("pBLowerWear", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pHead.addOrReplaceChild("pAhoge", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pHead.addOrReplaceChild("pKemomimi", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pBody.addOrReplaceChild("pShippo", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));

            pHead.addOrReplaceChild("frogTongue",
                    CubeListBuilder.create().texOffs(24, 13).addBox(-2F, -0.5F, -7F, 4, 0, 7,
                            EnumSet.of(Direction.UP)),
                    PartPose.offset(0, yOffset - 0.5F, -4F));

            return LayerDefinition.create(mesh, 48, 48);
        }

        @Override
        public void resetPartsPosAndRot() {
            super.resetPartsPosAndRot();

            this.frogTongue.setPos(0, -0.5F, -4);
            this.frogTongue.setRotation(0, PMath.toRad(180), 0);
            this.frogTongue.zScale = 0F;
            this.frogTongue.xScale = 1F;
        }

        public void setupAnim(Frog entity, float swing, float swingAmount, float age, float headRotY,
                float headRotX) {
            // this.animate(p_233372_.jumpAnimationState, FrogAnimation.FROG_JUMP, p_233375_);
            // this.animate(p_233372_.croakAnimationState, FrogAnimation.FROG_CROAK, p_233375_);
            this.animate(entity.tongueAnimationState, FrogAnimation.FROG_TONGUE, age);
            super.setupAnim(entity, swing, swingAmount, age, headRotY, headRotX);
        }

        protected void animate(AnimationState p_233386_, AnimationDefinition p_233387_, float p_233388_) {
            p_233386_.updateTime(p_233388_, 1);
        }

        @Override
        protected void setPostAnimations() {
            if (this.entity.tongueAnimationState.isStarted()) {
                // tongue
                float tongueAnimAge = PMath.clamp(this.entity.tongueAnimationState.getAccumulatedTime() / 600F, 0, 1);
                this.frogTongue.zScale = PMath.lerp(PMath.easeOut(tongueAnimAge), 0F, 5F);
                this.frogTongue.xScale = PMath.lerp(PMath.easeIn(tongueAnimAge), 1F, 0.5F);
            }
        }
    }
}