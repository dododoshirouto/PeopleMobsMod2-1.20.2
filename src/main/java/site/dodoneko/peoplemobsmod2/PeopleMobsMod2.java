package site.dodoneko.peoplemobsmod2;

import com.mojang.logging.LogUtils;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.*;
import net.minecraft.world.entity.monster.piglin.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
import site.dodoneko.peoplemobsmod2.layer.PMM2_OversizeModelLayer;
import site.dodoneko.peoplemobsmod2.model.PMM2_PiglinModel;
import site.dodoneko.peoplemobsmod2.model.PMM2_Slime;
import site.dodoneko.peoplemobsmod2.renderer.*;

import org.slf4j.Logger;

@Mod(PeopleMobsMod2.MODID)
public class PeopleMobsMod2 {
    public static final String MODID = "peoplemobsmod2";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final boolean DEBUG = true;

    public static final ModelLayerLocation PMM2_HUMANOID_LAYER = new ModelLayerLocation(
            new ResourceLocation(MODID, "humanoid_layer"), "main");

    public PeopleMobsMod2() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use EventBusSubscriber to automatically register all static methods
    // in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SuppressWarnings("null")
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("[PMM2] PEOPLE MOBS MOD2 LOADED!");
            LOGGER.info("[PMM2] A world where there is only me and girls.");

