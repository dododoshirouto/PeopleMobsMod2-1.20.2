package site.dodoneko.peoplemobsmod2.base;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import java.util.function.Function;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;

@OnlyIn(Dist.CLIENT)
public class PMM2_HumanoidModel<T extends Mob> extends HumanoidModel<T> {
    // public static final float OVERLAY_SCALE = 0.25F;
    // public static final float HAT_OVERLAY_SCALE = 0.5F;
    // public static final float LEGGINGS_OVERLAY_SCALE = -0.1F;
    // private static final float DUCK_WALK_ROTATION = 0.005F;
    // private static final float SPYGLASS_ARM_ROT_Y = 0.2617994F;
    // private static final float SPYGLASS_ARM_ROT_X = 1.9198622F;
    // private static final float SPYGLASS_ARM_CROUCH_ROT_X = 0.2617994F;
    // public static final float TOOT_HORN_XROT_BASE = 1.4835298F;
    // public static final float TOOT_HORN_YROT_BASE = ((float)Math.PI / 6F);
    // public final ModelPart head;
    // public final ModelPart hat;
    // public final ModelPart body;
    // public final ModelPart rightArm;
    // public final ModelPart leftArm;
    // public final ModelPart rightLeg;
    // public final ModelPart leftLeg;
    public final ModelPart pHead;
    public final ModelPart pBody;
    public final ModelPart pArmL;
    public final ModelPart pArmR;
    public final ModelPart pLegL;
    public final ModelPart pLegR;
    public final ModelPart pBUpper;
    public final ModelPart pBLower;
    public final ModelPart pHeadWear;
    public final ModelPart pBodyWear;
    public final ModelPart pArmLWear;
    public final ModelPart pArmRWear;
    public final ModelPart pLegLWear;
    public final ModelPart pLegRWear;
    public final ModelPart pBUpperWear;
    public final ModelPart pBLowerWear;
    public final ModelPart pAhoge;
    public final ModelPart pKemomimi;
    public final ModelPart pShippo;
    public final ModelPart pEyebrowL;
    public final ModelPart pEyebrowR;
    public PMM2_HumanoidModel.ArmPose leftArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
    public PMM2_HumanoidModel.ArmPose rightArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
    // public boolean crouching;
    // public float swimAmount;

    // AgeableListModel
    public final boolean scaleHead;
    public final float babyYHeadOffset;
    public final float babyZHeadOffset;
    public final float babyHeadScale;
    public final float babyBodyScale;
    public final float bodyYOffset;

    public PMM2_HumanoidModel(ModelPart root) {
        this(root, RenderType::entityCutoutNoCull);
    }

