package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.frog.Frog;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PMM2_FrogModel extends PMM2_HumanoidModel<Frog> {

    public PMM2_FrogModel(ModelPart root) {
        super(root);
    }

    @Override
    protected void setStayAnimations() {
        if (this.isJumping || this.limbSwingAmount > 0.01F)
            return;

        this.setSittingAnimations();
    }

    @Override
    protected void setWalkingAnimations() {
        this.limbSwing *= 0.3f;
        super.setWalkingAnimations();
        this.limbSwing /= 0.3f;
    }

    @Override
    protected void setPostAnimations() {
        if (this.entity.croakAnimationState.isStarted()) {
            // croaking
            this.pHead.xRot = PMath.toRad(-45F + PMath.sin1(this.ageInTicks / 15F) * 5F);
            this.pHead.yRot += PMath.toRad(PMath.sin1(this.ageInTicks / 2F) * 5F);
        }

        if (this.entity.tongueAnimationState.isStarted()) {
            // tongue
            this.frogTongue.zScale = 5.0F;
            this.frogTongue.xScale = 0.5F;
            this.frogTongue.z = -3F + -3.5F * this.frogTongue.zScale;
        }
    }
}

/*
 * "croak": "animation.frog.croak",
 * "jump": "animation.frog.jump",
 * "walk": "animation.frog.walk",
 * "idle_water": "animation.frog.idle.water",
 * "look_at_target": "animation.common.look_at_target"
 * "tongue": "animation.frog.tongue", // 舌を伸ばす
 * "swim": "animation.frog.swim",
 */
