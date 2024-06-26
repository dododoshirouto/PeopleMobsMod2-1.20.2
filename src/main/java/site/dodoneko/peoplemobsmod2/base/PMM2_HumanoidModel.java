package site.dodoneko.peoplemobsmod2.base;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.minecraft.Util;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;

@OnlyIn(Dist.CLIENT)
public class PMM2_HumanoidModel<E extends Mob> extends HumanoidModel<E> {
    public final ModelPart pHead;
    public final ModelPart pFace;
    public final ModelPart pFace_twinkled;
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
    // public final ModelPart frogTongue;
    public PMM2_HumanoidModel.ArmPose leftArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
    public PMM2_HumanoidModel.ArmPose rightArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;

    protected Map<Integer, Float> twinklesTimes = new HashMap<Integer, Float>();
    protected Map<Integer, Boolean> twinkledNow = new HashMap<Integer, Boolean>();

    // AgeableListModel
    public final boolean scaleHeadAtChild;
    public final float babyYHeadOffset;
    public final float babyZHeadOffset;
    public final float babyHeadScale;
    public final float babyBodyScale;
    public final float bodyYOffset;

    // model options
    public float modelScale = 0.9F;
    public float modelAddSize = 0.0F;
    public float bHeight = 0.3F;
    public boolean useChildModel = false;
    public boolean doFlyFlap = false;
    public boolean forwardArm = false;
    public boolean isFloating = false;
    public float floatingHeight = 0.0F;
    public boolean doWalkBounding = true;
    public float walkSwingSpeed = 1.0f;

    // entity status
    public E entity;
    /** Hashed UUID */
    public int entityId;
    public float limbSwing;
    public float limbSwingAmount;
    public float ageInTicks;
    public float headRotY;
    public float headRotX;
    public boolean isChild;
    public boolean isAggressive;
    public boolean isCrouching;
    public boolean isSittingOnGround;
    public boolean isJumping;
    public boolean isSwimming;
    public boolean isFlying;
    public boolean isDying;
    /** 乗客がいる状態 */
    public boolean hasPassengers;
    /** 震えている | 息苦しい(Suffocating) */
    public boolean isShaking;

    public boolean hasAnything;
    public boolean hasItem;
    public boolean hasBlock;
    public boolean hasFood;
    public boolean hasBow;
    /** なついている状態 */
    public boolean isInterested;
    /** Enderman */
    public boolean isCreepy;
    /** Creeper */
    public boolean isSwelling;
    /** Spider */
    public boolean isClimbing;
    /** Chicken */
    public float eggTime;
    /** Sheep */
    public boolean isEating;

    public PMM2_HumanoidModel(ModelPart root) {
        this(root, RenderType::entityCutoutNoCull);
    }

