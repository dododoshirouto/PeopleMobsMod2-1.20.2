package site.dodoneko.peoplemobsmod2.base;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import java.util.function.Function;

import net.minecraft.Util;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    // model options
    public boolean doWalkBounding = true;
    public boolean useChildModel = false;
    public float modelScale = 1.0F;
    public float bHeight = 0.5F;

    // entity status
    public T entity;
    public int entityId;
    public float limbSwing;
    public float limbSwingAmount;
    public float ageInTicks;
    public float headRotY;
    public float headRotX;
    public boolean isChild;
    public boolean isAggressive;
    public boolean hasBow;
    public boolean carrying;
    public boolean creepy;

    public PMM2_HumanoidModel(ModelPart root) {
        this(root, RenderType::entityCutoutNoCull);
    }

    public PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc) {
        super(root, renderFunc);

        PeopleMobsMod2.LOGGER
                .info("[PMM2] PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc)");

        // this.head = root.getChild("head");
        // this.hat = root.getChild("hat");
        // this.body = root.getChild("body");
        // this.rightArm = root.getChild("right_arm");
        // this.leftArm = root.getChild("left_arm");
        // this.rightLeg = root.getChild("right_leg");
        // this.leftLeg = root.getChild("left_leg");

        this.pHead = root.getChild("pHead");
        this.pBody = root.getChild("pBody");
        this.pArmL = pBody.getChild("pArmL");
        this.pArmR = pBody.getChild("pArmR");
        this.pLegL = pBody.getChild("pLegL");
        this.pLegR = pBody.getChild("pLegR");
        this.pBUpper = pBody.getChild("pBUpper");
        this.pBLower = pBUpper.getChild("pBLower");
        this.pHeadWear = pHead.getChild("pHeadWear");
        this.pBodyWear = pBody.getChild("pBodyWear");
        this.pArmLWear = pArmL.getChild("pArmLWear");
        this.pArmRWear = pArmR.getChild("pArmRWear");
        this.pLegLWear = pLegL.getChild("pLegLWear");
        this.pLegRWear = pLegR.getChild("pLegRWear");
        // this.pBUpperWear = null;
        // this.pBLowerWear = null;
        this.pBUpperWear = pBUpper.getChild("pBUpperWear");
        this.pBLowerWear = pBLower.getChild("pBLowerWear");
        this.pAhoge = pHead.getChild("pAhoge");
        this.pKemomimi = pHead.getChild("pKemomimi");
        this.pShippo = null;
        this.pEyebrowL = null;
        this.pEyebrowR = null;
        // this.pShippo = pBody.getChild("pShippo");
        // this.pEyebrowL = pHead.getChild("pEyebrowL");
        // this.pEyebrowR = pHead.getChild("pEyebrowR");

        this.doWalkBounding = true;
        this.useChildModel = false;
        this.modelScale = 1.0F;
        this.bHeight = 0.5F;

        // AgeableListModel
        this.scaleHead = true;
        this.babyYHeadOffset = 16.0F;
        this.babyZHeadOffset = 0.0F;
        this.babyHeadScale = 0.5F * 1.5F;
        this.babyBodyScale = 0.5F;
        this.bodyYOffset = 24.0F;
    }

    @SuppressWarnings({ "null" })
    public static LayerDefinition createBodyLayer() {
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel.createBodyLayer()");
        return LayerDefinition.create(createMesh(new CubeDeformation(0.0F), 0), 64, 64);
    }

    @SuppressWarnings({ "null" })
    public static MeshDefinition createMesh(CubeDeformation cube, float yOffset) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel.createMesh()");

        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));

        PartDefinition pHead = root.addOrReplaceChild("pHead",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8, cube),
                PartPose.offset(0, yOffset, 0));
        PartDefinition pBody = root.addOrReplaceChild("pBody",
                CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, cube),
                PartPose.offset(0, yOffset, 0));
        PartDefinition pArmL = pBody.addOrReplaceChild("pArmL",
                CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -1.2F, -2.0F, 3, 12, 4, cube),
                PartPose.offset(5.0F, 2.5F + yOffset, 0.0F));
        PartDefinition pArmR = pBody.addOrReplaceChild("pArmR",
                CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.2F, -2.0F, 3, 12, 4, cube),
                PartPose.offset(-5.0F, 2.5F + yOffset, 0.0F));
        PartDefinition pLegL = pBody.addOrReplaceChild("pLegL",
                CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, cube),
                PartPose.offset(2.2F, 12.0F + yOffset, 0.0F));
        PartDefinition pLegR = pBody.addOrReplaceChild("pLegR",
                CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, cube),
                PartPose.offset(-2.2F, 12.0F + yOffset, 0.0F));
        PartDefinition pBUpper = pBody.addOrReplaceChild("pBUpper",
                CubeListBuilder.create().texOffs(16, 21).addBox(-4.0F, 0.0F, -4.0F, 8, 4, 4, cube.extend(-0.05F)),
                PartPose.offset(0, 0 + yOffset, -2));
        PartDefinition pBLower = pBUpper.addOrReplaceChild("pBLower",
                CubeListBuilder.create().texOffs(16, 25).addBox(-4.0F, 0.0F, -4.0F, 8, 3, 4, cube.extend(-0.02F)),
                PartPose.offset(0, 0 + yOffset, -4));

        pHead.addOrReplaceChild("pHeadWear",
                CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, cube.extend(0.5F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pBody.addOrReplaceChild("pBodyWear",
                CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, cube.extend(0.45F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pArmL.addOrReplaceChild("pArmLWear",
                CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, cube.extend(0.4F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pArmR.addOrReplaceChild("pArmRWear",
                CubeListBuilder.create().texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, cube.extend(0.4F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pLegL.addOrReplaceChild("pLegLWear",
                CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, cube.extend(0.4F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pLegR.addOrReplaceChild("pLegRWear",
                CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, cube.extend(0.4F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pBUpper.addOrReplaceChild("pBUpperWear", CubeListBuilder.create().texOffs(16, 36).addBox(-4.0F, 0.125F, -4.125F,
                8, 4, 4, cube.extend(0.45F - 0.05F)), PartPose.offset(0, 0 + yOffset, 0));
        pBLower.addOrReplaceChild("pBLowerWear", CubeListBuilder.create().texOffs(16, 40).addBox(-4.0F, 0.125F, -4.125F,
                8, 3, 4, cube.extend(0.45F - 0.02F)), PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pAhoge",
                CubeListBuilder.create().texOffs(24, 0).addBox(-3.5F, -3.0F, 0.0F, 7, 3, 1, cube),
                PartPose.offset(0, -8.0F + yOffset, 1.0F));
        pHead.addOrReplaceChild("pKemomimi",
                CubeListBuilder.create().texOffs(24, 4).addBox(-3.5F, -3.0F, -1.0F, 7, 3, 1, cube),
                PartPose.offset(0, -8.0F + yOffset, -1.0F));
        // pBody.addOrReplaceChild("pShippo",
        // CubeListBuilder.create().texOffs(54, 16).addBox(-5.0F, 0.0F, 0.0F, 10, 12, 0,
        // cube),
        // PartPose.offset(0, 10 + yOffset, 2));
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

    @SuppressWarnings("null")
    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float ticks) {
        // this.swimAmount = entity.getSwimAmount(ticks);
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, ticks);

        @SuppressWarnings("unused")
        ArmPose mainArmPose = entity.getMainArm() == HumanoidArm.LEFT ? this.leftArmPose : this.rightArmPose;
        @SuppressWarnings("unused")
        ArmPose otherArmPose = entity.getMainArm() == HumanoidArm.LEFT ? this.rightArmPose : this.leftArmPose;

        this.entity = entity;
        this.setArmPoses();
    }

    public void resetPartsPosAndRot() {
        this.pHead.setPos(0,0,0);
        this.pHead.setRotation(0, 0, 0);
        this.pBody.setPos(0,0,0);
        this.pBody.setRotation(0, 0, 0);
        this.pArmL.setPos(5.0F,2.5F,0.0F);
        this.pArmL.setRotation(0, 0, 0);
        this.pArmR.setPos(-5.0F,2.5F,0.0F);
        this.pArmR.setRotation(0, 0, 0);
        this.pLegL.setPos(2.2F,12.0F,0.0F);
        this.pLegL.setRotation(0, 0, 0);
        this.pLegR.setPos(-2.2F,12.0F,0.0F);
        this.pLegR.setRotation(0, 0, 0);
        this.pBUpper.setPos(0,0,-2);
        this.pBUpper.setRotation(0, 0, 0);
        this.pBLower.setPos(0,0,-4);
        this.pBLower.setRotation(0, 0, 0);

        this.pHeadWear.setPos(0,0,0);
        this.pHeadWear.setRotation(0, 0, 0);
        this.pBodyWear.setPos(0,0,0);
        this.pBodyWear.setRotation(0, 0, 0);
        this.pArmLWear.setPos(0,0,0);
        this.pArmLWear.setRotation(0, 0, 0);
        this.pArmRWear.setPos(0,0,0);
        this.pArmRWear.setRotation(0, 0, 0);
        this.pLegLWear.setPos(0,0,0);
        this.pLegLWear.setRotation(0, 0, 0);
        this.pLegRWear.setPos(0,0,0);
        this.pLegRWear.setRotation(0, 0, 0);

        this.pAhoge.setPos(0,-8,1);
        this.pAhoge.setRotation(0, 0, 0);
        this.pKemomimi.setPos(0,-8,-1);
        this.pKemomimi.setRotation(0, 0, 0);

    }

    @SuppressWarnings("null")
    public void setEntityStatus(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    float headPitch) {
        this.entity = entity;
        this.entityId = entity.getId();
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = (float) Util.getMillis() / 100 + this.entityId * 10;
        this.headRotY = netHeadYaw;
        this.headRotX = headPitch;
        this.isChild = this.entity.isBaby();
        this.isAggressive = entity.isAggressive();
        this.hasBow = this.entity.getItemInHand(InteractionHand.MAIN_HAND).is(Items.BOW) || this.entity.getItemInHand(InteractionHand.MAIN_HAND).is(Items.CROSSBOW);

        if (entity instanceof EnderMan) {
            this.carrying = ((EnderMan)entity).getCarriedBlock() != null;
            this.creepy = ((EnderMan)entity).isCreepy();
        }

        // set pose
        if (!this.entity.onGround()) {
            if (this.entity.isInWater()) {
                this.entity.setPose(Pose.SWIMMING);
            } else {
                this.entity.setPose(Pose.FALL_FLYING);
            }
        } else if (this.entity.getPose() == Pose.FALL_FLYING) {
            this.entity.setPose(Pose.STANDING);
        }
        if (this.entity.isDeadOrDying()) {
            this.entity.setPose(Pose.DYING);
        }
        
        this.prepareMobModel(this.entity, this.limbSwing, this.limbSwingAmount, this.ageInTicks);
    }

    @SuppressWarnings("null")
    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {
        this.setEntityStatus(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.resetPartsPosAndRot();

        // from 1.14.4 PMM2
        if (this.setPreAnimations()) {
            return;
        }

        // 頭の回転
        this.setHeadYawAndPitch();

        // 何もしてないときのモーション
        this.setStayAnimations();

        // 歩いてるときのモーション
        if (this.limbSwingAmount > 0.01F) {
            this.setWalkingAnimations();
        }

        // 乗ってる時のモーション
        if (this.riding) {
            this.setRidingAnimations();
        }

        // 手に何か持ってるとき
        this.setArmHasAnythingAnimations(this.pArmL, this.leftArmPose);
        this.setArmHasAnythingAnimations(this.pArmR, this.rightArmPose);

        // スニーク時のモーション
        if (((LivingEntity) this.entity).getPose() == Pose.CROUCHING) {
            this.setSneakAnimations();
        }

        // プレイヤーに迫ってくるときのモーション
        if (this.isAggressive) {
            setAggressiveAnimations();
        }

        // ダメージ時のモーション
        if (this.entity.hurtTime > 1) {
            this.setDamagedAnimations();
        }

        // 水中にいるとき
        else if (entity.getPose() == Pose.SWIMMING) {
            this.setSwimmingAnimations();
        }

        // 空中にいるの時のモーション
        else if (entity.getPose() == Pose.FALL_FLYING) {
            this.setJumpAnimations();
        }

        // // 空中にいるの時のモーション
        // else if (!this.entity.onGround()) {
        //     // 水中にいるとき
        //     if (this.entity.isInWater()) {
        //         this.setSwimmingAnimations();
        //     } else {
        //         this.setJumpAnimations();
        //     }
        // }

        // boolean flag = entity.getFallFlyingTicks() > 4;
        // boolean flag1 = entity.isVisuallySwimming();
        // this.pHead.yRot = netHeadYaw * ((float) Math.PI / 180F);
        // if (flag) {
        // this.pHead.xRot = (-(float) Math.PI / 4F);
        // } else if (this.swimAmount > 0.0F) {
        // if (flag1) {
        // this.pHead.xRot = this.rotlerpRad(this.swimAmount, this.pHead.xRot, (-(float)
        // Math.PI / 4F));
        // } else {
        // this.pHead.xRot = this.rotlerpRad(this.swimAmount, this.pHead.xRot,
        // headPitch * ((float) Math.PI / 180F));
        // }
        // } else {
        // this.pHead.xRot = headPitch * ((float) Math.PI / 180F);
        // }

        // this.pBody.yRot = 0.0F;
        // this.pArmR.z = 0.0F;
        // this.pArmR.x = -5.0F;
        // this.pArmL.z = 0.0F;
        // this.pArmL.x = 5.0F;
        // float f = 1.0F;
        // if (flag) {
        // f = (float) entity.getDeltaMovement().lengthSqr();
        // f /= 0.2F;
        // f *= f * f;
        // }

        // if (f < 1.0F) {
        // f = 1.0F;
        // }

        // this.pArmR.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F *
        // limbSwingAmount * 0.5F / f;
        // this.pArmL.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount *
        // 0.5F
        // / f;
        // this.pArmR.zRot = 0.0F;
        // this.pArmL.zRot = 0.0F;
        // this.pLegR.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        // this.pLegL.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F *
        // limbSwingAmount / f;
        // this.pLegR.yRot = 0.005F;
        // this.pLegL.yRot = -0.005F;
        // this.pLegR.zRot = 0.005F;
        // this.pLegL.zRot = -0.005F;
        // if (this.riding) {
        // this.pArmR.xRot += (-(float) Math.PI / 5F);
        // this.pArmL.xRot += (-(float) Math.PI / 5F);
        // this.pLegR.xRot = -1.4137167F;
        // this.pLegR.yRot = ((float) Math.PI / 10F);
        // this.pLegR.zRot = 0.07853982F;
        // this.pLegL.xRot = -1.4137167F;
        // this.pLegL.yRot = (-(float) Math.PI / 10F);
        // this.pLegL.zRot = -0.07853982F;
        // }

        // this.pArmR.yRot = 0.0F;
        // this.pArmL.yRot = 0.0F;
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

        // this.setupAttackAnimation(entity, ageInTicks);
        // if (this.crouching) {
        // this.pBody.xRot = 0.5F;
        // this.pArmR.xRot += 0.4F;
        // this.pArmL.xRot += 0.4F;
        // this.pLegR.z = 4.0F;
        // this.pLegL.z = 4.0F;
        // this.pLegR.y = 12.2F;
        // this.pLegL.y = 12.2F;
        // this.pHead.y = 4.2F;
        // this.pBody.y = 3.2F;
        // this.pArmL.y = 5.2F;
        // this.pArmR.y = 5.2F;
        // } else {
        // this.pBody.xRot = 0.0F;
        // this.pLegR.z = 0.0F;
        // this.pLegL.z = 0.0F;
        // this.pLegR.y = 12.0F;
        // this.pLegL.y = 12.0F;
        // this.pHead.y = 0.0F;
        // this.pBody.y = 0.0F;
        // this.pArmL.y = 2.0F;
        // this.pArmR.y = 2.0F;
        // }

        // if (this.rightArmPose != PMM2_HumanoidModel.ArmPose.SPYGLASS) {
        // AnimationUtils.bobModelPart(this.pArmR, ageInTicks, 1.0F);
        // }

        // if (this.leftArmPose != PMM2_HumanoidModel.ArmPose.SPYGLASS) {
        // AnimationUtils.bobModelPart(this.pArmL, ageInTicks, -1.0F);
        // }

        // if (this.swimAmount > 0.0F) {
        // float f5 = limbSwing % 26.0F;
        // HumanoidArm humanoidarm = this.getAttackArm(entity);
        // float f1 = humanoidarm == HumanoidArm.RIGHT && this.attackTime > 0.0F ? 0.0F
        // : this.swimAmount;
        // float f2 = humanoidarm == HumanoidArm.LEFT && this.attackTime > 0.0F ? 0.0F :
        // this.swimAmount;
        // if (!entity.isUsingItem()) {
        // if (f5 < 14.0F) {
        // this.pArmL.xRot = this.rotlerpRad(f2, this.pArmL.xRot, 0.0F);
        // this.pArmR.xRot = Mth.lerp(f1, this.pArmR.xRot, 0.0F);
        // this.pArmL.yRot = this.rotlerpRad(f2, this.pArmL.yRot, (float) Math.PI);
        // this.pArmR.yRot = Mth.lerp(f1, this.pArmR.yRot, (float) Math.PI);
        // this.pArmL.zRot = this.rotlerpRad(f2, this.pArmL.zRot, (float) Math.PI
        // + 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
        // this.pArmR.zRot = Mth.lerp(f1, this.pArmR.zRot, (float) Math.PI
        // - 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
        // } else if (f5 >= 14.0F && f5 < 22.0F) {
        // float f6 = (f5 - 14.0F) / 8.0F;
        // this.pArmL.xRot = this.rotlerpRad(f2, this.pArmL.xRot, ((float) Math.PI /
        // 2F) * f6);
        // this.pArmR.xRot = Mth.lerp(f1, this.pArmR.xRot, ((float) Math.PI / 2F)
        // * f6);
        // this.pArmL.yRot = this.rotlerpRad(f2, this.pArmL.yRot, (float) Math.PI);
        // this.pArmR.yRot = Mth.lerp(f1, this.pArmR.yRot, (float) Math.PI);
        // this.pArmL.zRot = this.rotlerpRad(f2, this.pArmL.zRot, 5.012389F -
        // 1.8707964F * f6);
        // this.pArmR.zRot = Mth.lerp(f1, this.pArmR.zRot, 1.2707963F + 1.8707964F
        // * f6);
        // } else if (f5 >= 22.0F && f5 < 26.0F) {
        // float f3 = (f5 - 22.0F) / 4.0F;
        // this.pArmL.xRot = this.rotlerpRad(f2, this.pArmL.xRot,
        // ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * f3);
        // this.pArmR.xRot = Mth.lerp(f1, this.pArmR.xRot,
        // ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * f3);
        // this.pArmL.yRot = this.rotlerpRad(f2, this.pArmL.yRot, (float) Math.PI);
        // this.pArmR.yRot = Mth.lerp(f1, this.pArmR.yRot, (float) Math.PI);
        // this.pArmL.zRot = this.rotlerpRad(f2, this.pArmL.zRot, (float) Math.PI);
        // this.pArmR.zRot = Mth.lerp(f1, this.pArmR.zRot, (float) Math.PI);
        // }
        // }

        // this.pLegL.xRot = Mth.lerp(this.swimAmount, this.pLegL.xRot,
        // 0.3F * Mth.cos(limbSwing * 0.33333334F + (float) Math.PI));
        // this.pLegR.xRot = Mth.lerp(this.swimAmount, this.pLegR.xRot,
        // 0.3F * Mth.cos(limbSwing * 0.33333334F));
        // }

        // this.hat.copyFrom(this.head);

        this.setAddAnimations();

        // 死亡時のモーション
        // if (((LivingEntity) this.entity).getPose() == Pose.DYING) {
        if (this.entity.getPose() == Pose.DYING) {
            setDeadAnimations();
        }

        this.setBoobsAnimations();
        this.setShippoAnimations();
        this.setAhogeAnimations();
        this.setTwinkleAnimations();

        this.setPostAnimations();

        // 足の角度による接続部の整合性
        float gapLY = 0.0F, gapRY = 0.0F;
        if (this.pLegL.xRot < 0.0F) {
            this.pLegL.y += gapLY = (float) Math.sin(this.pLegL.xRot) * 2.0F;
            this.pLegL.z += (1F - (float) Math.cos(this.pLegL.xRot)) * 2.0F;
        } else {
            this.pLegL.y += gapLY = (float) PMath
                    .sin(-PMath.min(this.pLegL.xRot, PMath.toRad(30F))) * 2.0F;
            this.pLegL.z -= (1F
                    - (float) PMath.cos(-PMath.min(this.pLegL.xRot, PMath.toRad(30F)))) * 2.0F;
        }
        if (this.pLegR.xRot < 0.0F) {
            this.pLegR.y += gapRY = (float) PMath.sin(this.pLegR.xRot) * 2.0F;
            this.pLegR.z += (1F - (float) PMath.cos(this.pLegR.xRot)) * 2.0F;
        } else {
            this.pLegR.y += gapRY = (float) PMath
                    .sin(-PMath.min(this.pLegR.xRot, PMath.toRad(30F))) * 2.0F;
            this.pLegR.z -= (1F
                    - (float) PMath.cos(-PMath.min(this.pLegR.xRot, PMath.toRad(30F)))) * 2.0F;
        }
        float y = PMath.min(gapLY, gapRY);
        this.pBody.y -= y;
        this.pHead.y -= y;
    }

    protected boolean setPreAnimations() {
        return false;
    }

    /** 頭の回転 */
    protected void setHeadYawAndPitch() {
        this.pHead.yRot = PMath.toRad(this.headRotY);
        this.pHead.xRot = PMath.toRad(this.headRotX);
    }

    /** 何もしてないときのモーション */
    protected void setStayAnimations() {
        this.pArmL.zRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 70F) * 3F - 3F);
        this.pArmR.zRot = PMath.toRad(PMath.cos1(this.ageInTicks / 70F) * 3F + 3F);
        this.pArmL.xRot = PMath.toRad(-PMath.sin1(this.ageInTicks / 93F) * 3F);
        this.pArmR.xRot = PMath.toRad(PMath.sin1(this.ageInTicks / 93F) * 3F);
    }

    /** 歩いてる時のモーション */
    protected void setWalkingAnimations() {
        // if (speed < 0.5) {
        this.pArmR.yRot = PMath.toRad(17F * this.limbSwingAmount);
        this.pArmL.yRot = PMath.toRad(-17F * this.limbSwingAmount);
        this.pArmR.xRot = PMath.toRad(-PMath.cos1(this.limbSwing / 4.7F) * 60F * this.limbSwingAmount);
        this.pArmL.xRot = PMath.toRad(PMath.cos1(this.limbSwing / 4.7F) * 60F * this.limbSwingAmount);
        this.pArmR.zRot = PMath.toRad(19F * this.limbSwingAmount);
        this.pArmL.zRot = PMath.toRad(-19F * this.limbSwingAmount);
        this.pLegR.zRot = PMath.toRad(4.3F * this.limbSwingAmount);
        this.pLegL.zRot = PMath.toRad(-4.3F * this.limbSwingAmount);
        this.pLegR.yRot = PMath.toRad(5F * this.limbSwingAmount);
        this.pLegL.yRot = PMath.toRad(-5F * this.limbSwingAmount);
        this.pLegR.xRot = PMath.toRad(PMath.cos1(this.limbSwing / 4.7F) * 80F * this.limbSwingAmount);
        this.pLegL.xRot = PMath.toRad(-PMath.cos1(this.limbSwing / 4.7F) * 80F * this.limbSwingAmount);
        this.pLegR.z = PMath
                .toRad(PMath.sin1(this.limbSwing / 4.7F) * 60F * this.limbSwingAmount + 60F * this.limbSwingAmount);
        this.pLegL.z = PMath
                .toRad(-PMath.sin1(this.limbSwing / 4.7F) * 60F * this.limbSwingAmount + 60F * this.limbSwingAmount);

        // } else { // running

        // }

        if (this.doWalkBounding) {
            this.pBody.y -= (PMath.abs(PMath.sin1(limbSwing * 4.7F)) * 2F
                    - 1F * (this.isChild ? 0.5F : 1F)) * limbSwingAmount * this.modelScale;
            this.pHead.y -= (PMath.abs(PMath.sin1(limbSwing * 4.7F)) * 2F
                    - 1F * (this.isChild ? 0.5F : 1F)) * limbSwingAmount * this.modelScale;
        }
    }

    /** 何かに乗ってる時のモーション */
    protected void setRidingAnimations() {
        this.pArmR.xRot = PMath.toRad(-12F);
        this.pArmL.xRot = PMath.toRad(-12F);
        this.pLegR.xRot = PMath.toRad(-80);
        this.pLegR.yRot = PMath.toRad(5);
        this.pLegR.zRot = PMath.toRad(4.5F);
        this.pLegL.xRot = PMath.toRad(-80);
        this.pLegL.yRot = PMath.toRad(-5);
        this.pLegL.zRot = PMath.toRad(-4.5F);
    }

    @SuppressWarnings("null")
    protected void setArmPoses() {
        ArmPose mainArmPose = this.entity.getMainArm() == HumanoidArm.LEFT ? this.leftArmPose : this.rightArmPose;
        ArmPose otherArmPose = this.entity.getMainArm() == HumanoidArm.LEFT ? this.rightArmPose : this.leftArmPose;

        mainArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
        otherArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
        ItemStack itemStack = this.entity.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemStack.is(Items.BOW)) {
            mainArmPose = PMM2_HumanoidModel.ArmPose.BOW_AND_ARROW;
        } else if (this.carrying) {
            mainArmPose = PMM2_HumanoidModel.ArmPose.BLOCK;
        } else if (itemStack.isEmpty()) {

        } else {
            mainArmPose = PMM2_HumanoidModel.ArmPose.ITEM;
        }
        // PeopleMobsMod2.LOGGER
        //         .debug("[PPM2] mainArmPose " + mainArmPose + " Entity " + entity.getClass().getName());
        // PeopleMobsMod2.LOGGER
        //         .debug("[PPM2] ArmPoseRight " + this.rightArmPose + " Entity " + entity.getClass().getName());

        this.rightArmPose = this.entity.getMainArm() == HumanoidArm.RIGHT ? mainArmPose : otherArmPose;
        this.leftArmPose = this.entity.getMainArm() == HumanoidArm.LEFT ? mainArmPose : otherArmPose;
    }

    // 手に何か持ってるとき
    protected void setArmHasAnythingAnimations(ModelPart arm, ArmPose pose) {
        float armSide = arm == this.pArmL ? 1f : -1f;
        ModelPart other = arm == this.pArmL ? this.pArmR : this.pArmL;

        switch (pose) {
            case EMPTY:
                break;
            case ITEM:
                arm.xRot = arm.xRot * 0.5F - PMath.toRad(5.7F);
                arm.yRot = PMath.toRad(0.0F);
                break;
            case BLOCK:
                arm.xRot = arm.xRot * 0.5F - PMath.toRad(18);
                arm.yRot = PMath.toRad(30) * armSide;
                break;
            case BOW_AND_ARROW:
                other.yRot = PMath.toRad(-28) * armSide;
                arm.yRot = PMath.toRad(5.7F) * armSide;
                other.xRot = PMath.toRad(-90);
                arm.xRot = PMath.toRad(-90);
                if (this.isAggressive) {
                    other.yRot += this.pHead.yRot;
                    arm.yRot += this.pHead.yRot;
                    other.xRot += this.pHead.xRot;
                    arm.xRot += this.pHead.xRot;
                } else {
                    other.xRot += PMath.toRad(45);
                    arm.xRot += PMath.toRad(45);
                }
                break;
            case THROW_SPEAR:
                break;
            case CROSSBOW_CHARGE:
                break;
            case CROSSBOW_HOLD:
                break;
            case SPYGLASS:
                break;
            case TOOT_HORN:
                break;
            case BRUSH:
                break;
        }
    }

    /** スニーク時のモーション */
    protected void setSneakAnimations() {
        this.pBody.xRot += 0.5F;
        this.pArmR.xRot += 0.2F;
        this.pArmL.xRot += 0.2F;
        this.pArmR.zRot += 0.2F;
        this.pArmL.zRot -= 0.2F;
        this.pLegR.xRot -= 0.75F;
        this.pLegL.xRot -= 0.75F;
        this.pHead.y += 3.0F;
        this.pBody.y += 3.0F;
        this.pHead.z -= 1.5F;
        this.pBody.z -= 1.5F;
    }

    // プレイヤーに迫ってくるときのモーション
    protected void setAggressiveAnimations() {
        if (this.hasBow)
            return;
        this.pArmR.xRot = PMath.toRad(PMath.cos1(this.limbSwing / 2.3F - 4.4f) * 8.5F - 85F);
        this.pArmL.xRot = PMath.toRad(PMath.cos1(this.limbSwing / 2.3F - 4.4F) * 8.5F - 85F);
        this.pArmR.y = 2.5F + PMath.cos1(this.limbSwing / 2.3F - 2.2F) * 0.5F + 0.5F;
        this.pArmL.y = 2.5F + PMath.cos1(this.limbSwing / 2.3F - 2.2F) * 0.5F + 0.5F;
    }

    /** ダメージ受けた時のアニメーション */
    protected void setDamagedAnimations() {
        this.setWalkingAnimations();
        this.pHead.xRot = PMath.toRad(15F);
        this.pLegL.xRot -= PMath.toRad(20F);
        this.pLegR.xRot -= PMath.toRad(20F);
        this.pArmL.zRot -= PMath.toRad(80F);
        this.pArmR.zRot += PMath.toRad(80F);
        this.pArmL.yRot -= PMath.toRad(80F);
        this.pArmR.yRot += PMath.toRad(80F);
    }

    /** 水中にいるときのモーション */
    protected void setSwimmingAnimations() {
        float vy = (float) this.entity.getDeltaMovement().length();
        this.pLegR.xRot = PMath.toRad(PMath.cos1(this.ageInTicks / 10F) * -30F + 12F + 23F * (1 - vy));
        this.pLegL.xRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 10F) * -30F + 12F + 23F * (1 - vy));
        this.pLegR.zRot = PMath.toRad(2.8F);
        this.pLegL.zRot = PMath.toRad(-2.8F);

        this.pArmR.zRot = PMath.toRad(PMath.cos1(this.ageInTicks / 14F) * (75F + 45F * vy) + 57F + 75F * vy);
        this.pArmL.zRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 14F) * (75F + 45F * vy) - 57F - 75F * vy);
        this.pArmR.xRot = PMath.toRad((PMath.cos1(this.ageInTicks / 14F) * -45F - 57F) * (1 - vy));
        this.pArmL.xRot = PMath.toRad((PMath.cos1(this.ageInTicks / 14F) * -45F - 57F) * (1 - vy));

        this.pBody.xRot = PMath.toRad(11.4F + 63F * vy);
        this.pHead.z = this.pBody.z = -6F * (float) this.entity.getDeltaMovement().length();
    }

    /** onGroundじゃない時のモーション */
    protected void setJumpAnimations() {
        float fallingSpeed = PMath.clamp((float) entity.getDeltaMovement().y, -1.0F, 1.0F);
        this.pArmL.zRot = -PMath.toRad(80F - 30F * fallingSpeed);
        this.pArmR.zRot = PMath.toRad(80F - 30F * fallingSpeed);
        this.pLegL.yRot = PMath.toRad(-fallingSpeed * 40);
        this.pLegL.yRot = PMath.toRad(fallingSpeed * 40);
        this.pLegL.xRot = PMath.toRad(fallingSpeed * 75);
        this.pLegR.xRot = PMath.toRad(fallingSpeed * 75);
    }

    //
    //
    //

    protected void setAddAnimations() {
    }

    // 死亡時のモーション
    protected void setDeadAnimations() {
        this.pArmR.zRot = PMath.toRad(15);
        this.pArmL.zRot = PMath.toRad(-15);
        this.pArmR.xRot = PMath.toRad(-130);
        this.pArmL.xRot = PMath.toRad(-130);
        this.pArmR.z = -1.5f;
        this.pArmL.z = -1.5f;

        this.pLegR.zRot = PMath.toRad(-2);
        this.pLegL.zRot = PMath.toRad(2);
        this.pLegR.xRot = PMath.toRad(-35);
        this.pLegL.xRot = PMath.toRad(-35);

        this.pBody.xRot = PMath.toRad(12);
        this.pHead.yRot = PMath.toRad((PMath.sin1(ageInTicks / 30) * 18));
        this.pHead.xRot = PMath.toRad(16);

        // this.pEyelidL.z = this.pEyelidR.z = 0F;
        // this.pEyelidL.y = this.pEyelidR.y = 0F;
    }

    /** おっぱい部分のアニメーション */
    protected void setBoobsAnimations() {
        this.pBUpper.y = this.bHeight * 1F;
        this.pBUpper.xRot = -PMath.asin(this.bHeight) + PMath.PI / 2;
        float h = this.bHeight;
        if (this.bHeight > 0.5F)
            h = 1.0F - h;
        this.pBLower.xRot = -(this.pBUpper.xRot - PMath.PI / 2) * (2 + h);

        if (true /* this.boobsSwing */) {
            this.pBUpper.y += PMath.max(
                    PMath.min(-(this.pBody.y + (float) this.entity.getDeltaMovement().y) * this.bHeight * 1.5F, 1.0F),
                    -0.8F);
            this.pBUpper.xRot += PMath.max(PMath.min(
                    -(this.pBody.y + (float) this.entity.getDeltaMovement().y) * this.bHeight * 0.4F * PMath.PI,
                    0.25F * PMath.PI), 0.05F * PMath.PI);

            float f = this.bHeight;
            if (this.bHeight > 0.5F)
                f = 1.0F - f;
            this.pBLower.xRot = -(this.pBUpper.xRot - PMath.PI / 2) * (2 + f);
        }
    }

    /** しっぽのアニメーション */
    protected void setShippoAnimations() {
    }

    /** あほげとけもみみのアニメーション */
    protected void setAhogeAnimations() {
        float f1 = -0.6F * (float) this.entity.getDeltaMovement().length() * 10F;
        if (this.doWalkBounding)
            f1 += PMath.abs(PMath.cos(limbSwing * 1.3314F - 1.3F)) * this.limbSwingAmount;
        if (true /* this.ahogeSwing */)
            this.pAhoge.xRot = f1;
        if (true /* this.kemomimiSwing */)
            this.pKemomimi.xRot = f1 * 0.65F;
    }

    /** まばたき */
    protected void setTwinkleAnimations() {
    }

    protected void setPostAnimations() {
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
        // this.pArmR.visible = allVisible;
        // this.pArmL.visible = allVisible;
        // this.pLegR.visible = allVisible;
        // this.pLegL.visible = allVisible;
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

    // extended methods

    // AgeableListModel

    @SuppressWarnings("null")
    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer vertex, int p_102036_, int p_102037_,
            float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
        if (this.young) {
            pose.pushPose();
            if (this.scaleHead) {
                pose.scale(this.babyHeadScale, this.babyHeadScale, this.babyHeadScale);
            }

            pose.translate(0.0F, this.babyYHeadOffset / 16.0F, this.babyZHeadOffset / 16.0F);
            this.headParts().forEach((part) -> {
                part.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
            pose.popPose();
            pose.pushPose();
            pose.scale(this.babyBodyScale, this.babyBodyScale, this.babyBodyScale);
            pose.translate(0.0F, this.bodyYOffset / 16.0F, 0.0F);
            this.bodyParts().forEach((part) -> {
                part.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
            pose.popPose();
        } else {
            this.headParts().forEach((part) -> {
                part.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
            this.bodyParts().forEach((part) -> {
                part.render(pose, vertex, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_,
                        p_102041_);
            });
        }

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
