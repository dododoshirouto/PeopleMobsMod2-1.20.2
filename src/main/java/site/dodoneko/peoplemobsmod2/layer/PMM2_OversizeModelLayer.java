package site.dodoneko.peoplemobsmod2.layer;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class PMM2_OversizeModelLayer<E extends Mob, M extends PMM2_HumanoidModel<E>> extends RenderLayer<E, PMM2_HumanoidModel<E>> {

    public static final Map<Class<?>, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();

    private final PMM2_HumanoidModel<E> model;

    @SuppressWarnings("null")
    public PMM2_OversizeModelLayer(RenderLayerParent<E, PMM2_HumanoidModel<E>> renderParent, EntityModelSet modelSet) {
        super(renderParent);
        this.model = new PMM2_HumanoidModel<E>(modelSet.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER));
        setTextureMaps();
    }

    @SuppressWarnings("null")
    public void render(PoseStack matrix, MultiBufferSource buffer, int i3, E entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getParentModel().copyPropertiesTo(this.model);

        if (entity.isInvisible()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean glowing = minecraft.shouldEntityAppearGlowing(entity);
            if (glowing) {
                this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, f4);
                this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(getTextureLocation(entity)));
                this.model.renderToBuffer(matrix, vertexconsumer, i3,
                        LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 0.0F, 0.0F, 0.0F, 1.0F);
            }
        } else {
            if (entity instanceof Sheep) {
                renderSheep(matrix, buffer, i3, (Sheep) entity, limbSwing, limbSwingAmount, f4, ageInTicks, netHeadYaw,
                        headPitch);
            }
        }
    }

    private static void setTextureMaps() {
        if (!TEXTURES_MAP.isEmpty())
            return;

        TEXTURES_MAP.put(Sheep.class, new ResourceLocation("textures/entity/sheep/sheep-chan_fur.png"));
    }

    @SuppressWarnings({ "null" })
    public ResourceLocation getTextureLocation(E entity) {
        if (TEXTURES_MAP.containsKey(entity.getClass())) {
            return TEXTURES_MAP.get(entity.getClass());
        }
        PeopleMobsMod2.DEBUG("No Textures Found", entity.getClass().getSimpleName());
        return null;
    }

    @SuppressWarnings({ "null", "unchecked" })
    private void renderSheep(PoseStack matrix, MultiBufferSource buffer, int i3, Sheep entity, float limbSwing,
            float limbSwingAmount, float f4, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isSheared())
            return;

        float f = 1F;
        float f1 = 1F;
        float f2 = 1F;
        if (entity.hasCustomName() && "jeb_".equals(entity.getName().getString())) {
            int i1 = 25;
            int i = entity.tickCount / i1 + entity.getId();
            int j = DyeColor.values().length;
            int k = i % j;
            int l = (i + 1) % j;
            float f3 = ((float) (entity.tickCount % i1) + f1) / 25.0F;
            float[] afloat1 = Sheep.getColorArray(DyeColor.byId(k));
            float[] afloat2 = Sheep.getColorArray(DyeColor.byId(l));
            f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
            f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
            f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
        } else {
            float[] afloat = Sheep.getColorArray(entity.getColor());
            f = afloat[0];
            f1 = afloat[1];
            f2 = afloat[2];
        }

        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, getTextureLocation((E) entity), matrix,
                buffer, i3, (E) entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
                f1, f, f1, f2);
    }
}