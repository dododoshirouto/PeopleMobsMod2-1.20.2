package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.Fox;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PMM2_FoxModel extends PMM2_HumanoidModel<Fox> {

    public float headRotZ;
    public boolean isSleeping;
    public boolean isHeadInGround;
    public boolean isPouncing;

    public PMM2_FoxModel(ModelPart root) {
        super(root);
    }

    public void copyPropertiesTo(PMM2_FoxModel model) {
        model.headRotZ = this.headRotZ;
        model.isSleeping = this.isSleeping;
        model.isHeadInGround = this.isHeadInGround;
        model.isPouncing = this.isPouncing;
        super.copyPropertiesTo(model);
    }

    @Override
    public void setEntityStatus(Fox entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {
        super.setEntityStatus(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        
        this.isSittingOnGround = ((Fox) entity).isSitting();
        this.isJumping = ((Fox) entity).isJumping();
        this.isInterested = ((Fox) entity).isInterested();

        this.headRotZ = ((Fox) entity).getHeadRollAngle(1F);
        this.isSleeping = ((Fox) entity).isSleeping();
        this.isHeadInGround = ((Fox) entity).isFaceplanted();
        this.isPouncing = ((Fox) entity).isPouncing();
    }

    @Override
    protected void setAddAnimations() {
        if (this.isCrouching) {
            // かがんで地面のにおいを追跡
            this.setSneakAnimations();
            // this.pHead.xRot += -0.28F;
            this.pHead.yRot = PMath.toRad(PMath.sin1(this.ageInTicks / 22.4F) * 35F);
            this.pShippo.yRot = PMath.toRad(PMath.sin1(this.ageInTicks / 7.8F) * 18.3F);

            float f1 = PMath.abs(PMath.sin1(this.ageInTicks / 22.4F) * PMath.sin1(this.ageInTicks / 22.4F * 4));
            this.pHead.xRot += PMath.toRad((f1 > 0.5f ? f1 * 2 - 1 : 0) * 30F);
        } else

        if (this.isSleeping) {
            this.setSleepingAnimations();
        } else

        if (this.isSittingOnGround) {
            this.setSittingAnimations();
        } else

        if (this.isHeadInGround) {
            // System.out.println("fox is Fitting on snow");

            this.pArmL.zRot = -PMath.toRad(80F - 30F);
            this.pArmR.zRot = PMath.toRad(80F - 30F);
            this.pLegL.yRot = PMath.toRad(-38F);
            this.pLegL.yRot = PMath.toRad(38F);
            this.pLegL.xRot = PMath.toRad(74.5F);
            this.pLegR.xRot = PMath.toRad(74.5F);

            this.pBody.xRot += PMath.toRad(150F);
            this.pHead.xRot += PMath.toRad(150F);

            this.pHead.y += 24F;
            this.pBody.y += 24F;
        } else if (this.isJumping) {
            // System.out.println("fox is jumping");

            float fallingSpeed = PMath.clamp((float) this.entity.getDeltaMovement().y, -1.0F, 1.0F);
            this.pArmL.zRot = -PMath.toRad(80F - 30F * fallingSpeed);
            this.pArmR.zRot = PMath.toRad(80F - 30F * fallingSpeed);
            this.pLegL.yRot = PMath.toRad(-38F);
            this.pLegL.yRot = PMath.toRad(38F);
            this.pLegL.xRot = PMath.toRad(74.5F);
            this.pLegR.xRot = PMath.toRad(74.5F);

            float moveYRate = PMath.clamp(1F - ((float) this.entity.getDeltaMovement().y + 0.5F) / (1F), 0, 1);

            this.pBody.xRot += PMath.lerp(0, PMath.toRad(150F), moveYRate);
            this.pHead.xRot += PMath.lerp(0, PMath.toRad(150F), moveYRate);
        } else
        // if (this.entity.func_213490_ee()) {
        // // System.out.println("fox is jumping ?");
        // } else
        if (this.isInterested /* ? */) {
            // System.out.println("fox is ready to jump");
            // 仮りのモーション
            this.pBody.yRot = PMath.toRad(PMath.sin(this.ageInTicks / 6.2F) * 24F);
            this.setSneakAnimations();
        }
    }

    @Override
    public void setPostAnimations() {
        if (this.isCrouching) {
            // かがんで地面のにおいを追跡
        this.pFace.visible = !(this.pFace_twinkled.visible = true);
        } else if (this.isSleeping) {
            this.pFace.visible = !(this.pFace_twinkled.visible = true);
        } else if (this.isSittingOnGround) {
        } else if (this.isHeadInGround) {
        } else if (this.isJumping) {
        } else
        // if (this.entity.func_213490_ee()) {
        // } else
        if (this.isInterested) {
            // 仮りのモーション
            this.pBody.yRot = PMath.toRad(PMath.sin(this.ageInTicks / 6.2F) * 24F);
        }
    }
}