            // older 1.5
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_ZombieRenderer.class, 0.9F, 0.3F);
            PMM2_HumanoidMobRenderer.setForwardArm(PMM2_ZombieRenderer.class, true);
            PMM2_HumanoidMobRenderer.setUseArmor(PMM2_ZombieRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_CreeperRenderer.class, 0.8F, 0.4F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_SpiderRenderer.class, 1.0F, 0.2F, true);
            PMM2_HumanoidMobRenderer.setForwardArm(PMM2_SpiderRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_CaveSpiderRenderer.class, 0.5F, 0.2F, true);
            PMM2_HumanoidMobRenderer.setForwardArm(PMM2_CaveSpiderRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_EndermanRenderer.class, 1.0F, 0.8F);
            PMM2_HumanoidMobRenderer.setIsFloating(PMM2_EndermanRenderer.class, true, 12.0F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_SkeletonRenderer.class, 0.9F, 0.15F);
            PMM2_HumanoidMobRenderer.setUseArmor(PMM2_SkeletonRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_WitherSkeletonRenderer.class, 1.08F, 0.5F);
            PMM2_HumanoidMobRenderer.setUseArmor(PMM2_WitherSkeletonRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_ChickenRenderer.class, 0.7F, 0.3F, true, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_PigRenderer.class, 0.7F, 0.8F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_SheepRenderer.class, 0.8F, 0.4F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_CowRenderer.class, 0.85F, 1.0F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_MooshroomRenderer.class, 0.8F, 0.9F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_SquidRenderer.class, 0.7F, 0.3F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_BatRenderer.class, 0.8F, 0.2F, true, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_FoxRenderer.class, 0.4F, 0.25F);
            PMM2_HumanoidMobRenderer.addTexture(Zombie.class, "zombie/zombie-chan");
            PMM2_HumanoidMobRenderer.addTexture(Skeleton.class, "skeleton/skeleton-chan");
            PMM2_HumanoidMobRenderer.addTexture(WitherSkeleton.class, "skeleton/wither_skeleton-chan");
            PMM2_HumanoidMobRenderer.addTexture(Creeper.class, "creeper/creeper-chan");
            PMM2_HumanoidMobRenderer.addTexture(Spider.class, "spider/spider-chan");
            PMM2_HumanoidMobRenderer.addTexture(CaveSpider.class, "spider/cave_spider-chan");
            PMM2_HumanoidMobRenderer.addTexture(EnderMan.class, "enderman/enderman-chan");
            PMM2_HumanoidMobRenderer.addTexture(Chicken.class, "chicken-chan");
            PMM2_HumanoidMobRenderer.addTexture(Pig.class, "pig/pig-chan");
            PMM2_HumanoidMobRenderer.addTexture(Sheep.class, "sheep/sheep-chan");
            PMM2_HumanoidMobRenderer.addTexture(Cow.class, "cow/cow-chan");
            PMM2_HumanoidMobRenderer.addTexture(MushroomCow.class, "cow/red_mooshroom-chan");
            PMM2_HumanoidMobRenderer.addTexture(Squid.class, "squid-chan");
            PMM2_HumanoidMobRenderer.addTexture(Bat.class, "bat-chan");
            PMM2_HumanoidMobRenderer.addTexture(Fox.class, "fox/fox-chan");
            EntityRenderers.register(EntityType.ZOMBIE, PMM2_ZombieRenderer::new);
            EntityRenderers.register(EntityType.CREEPER, PMM2_CreeperRenderer::new);
            EntityRenderers.register(EntityType.SPIDER, PMM2_SpiderRenderer::new);
            EntityRenderers.register(EntityType.CAVE_SPIDER, PMM2_CaveSpiderRenderer::new);
            EntityRenderers.register(EntityType.ENDERMAN, PMM2_EndermanRenderer::new);
            EntityRenderers.register(EntityType.SILVERFISH, PMM2_SilverfishRenderer::new);
            EntityRenderers.register(EntityType.ENDERMITE, PMM2_EndermiteRenderer::new);
            EntityRenderers.register(EntityType.SKELETON, PMM2_SkeletonRenderer::new);
            EntityRenderers.register(EntityType.WITHER_SKELETON, PMM2_WitherSkeletonRenderer::new);
            EntityRenderers.register(EntityType.CHICKEN, PMM2_ChickenRenderer::new);
            EntityRenderers.register(EntityType.PIG, PMM2_PigRenderer::new);
            EntityRenderers.register(EntityType.SHEEP, PMM2_SheepRenderer::new);
            EntityRenderers.register(EntityType.COW, PMM2_CowRenderer::new);
            EntityRenderers.register(EntityType.MOOSHROOM, PMM2_MooshroomRenderer::new);
            EntityRenderers.register(EntityType.FOX, PMM2_FoxRenderer::new);
            EntityRenderers.register(EntityType.SQUID, PMM2_SquidRenderer::new);
            EntityRenderers.register(EntityType.BAT, PMM2_BatRenderer::new);

            // 1.6 Pyon Pyon Update
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_RabbitRenderer.class, 0.5F, 0.3F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_SilverfishRenderer.class, 0.5F, 0.3F, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_EndermiteRenderer.class, 0.5F, 0.4F, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_DolphinRenderer.class, 0.8F, 0.6F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_SnowGolemRenderer.class, 1.0F, 0.4F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_IronGolemRenderer.class, 1.5F, 0.7F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_FrogRenderer.class, 0.4F, 0.6F);
            PMM2_HumanoidMobRenderer.addTexture(Rabbit.class, "rabbit/white-chan");
            PMM2_HumanoidMobRenderer.addTexture(Silverfish.class, "silverfish-chan");
            PMM2_HumanoidMobRenderer.addTexture(Endermite.class, "endermite-chan");
            PMM2_HumanoidMobRenderer.addTexture(Dolphin.class, "dolphin-chan");
            PMM2_HumanoidMobRenderer.addTexture(SnowGolem.class, "snow_golem-chan");
            PMM2_HumanoidMobRenderer.addTexture(IronGolem.class, "iron_golem-chan");
            EntityRenderers.register(EntityType.RABBIT, PMM2_RabbitRenderer::new);
            EntityRenderers.register(EntityType.SNOW_GOLEM, PMM2_SnowGolemRenderer::new);
            EntityRenderers.register(EntityType.IRON_GOLEM, PMM2_IronGolemRenderer::new);
            EntityRenderers.register(EntityType.DOLPHIN, PMM2_DolphinRenderer::new);
            EntityRenderers.register(EntityType.FROG, PMM2_FrogRenderer::new);

            // 1.7 Cutey Nether Update
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_BlazeRenderer.class, 0.67F, 0.5F);
            PMM2_HumanoidMobRenderer.setIsFloating(PMM2_BlazeRenderer.class, true, 8F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_GhastRenderer.class, 6.0F, 0.5F, true);
            PMM2_HumanoidMobRenderer.setIsFloating(PMM2_GhastRenderer.class, true, -16F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_MagmaCubeRenderer.class, 0.55F, 0.5F, true);
            PMM2_HumanoidMobRenderer.setForwardArm(PMM2_MagmaCubeRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_HoglinRenderer.class, 0.87F, 0.9F);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_ZoglinRenderer.class, 0.87F, 0.9F);
            PMM2_HumanoidMobRenderer.setForwardArm(PMM2_ZoglinRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_PiglinRenderer.class, 0.9F, 0.5F);
            PMM2_HumanoidMobRenderer.setUseArmor(PMM2_PiglinRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_PiglinBruteRenderer.class, 0.9F, 0.5F);
            PMM2_HumanoidMobRenderer.setUseArmor(PMM2_PiglinBruteRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_ZombifiedPiglinRenderer.class, 0.9F, 0.5F);
            PMM2_HumanoidMobRenderer.setForwardArm(PMM2_ZombifiedPiglinRenderer.class, true);
            PMM2_HumanoidMobRenderer.setUseArmor(PMM2_ZombifiedPiglinRenderer.class, true);
            PMM2_HumanoidMobRenderer.setModelScales(PMM2_StriderRenderer.class, 0.83F, 0.5F);
            PMM2_HumanoidMobRenderer.addTexture(Piglin.class, "piglin/piglin-chan");
            PMM2_HumanoidMobRenderer.addTexture(ZombifiedPiglin.class, "piglin/zombified_piglin-chan");
            EntityRenderers.register(EntityType.BLAZE, PMM2_BlazeRenderer::new);
            EntityRenderers.register(EntityType.GHAST, PMM2_GhastRenderer::new);
            EntityRenderers.register(EntityType.MAGMA_CUBE, PMM2_MagmaCubeRenderer::new);
            EntityRenderers.register(EntityType.HOGLIN, PMM2_HoglinRenderer::new);
            EntityRenderers.register(EntityType.ZOGLIN, PMM2_ZoglinRenderer::new);
            EntityRenderers.register(EntityType.PIGLIN, PMM2_PiglinRenderer::new);
            EntityRenderers.register(EntityType.PIGLIN_BRUTE, PMM2_PiglinBruteRenderer::new);
            EntityRenderers.register(EntityType.ZOMBIFIED_PIGLIN, PMM2_ZombifiedPiglinRenderer::new);
            EntityRenderers.register(EntityType.STRIDER, PMM2_StriderRenderer::new);
        }

        @SuppressWarnings("null")
        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(PeopleMobsMod2.PMM2_HUMANOID_LAYER, PMM2_HumanoidModel::createBodyLayer);
        }
    }

    public static void DEBUG(String msg, Object... args) {
        if (DEBUG) {
            String argStr = "";
            for (Object arg : args) {
                argStr += " " + arg.toString();
            }
            LOGGER.debug("[PMM2] " + msg + argStr);
        }
    }

    /**
     *      Mobs Renderers
     */

    public static class PMM2_ZombieRenderer extends PMM2_HumanoidMobRenderer<Zombie, PMM2_HumanoidModel<Zombie>> {
        @SuppressWarnings("null")
        public PMM2_ZombieRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_SpiderRenderer extends PMM2_HumanoidMobRenderer<Spider, PMM2_HumanoidModel<Spider>> {
        @SuppressWarnings("null")
        public PMM2_SpiderRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_CaveSpiderRenderer
            extends PMM2_HumanoidMobRenderer<CaveSpider, PMM2_HumanoidModel<CaveSpider>> {
        @SuppressWarnings("null")
        public PMM2_CaveSpiderRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_SkeletonRenderer extends PMM2_HumanoidMobRenderer<Skeleton, PMM2_HumanoidModel<Skeleton>> {
        @SuppressWarnings("null")
        public PMM2_SkeletonRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_WitherSkeletonRenderer
            extends PMM2_HumanoidMobRenderer<WitherSkeleton, PMM2_HumanoidModel<WitherSkeleton>> {
        @SuppressWarnings("null")
        public PMM2_WitherSkeletonRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_ChickenRenderer extends PMM2_HumanoidMobRenderer<Chicken, PMM2_HumanoidModel<Chicken>> {
        @SuppressWarnings("null")
        public PMM2_ChickenRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_PigRenderer extends PMM2_HumanoidMobRenderer<Pig, PMM2_HumanoidModel<Pig>> {
        @SuppressWarnings("null")
        public PMM2_PigRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_SheepRenderer extends PMM2_HumanoidMobRenderer<Sheep, PMM2_HumanoidModel<Sheep>> {
        @SuppressWarnings("null")
        public PMM2_SheepRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
            this.addLayer(new PMM2_OversizeModelLayer<>(this, entity.getModelSet()));
        }
    }

    public static class PMM2_CowRenderer extends PMM2_HumanoidMobRenderer<Cow, PMM2_HumanoidModel<Cow>> {
        @SuppressWarnings("null")
        public PMM2_CowRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_BatRenderer extends PMM2_HumanoidMobRenderer<Bat, PMM2_HumanoidModel<Bat>> {
        @SuppressWarnings("null")
        public PMM2_BatRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    // 1.6 Pyon Pyon Update

    public static class PMM2_SilverfishRenderer extends PMM2_HumanoidMobRenderer<Silverfish, PMM2_HumanoidModel<Silverfish>> {
        @SuppressWarnings("null")
        public PMM2_SilverfishRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_EndermiteRenderer extends PMM2_HumanoidMobRenderer<Endermite, PMM2_HumanoidModel<Endermite>> {
        @SuppressWarnings("null")
        public PMM2_EndermiteRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_DolphinRenderer extends PMM2_HumanoidMobRenderer<Dolphin, PMM2_HumanoidModel<Dolphin>> {
        @SuppressWarnings("null")
        public PMM2_DolphinRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    public static class PMM2_SnowGolemRenderer extends PMM2_HumanoidMobRenderer<SnowGolem, PMM2_HumanoidModel<SnowGolem>> {
        @SuppressWarnings("null")
        public PMM2_SnowGolemRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }

    // 1.7 Cutey Nether Update

    /**
     * @see BlazeRenderer
     * @see BlazeModel
     */
    public static class PMM2_BlazeRenderer extends PMM2_HumanoidMobRenderer<Blaze, PMM2_HumanoidModel<Blaze>> {
        @SuppressWarnings("null")
        public PMM2_BlazeRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see GhastRenderer
     * @see GhastModel
     */
    public static class PMM2_GhastRenderer extends PMM2_HumanoidMobRenderer<Ghast, PMM2_HumanoidModel<Ghast>> {
        @SuppressWarnings("null")
        public PMM2_GhastRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see MagmaCubeRenderer
     * @see MagmaCubeModel
     */
    public static class PMM2_MagmaCubeRenderer extends PMM2_HumanoidMobRenderer<MagmaCube, PMM2_Slime<MagmaCube>> {
        @SuppressWarnings("null")
        public PMM2_MagmaCubeRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_Slime<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see HoglinRenderer
     * @see HoglinModel
     */
    public static class PMM2_HoglinRenderer extends PMM2_HumanoidMobRenderer<Hoglin, PMM2_HumanoidModel<Hoglin>> {
        @SuppressWarnings("null")
        public PMM2_HoglinRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see ZoglinRenderer
     * @see ZoglinModel
     */
    public static class PMM2_ZoglinRenderer extends PMM2_HumanoidMobRenderer<Zoglin, PMM2_HumanoidModel<Zoglin>> {
        @SuppressWarnings("null")
        public PMM2_ZoglinRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see PiglinRenderer
     * @see PiglinModel
     */
    public static class PMM2_PiglinRenderer extends PMM2_HumanoidMobRenderer<Piglin, PMM2_PiglinModel> {
        @SuppressWarnings("null")
        public PMM2_PiglinRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_PiglinModel(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see PiglinBruteRenderer
     * @see PiglinBruteModel
     */
    public static class PMM2_PiglinBruteRenderer extends PMM2_HumanoidMobRenderer<PiglinBrute, PMM2_HumanoidModel<PiglinBrute>> {
        @SuppressWarnings("null")
        public PMM2_PiglinBruteRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see PiglinRenderer
     * @see PiglinModel
     */
    public static class PMM2_ZombifiedPiglinRenderer extends PMM2_HumanoidMobRenderer<ZombifiedPiglin, PMM2_HumanoidModel<ZombifiedPiglin>> {
        @SuppressWarnings("null")
        public PMM2_ZombifiedPiglinRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
    /**
     * @see StriderRenderer
     * @see StriderModel
     */
    public static class PMM2_StriderRenderer extends PMM2_HumanoidMobRenderer<Strider, PMM2_HumanoidModel<Strider>> {
        @SuppressWarnings("null")
        public PMM2_StriderRenderer(EntityRendererProvider.Context entity) {
            super(entity, new PMM2_HumanoidModel<>(entity.bakeLayer(PeopleMobsMod2.PMM2_HUMANOID_LAYER)), modelScale);
        }
    }
}
