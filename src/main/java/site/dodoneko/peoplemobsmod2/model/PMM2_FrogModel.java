package site.dodoneko.peoplemobsmod2.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.frog.Frog;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PMM2_FrogModel extends PMM2_HumanoidModel<Frog> {
    
    public PMM2_FrogModel(ModelPart root) {
        super(root);
    }
    
}


/*
 * "croak": "animation.frog.croak",
                "jump": "animation.frog.jump",
                "walk": "animation.frog.walk",
                "idle_water": "animation.frog.idle.water",
                "look_at_target": "animation.common.look_at_target"
                "tongue": "animation.frog.tongue", // 舌を伸ばす
                "swim": "animation.frog.swim",
 */
