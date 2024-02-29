package site.dodoneko.peoplemobsmod2;

import com.mojang.logging.LogUtils;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidMobRenderer;
import site.dodoneko.peoplemobsmod2.base.PMM2_HumanoidModel;
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

            setMobTextures();

            PMM2_ZombieRenderer.        setModelScales(0.9F,  0.3F);
            PMM2_ZombieRenderer.setForwardArm(true);
            PMM2_CreeperRenderer.       setModelScales(0.8F,  0.4F);
            PMM2_SpiderRenderer.        setModelScales(1.0F,  0.2F, true);
            PMM2_SpiderRenderer.setForwardArm(true);
            PMM2_CaveSpiderRenderer.    setModelScales(0.5F,  0.2F, true);
            PMM2_CaveSpiderRenderer.setForwardArm(true);
            PMM2_EndermanRenderer.      setModelScales(1.0F,  0.8F);
            PMM2_EndermanRenderer.setIsFloating(true, 12.0F);
            PMM2_SilverfishRenderer.    setModelScales(0.5F,  0.3F, true);
            PMM2_EndermiteRenderer.     setModelScales(0.5F,  0.4F, true);
            PMM2_SkeletonRenderer.      setModelScales(0.9F,  0.15F);
            PMM2_WitherSkeletonRenderer.setModelScales(1.08F, 0.5F);
            PMM2_ChickenRenderer.       setModelScales(0.7F,  0.3F, true, true);
            PMM2_PigRenderer.           setModelScales(0.7F,  0.8F);
            PMM2_SheepRenderer.         setModelScales(0.8F,  0.4F);
            PMM2_CowRenderer.           setModelScales(0.85F, 1.0F);
            PMM2_MooshroomRenderer.     setModelScales(0.8F,  0.9F);
            PMM2_FoxRenderer.           setModelScales(0.4F,  0.25F);
            PMM2_RabbitRenderer.        setModelScales(0.5F,  0.3F);
            PMM2_SquidRenderer.         setModelScales(0.7F,  0.3F);
            PMM2_DolphinRenderer.       setModelScales(0.8F,  0.6F);
            PMM2_SnowGolemRenderer.     setModelScales(1.0F,  0.4F);
            PMM2_IronGolemRenderer.     setModelScales(1.5F,  0.7F);
            PMM2_BatRenderer.           setModelScales(0.8F,  0.2F, true, true);
            PMM2_FrogRenderer.           setModelScales(0.4F,  0.6F);

            // Monsters
            EntityRenderers.register(EntityType.ZOMBIE, PMM2_ZombieRenderer::new);
            EntityRenderers.register(EntityType.CREEPER, PMM2_CreeperRenderer::new);
            EntityRenderers.register(EntityType.SPIDER, PMM2_SpiderRenderer::new);
            EntityRenderers.register(EntityType.CAVE_SPIDER, PMM2_CaveSpiderRenderer::new);
            EntityRenderers.register(EntityType.ENDERMAN, PMM2_EndermanRenderer::new);
            EntityRenderers.register(EntityType.SILVERFISH, PMM2_SilverfishRenderer::new);
            EntityRenderers.register(EntityType.ENDERMITE, PMM2_EndermiteRenderer::new);
            EntityRenderers.register(EntityType.SKELETON, PMM2_SkeletonRenderer::new);
            EntityRenderers.register(EntityType.WITHER_SKELETON, PMM2_WitherSkeletonRenderer::new);
            
            // Animals
            EntityRenderers.register(EntityType.CHICKEN, PMM2_ChickenRenderer::new);
            EntityRenderers.register(EntityType.PIG, PMM2_PigRenderer::new);
            EntityRenderers.register(EntityType.SHEEP, PMM2_SheepRenderer::new);
            EntityRenderers.register(EntityType.COW, PMM2_CowRenderer::new);
            EntityRenderers.register(EntityType.MOOSHROOM, PMM2_MooshroomRenderer::new);
            EntityRenderers.register(EntityType.FOX, PMM2_FoxRenderer::new);
            EntityRenderers.register(EntityType.RABBIT, PMM2_RabbitRenderer::new);
            EntityRenderers.register(EntityType.FROG, PMM2_FrogRenderer::new);

            // WaterAnimals
            EntityRenderers.register(EntityType.SQUID, PMM2_SquidRenderer::new);
            EntityRenderers.register(EntityType.DOLPHIN, PMM2_DolphinRenderer::new);
            
            // Golems
            EntityRenderers.register(EntityType.SNOW_GOLEM, PMM2_SnowGolemRenderer::new);
            EntityRenderers.register(EntityType.IRON_GOLEM, PMM2_IronGolemRenderer::new);

            // AmbientCreatures
            EntityRenderers.register(EntityType.BAT, PMM2_BatRenderer::new);
        }

        @SuppressWarnings("null")
        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(PeopleMobsMod2.PMM2_HUMANOID_LAYER, PMM2_HumanoidModel::createBodyLayer);
        }

        static void setMobTextures() {
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
            PMM2_HumanoidMobRenderer.addTexture(Rabbit.class, "rabbit/white-chan");
            PMM2_HumanoidMobRenderer.addTexture(Dolphin.class, "dolphin-chan");
            
            PMM2_HumanoidMobRenderer.addTexture(SnowGolem.class, "snow_golem-chan");
            PMM2_HumanoidMobRenderer.addTexture(IronGolem.class, "iron_golem-chan");
            PMM2_HumanoidMobRenderer.addTexture(Silverfish.class, "silverfish-chan");
            PMM2_HumanoidMobRenderer.addTexture(Endermite.class, "endermite-chan");
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
}
