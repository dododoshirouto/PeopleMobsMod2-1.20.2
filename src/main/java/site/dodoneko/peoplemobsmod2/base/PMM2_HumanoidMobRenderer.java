package site.dodoneko.peoplemobsmod2.base;

import java.util.Map;
import com.google.common.collect.Maps;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;
import site.dodoneko.peoplemobsmod2.layer.PMM2_HumanHeldItemLayer;

@OnlyIn(Dist.CLIENT)
public abstract class PMM2_HumanoidMobRenderer<E extends Mob, M extends PMM2_HumanoidModel<E>>
        extends MobRenderer<E, M> {

    // model options
    @SuppressWarnings("rawtypes")
    public static Map<Class<? extends PMM2_HumanoidMobRenderer>, ModelOptions> MODEL_SCALES = Maps.newHashMap();
    public static float modelScale = 0.9F;
    public static float bHeight = 0.3F;
    public static boolean useChildModel = false;
    public static boolean doFlyFlap = false;
    public static boolean forwardArm = false;
    public static boolean useArmor = false;
    public static boolean isFloating = false;
    public static float floatingHeight = 0.0F;
    public static boolean doWalkBounding = true;

    public static class ModelOptions {
        public float modelScale = 0.9F;
        public float bHeight = 0.3F;
        public boolean useChildModel = false;
        public boolean doFlyFlap = false;
        public boolean forwardArm = false;
        public boolean useArmor = false;
        public boolean isFloating = false;
        public float floatingHeight = 0.0F;
        public boolean doWalkBounding = true;

        public ModelOptions() {
            this.modelScale = 0.9F;
            this.bHeight = 0.3F;
            this.useChildModel = false;
            this.doFlyFlap = false;
            this.forwardArm = false;
            this.useArmor = false;
            this.isFloating = false;
            this.floatingHeight = 0.0F;
            this.doWalkBounding = true;
        }
    }

    @SuppressWarnings("rawtypes")
    public static void setModelScales(Class<? extends PMM2_HumanoidMobRenderer> entity, float scale, float height) {
        if (!MODEL_SCALES.containsKey(entity))
            MODEL_SCALES.put(entity, new ModelOptions());
        MODEL_SCALES.get(entity).modelScale = scale;
        MODEL_SCALES.get(entity).bHeight = height;
    }

    @SuppressWarnings("rawtypes")
    public static void setModelScales(Class<? extends PMM2_HumanoidMobRenderer> entity, float scale, float height,
            boolean isChild) {
        setModelScales(entity, scale, height);
        MODEL_SCALES.get(entity).useChildModel = isChild;
    }

    @SuppressWarnings("rawtypes")
    public static void setModelScales(Class<? extends PMM2_HumanoidMobRenderer> entity, float scale, float height,
            boolean isChild, boolean flyFlap) {
        setModelScales(entity, scale, height, isChild);
        MODEL_SCALES.get(entity).doFlyFlap = flyFlap;
    }

    @SuppressWarnings("rawtypes")
    public static void setForwardArm(Class<? extends PMM2_HumanoidMobRenderer> entity, boolean v) {
        if (!MODEL_SCALES.containsKey(entity))
            MODEL_SCALES.put(entity, new ModelOptions());
        MODEL_SCALES.get(entity).forwardArm = v;
    }

    @SuppressWarnings("rawtypes")
    public static void setUseArmor(Class<? extends PMM2_HumanoidMobRenderer> entity, boolean v) {
        if (!MODEL_SCALES.containsKey(entity))
            MODEL_SCALES.put(entity, new ModelOptions());
        MODEL_SCALES.get(entity).useArmor = v;
    }

    @SuppressWarnings("rawtypes")
    public static void setIsFloating(Class<? extends PMM2_HumanoidMobRenderer> entity, boolean v, float h) {
        if (!MODEL_SCALES.containsKey(entity))
            MODEL_SCALES.put(entity, new ModelOptions());
        MODEL_SCALES.get(entity).isFloating = v;
        MODEL_SCALES.get(entity).floatingHeight = h;
    }

    @SuppressWarnings("rawtypes")
    public static void setDoWalkBounding(Class<? extends PMM2_HumanoidMobRenderer> entity, boolean v) {
        if (!MODEL_SCALES.containsKey(entity))
            MODEL_SCALES.put(entity, new ModelOptions());
        MODEL_SCALES.get(entity).doWalkBounding = v;
    }

    @SuppressWarnings("rawtypes")
    public static void initModelOptions(Class<? extends PMM2_HumanoidMobRenderer> entity) {
        if (!MODEL_SCALES.containsKey(entity)) {
            PeopleMobsMod2.DEBUG("No model options found for " + entity.getName());
            modelScale = 0.9F;
            bHeight = 0.3F;
            useChildModel = false;
            doFlyFlap = false;
            forwardArm = false;
            useArmor = false;
            isFloating = false;
            floatingHeight = 0.0F;
            doWalkBounding = true;
            return;
        }
        modelScale = MODEL_SCALES.get(entity).modelScale;
        bHeight = MODEL_SCALES.get(entity).bHeight;
        useChildModel = MODEL_SCALES.get(entity).useChildModel;
        doFlyFlap = MODEL_SCALES.get(entity).doFlyFlap;
        forwardArm = MODEL_SCALES.get(entity).forwardArm;
        useArmor = MODEL_SCALES.get(entity).useArmor;
        isFloating = MODEL_SCALES.get(entity).isFloating;
        floatingHeight = MODEL_SCALES.get(entity).floatingHeight;
        doWalkBounding = MODEL_SCALES.get(entity).doWalkBounding;
    }

    public static final Map<Class<?>, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();
    public static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation(
            "textures/entity/" + "sample-chan" + ".png");
    // public static final Map<Class<?>, Class<PMM2_HumanoidModel>> MODEL_MAP =
    // Maps.newHashMap();

    // public float modelScale = 0.8F;

    @SuppressWarnings({ "null", "unchecked" })
    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float ms) {
        super(entity, model, ms / 2);
        initModelOptions(this.getClass());

        this.model.modelScale = modelScale;
        this.model.bHeight = bHeight;
        this.model.useChildModel = useChildModel;
        this.model.doFlyFlap = doFlyFlap;
        this.model.forwardArm = forwardArm;
        this.model.isFloating = isFloating;
        this.model.floatingHeight = floatingHeight;
        this.model.doWalkBounding = doWalkBounding;

        this.shadowRadius = modelScale / 2;

        this.addLayer(new CustomHeadLayer<>(this, entity.getModelSet(), modelScale, modelScale, modelScale,
                entity.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, entity.getModelSet()));
        this.addLayer(new PMM2_HumanHeldItemLayer<>(this, entity.getItemInHandRenderer()));
        if (useArmor)
            this.addArmorLayers(entity,
                    (M) new PMM2_HumanoidModel<E>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)),
                    (M) new PMM2_HumanoidModel<E>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)));
    }

    @SuppressWarnings("null")
    public void addArmorLayers(EntityRendererProvider.Context entity, M innerArmorModel, M outerArmorModel) {
        this.addLayer(new HumanoidArmorLayer<>(this, innerArmorModel, outerArmorModel, entity.getModelManager()));
    }

    public static void addTexture(Class<?> entityClass, String texturePath) {
        if (texturePath == null)
            return;
        TEXTURES_MAP.put(entityClass, new ResourceLocation("textures/entity/" + texturePath + ".png"));
    }

    /** If the entity has color variations, use this method override. */
    @SuppressWarnings({ "null" })
    @Override
    public ResourceLocation getTextureLocation(E entity) {
        if (TEXTURES_MAP.containsKey(entity.getClass())) {
            return TEXTURES_MAP.get(entity.getClass());
        }
        return TEXTURE_DEFAULT;
    }

    // individual methods

    @SuppressWarnings("null")
    protected boolean isShaking(E entity) {
        if (entity instanceof Zombie) {
            return super.isShaking(entity) || ((Zombie) entity).isUnderWaterConverting();
        } else if (entity instanceof AbstractSkeleton) {
            return ((AbstractSkeleton) entity).isShaking();
        }

        return super.isShaking(entity);
    }

    @SuppressWarnings("null")
    protected float getFlipDegrees(E entity) {
        if (entity instanceof Spider || entity instanceof CaveSpider || entity instanceof Endermite
                || entity instanceof Silverfish) {
            return 180.0F;
        }
        return 90.0F;
    }
}