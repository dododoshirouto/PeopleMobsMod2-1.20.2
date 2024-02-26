package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.Fox;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.base.PMath;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PMM2_FoxModel extends PMM2_HumanoidModel<Fox> {
    public PMM2_FoxModel(ModelPart root) {
        super(root);
    }

    @Override
    protected void setAddAnimations() {
    }
}
