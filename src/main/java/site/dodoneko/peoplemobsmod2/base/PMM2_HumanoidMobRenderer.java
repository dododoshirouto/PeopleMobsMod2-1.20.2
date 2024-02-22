package site.dodoneko.peoplemobsmod2.base;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.dodoneko.peoplemobsmod2.PeopleMobsMod2;

@OnlyIn(Dist.CLIENT)
public abstract class PMM2_HumanoidMobRenderer<T extends Mob, M extends PMM2_HumanoidModel<T>>
        extends MobRenderer<T, M> {

    public static final Map<Class<?>, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();
    public static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation(
            "textures/entity/" + "sample-chan" + ".png");
    // public static final Map<Class<?>, Class<PMM2_HumanoidModel>> MODEL_MAP = Maps.newHashMap();

    // public float modelScale = 0.8F;

    @SuppressWarnings("null")
    public PMM2_HumanoidMobRenderer(EntityRendererProvider.Context entity, M model, float modelScale) {
        super(entity, model, modelScale / 2);
        this.getModel().modelScale = modelScale;

        this.addLayer(new CustomHeadLayer<>(this, entity.getModelSet(), modelScale, modelScale, modelScale,
                entity.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, entity.getModelSet()));
        this.addLayer(new ItemInHandLayer<>(this, entity.getItemInHandRenderer()));
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

    // public static void addModel(Class<?> entityClass, Class<PMM2_HumanoidModel> class1) {
    //     if (class1 == null)
    //         return;
    //     MODEL_MAP.put(entityClass, class1);
    // }

    /** If the entity has color variations, use this method override. */
    @SuppressWarnings({ "null" })
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (TEXTURES_MAP.containsKey(entity.getClass())) {
            return TEXTURES_MAP.get(entity.getClass());
        }
        return TEXTURE_DEFAULT;
    }

    // /** If the entity has model variations, use this method override. */
    // public static Class<PMM2_HumanoidModel> getModel(EntityRendererProvider.Context entity) {
    //     if (MODEL_MAP.containsKey(entity.getClass())) {
    //         return (Class<PMM2_HumanoidModel>)MODEL_MAP.get(entity.getClass());
    //     }
    //     PeopleMobsMod2.LOGGER.error("[PMM2] No Model.", entity.toString());
    //     return null;
    // }

    @SuppressWarnings("null")
    @Override
    public void render(T entity, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_,
            int p_115460_) {
        if (entity instanceof EnderMan) {
            BlockState block = ((EnderMan) entity).getCarriedBlock();
            PMM2_HumanoidModel<T> model = this.getModel();
            model.carrying = block != null;
            model.isCreepy = ((EnderMan) entity).isCreepy();
        }

        super.render(entity, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }

    // individual methods

    @SuppressWarnings("null")
    protected boolean isShaking(T entity) {
        if (entity instanceof Zombie)
            return super.isShaking(entity) || ((Zombie) entity).isUnderWaterConverting();

        return super.isShaking(entity);
    }
}