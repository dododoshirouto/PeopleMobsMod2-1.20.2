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
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Spider;
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
    public final ModelPart pEyelidL;
    public final ModelPart pEyelidR;
    public PMM2_HumanoidModel.ArmPose leftArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
    public PMM2_HumanoidModel.ArmPose rightArmPose = PMM2_HumanoidModel.ArmPose.EMPTY;
    // public boolean crouching;
    // public float swimAmount;

    protected Map<Integer, Float> twinklesTimes = new HashMap<Integer, Float>();
    protected Map<Integer, Boolean> twinkledNow = new HashMap<Integer, Boolean>();

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
    public boolean flyFlap = false;
    public boolean forwardArm = false;
    public float modelScale = 1.0F;
    public float bHeight = 0.5F;
    public boolean isFloating = false;
    public float floatingHeight = 0.0F;

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
    public boolean isCrouching;
    public boolean isSittingOnGround;
    public boolean isJumping;
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
    /** Fox */
    public float headRotZ;
    /** Fox */
    public boolean isSleeping;
    /** Fox 顔が突き刺さった状態 */
    public boolean isHeadInGround;
    /** Fox */
    public boolean isPouncing;

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
        // this.pShippo = null;
        // this.pEyebrowL = null;
        // this.pEyebrowR = null;
        this.pShippo = pBody.getChild("pShippo");
        this.pEyelidL = pHead.getChild("pEyelidL");
        this.pEyelidL.yRot = PMath.PI;
        this.pEyelidR = pHead.getChild("pEyelidR");

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
                CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, cube),
                PartPose.offset(5.0F, 1.5F + yOffset, 0.0F));
        PartDefinition pArmR = pBody.addOrReplaceChild("pArmR",
                CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, cube),
                PartPose.offset(-5.0F, 1.5F + yOffset, 0.0F));
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
        pBody.addOrReplaceChild("pShippo",
                CubeListBuilder.create().texOffs(54, 16).addBox(-5.0F, 0.0F, 0.0F, 10, 12, 0,
                        EnumSet.of(Direction.NORTH)),
                PartPose.offset(0, 10 + yOffset, 2));
        pHead.addOrReplaceChild("pEyelidL",
                CubeListBuilder.create().texOffs(12, 16).addBox(-3.0F, -4.0F, 2.1F, 2, 2, 2, cube),
                PartPose.offset(0, 0 + yOffset, 0));
        pHead.addOrReplaceChild("pEyelidR",
                CubeListBuilder.create().texOffs(12, 16).addBox(-3.0F, -4.0F, -4.1F, 2, 2, 2, cube),
                PartPose.offset(0, 0 + yOffset, 1.0F));

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
        this.pHead.setPos(0, 0, 0);
        this.pHead.setRotation(0, 0, 0);
        this.pBody.setPos(0, 0, 0);
        this.pBody.setRotation(0, 0, 0);
        this.pArmL.setPos(5.0F, 2.5F, 0.0F);
        this.pArmL.setRotation(0, 0, 0);
        this.pArmR.setPos(-5.0F, 2.5F, 0.0F);
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
        this.isCrouching = entity.isCrouching();
        this.hasAnything = !this.entity.getMainHandItem().isEmpty();
        /** TODO: see Enderman class. */
        this.hasItem = this.hasAnything;
        // this.hasBlock = false;
        this.hasFood = this.entity.getMainHandItem().isEdible();
        this.hasBow = this.entity.getMainHandItem().is(Items.BOW) || this.entity.getMainHandItem().is(Items.CROSSBOW);

        if (entity instanceof Creeper) {
            this.isSwelling = ((Creeper) entity).getSwelling(this.limbSwingAmount) > 0F;
        } else if (entity instanceof EnderMan) {
            // TODO: create carrying block animation.
            this.hasBlock = ((EnderMan) entity).getCarriedBlock() != null;
            // TODO: create enderman creepy animation.
            this.isCreepy = ((EnderMan) entity).isCreepy();
        } else if (entity instanceof Spider) {
            // TODO: create climbing animation.
            this.isClimbing = ((Spider) entity).isClimbing();
        } else if (entity instanceof Chicken) {
            // TODO: create chicken egg animation.
            this.eggTime = (float) ((Chicken) entity).eggTime / 100F;
        } else if (entity instanceof Sheep) {
            // TODO: create eating animation.
            this.isEating = ((Sheep) entity).getHeadEatPositionScale(0F) != 0F;
        } else if (entity instanceof Fox) {
            this.headRotZ = ((Fox) entity).getHeadRollAngle(1F);
            this.isSleeping = ((Fox) entity).isSleeping();
            this.isSittingOnGround = ((Fox) entity).isSitting();
            this.isHeadInGround = ((Fox) entity).isFaceplanted();
            this.isPouncing = ((Fox) entity).isPouncing();
            this.isJumping = ((Fox) entity).isJumping();
            this.isInterested = ((Fox) entity).isInterested();
        } else if (entity instanceof Rabbit) {
            this.isJumping = ((Rabbit) entity).getJumpCompletion(0F) > 0.0F;
        } else if (entity instanceof SnowGolem) {
            if (!((SnowGolem) entity).hasPumpkin()) {
                this.pHeadWear.visible = false;
            }
        }

        // TODO: create BatModel class.

        // set pose
        // TODO: set pose to rided when isChickenJockey, saddle
        if (!this.entity.onGround()) {
            if (this.entity.isInWaterOrBubble()) {
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
        if (((Mob) this.entity).getPose() == Pose.CROUCHING) {
            this.setSneakAnimations();
        }

        // プレイヤーに迫ってくるときのモーション
        if (this.isAggressive || this.forwardArm) {
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
        else if (entity.getPose() == Pose.FALL_FLYING || this.isJumping) {
            if (flyFlap)
                this.setFlapFlyingAnimations();
            else
                this.setJumpAnimations();
        }

        // 爆発しそうなモーション
        if (this.isSwelling) {
            this.setSwellingAnimations();
        }

        this.setAddAnimations();

        // 死亡時のモーション
        // if (((Mob) this.entity).getPose() == Pose.DYING) {
        if (this.entity.getPose() == Pose.DYING) {
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
                    - 1F * (this.isChild || this.useChildModel ? 0.5F : 1F)) * limbSwingAmount * this.modelScale;
            this.pHead.y -= (PMath.abs(PMath.sin1(limbSwing * 4.7F)) * 2F
                    - 1F * (this.isChild || this.useChildModel ? 0.5F : 1F)) * limbSwingAmount * this.modelScale;
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
                // TODO: create ThrowSpear animations 投げ槍
                break;
            // player? 
            // case CROSSBOW_CHARGE:
            // // _TODO_: create CROSSBOW animations
            //     break;
            // case CROSSBOW_HOLD:
            //     break;
            // case SPYGLASS:
            // // _TODO_: create SPYGLASS animations
            //     break;
            // case TOOT_HORN:
            // // _TODO_: create TOOT_HORN animations
            //     break;
            // case BRUSH:
            // // _TODO_: create BRUSH animations
            //     break;
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

        // this.bipedLeftEyelid.z = this.bipedRightEyelid.z = 0F;
        // this.bipedLeftEyelid.rotationPointY = this.bipedRightEyelid.rotationPointY =
        // 0F;
    }

    // 羽ばたくアニメーション
    protected void setFlapFlyingAnimations() {
        this.pArmR.zRot = PMath.toRad(PMath.cos1(this.ageInTicks / 2F) * 57F + 90F);
        this.pArmL.zRot = PMath.toRad(-PMath.cos1(this.ageInTicks / 2F) * 57F - 90F);
        this.pLegR.xRot = PMath.toRad(-(float) this.entity.getDeltaMovement().y * 225F);
        this.pLegL.xRot = PMath.toRad(-(float) this.entity.getDeltaMovement().y * 225F);
        this.pLegR.yRot = PMath.toRad((float) this.entity.getDeltaMovement().y * 60F);
        this.pLegL.yRot = PMath.toRad((float) this.entity.getDeltaMovement().y * 60F);
        this.pBody.yRot = PMath.toRad((float) this.entity.getDeltaMovement().y * 50F);
        this.pBody.yRot = PMath.toRad((float) this.entity.getDeltaMovement().y * 50F);
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

        this.pEyelidL.z = this.pEyelidR.z = 0F;
        this.pEyelidL.y = this.pEyelidR.y = 0F;
    }

    /** Bのアニメーション */
    protected void setBAnimations() {
        // TODO: Check b bounce.
        this.bHeight = PMath.max(this.bHeight, 0);
        if (this.bHeight == 0)
            return;
        this.pBUpper.y = this.bHeight * 1F;
        this.pBUpper.xRot = -PMath.asin(PMath.clamp(this.bHeight, 0, 1) / 1.4142F) + PMath.PI / 2;
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
        if (this.twinkledNow.get(this.entityId)) {
            this.pEyelidL.z = this.pEyelidR.z = 0F;
            this.pEyelidL.y = this.pEyelidR.y = 0F;
        } else {
            this.pEyelidL.z = this.pEyelidR.z = 0.2F;
            this.pEyelidL.y = this.pEyelidR.y = -2F;
        }
    }

    protected void setPostAnimations() {
    }

    @SuppressWarnings("null")
    public void copyPropertiesTo(PMM2_HumanoidModel<T> model) {
        super.copyPropertiesTo(model);
        // TODO: Copy all model options.
        // TODO: Copy all entity status.
        // TODO: Copy all parts.
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
        // TODO: Set all visible.
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
        return arm == HumanoidArm.LEFT ? this.pArmL : this.pArmR;
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

        if (this.young || this.isChild || this.useChildModel) {
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
            // TODO: Check foot on ground.
            pose.translate(0, (1F - this.modelScale) * 2F, 0);
            pose.scale(this.modelScale, this.modelScale, this.modelScale);

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
