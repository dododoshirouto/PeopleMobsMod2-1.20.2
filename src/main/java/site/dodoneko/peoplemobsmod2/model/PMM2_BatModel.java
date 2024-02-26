package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.ambient.Bat;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PMM2_BatModel extends PMM2_HumanoidModel<Bat> {
    public PMM2_BatModel(ModelPart root) {
        super(root);
    }

    @Override
    protected void setAddAnimations() {
        if (this.entity.isResting()) {
            this.pBody.xRot = PMath.toRad(150F);
            this.pLegL.xRot = this.pLegR.xRot = 0;
            this.pLegL.zRot = this.pLegR.zRot = 0;
            this.setSneakAnimations();
            this.pHead.xRot = this.pBody.xRot + PMath.toRad(17F);
            this.pHead.z = 1.5F;
            this.pBody.z = 1.5F;
            this.pArmR.zRot = PMath.toRad(15F);
            this.pArmL.zRot = PMath.toRad(-15F);
            this.pArmR.xRot = PMath.toRad(-130F);
            this.pArmL.xRot = PMath.toRad(-130F);
            this.pArmR.z = -1.5F;
            this.pArmL.z = -1.5F;
        }
    }
}
