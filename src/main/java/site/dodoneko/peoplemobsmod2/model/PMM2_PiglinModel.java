package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.piglin.Piglin;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @see net.minecraft.client.model.PiglinModel
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_PiglinModel extends PMM2_HumanoidModel<Piglin> {

    public boolean isDancing;

    public PMM2_PiglinModel(ModelPart root) {
        super(root);
    }

    @Override
    public void resetPartsPosAndRot() {
        super.resetPartsPosAndRot();
        this.isDancing = false;
    }

    @Override
    public void setEntityStatus(Piglin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {
        super.setEntityStatus(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.isDancing = entity.isDancing();
    }

    @Override
    protected void setAddAnimations() {
        if (this.isDancing) {

            this.entity.getUUID().hashCode();

            switch (PMath.floori(PMath.abs(this.entityId)/1772)%4) {
                case 0:
                    danceAnimation1();
                    break;
                case 1:
                    danceAnimation2();
                    break;
                case 2:
                    danceAnimation3();
                    break;
                case 3:
                    danceAnimation4();
                    break;
            }
        }
    }

    private void danceAnimation1() {
        float swing_1_0 = PMath.sin1((this.ageInTicks * 0.5F) / 10F);
        float swing_4_0 = PMath.sin1((this.ageInTicks * 2F) / 10F);
        float swing_4_1 = PMath.sin1((this.ageInTicks * 2F) / 10F - 0.15F);
        float swing_4_2 = PMath.sin1((this.ageInTicks * 2F) / 10F - 0.3F);
        float swing_4_3 = PMath.sin1((this.ageInTicks * 2F) / 10F - 0.45F);

        this.pHead.x = swing_1_0 * 0.75F;
        this.pHead.y = swing_4_0 * 0.5F + 0.5F;
        this.pBody.y = swing_4_1 * 0.5F + 0.5F;
        this.pArmL.y = swing_4_2 * 0.5F + 0.5F + 0.5F;
        this.pArmR.y = swing_4_2 * 0.5F + 0.5F + 0.5F;
        this.pLegL.y = swing_4_3 * 0.5F - 0.5F + 12.0F;
        this.pLegR.y = swing_4_3 * 0.5F - 0.5F + 12.0F;
        this.pArmL.zRot = PMath.toRad(-90F);
        this.pArmR.zRot = PMath.toRad(90F);
    }

    private void danceAnimation2() {
    float swing_1_0 = PMath.sin1((this.ageInTicks * 0.5f) / 10);
    float swing_1_1 = PMath.sin1((this.ageInTicks * 0.5f + 0.1f) / 10);
    float swing_1_2 = PMath.sin1((this.ageInTicks * 0.5f + 0.2f) / 10);

    float bodyRad = (1- PMath.pow(swing_1_0, 2)) * 15 + 15;

    this.pBody.xRot = PMath.toRad(bodyRad);
    this.pLegL.xRot = PMath.toRad(-bodyRad*2);
    this.pLegR.xRot = PMath.toRad(-bodyRad*2);

    if (PMath.floor(this.ageInTicks / 40)%2 == 0) {
        this.pArmR.yRot = PMath.toRad(-20);
        this.pArmR.xRot = PMath.toRad((1- PMath.pow(swing_1_1, 2)) * -10 -25);
        this.pArmR.zRot = PMath.toRad(-25);
        
        this.pArmL.xRot = PMath.toRad((1- PMath.pow(swing_1_2, 2)) * -10 -110);
    } else {
        this.pArmL.yRot = PMath.toRad(20);
        this.pArmL.xRot = PMath.toRad((1- PMath.pow(swing_1_1, 2)) * -10 -25);
        this.pArmL.zRot = PMath.toRad(25);
        
        this.pArmR.xRot = PMath.toRad((1- PMath.pow(swing_1_2, 2)) * -10 -110);
    }

    this.pLegL.zRot = PMath.toRad(-7);
    this.pLegR.zRot = PMath.toRad(7);

    this.pBody.y = this.pHead.y = (PMath.sinD(bodyRad))*4;
    }

    private void danceAnimation3() {
        float swing_1_0 = PMath.sin1((this.ageInTicks * 0.5f) / 10);
        float swing_1_1 = PMath.sin1((this.ageInTicks * 0.5f) / 10 + 0.1f);
        float swing_1_2 = PMath.sin1((this.ageInTicks * 0.5f) / 10 + 0.2f);
        float swing_1_3 = PMath.sin1((this.ageInTicks * 0.5f) / 10 + 0.3f);
        float swing_2_0 = PMath.sin1((this.ageInTicks * 0.5f - 0.4f) / 10);
    
        this.pBody.x = this.pHead.x = swing_1_0 * 2;
        this.pBody.y = (PMath.pow2(swing_1_0) - 1) * 0.5f;
        this.pHead.y = this.pBody.y + (PMath.pow2(swing_1_1) + 1) * 0.25f;
        this.pArmL.y = this.pArmR.y = (-PMath.pow2(swing_1_2) + 1) * 0.25f;
        this.pLegL.y = this.pLegR.y = (PMath.pow2(swing_1_3) + 1) * 0.25f + 12;

        pHead.xRot = PMath.toRad(-PMath.pow2(swing_1_2) * 5 + 10);
    
        this.pArmL.xRot = PMath.toRad(15);
        this.pArmL.yRot = PMath.toRad(10);
        this.pArmL.zRot = PMath.toRad(10);
        this.pArmR.xRot = PMath.toRad(15);
        this.pArmR.yRot = PMath.toRad(-10);
        this.pArmR.zRot = PMath.toRad(-10);
    
        this.pLegL.zRot = PMath.toRad(PMath.clamp(swing_2_0, 1, 0) * -10);
        this.pLegR.zRot = PMath.toRad(PMath.clamp(swing_2_0, -1, 0) * -10);
    }
    
    private void danceAnimation4() {
        float swing_1_0 = PMath.sin1((this.ageInTicks * 1f) / 10);
        float swing_1_1 = PMath.sin1((this.ageInTicks * 1f) / 10 + 0.1f);

        float bodyRotateAngleZ = 18;
        float bodyRotateAngleX = 20;
        this.pBody.zRot = PMath.toRad(swing_1_0 * bodyRotateAngleZ);
        this.pHead.zRot = PMath.toRad(swing_1_0 * -8);
        this.pLegL.zRot = PMath.toRad(PMath.clamp(swing_1_0, 1, -0.5f) * bodyRotateAngleZ * -2 + 3);
        this.pLegR.zRot = PMath.toRad(PMath.clamp(swing_1_0, -1, 0.5f) * bodyRotateAngleZ * -2 - 3);

        this.pHead.y = (PMath.abs(swing_1_1)) * 2;
        
        this.pBody.xRot = PMath.toRad(PMath.abs(swing_1_0) * bodyRotateAngleX);
        this.pLegL.xRot = PMath.toRad(-PMath.abs(swing_1_0) * bodyRotateAngleX*2);
        this.pLegR.xRot = PMath.toRad(-PMath.abs(swing_1_0) * bodyRotateAngleX*2);

        this.pArmL.xRot = PMath.toRad(swing_1_0 * 45 - 90);
        this.pArmR.xRot = PMath.toRad(-swing_1_0 * 45 - 90);

        this.pEyelidL.y = this.pEyelidL.z = this.pEyelidR.y = this.pEyelidR.z = 0;

        this.pBody.y = this.pHead.y = PMath.abs(PMath.sinD(swing_1_0 * bodyRotateAngleZ))*PMath.abs(PMath.sinD(swing_1_0 * bodyRotateAngleX))*4;
    }
}