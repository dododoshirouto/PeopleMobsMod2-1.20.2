package site.dodoneko.peoplemobsmod2.model;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Slime;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @see net.minecraft.client.model.SlimeModel
 * @see net.minecraft.client.renderer.entity.SlimeRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_Slime<E extends Slime> extends PMM2_HumanoidModel<E> {

    static final Map<Integer, Float> LAST_MOVEMENT_Y = new HashMap<Integer, Float>();
    static final Map<Integer, Float> PUYOPUYO_SCALE = new HashMap<Integer, Float>();

    public PMM2_Slime(ModelPart root) {
        super(root);
    }

    @Override
    protected void setAddAnimations() {

        this.modelScale = (float) this.entity.getSize() * 0.55F;

        float nowMoveY = (float) this.entity.getDeltaMovement().y;
        float lastMoveY = LAST_MOVEMENT_Y.getOrDefault(this.entityId, nowMoveY);
        float puyoScale = PUYOPUYO_SCALE.getOrDefault(this.entityId, 1F);

        // if (this.isJumping) {
        float deltaMoveY = nowMoveY - lastMoveY;
        deltaMoveY *= 100F;
        if (PMath.abs(deltaMoveY) < 0.01F) {
            deltaMoveY = 0F;
        }
        // puyoScale -= (PMath.easeOut(PMath.clamp(deltaMoveY, -1F, 1F))) * 0.02F;
        puyoScale -= (PMath.clamp(deltaMoveY, -1F, 1F)) * 0.3F;
        // PeopleMobsMod2.DEBUG("deltaMoveY: " + deltaMoveY);
        // }

        puyoScale = (puyoScale - 1F) * 0.92F + 1F;
        if (PMath.abs(puyoScale - 1F) < 0.1F) {
            puyoScale = 1F;
        }

        // PeopleMobsMod2.DEBUG("puyoScale: " + puyoScale);
        // PeopleMobsMod2.DEBUG("nowMoveY: " + nowMoveY);

        LAST_MOVEMENT_Y.put(this.entityId, (float) this.entity.getDeltaMovement().y);
        PUYOPUYO_SCALE.put(this.entityId, puyoScale);

        // float f1 = (float) this.entity.getSize();
        // float f2 = PMath.lerp(1F, this.entity.oSquish, this.entity.squish) / (f1 *
        // 0.5F + 1.0F);
        // float f3 = 1.0F / (f2 + 1.0F);
        // // p_115984_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);

        this.pBody.y += 24F * (1 - puyoScale);
        this.pHead.y += 24F * (1 - puyoScale);
        this.pBody.yScale = this.pHead.yScale = puyoScale;
        this.pBody.xScale = this.pHead.xScale = 1.0F / puyoScale;
        this.pBody.zScale = this.pHead.zScale = 1.0F / puyoScale;
    }
}
