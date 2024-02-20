package site.dodoneko.peoplemobsmod2.base;

import java.util.Map;

import com.google.common.collect.Maps;
import net.minecraft.client.model.HumanoidModel;
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

    public float modelScale = 0.8F;

    @SuppressWarnings("null")
    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float modelScale) {
        super(entity, model, modelScale/2);
        PeopleMobsMod2.LOGGER.info(
                "[PMM2] PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float shadowRadius)");
        this.modelScale = modelScale;
        
        this.addLayer(new CustomHeadLayer<>(this, entity.getModelSet(), modelScale, modelScale, modelScale,
                entity.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, entity.getModelSet()));
        this.addLayer(new ItemInHandLayer<>(this, entity.getItemInHandRenderer()));
    }

    @SuppressWarnings("null")
    public void addArmorLayers(EntityRendererProvider.Context entity, M innerArmorModel, M outerArmorModel) {
        PeopleMobsMod2.LOGGER.info(
                "[PMM2] addArmorLayers(EntityRendererProvider.Context entity, M innerArmorModel, M outerArmorModel)");
        this.addLayer(new HumanoidArmorLayer<>(this, innerArmorModel, outerArmorModel, entity.getModelManager()));
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
}