package site.dodoneko.peoplemobsmod2.model;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.IronGolem;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @see net.minecraft.client.model.SlimeModel
 * @see net.minecraft.client.renderer.entity.SlimeRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_IronGolemModel extends PMM2_HumanoidModel<IronGolem> {

    static final Map<Integer, Float> LAST_MOVEMENT_Y = new HashMap<Integer, Float>();
    static final Map<Integer, Float> PUYOPUYO_SCALE = new HashMap<Integer, Float>();

    public PMM2_IronGolemModel(ModelPart root) {
        super(root);
    }

    @Override
    protected void setAddAnimations() {
        if (this.limbSwingAmount > 0.01F && !this.isFloating && !this.isSwimming && !this.isFlying && !this.isJumping && !this.isClimbing) {
            this.pBody.zRot = PMath.toRad( -this.pLegL.xRot / PMath.Deg2Rad * 0.2f );
            this.pHead.zRot += PMath.toRad( -this.pLegL.xRot / PMath.Deg2Rad * 0.2f );
            this.pBody.x = this.pHead.x = PMath.sin(this.pBody.zRot) * 24f;
            this.pBody.y = this.pHead.y = -(1f - PMath.cos(this.pBody.zRot)) * 24f;
        }
    }
}
