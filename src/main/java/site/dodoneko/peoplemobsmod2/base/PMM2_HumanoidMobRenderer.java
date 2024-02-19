package site.dodoneko.peoplemobsmod2.base;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;

@OnlyIn(Dist.CLIENT)
public abstract class PMM2_HumanoidMobRenderer<T extends Mob, M extends HumanoidModel<T>> extends MobRenderer<T, M> {

    public static final Map<Class<?>, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();
    public static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation(
            "textures/entity/" + "sample-chan" + ".png");

    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float shadowRadius) {
        this(entity, model, shadowRadius, 1.0F, 1.0F, 1.0F);
        PeopleMobsMod2.LOGGER.info(
                "[PMM2] PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float shadowRadius)");
    }

    /** with Armor and Item layers. */
    @SuppressWarnings("null")
    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model1, M model2, M model3,
            float shadowRadius) {
        this(entity, model1, shadowRadius);
        PeopleMobsMod2.LOGGER.info(
                "[PMM2] PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model1, M model2, M model3, float shadowRadius)");
        this.addLayer(new HumanoidArmorLayer<>(this, model2, model3, entity.getModelManager()));
    }

    @SuppressWarnings("null")
    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model1, M model2, M model3) {
        this(entity, model1, model2, model3, 0.5F);
        PeopleMobsMod2.LOGGER
                .info("PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model1, M model2, M model3)");
    }

    @SuppressWarnings("null")
    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float shadowRadius, float scaleX,
            float scaleY, float scaleZ) {
        super(entity, model, shadowRadius);
        PeopleMobsMod2.LOGGER.info(
                "[PMM2] PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float shadowRadius, float scaleX, float scaleY, float scaleZ)");
        this.addLayer(new CustomHeadLayer<>(this, entity.getModelSet(), scaleX, scaleY, scaleZ,
                entity.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, entity.getModelSet()));
        this.addLayer(new ItemInHandLayer<>(this, entity.getItemInHandRenderer()));
    }

    public static void addTexture(Class<?> entityClass, String texturePath) {
        PeopleMobsMod2.LOGGER.info("[PMM2] addTexture(EntityType<? extends T> entity, String texturePath)",
                texturePath);
        if (texturePath == null)
            return;
        TEXTURES_MAP.put(entityClass, new ResourceLocation("textures/entity/" + texturePath + ".png"));
    }

    /** If the entity has color variations, use this method override. */
    @SuppressWarnings({ "null" })
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        PeopleMobsMod2.LOGGER.info("[PMM2] getTextureLocation(T entity)", entity.toString());
        if (TEXTURES_MAP.containsKey(entity.getClass())) {
            return TEXTURES_MAP.get(entity.getClass());
        }
        return TEXTURE_DEFAULT;
    }

    // individual methods

    @SuppressWarnings("null")
    protected boolean isShaking(T entity) {
        PeopleMobsMod2.LOGGER.info("[PMM2] isShaking(T entity)", entity.toString());
        if (entity instanceof Zombie)
            return super.isShaking(entity) || ((Zombie) entity).isUnderWaterConverting();

        return super.isShaking(entity);
    }

    // DEBUG

    @Override
    public void render(T p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_,
            int p_114490_) {
        PeopleMobsMod2.LOGGER.info("[PMM2] PMM2_HumanoidMobRenderer.render()", p_114485_.toString(), p_114486_,
                p_114487_, p_114488_, p_114489_, p_114490_);
        super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
    }
}