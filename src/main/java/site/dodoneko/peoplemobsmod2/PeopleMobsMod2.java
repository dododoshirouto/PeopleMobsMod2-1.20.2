package site.dodoneko.peoplemobsmod2;

import com.mojang.logging.LogUtils;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
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

    HumanoidModel<Zombie> ref_humanoidModel;
    ZombieModel<Zombie> ref_zombieModel;
    EndermanModel<EnderMan> ref_endermanModel;

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

            EntityRenderers.register(EntityType.ZOMBIE, PMM2_ZombieRenderer::new);
            EntityRenderers.register(EntityType.SKELETON, PMM2_SkeletonRenderer::new);
            EntityRenderers.register(EntityType.CREEPER, PMM2_CreeperRenderer::new);
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
            PMM2_HumanoidMobRenderer.addTexture(Bat.class, "bat-chan");

            PMM2_HumanoidMobRenderer.addTexture(Chicken.class, "chicken-chan");
            PMM2_HumanoidMobRenderer.addTexture(Pig.class, "pig/pig-chan");
            PMM2_HumanoidMobRenderer.addTexture(Sheep.class, "sheep/sheep-chan");
            PMM2_HumanoidMobRenderer.addTexture(Cow.class, "cow/cow-chan");
            PMM2_HumanoidMobRenderer.addTexture(MushroomCow.class, "cow/red_mooshroom-chan");
            PMM2_HumanoidMobRenderer.addTexture(Fox.class, "fox/fox-chan");
            PMM2_HumanoidMobRenderer.addTexture(Rabbit.class, "rabbit/white-chan");

            PMM2_HumanoidMobRenderer.addTexture(Squid.class, "squid-chan");

            PMM2_HumanoidMobRenderer.addTexture(SnowGolem.class, "snow_golem-chan");
            PMM2_HumanoidMobRenderer.addTexture(IronGolem.class, "iron_golem-chan");
            PMM2_HumanoidMobRenderer.addTexture(Silverfish.class, "silverfish-chan");
            PMM2_HumanoidMobRenderer.addTexture(Endermite.class, "endermite-chan");
            PMM2_HumanoidMobRenderer.addTexture(Dolphin.class, "dolphin-chan");
        }
    }
}