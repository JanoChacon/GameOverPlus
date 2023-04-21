package com.janochacon.gameoverplus;

import com.janochacon.gameoverplus.capability.Capabilities;
import com.janochacon.gameoverplus.listener.BattleLostEvent;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(GameOverPlus.MOD_ID)
@Mod.EventBusSubscriber(modid = GameOverPlus.MOD_ID)
public class GameOverPlus {

    public static final String MOD_ID = "gameoverplus";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static GameOverPlus instance;

    public GameOverPlus() {

        instance = this;
    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event)  {
        // Logic for when the server is starting here
        // Here is how you register a listener for Pixelmon events
        // Pixelmon has its own event bus for its events, as does TCG
        // So any event listener for those mods need to be registered to those specific event buses
        Capabilities.register();
        MinecraftForge.EVENT_BUS.register(new Capabilities());
        Pixelmon.EVENT_BUS.register(new BattleLostEvent());
       // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigMod.SPEC, "pixelmonwhiteout.toml");

    }


    @SubscribeEvent
    public static void onServerStarted(FMLServerStartedEvent event) {
        // Logic for once the server has started here
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        //Register command logic here
        // Commands don't have to be registered here
        // However, not registering them here can lead to some hybrids/server software not recognising the commands
    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {
        // Logic for when the server is stopping
    }

    @SubscribeEvent
    public static void onServerStopped(FMLServerStoppedEvent event) {
        // Logic for when the server is stopped
    }

    public static GameOverPlus getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
