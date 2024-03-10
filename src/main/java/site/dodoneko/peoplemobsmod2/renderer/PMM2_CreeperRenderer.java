package site.dodoneko.peoplemobsmod2.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

/**
 * @see net.minecraft.client.model.CreeperModel
 * @see net.minecraft.client.renderer.entity.CreeperRenderer
 */
@OnlyIn(Dist.CLIENT)
public class PMM2_CreeperRenderer<T extends Creeper> extends PMM2_HumanoidMobRenderer<T, PMM2_HumanoidModel<T>> {

    @SuppressWarnings("null")
    public PMM2_CreeperRenderer(EntityRendererProvider.Context entity) {
        super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_TWINKLED_HUMANOID_LAYER)), modelScale);
    }

    @SuppressWarnings("null")
    protected void scale(Creeper entity, PoseStack matrix, float partialTick) {
        float f = entity.getSwelling(partialTick);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        matrix.scale(f2, f3, f2);
    }

    @SuppressWarnings("null")
    protected float getWhiteOverlayProgress(Creeper entity, float partialTick) {
        float f = entity.getSwelling(partialTick);
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }
}