    public PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc) {
        super(root, renderFunc);

        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc)");

        // this.head = root.getChild("head");
        // this.hat = root.getChild("hat");
        // this.body = root.getChild("body");
        // this.rightArm = root.getChild("right_arm");
        // this.leftArm = root.getChild("left_arm");
        // this.rightLeg = root.getChild("right_leg");
        // this.leftLeg = root.getChild("left_leg");

        this.pHead = root.getChild("pHead");
        this.pBody = root.getChild("pBody");
        this.pArmL = null;
        this.pArmR = null;
        this.pLegL = null;
        this.pLegR = null;
        this.pBUpper = null;
        this.pBLower = null;
        // this.pHeadWear = null;
        // this.pBodyWear = null;
        this.pArmLWear = null;
        this.pArmRWear = null;
        this.pLegLWear = null;
        this.pLegRWear = null;
        this.pBUpperWear = null;
        this.pBLowerWear = null;
        this.pAhoge = null;
        this.pKemomimi = null;
        this.pShippo = null;
        this.pEyebrowL = null;
        this.pEyebrowR = null;
        // this.pArmL = pBody.getChild("pArmL");
        // this.pArmR = pBody.getChild("pArmR");
        // this.pLegL = pBody.getChild("pLegL");
        // this.pLegR = pBody.getChild("pLegR");
        // this.pBUpper = pBody.getChild("pBUpper");
        // this.pBLower = pBody.getChild("pBLower");
        this.pHeadWear = pHead.getChild("pHeadWear");
        this.pBodyWear = pBody.getChild("pBodyWear");
        // this.pArmLWear = pArmL.getChild("pArmLWear");
        // this.pArmRWear = pArmR.getChild("pArmRWear");
        // this.pLegLWear = pLegL.getChild("pLegLWear");
        // this.pLegRWear = pLegR.getChild("pLegRWear");
        // this.pBUpperWear = pBUpper.getChild("pBUpperWear");
        // this.pBLowerWear = pBLower.getChild("pBLowerWear");
        // this.pAhoge = pHead.getChild("pAhoge");
        // this.pKemomimi = pHead.getChild("pKemomimi");
        // this.pShippo = pBody.getChild("pShippo");
        // this.pEyebrowL = pHead.getChild("pEyebrowL");
        // this.pEyebrowR = pHead.getChild("pEyebrowR");

        // AgeableListModel
        this.scaleHead = true;
        this.babyYHeadOffset = 16.0F;
        this.babyZHeadOffset = 0.0F;
        this.babyHeadScale = 2.0F;
        this.babyBodyScale = 2.0F;
        this.bodyYOffset = 24.0F;
    }

    @SuppressWarnings({ "null", "unused" })
    public static LayerDefinition createBodyLayer() {
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel.createBodyLayer()");
        return LayerDefinition.create(createMesh(new CubeDeformation(0.0F), 0), 64, 64);
    }

    @SuppressWarnings({ "null", "unused" })
    public static MeshDefinition createMesh(CubeDeformation cube, float yOffset) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel.createMesh()");

        PartDefinition head = root.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cube),
                PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F,
                8.0F, 8.0F, 8.0F, cube.extend(0.5F)), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, cube),
                PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cube),
                PartPose.offset(-5.0F, 2.0F + yOffset, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F,
                -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cube), PartPose.offset(5.0F, 2.0F + yOffset, 0.0F));
        root.addOrReplaceChild("right_leg",
                CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cube),
                PartPose.offset(-1.9F, 12.0F + yOffset, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F,
                0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cube), PartPose.offset(1.9F, 12.0F + yOffset, 0.0F));

        PartDefinition pHead = root.addOrReplaceChild("pHead",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8, cube), PartPose.offset(0, yOffset, 0));
        PartDefinition pBody = root.addOrReplaceChild("pBody",
                CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, cube),
                PartPose.offset(0, yOffset, 0));
        PartDefinition pArmL = pBody.addOrReplaceChild("pArmL", CubeListBuilder.create(),
                PartPose.offset(0, 0 + yOffset, 0));
        PartDefinition pArmR = pBody.addOrReplaceChild("pArmR", CubeListBuilder.create(),
                PartPose.offset(0, 0 + yOffset, 0));
        PartDefinition pLegL = pBody.addOrReplaceChild("pLegL", CubeListBuilder.create(),
                PartPose.offset(0, 0 + yOffset, 0));
        PartDefinition pLegR = pBody.addOrReplaceChild("pLegR", CubeListBuilder.create(),
                PartPose.offset(0, 0 + yOffset, 0));
        PartDefinition pBUpper = pBody.addOrReplaceChild("pBUpper", CubeListBuilder.create(),
                PartPose.offset(0, 0 + yOffset, 0));
        PartDefinition pBLower = pBody.addOrReplaceChild("pBLower", CubeListBuilder.create(),
                PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pHeadWear",
                CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, cube.extend(0.5F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pBody.addOrReplaceChild("pBodyWear",
                CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, cube.extend(0.45F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pArmL.addOrReplaceChild("pArmLWear", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pArmR.addOrReplaceChild("pArmRWear", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pLegL.addOrReplaceChild("pLegLWear", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pLegR.addOrReplaceChild("pLegRWear", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pBUpper.addOrReplaceChild("pBUpperWear", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pBLower.addOrReplaceChild("pBLowerWear", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pAhoge", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pKemomimi", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pBody.addOrReplaceChild("pShippo", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pEyebrowL", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pEyebrowR", CubeListBuilder.create(), PartPose.offset(0, 0 + yOffset, 0));

        return mesh;
    }

    @SuppressWarnings("null")
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.pHead);
    }

    @SuppressWarnings("null")
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.pBody);
    }

    public void prepareMobModel(@SuppressWarnings("null") T entity, float p_102862_, float p_102863_, float p_102864_) {
        this.swimAmount = entity.getSwimAmount(p_102864_);
        super.prepareMobModel(entity, p_102862_, p_102863_, p_102864_);
    }

    @SuppressWarnings("null")
    @Override
    public void setupAnim(T entity, float swingTime_, float swingScale_, float p_102869_, float headYRotation,
            float headXRotation) {
        // boolean flag = entity.getFallFlyingTicks() > 4;
        // boolean flag1 = entity.isVisuallySwimming();
        // this.head.yRot = headYRotation * ((float) Math.PI / 180F);
        // if (flag) {
        // this.head.xRot = (-(float) Math.PI / 4F);
        // } else if (this.swimAmount > 0.0F) {
        // if (flag1) {
        // this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float)
        // Math.PI / 4F));
        // } else {
        // this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot,
        // headXRotation * ((float) Math.PI / 180F));
        // }
        // } else {
        // this.head.xRot = headXRotation * ((float) Math.PI / 180F);
        // }

        // this.body.yRot = 0.0F;
        // this.rightArm.z = 0.0F;
        // this.rightArm.x = -5.0F;
        // this.leftArm.z = 0.0F;
        // this.leftArm.x = 5.0F;
        // float f = 1.0F;
        // if (flag) {
        // f = (float) entity.getDeltaMovement().lengthSqr();
        // f /= 0.2F;
        // f *= f * f;
        // }

        // if (f < 1.0F) {
        // f = 1.0F;
        // }

        // this.rightArm.xRot = Mth.cos(swingTime_ * 0.6662F + (float) Math.PI) * 2.0F *
        // swingScale_ * 0.5F / f;
        // this.leftArm.xRot = Mth.cos(swingTime_ * 0.6662F) * 2.0F * swingScale_ * 0.5F
        // / f;
        // this.rightArm.zRot = 0.0F;
        // this.leftArm.zRot = 0.0F;
        // this.rightLeg.xRot = Mth.cos(swingTime_ * 0.6662F) * 1.4F * swingScale_ / f;
        // this.leftLeg.xRot = Mth.cos(swingTime_ * 0.6662F + (float) Math.PI) * 1.4F *
        // swingScale_ / f;
        // this.rightLeg.yRot = 0.005F;
        // this.leftLeg.yRot = -0.005F;
        // this.rightLeg.zRot = 0.005F;
        // this.leftLeg.zRot = -0.005F;
        // if (this.riding) {
        // this.rightArm.xRot += (-(float) Math.PI / 5F);
        // this.leftArm.xRot += (-(float) Math.PI / 5F);
        // this.rightLeg.xRot = -1.4137167F;
        // this.rightLeg.yRot = ((float) Math.PI / 10F);
        // this.rightLeg.zRot = 0.07853982F;
        // this.leftLeg.xRot = -1.4137167F;
        // this.leftLeg.yRot = (-(float) Math.PI / 10F);
        // this.leftLeg.zRot = -0.07853982F;
        // }

        // this.rightArm.yRot = 0.0F;
        // this.leftArm.yRot = 0.0F;
        // boolean flag2 = entity.getMainArm() == HumanoidArm.RIGHT;
        // if (entity.isUsingItem()) {
        // boolean flag3 = entity.getUsedItemHand() == InteractionHand.MAIN_HAND;
        // if (flag3 == flag2) {
        // this.poseRightArm(entity);
        // } else {
        // this.poseLeftArm(entity);
        // }
        // } else {
        // boolean flag4 = flag2 ? this.leftArmPose.isTwoHanded() :
        // this.rightArmPose.isTwoHanded();
        // if (flag2 != flag4) {
        // this.poseLeftArm(entity);
        // this.poseRightArm(entity);
        // } else {
        // this.poseRightArm(entity);
        // this.poseLeftArm(entity);
        // }
        // }

        // this.setupAttackAnimation(entity, p_102869_);
        // if (this.crouching) {
        // this.body.xRot = 0.5F;
        // this.rightArm.xRot += 0.4F;
        // this.leftArm.xRot += 0.4F;
        // this.rightLeg.z = 4.0F;
        // this.leftLeg.z = 4.0F;
        // this.rightLeg.y = 12.2F;
        // this.leftLeg.y = 12.2F;
        // this.head.y = 4.2F;
        // this.body.y = 3.2F;
        // this.leftArm.y = 5.2F;
        // this.rightArm.y = 5.2F;
        // } else {
        // this.body.xRot = 0.0F;
        // this.rightLeg.z = 0.0F;
        // this.leftLeg.z = 0.0F;
        // this.rightLeg.y = 12.0F;
        // this.leftLeg.y = 12.0F;
        // this.head.y = 0.0F;
        // this.body.y = 0.0F;
        // this.leftArm.y = 2.0F;
        // this.rightArm.y = 2.0F;
        // }

        // if (this.rightArmPose != PMM2_HumanoidModel.ArmPose.SPYGLASS) {
        // AnimationUtils.bobModelPart(this.rightArm, p_102869_, 1.0F);
        // }

        // if (this.leftArmPose != PMM2_HumanoidModel.ArmPose.SPYGLASS) {
        // AnimationUtils.bobModelPart(this.leftArm, p_102869_, -1.0F);
        // }

        // if (this.swimAmount > 0.0F) {
        // float f5 = swingTime_ % 26.0F;
        // HumanoidArm humanoidarm = this.getAttackArm(entity);
        // float f1 = humanoidarm == HumanoidArm.RIGHT && this.attackTime > 0.0F ? 0.0F
        // : this.swimAmount;
        // float f2 = humanoidarm == HumanoidArm.LEFT && this.attackTime > 0.0F ? 0.0F :
        // this.swimAmount;
        // if (!entity.isUsingItem()) {
        // if (f5 < 14.0F) {
        // this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, 0.0F);
        // this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, 0.0F);
        // this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float) Math.PI);
        // this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float) Math.PI);
        // this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, (float) Math.PI
        // + 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
        // this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, (float) Math.PI
        // - 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
        // } else if (f5 >= 14.0F && f5 < 22.0F) {
        // float f6 = (f5 - 14.0F) / 8.0F;
        // this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, ((float) Math.PI /
        // 2F) * f6);
        // this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, ((float) Math.PI / 2F)
        // * f6);
        // this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float) Math.PI);
        // this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float) Math.PI);
        // this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, 5.012389F -
        // 1.8707964F * f6);
        // this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, 1.2707963F + 1.8707964F
        // * f6);
        // } else if (f5 >= 22.0F && f5 < 26.0F) {
        // float f3 = (f5 - 22.0F) / 4.0F;
        // this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot,
        // ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * f3);
        // this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot,
        // ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * f3);
        // this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float) Math.PI);
        // this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float) Math.PI);
        // this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, (float) Math.PI);
        // this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, (float) Math.PI);
        // }
        // }

        // this.leftLeg.xRot = Mth.lerp(this.swimAmount, this.leftLeg.xRot,
        // 0.3F * Mth.cos(swingTime_ * 0.33333334F + (float) Math.PI));
        // this.rightLeg.xRot = Mth.lerp(this.swimAmount, this.rightLeg.xRot,
        // 0.3F * Mth.cos(swingTime_ * 0.33333334F));
        // }

        // this.hat.copyFrom(this.head);
    }

    @SuppressWarnings("null")
    private void poseRightArm(T entity) {
        switch (this.rightArmPose) {
            case EMPTY:
                this.rightArm.yRot = 0.0F;
                break;
            case BLOCK:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424779F;
                this.rightArm.yRot = (-(float) Math.PI / 6F);
                break;
            case ITEM:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - ((float) Math.PI / 10F);
                this.rightArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
                this.rightArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot;
                this.leftArm.yRot = 0.1F + this.head.yRot + 0.4F;
                this.rightArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
                this.leftArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, entity, true);
                break;
            case CROSSBOW_HOLD:
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
                break;
            case BRUSH:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - ((float) Math.PI / 5F);
                this.rightArm.yRot = 0.0F;
                break;
            case SPYGLASS:
                this.rightArm.xRot = Mth.clamp(
                        this.head.xRot - 1.9198622F - (entity.isCrouching() ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.rightArm.yRot = this.head.yRot - 0.2617994F;
                break;
            case TOOT_HORN:
                this.rightArm.xRot = Mth.clamp(this.head.xRot, -1.2F, 1.2F) - 1.4835298F;
                this.rightArm.yRot = this.head.yRot - ((float) Math.PI / 6F);
            default:
                this.rightArmPose.applyTransform(this, entity, net.minecraft.world.entity.HumanoidArm.RIGHT);
        }

    }

    @SuppressWarnings("null")
    private void poseLeftArm(T entity) {
        switch (this.leftArmPose) {
            case EMPTY:
                this.leftArm.yRot = 0.0F;
                break;
            case BLOCK:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.9424779F;
                this.leftArm.yRot = ((float) Math.PI / 6F);
                break;
            case ITEM:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - ((float) Math.PI / 10F);
                this.leftArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI;
                this.leftArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot - 0.4F;
                this.leftArm.yRot = 0.1F + this.head.yRot;
                this.rightArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
                this.leftArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, entity, false);
                break;
            case CROSSBOW_HOLD:
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, false);
                break;
            case BRUSH:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - ((float) Math.PI / 5F);
                this.leftArm.yRot = 0.0F;
                break;
            case SPYGLASS:
                this.leftArm.xRot = Mth.clamp(
                        this.head.xRot - 1.9198622F - (entity.isCrouching() ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.leftArm.yRot = this.head.yRot + 0.2617994F;
                break;
            case TOOT_HORN:
                this.leftArm.xRot = Mth.clamp(this.head.xRot, -1.2F, 1.2F) - 1.4835298F;
                this.leftArm.yRot = this.head.yRot + ((float) Math.PI / 6F);
            default:
                this.leftArmPose.applyTransform(this, entity, net.minecraft.world.entity.HumanoidArm.LEFT);
        }

    }

    protected void setupAttackAnimation(@SuppressWarnings("null") T entity, float p_102859_) {
        // if (!(this.attackTime <= 0.0F)) {
        // HumanoidArm humanoidarm = this.getAttackArm(entity);
        // ModelPart modelpart = this.getArm(humanoidarm);
        // float f = this.attackTime;
        // this.body.yRot = Mth.sin(Mth.sqrt(f) * ((float) Math.PI * 2F)) * 0.2F;
        // if (humanoidarm == HumanoidArm.LEFT) {
        // this.body.yRot *= -1.0F;
        // }

        // this.rightArm.z = Mth.sin(this.body.yRot) * 5.0F;
        // this.rightArm.x = -Mth.cos(this.body.yRot) * 5.0F;
        // this.leftArm.z = -Mth.sin(this.body.yRot) * 5.0F;
        // this.leftArm.x = Mth.cos(this.body.yRot) * 5.0F;
        // this.rightArm.yRot += this.body.yRot;
        // this.leftArm.yRot += this.body.yRot;
        // this.leftArm.xRot += this.body.yRot;
        // f = 1.0F - this.attackTime;
        // f *= f;
        // f *= f;
        // f = 1.0F - f;
        // float f1 = Mth.sin(f * (float) Math.PI);
        // float f2 = Mth.sin(this.attackTime * (float) Math.PI) * -(this.head.xRot -
        // 0.7F) * 0.75F;
        // modelpart.xRot -= f1 * 1.2F + f2;
        // modelpart.yRot += this.body.yRot * 2.0F;
        // modelpart.zRot += Mth.sin(this.attackTime * (float) Math.PI) * -0.4F;
        // }
    }

    protected float rotlerpRad(float time, float rotA, float rotB) {
        float f = (rotB - rotA) % ((float) Math.PI * 2F);
        if (f < -(float) Math.PI) {
            f += ((float) Math.PI * 2F);
        }

        if (f >= (float) Math.PI) {
            f -= ((float) Math.PI * 2F);
        }

        return rotA + time * f;
    }

    private float quadraticArmUpdate(float value) {
        return -65.0F * value + value * value;
    }

    @SuppressWarnings("null")
    public void copyPropertiesTo(PMM2_HumanoidModel<T> model) {
        super.copyPropertiesTo(model);
        model.leftArmPose = this.leftArmPose;
        model.rightArmPose = this.rightArmPose;
        model.crouching = this.crouching;
        // model.head.copyFrom(this.head);
        // model.hat.copyFrom(this.hat);
        // model.body.copyFrom(this.body);
        // model.rightArm.copyFrom(this.rightArm);
        // model.leftArm.copyFrom(this.leftArm);
        // model.rightLeg.copyFrom(this.rightLeg);
        // model.leftLeg.copyFrom(this.leftLeg);
    }

    public void setAllVisible(boolean allVisible) {
        // this.head.visible = allVisible;
        // this.hat.visible = allVisible;
        // this.body.visible = allVisible;
        // this.rightArm.visible = allVisible;
        // this.leftArm.visible = allVisible;
        // this.rightLeg.visible = allVisible;
        // this.leftLeg.visible = allVisible;
    }

    @SuppressWarnings("null")
    public void translateToHand(HumanoidArm arm, PoseStack pose) {
        this.getArm(arm).translateAndRotate(pose);
    }

    @SuppressWarnings("null")
    protected ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public ModelPart getHead() {
        return this.pHead;
    }

    private HumanoidArm getAttackArm(T entity) {
        HumanoidArm humanoidarm = entity.getMainArm();
        return entity.swingingArm == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.getOpposite();
    }

    // extended methods

    // AgeableListModel

    @SuppressWarnings("null")
    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer vertex, int p_102036_, int p_102037_,
            float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
        if (this.young) {
            pose.pushPose();
            if (this.scaleHead) {
                float f = 1.5F / this.babyHeadScale;
                pose.scale(f, f, f);
            }

            pose.translate(0.0F, this.babyYHeadOffset / 16.0F, this.babyZHeadOffset / 16.0F);
            this.headParts().forEach((p_102081_) -> {
                p_102081_.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
            pose.popPose();
            pose.pushPose();
            float f1 = 1.0F / this.babyBodyScale;
            pose.scale(f1, f1, f1);
            pose.translate(0.0F, this.bodyYOffset / 16.0F, 0.0F);
            this.bodyParts().forEach((p_102071_) -> {
                p_102071_.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
            pose.popPose();
        } else {
            this.headParts().forEach((p_102061_) -> {
                p_102061_.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
            this.bodyParts().forEach((p_102051_) -> {
                p_102051_.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
        }

    }

    // individual methods

    public boolean isAggressive(T entity) {
        return entity.isAggressive();
    }

    @OnlyIn(Dist.CLIENT)
    public static enum ArmPose implements net.minecraftforge.common.IExtensibleEnum {
        EMPTY(false),
        ITEM(false),
        BLOCK(false),
        BOW_AND_ARROW(true),
        THROW_SPEAR(false),
        CROSSBOW_CHARGE(true),
        CROSSBOW_HOLD(true),
        SPYGLASS(false),
        TOOT_HORN(false),
        BRUSH(false);

        private final boolean twoHanded;

        private ArmPose(boolean towHanded) {
            this.twoHanded = towHanded;
            this.forgeArmPose = null;
        }

        public boolean isTwoHanded() {
            return this.twoHanded;
        }

        // FORGE START
        @javax.annotation.Nullable
        private final net.minecraftforge.client.IArmPoseTransformer forgeArmPose;

        private ArmPose(boolean twoHanded,
                @javax.annotation.Nonnull net.minecraftforge.client.IArmPoseTransformer forgeArmPose) {
            this.twoHanded = twoHanded;
            com.google.common.base.Preconditions.checkNotNull(forgeArmPose,
                    "Cannot create new ArmPose with null transformer!");
            this.forgeArmPose = forgeArmPose;
        }

        public static ArmPose create(String name, boolean twoHanded,
                @javax.annotation.Nonnull net.minecraftforge.client.IArmPoseTransformer forgeArmPose) {
            throw new IllegalStateException("Enum not extended");
        }

        public <T extends Mob> void applyTransform(PMM2_HumanoidModel<T> model, T entity,
                net.minecraft.world.entity.HumanoidArm arm) {
            if (this.forgeArmPose != null)
                this.forgeArmPose.applyTransform(model, entity, arm);
        }
        // FORGE END
    }
}
