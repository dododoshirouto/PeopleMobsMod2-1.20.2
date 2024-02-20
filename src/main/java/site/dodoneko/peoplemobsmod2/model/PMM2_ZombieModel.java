package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_ZombieModel<T extends Zombie> extends PMM2_HumanoidModel<T> {
    public PMM2_ZombieModel(ModelPart modelPart) {
        super(modelPart);
    }

    @SuppressWarnings("null")
    public void setupAnim(T entity, float p_102002_, float p_102003_, float p_102004_, float p_102005_,
            float p_102006_) {
        super.setupAnim(entity, p_102002_, p_102003_, p_102004_, p_102005_, p_102006_);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entity), this.attackTime,
                p_102004_);
    }

    public boolean isAggressive(T entity) {
        return entity.isAggressive();
    }
}