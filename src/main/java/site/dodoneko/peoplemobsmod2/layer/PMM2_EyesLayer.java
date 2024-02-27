package site.dodoneko.peoplemobsmod2.layer;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_EyesLayer<E extends Mob, M extends PMM2_HumanoidModel<E>> extends RenderLayer<E, PMM2_HumanoidModel<E>> {
    public static final Map<Class<?>, RenderType> TEXTURES_MAP = Maps.newHashMap();

    public PMM2_EyesLayer(RenderLayerParent<E, PMM2_HumanoidModel<E>> parent) {
        super(parent);
        setTextureMaps();
    }

    private static void setTextureMaps() {
        if (!TEXTURES_MAP.isEmpty())
            return;

        TEXTURES_MAP.put(EnderMan.class, RenderType.eyes(new ResourceLocation("textures/entity/enderman/enderman-chan_eyes.png")));
        TEXTURES_MAP.put(Spider.class, RenderType.eyes(new ResourceLocation("textures/entity/spider/spider-chan_eyes.png")));
        TEXTURES_MAP.put(CaveSpider.class, RenderType.eyes(new ResourceLocation("textures/entity/spider/spider-chan_eyes.png")));
    }

    public RenderType getTexture(E entity) {
        if (TEXTURES_MAP.containsKey(entity.getClass())) {
            return TEXTURES_MAP.get(entity.getClass());
        }
        PeopleMobsMod2.DEBUG("No Textures Found", entity.getClass().getSimpleName());
        return null;
    }

    @SuppressWarnings("null")
    public void render(PoseStack matrix, MultiBufferSource buffer, int i3, E entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexconsumer = buffer.getBuffer(getTexture(entity));
        this.getParentModel().renderToBuffer(matrix, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,
                1.0F, 1.0F);
    }
}