    public PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc) {
        this(root, renderFunc, 0F);
    }

    public PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc, float addSize) {
        super(root, renderFunc);

        PeopleMobsMod2.LOGGER
                .info("[PMM2] PMM2_HumanoidModel(ModelPart root, Function<ResourceLocation, RenderType> renderFunc)");

        this.modelAddSize = addSize;

        this.pHead = root.getChild("pHead");
        this.pFace = pHead.getChild("pFace");
        this.pFace_twinkled = pHead.getChild("pFace_twinkled");
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
        this.pBUpperWear = pBUpper.getChild("pBUpperWear");
        this.pBLowerWear = pBLower.getChild("pBLowerWear");
        this.pAhoge = pHead.getChild("pAhoge");
        this.pKemomimi = pHead.getChild("pKemomimi");
        this.pShippo = pBody.getChild("pShippo");

        // this.frogTongue = pHead.getChild("frogTongue");

        this.doWalkBounding = true;
        this.useChildModel = false;
        this.modelScale = 1.0F;
        this.bHeight = 0.5F;

        // AgeableListModel
        this.scaleHeadAtChild = true;
        this.babyYHeadOffset = 16.0F;
        this.babyZHeadOffset = 0.0F;
        this.babyHeadScale = 0.5F * 1.5F;
        this.babyBodyScale = 0.5F;
        this.bodyYOffset = 24.0F;
    }

    public static LayerDefinition createBodyLayerUseTwinkleFace() {
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel.createBodyLayerUseTwinkleFace()");
        return LayerDefinition.create(createMesh(new CubeDeformation(0F), 0, true), 64, 64);
    }

    public static LayerDefinition createBodyLayer() {
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidModel.createBodyLayer()");
        return LayerDefinition.create(createMesh(new CubeDeformation(0F), 0, false), 64, 64);
    }

    public static MeshDefinition createMesh(CubeDeformation cube, float yOffset, boolean useTwinkledFace) {
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

        // PartDefinition pHead = root.addOrReplaceChild("pHead",
        // CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8, cube),
        // PartPose.offset(0, yOffset, 0));
        PartDefinition pHead = null;
        if (useTwinkledFace) {
            pHead = root.addOrReplaceChild("pHead",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8,
                            EnumSet.of(Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN)),
                    PartPose.offset(0, yOffset, 0));
            pHead.addOrReplaceChild("pFace",
                    CubeListBuilder.create().texOffs(8, 8).addBox(-4, -8, -4, 8, 8, 0,
                            EnumSet.of(Direction.NORTH)),
                    PartPose.offset(0, yOffset, 0));
            pHead.addOrReplaceChild("pFace_twinkled",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 0,
                            EnumSet.of(Direction.NORTH)),
                    PartPose.offset(0, yOffset, 0));
        } else {
            pHead = root.addOrReplaceChild("pHead",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8,
                            cube),
                    PartPose.offset(0, yOffset, 0));
            pHead.addOrReplaceChild("pFace", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
            pHead.addOrReplaceChild("pFace_twinkled", CubeListBuilder.create(),
                    PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        }
        PartDefinition pBody = root.addOrReplaceChild("pBody",
                CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, cube),
                PartPose.offset(0, yOffset, 0));
        PartDefinition pArmL = pBody.addOrReplaceChild("pArmL",
                CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -0.5F, -2.0F, 3, 12, 4, cube),
                PartPose.offset(5.0F, 0.0F + yOffset, 0.0F));
        PartDefinition pArmR = pBody.addOrReplaceChild("pArmR",
                CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -0.5F, -2.0F, 3, 12, 4, cube),
                PartPose.offset(-5.0F, 0.0F + yOffset, 0.0F));
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
                PartPose.offset(0, 0 + yOffset + 0.003F, -4 + 0.05F + 0.002F));

        pHead.addOrReplaceChild("pHeadWear",
                CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, cube.extend(0.5F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pBody.addOrReplaceChild("pBodyWear",
                CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, cube.extend(0.45F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pArmL.addOrReplaceChild("pArmLWear",
                CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -0.5F, -2.0F, 3, 12, 4, cube.extend(0.4F)),
                PartPose.offset(0, 0 + yOffset, 0));
        pArmR.addOrReplaceChild("pArmRWear",
                CubeListBuilder.create().texOffs(40, 32).addBox(-2.0F, -0.5F, -2.0F, 3, 12, 4, cube.extend(0.4F)),
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
        pBody.addOrReplaceChild("pShippo",
                CubeListBuilder.create().texOffs(54, 16).addBox(-5.0F, 0.0F, 0.0F, 10, 12, 0,
                        EnumSet.of(Direction.NORTH)),
                PartPose.offset(0, 10 + yOffset, 2));

        // pHead.addOrReplaceChild("frogTongue",
        // CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -0.5F, -3F, 4, 0, 7,
        // EnumSet.of(Direction.UP)),
        // PartPose.offset(0, yOffset - 0.5F, -3F));

        return mesh;
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.pHead);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.pBody);
    }

    @SuppressWarnings("null")
    @Override
    public void prepareMobModel(E entity, float limbSwing, float limbSwingAmount, float ticks) {
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
        this.pHead.setPos(0, 0, 0);
        this.pHead.setRotation(0, 0, 0);
        this.pBody.setPos(0, 0, 0);
        this.pBody.setRotation(0, 0, 0);
        this.pArmL.setPos(5.0F, 0.5F, 0.0F);
        this.pArmL.setRotation(0, 0, 0);
        this.pArmR.setPos(-5.0F, 0.5F, 0.0F);
        this.pArmR.setRotation(0, 0, 0);
        this.pLegL.setPos(2.2F, 12.0F, 0.0F);
        this.pLegL.setRotation(0, 0, 0);
        this.pLegR.setPos(-2.2F, 12.0F, 0.0F);
        this.pLegR.setRotation(0, 0, 0);
        this.pBUpper.setPos(0, 0, -2);
        this.pBUpper.setRotation(0, 0, 0);
        this.pBLower.setPos(0, 0, -4);
        this.pBLower.setRotation(0, 0, 0);

        this.pHeadWear.setPos(0, 0, 0);
        this.pHeadWear.setRotation(0, 0, 0);
        this.pBodyWear.setPos(0, 0, 0);
        this.pBodyWear.setRotation(0, 0, 0);
        this.pArmLWear.setPos(0, 0, 0);
        this.pArmLWear.setRotation(0, 0, 0);
        this.pArmRWear.setPos(0, 0, 0);
        this.pArmRWear.setRotation(0, 0, 0);
        this.pLegLWear.setPos(0, 0, 0);
        this.pLegLWear.setRotation(0, 0, 0);
        this.pLegRWear.setPos(0, 0, 0);
        this.pLegRWear.setRotation(0, 0, 0);

        this.pAhoge.setPos(0, -8, 1);
        this.pAhoge.setRotation(0, 0, 0);
        this.pKemomimi.setPos(0, -8, -1);
        this.pKemomimi.setRotation(0, 0, 0);

        this.pFace.visible = true;
        this.pFace_twinkled.visible = false;

        // this.frogTongue.setPos(0, -0.5F, 3);
        // this.frogTongue.setRotation(0, 0, 0);
        // this.frogTongue.zScale = 0F;

        this.limbSwing = 0;
        this.limbSwingAmount = 0;
        this.ageInTicks = 0;
        this.headRotY = 0;
        this.headRotX = 0;
        this.isChild = false;
        this.isAggressive = false;
        this.isCrouching = false;
        this.isSittingOnGround = false;
        this.isJumping = false;
        this.isSwimming = false;
        this.isFlying = false;
        this.isDying = false;
        this.isShaking = false;

        this.hasAnything = false;
        this.hasItem = false;
        this.hasBlock = false;
        this.hasFood = false;
        this.hasBow = false;
        this.isInterested = false;
        this.isCreepy = false;
        this.isSwelling = false;
        this.isClimbing = false;
        this.eggTime = 9999;
        this.isEating = false;
    }

    public void setEntityStatus(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {
        this.entity = entity;
        // this.entityId = entity.getId();
        this.entityId = this.entity.getUUID().hashCode();
        this.limbSwing = limbSwing * this.walkSwingSpeed;
        this.limbSwingAmount = limbSwingAmount / this.walkSwingSpeed;
        // TODO: change to /1000 from /100
        this.ageInTicks = (float) Util.getMillis() / 100f + PMath.getEntityRand(this, "ageInTicks") * 60 * 100f;
        this.headRotY = netHeadYaw;
        this.headRotX = headPitch;
        this.isChild = this.entity.isBaby();
        this.isAggressive = entity.isAggressive();
        this.isCrouching = entity.isCrouching();
        this.hasAnything = !this.entity.getMainHandItem().isEmpty();
        this.hasItem = this.hasAnything;
        // this.hasBlock = false;
        this.hasFood = this.entity.getMainHandItem().isEdible();
        this.hasBow = this.entity.getMainHandItem().is(Items.BOW) || this.entity.getMainHandItem().is(Items.CROSSBOW);

        this.hasPassengers = !entity.getPassengers().isEmpty();
        this.isShaking = entity.isFullyFrozen();

        if (entity instanceof Creeper) {
            this.isSwelling = ((Creeper) entity).getSwelling(this.limbSwingAmount) > 0F;
        } else if (entity instanceof EnderMan) {
            this.hasBlock = ((EnderMan) entity).getCarriedBlock() != null;
            this.isAggressive = ((EnderMan) entity).isCreepy();
        } else if (entity instanceof Spider) {
            this.isClimbing = ((Spider) entity).isClimbing();
        } else if (entity instanceof Chicken) {
            // TODO: create chicken egg animation.
            this.eggTime = (float) ((Chicken) entity).eggTime / 100F;
        } else if (entity instanceof Sheep) {
            this.isEating = ((Sheep) entity).getHeadEatPositionScale(0F) != 0F;
        } else if (entity instanceof Rabbit) {
            this.isJumping = ((Rabbit) entity).getJumpCompletion(0F) > 0.0F;
        } else if (entity instanceof SnowGolem) {
            this.pHeadWear.visible = ((SnowGolem) entity).hasPumpkin();
        } else if (entity instanceof Blaze) {
            if (((Blaze) entity).isOnFire())
                this.isAggressive = true;
        } else if (entity instanceof Ghast) {
            if (((Ghast) entity).isCharging())
                this.isAggressive = true;
        } else if (entity instanceof MagmaCube) {
            this.modelScale = ((MagmaCube) entity).getScale() * 0.55F;
        } else if (entity instanceof Strider) {
            this.isShaking = this.isShaking || ((Strider) entity).isSuffocating();
        }

        // set pose
        if (!this.entity.onGround()) {
            if (this.entity.isInWaterOrBubble()) {
                this.isSwimming = true;
            } else {
                this.isFlying = true;
            }
        } else if (this.isFlying) {
            this.isFlying = false;
        }

        if (!this.entity.isInWaterOrBubble() && this.isSwelling) {
            this.isSwelling = false;
        }
        if (this.entity.isDeadOrDying()) {
            this.isDying = true;
        }

        this.prepareMobModel(this.entity, this.limbSwing, this.limbSwingAmount, this.ageInTicks);
    }

    @SuppressWarnings("null")
    @Override
    public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {

        this.resetPartsPosAndRot();
        this.setEntityStatus(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // from 1.14.4 PMM2
        if (this.setPreAnimations()) {
            return;
        }

        // 頭の回転
        this.setHeadYawAndPitch();

        // 何もしてないときのモーション
        this.setStayAnimations();

        if (this.hasPassengers) {
            this.setSneakAnimations();
            this.pBody.z = this.pHead.z = -2f;
        }

        if (this.isFloating) {
            // 浮遊するモブのモーション
            this.setFloatingAnimations();
        } else
        // 歩いてるときのモーション
        if (this.limbSwingAmount > 0.01F) {
            this.setWalkingAnimations();
        }

        // 手に何か持ってるとき
        this.setArmHasAnythingAnimations(this.pArmL, this.leftArmPose);
        this.setArmHasAnythingAnimations(this.pArmR, this.rightArmPose);

        // スニーク時のモーション
        if (((Mob) this.entity).getPose() == Pose.CROUCHING) {
            this.setSneakAnimations();
        }

        // プレイヤーに迫ってくるときのモーション
        if (this.isAggressive || this.forwardArm) {
            setAggressiveAnimations();
        }

        if (!this.isFloating) {
            // 水中にいるとき
            if (this.isSwimming) {
                this.setSwimmingAnimations();
            }

            // 空中にいるの時のモーション
            if (this.isFlying || this.isJumping) {
                if (doFlyFlap)
                    this.setFlapFlyingAnimations();
                else
                    this.setJumpAnimations();
            }

            if (this.isClimbing) {
                this.setClimbingAnimations();
            }
        }
        

        // 乗ってる時のモーション
        if (this.riding) {
            this.setRidingAnimations();
            // this.setSittingAnimations();
        }

        if (this.isShaking) {
            // PeopleMobsMod2.DEBUG("is shaking");
            this.setShakingAnimations();
        }

        if (this.isEating) {
            this.setSittingAnimations();
        }

        if (this.eggTime < 5F) { // 動作してるか…?
            this.setSwellingAnimations();
        }

        // ダメージ時のモーション
        if (this.entity.hurtTime > 1) {
            this.setDamagedAnimations();
        }

        // 爆発しそうなモーション
        if (this.isSwelling) {
            this.setSwellingAnimations();
        }

        this.setAddAnimations();

        // 死亡時のモーション
        if (this.isDying) {
            setDeadAnimations();
        }

        this.setBAnimations();
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

    /** 浮遊するモブのモーション */
    protected void setFloatingAnimations() {
        float f1 = PMath.sin1(this.ageInTicks / 15.7f);
        float f2 = PMath.sin1(this.ageInTicks / 15.7f - 0.2F);

        this.pHead.y = this.pBody.y = -this.floatingHeight - 1F + f1 * 1.5F;
        this.pBody.xRot = PMath.toRad(-5.7F + f1 * 1.7F);
        // PeopleMobsMod2.DEBUG("setFloatingAnimations:", f1, f2);

        this.pArmR.zRot = PMath.toRad(28F - f2 * 8.6F);
        this.pArmL.zRot = PMath.toRad(-28F + f2 * 8.6F);
        this.pArmR.xRot = PMath.toRad(14F);
        this.pArmL.xRot = PMath.toRad(14F);

        this.pLegR.zRot = PMath.toRad(8.6F);
        this.pLegL.zRot = PMath.toRad(-8.6F);
        this.pLegR.xRot = PMath.toRad(17F - f2 * 5.7F);
        this.pLegL.xRot = PMath.toRad(17F - f2 * 5.7F);

        this.pBody.xRot += PMath.toRad(this.limbSwingAmount * 57F);
        this.pArmR.xRot -= PMath.toRad(this.limbSwingAmount * 57F);
        this.pArmL.xRot -= PMath.toRad(this.limbSwingAmount * 57F);
        this.pLegR.xRot -= PMath.toRad(this.limbSwingAmount * 11.4F);
        this.pLegL.xRot -= PMath.toRad(this.limbSwingAmount * 11.4F);
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
            float f = (PMath.abs(PMath.sin1(limbSwing / 4.7F)) * 2F
                    - 1F * (this.isChild || this.useChildModel ? 0.5F : 1F)) * limbSwingAmount * this.modelScale;
            this.pBody.y -= f;
            this.pHead.y -= f;
            // PeopleMobsMod2.DEBUG("walking body bounce", f);
        }
    }

    /** 何かに乗ってる時のモーション */
    protected void setRidingAnimations() {
        this.pBody.y = this.pHead.y = 12f;

        this.pArmR.zRot = -PMath.toRad(0);
        this.pArmR.xRot = PMath.toRad(-45);
        this.pArmR.yRot = -PMath.toRad(35);
        this.pArmL.zRot = PMath.toRad(0);
        this.pArmL.xRot = PMath.toRad(-45);
        this.pArmL.yRot = PMath.toRad(35);

        this.pLegR.xRot = PMath.toRad(-80);
        this.pLegR.yRot = PMath.toRad(5);
        this.pLegR.zRot = PMath.toRad(4.5F);
        this.pLegL.xRot = PMath.toRad(-80);
        this.pLegL.yRot = PMath.toRad(-5);
        this.pLegL.zRot = PMath.toRad(-4.5F);

        this.pLegL.z = -6f;
        this.pLegR.z = -6f;
    }

    protected void setArmPoses() {
        ArmPose mainArmPose = this.entity.getMainArm() == HumanoidArm.LEFT ? this.leftArmPose : this.rightArmPose;
        ArmPose otherArmPose = this.entity.getMainArm() == HumanoidArm.LEFT ? this.rightArmPose : this.leftArmPose;

        mainArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
        otherArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
        ItemStack itemStack = this.entity.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemStack.is(Items.BOW)) {
            mainArmPose = PMM2_HumanoidModel.ArmPose.BOW_AND_ARROW;
        } else if (this.hasBlock) {
            mainArmPose = PMM2_HumanoidModel.ArmPose.BLOCK;
        } else if (itemStack.isEmpty()) {

        } else {
            mainArmPose = PMM2_HumanoidModel.ArmPose.ITEM;
        }

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
                // arm.xRot = arm.xRot * 0.5F - PMath.toRad(18);
                // arm.yRot = PMath.toRad(30) * armSide;
                this.pArmL.xRot = -1.0F - this.pBody.xRot * 0.5F;
                this.pArmR.xRot = -1.0F - this.pBody.xRot * 0.5F;
                this.pArmL.yRot = 0.0F;
                this.pArmR.yRot = 0.0F;
                this.pArmL.zRot = 0.0F;
                this.pArmR.zRot = 0.0F;
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
                // TODO: create ThrowSpear animations 投げ槍
                break;
            case CROSSBOW_CHARGE:
                // _TODO_: create CROSSBOW animations
                break;
            case CROSSBOW_HOLD:
                break;
            // player?
            // case SPYGLASS:
            // // _TODO_: create SPYGLASS animations
            // break;
            // case TOOT_HORN:
            // // _TODO_: create TOOT_HORN animations
            // break;
            // case BRUSH:
            // // _TODO_: create BRUSH animations
            // break;
            default:
                break;
        }
    }

    /** スニーク時のモーション */
    protected void setSneakAnimations() {
        this.pBody.xRot += 0.5F;
        this.pArmR.xRot -= 0.2F;
        this.pArmL.xRot -= 0.2F;
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
        this.pArmR.y = 0.2F + PMath.cos1(this.limbSwing / 2.3F - 2.2F) * 0.5F + 0.5F;
        this.pArmL.y = 0.2F + PMath.cos1(this.limbSwing / 2.3F - 2.2F) * 0.5F + 0.5F;
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
        float vy = (float) this.entity.getDeltaMovement().y;
        float vz = PMath.easeOut(
                PMath.clamp(PMath.abs((float) this.entity.getDeltaMovement().horizontalDistance() / 0.5F), 0, 1));
        // PeopleMobsMod2.DEBUG("setSwimmingAnimations", vy, vz);
        this.pLegR.xRot = PMath.toRad(PMath.cos1(this.ageInTicks / 10F) * -30F + 12F + 23F * (1 - vy));
        this.pLegL.xRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 10F) * -30F + 12F + 23F * (1 - vy));
        this.pLegR.zRot = PMath.toRad(2.8F);
        this.pLegL.zRot = PMath.toRad(-2.8F);

        this.pArmR.zRot = PMath.toRad(PMath.cos1(this.ageInTicks / 14F) * (75F + 45F * vy) + 57F + 75F * vy);
        this.pArmL.zRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 14F) * (75F + 45F * vy) - 57F - 75F * vy);
        this.pArmR.xRot = PMath.toRad((PMath.cos1(this.ageInTicks / 14F) * -45F - 57F) * (1 - vy));
        this.pArmL.xRot = PMath.toRad((PMath.cos1(this.ageInTicks / 14F) * -45F - 57F) * (1 - vy));

        this.pBody.xRot = PMath.toRad(11.4F + 78F * (vy + vz));
        this.pHead.z = this.pBody.z = -6F + -6F * (float) this.entity.getDeltaMovement().length();
        this.pHead.y = this.pBody.y = 16F * PMath.sin(this.pBody.xRot);
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
    // Entity個別のアニメーション
    //

    // 膨張アニメーション：クリーパー
    protected void setSwellingAnimations() {
        this.pArmR.zRot = PMath.toRad(-15F);
        this.pArmL.zRot = PMath.toRad(15F);
        this.pArmR.xRot = PMath.toRad(-20F);
        this.pArmL.xRot = PMath.toRad(-20F);
        this.pArmR.z = -1.5F;
        this.pArmL.z = -1.5F;

        this.pLegR.zRot = PMath.toRad(-2F);
        this.pLegL.zRot = PMath.toRad(2F);
        this.pLegR.xRot = PMath.toRad(-36F);
        this.pLegL.xRot = PMath.toRad(-36F);

        this.pBody.xRot = PMath.toRad(12.6F);
        this.pHead.yRot = PMath.toRad((PMath.sin1(ageInTicks / 30F) * 18F));
        this.pHead.xRot = PMath.toRad(16.2F);

        this.pFace.visible = !(this.pFace_twinkled.visible = true);
    }

    // 羽ばたくアニメーション
    protected void setFlapFlyingAnimations() {

        float f1 = PMath.sin1(this.ageInTicks / (this.limbSwingAmount < 0.5F ? 2.5F : 1.8F));
        float f2 = PMath.cos1(this.ageInTicks / (this.limbSwingAmount < 0.5F ? 2.5F : 1.8F) + 0.028F);

        if (this.entity.getDeltaMovement().y < 0.0F) {
            f1 = 0.02F;
        }

        this.pBody.xRot += PMath.toRad(this.limbSwingAmount * 86F - 11.5F);
        this.pHead.xRot += PMath.toRad(this.limbSwingAmount * 28.6F);
        this.pLegR.zRot = PMath.toRad(8.6F);
        this.pLegL.zRot = PMath.toRad(-8.6F);
        this.pLegR.xRot = this.pLegL.xRot = PMath.toRad(PMath.lerp(-8.6F, 17F, this.limbSwingAmount) - f2 * 5.7F);
        this.pArmR.zRot = 90F;
        this.pArmL.zRot = -90F;
        this.pArmR.xRot = PMath.toRad(f1 * 57F);
        this.pArmL.xRot = PMath.toRad(f1 * 57F);
        this.pArmR.yRot = -90F + this.limbSwingAmount * 70F;
        this.pArmL.yRot = 90F - this.limbSwingAmount * 70F;
    }

    // 登ってるアニメーション
    protected void setClimbingAnimations() {
        this.pArmR.yRot = PMath.toRad(28.6F);
        this.pArmL.yRot = PMath.toRad(-28.6F);
        this.pArmR.xRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 4.7F) * 42F);
        this.pArmL.xRot = PMath.toRad(PMath.cos1(this.ageInTicks / 4.7F) * 42F);
        this.pArmR.zRot = PMath.toRad(17F);
        this.pArmL.zRot = PMath.toRad(-17F);
        this.pLegR.zRot = PMath.toRad(4.2F);
        this.pLegL.zRot = PMath.toRad(-4.2F);
        this.pLegR.yRot = PMath.toRad(8.5F);
        this.pLegL.yRot = PMath.toRad(-8.5F);
        this.pLegR.xRot = PMath.toRad(PMath.cos1(this.ageInTicks / 4.7F) * 80F);
        this.pLegL.xRot = -PMath.toRad(PMath.cos1(this.ageInTicks / 4.7F) * 80F);
        this.pLegR.z = PMath.sin1(this.ageInTicks / 4.7F) * 1F + 1F;
        this.pLegL.z = -PMath.sin1(this.ageInTicks / 4.7F) * 1F + 1F;

        this.pArmR.xRot -= PMath.toRad(90F);
        this.pArmL.xRot -= PMath.toRad(90F);
        this.pLegR.xRot -= PMath.toRad(45F);
        this.pLegL.xRot -= PMath.toRad(45F);
    }

    /** 震えているアニメーション */
    protected void setShakingAnimations() {
        this.setSneakAnimations();
        this.pBody.yRot += PMath.toRad(PMath.cos1(this.ageInTicks * 5f) * 3f);
        this.pHead.yRot += PMath.toRad(PMath.cos1(this.ageInTicks * 5f) * 3f);
    }

    /**
     * 座りモーション（任意呼び出し）
     */
    protected void setSittingAnimations() {
        this.pBody.xRot += 0.3F;
        this.pArmR.yRot = -60F * PMath.Deg2Rad;
        this.pArmL.yRot = 60F * PMath.Deg2Rad;
        this.pArmR.xRot = -0.25F;
        this.pArmL.xRot = -0.25F;
        this.pArmR.zRot = -0.18F;
        this.pArmL.zRot = 0.18F;
        this.pLegR.yRot = 30F * PMath.Deg2Rad;
        this.pLegL.yRot = -30F * PMath.Deg2Rad;
        this.pLegR.xRot = -80F * PMath.Deg2Rad;
        this.pLegL.xRot = -80F * PMath.Deg2Rad;
        this.pHead.y = 5F;
        this.pBody.y = 5F;
        this.pShippo.xRot += 3F;
    }

    /**
     * 眠りモーション（任意呼び出し）
     */
    protected void setSleepingAnimations() {
        float f = PMath.sin1(this.ageInTicks / (50f + PMath.getEntityRand(this, "sleepMotion") * 20f));
        float d = (PMath.getEntityRand(this, "sleepMotion") < 0.5f)? 1: -1;

        this.pBody.y = this.pHead.y = 24f - 12f;

        this.pBody.xRot = PMath.toRad(60f);

        this.pHead.xRot = PMath.toRad(60f);
        this.pHead.yRot = PMath.toRad(30f * d);
        this.pHead.zRot = PMath.toRad(-30f * d);

        this.pArmL.xRot = PMath.toRad(-90f - 20f);
        this.pArmL.yRot = PMath.toRad(0f);
        this.pArmL.zRot = -PMath.toRad(20f);
        this.pArmR.xRot = PMath.toRad(-90f - 20f);
        this.pArmR.yRot = PMath.toRad(0f);
        this.pArmR.zRot = -PMath.toRad(-20f);

        this.pLegL.xRot = PMath.toRad(-90f - 50f);
        this.pLegL.yRot = PMath.toRad(0f);
        this.pLegL.zRot = -PMath.toRad(-40f);
        this.pLegR.xRot = PMath.toRad(-90f - 50f);
        this.pLegR.yRot = PMath.toRad(0f);
        this.pLegR.zRot = -PMath.toRad(40f);

        this.pLegL.z = -4f;
        this.pLegR.z = -4f;

        this.pBody.xRot += PMath.toRad(f * 3f - 3f);
        this.pHead.xRot += PMath.toRad(f * 1f);
        this.pBody.y += (f * 0.5f + 0.5f);
        this.pHead.y += (f * 0.5f + 0.5f);
        this.pArmL.xRot -= PMath.toRad(f * 4f - 3f);
        this.pArmR.xRot -= PMath.toRad(f * 4f - 3f);
        this.pLegL.xRot -= PMath.toRad(f * 3f - 3f);
        this.pLegR.xRot -= PMath.toRad(f * 3f - 3f);
        
        this.pFace.visible = !(this.pFace_twinkled.visible = true);
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
        this.pHead.yRot = PMath.toRad((PMath.sin1(this.ageInTicks / 30) * 18));
        this.pHead.xRot = PMath.toRad(16);

        this.pFace.visible = !(this.pFace_twinkled.visible = true);
    }

    /** Bのアニメーション */
    protected void setBAnimations() {
        float hOffset = PMath.getEntityRand(this, "bHOffset") * 0.3F - 0.1F;
        float h = PMath.clamp(this.bHeight + hOffset, 0F, 1F);
        if (h == 0)
            return;
        float bXRot = (90F * PMath.Deg2Rad) - PMath.asin(h / 1.41421356F);

        this.pBUpper.y = h * 0.5F;
        this.pBUpper.xRot = bXRot;

        if (this.bHeight + hOffset > 1) {
            this.pBUpper.xScale = this.pBUpper.yScale = this.pBUpper.zScale = this.bHeight + hOffset;
        }

        if (true /* this.boobsSwing */) {
            float moveY = (float) this.entity.getDeltaMovement().y;
            this.pBUpper.y += PMath.clamp(
                    -((-this.pBody.y + this.floatingHeight) + moveY * 1F) * h * 1.5F,
                    1.0F, -0.5F);
            this.pBUpper.xRot += PMath.toRad(PMath.clamp(
                    ((-this.pBody.y + this.floatingHeight) + moveY * 1F) * h * 50F,
                    (90f - bXRot / PMath.Deg2Rad) / 2,
                    -10F));
        }

        float f = h;
        if (h > 0.5F)
            f = 1.0F - f;
        this.pBLower.xRot = PMath.clamp(-(this.pBUpper.xRot - PMath.PI / 2) * (2 + f),
                PMath.PI / 2 * 0.2F,
                PMath.PI / 2 * 0.95F);
    }

    /** しっぽのアニメーション */
    protected void setShippoAnimations() {
        // TODO: Swing left right with walking.
        if (true /* this.shippoSwing */) {
            this.pShippo.xRot = PMath.toRad(28F + (float) this.entity.getDeltaMovement().length() * 230F);
            if (this.doWalkBounding)
                this.pShippo.xRot += PMath.toRad(PMath.abs(PMath.cos1(limbSwing * 0.211F - 0.2F))
                        * this.limbSwingAmount * 45F);
        }
    }

    /** あほげとけもみみのアニメーション */
    protected void setAhogeAnimations() {
        float f1 = PMath.toRad((float) this.entity.getDeltaMovement().length() * -343F);
        if (this.doWalkBounding)
            f1 += PMath.toRad(PMath.abs(PMath.cos1(limbSwing * 0.211F - 0.2F)) * this.limbSwingAmount * 57F);
        if (true /* this.ahogeSwing */)
            this.pAhoge.xRot = f1;
        if (true /* this.kemomimiSwing */)
            this.pKemomimi.xRot = f1 * 0.65F;
    }

    /** まばたき */
    protected void setTwinkleAnimations() {
        if (!this.twinklesTimes.containsKey(entityId)) {
            if (this.twinklesTimes.size() > 300) {
                this.twinklesTimes.clear();
            }
            this.twinklesTimes.put(entityId, (float) Math.random() * 8F);
        }
        if (!this.twinkledNow.containsKey(entityId)) {
            if (this.twinkledNow.size() > 300) {
                this.twinkledNow.clear();
            }
            this.twinkledNow.put(entityId, false);
        }
        float twTime = this.twinklesTimes.get(entityId);
        twTime -= 0.05F;
        float hash = (Util.getMillis() / 10) % 1;
        if (this.twinkledNow.get(this.entityId)) {
            if (twTime < 0.0F) {
                this.twinkledNow.put(this.entityId, false);
                twTime = (float) Math.random() * 8 + hash;
            }
        } else {
            if (twTime < 0.0F) {
                this.twinkledNow.put(this.entityId, true);
                twTime = (float) Math.random() + hash;
            }
        }

        this.twinklesTimes.put(entityId, twTime);
        this.pFace.visible = !(this.pFace_twinkled.visible = this.twinkledNow.get(this.entityId));
    }

    protected void setPostAnimations() {
    }

    public void copyPropertiesTo(PMM2_HumanoidModel<E> model) {
        super.copyPropertiesTo(model);

        model.leftArmPose = this.leftArmPose;
        model.rightArmPose = this.rightArmPose;
        model.crouching = this.crouching;

        model.pHead.copyFrom(this.pHead);
        model.pBody.copyFrom(this.pBody);
        model.pArmR.copyFrom(this.pArmR);
        model.pArmL.copyFrom(this.pArmL);
        model.pLegR.copyFrom(this.pLegR);
        model.pLegL.copyFrom(this.pLegL);
        model.pShippo.copyFrom(this.pShippo);
        model.pAhoge.copyFrom(this.pAhoge);
        model.pKemomimi.copyFrom(this.pKemomimi);
        model.pFace.copyFrom(this.pFace);
        model.pFace_twinkled.copyFrom(this.pFace_twinkled);
        model.pHeadWear.copyFrom(this.pHeadWear);
        model.pBodyWear.copyFrom(this.pBodyWear);
        model.pArmRWear.copyFrom(this.pArmRWear);
        model.pArmLWear.copyFrom(this.pArmLWear);
        model.pLegRWear.copyFrom(this.pLegRWear);
        model.pLegLWear.copyFrom(this.pLegLWear);

        model.doWalkBounding = this.doWalkBounding;
        model.useChildModel = this.useChildModel;
        model.doFlyFlap = this.doFlyFlap;
        model.forwardArm = this.forwardArm;
        model.modelScale = this.modelScale;
        model.bHeight = this.bHeight;
        model.isFloating = this.isFloating;
        model.floatingHeight = this.floatingHeight;

        model.entity = this.entity;
        model.entityId = this.entityId;
        model.limbSwing = this.limbSwing;
        model.limbSwingAmount = this.limbSwingAmount;
        model.ageInTicks = this.ageInTicks;
        model.headRotY = this.headRotY;
        model.headRotX = this.headRotX;
        model.isChild = this.isChild;
        model.isAggressive = this.isAggressive;
        model.isCrouching = this.isCrouching;
        model.isSittingOnGround = this.isSittingOnGround;
        model.isJumping = this.isJumping;
        model.isSwimming = this.isSwimming;
        model.isFlying = this.isFlying;
        model.isDying = this.isDying;
        model.hasPassengers = this.hasPassengers;
        model.hasAnything = this.hasAnything;
        model.hasItem = this.hasItem;
        model.hasBlock = this.hasBlock;
        model.hasFood = this.hasFood;
        model.hasBow = this.hasBow;
        model.isInterested = this.isInterested;
        model.isCreepy = this.isCreepy;
        model.isSwelling = this.isSwelling;
        model.isClimbing = this.isClimbing;
        model.eggTime = this.eggTime;
        model.isEating = this.isEating;
    }

    public void setAllVisible(boolean allVisible) {
        this.pHead.visible = allVisible;
        this.pBody.visible = allVisible;
        this.pArmR.visible = allVisible;
        this.pArmL.visible = allVisible;
        this.pLegR.visible = allVisible;
        this.pLegL.visible = allVisible;
        this.pShippo.visible = allVisible;
        this.pAhoge.visible = allVisible;
        this.pKemomimi.visible = allVisible;
        this.pFace.visible = allVisible;
        this.pFace_twinkled.visible = allVisible;
        this.pHeadWear.visible = allVisible;
        this.pBodyWear.visible = allVisible;
        this.pArmRWear.visible = allVisible;
        this.pArmLWear.visible = allVisible;
        this.pLegRWear.visible = allVisible;
        this.pLegLWear.visible = allVisible;
    }

    @SuppressWarnings("null")
    public void translateToHand(HumanoidArm arm, PoseStack pose) {
        this.getArm(arm).translateAndRotate(pose);
    }

    @SuppressWarnings("null")
    protected ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.pArmL : this.pArmR;
    }

    public ModelPart getHead() {
        return this.pHead;
    }

    // extended methods

    // AgeableListModel

    @SuppressWarnings("null")
    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer vertex, int i1, int overlayType,
            float col_r, float col_g, float col_b, float col_a) {

        float scale = this.modelScale + ((PMath.getEntityRand(this, "modelScale1") * 2f - 1f)
                + (PMath.getEntityRand(this, "modelScale2") * 2f - 1f)
                + (PMath.getEntityRand(this, "modelScale3") * 2f - 1f)) / 3f * 2F / 16F;

        pose.pushPose();
        pose.translate(0, (1F - scale) * 26F / 16F, 0);
        pose.scale(scale, scale, scale);

        if (this.young || this.isChild || this.useChildModel) {
            pose.pushPose();
            if (this.scaleHeadAtChild) {
                pose.translate(0, -this.pHead.y * (this.babyHeadScale - this.babyBodyScale) / 16F, 0);
                pose.scale(this.babyHeadScale, this.babyHeadScale, this.babyHeadScale);
            }

            pose.translate(0.0F, this.babyYHeadOffset / 16.0F, this.babyZHeadOffset / 16.0F);
            this.headParts().forEach((part) -> {
                part.render(pose, vertex, i1, overlayType, col_r, col_g, col_b,
                        col_a);
            });
            pose.popPose();
            pose.pushPose();
            pose.scale(this.babyBodyScale, this.babyBodyScale, this.babyBodyScale);
            pose.translate(0.0F, this.bodyYOffset / 16.0F, 0.0F);
            this.bodyParts().forEach((part) -> {
                part.render(pose, vertex, i1, overlayType, col_r, col_g, col_b,
                        col_a);
            });
            pose.popPose();

        } else {
            this.headParts().forEach((part) -> {
                part.render(pose, vertex, i1, overlayType, col_r, col_g, col_b,
                        col_a);
            });
            this.bodyParts().forEach((part) -> {
                part.render(pose, vertex, i1, overlayType, col_r, col_g, col_b,
                        col_a);
            });
        }
        pose.popPose();
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
