package site.dodoneko.peoplemobsmod2;

import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import site.dodoneko.peoplemobsmod2.renderer.PMM2_ZombieRenderer;

import org.slf4j.Logger;

@Mod(PeopleMobsMod2.MODID)
public class PeopleMobsMod2 {
    public static final String MODID = "peoplemobsmod2";
    public static final Logger LOGGER = LogUtils.getLogger();

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

            PMM2_ZombieRenderer.addTexture(Zombie.class, "zombie/zombie-chan");
            EntityRenderers.register(EntityType.ZOMBIE, PMM2_ZombieRenderer::new);
        }
    }
}
