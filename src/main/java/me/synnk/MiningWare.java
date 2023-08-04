package me.synnk;

import me.synnk.config.Config;
import me.synnk.features.*;
import me.synnk.handlers.ChatEventHandler;
import me.synnk.managers.FeatureManager;
import me.synnk.managers.KeyBindingManager;
import me.synnk.utils.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Mod(modid = "miningware", useMetadata = true, clientSideOnly = true, name = "MiningWare", acceptedMinecraftVersions = "1.8.9", version = "1.0.0")
public class MiningWare {

    public static final String MODNAME = "MiningWare";
    public static final String VERSION = "1.0.0";
    public static final String MODID = "miningware";

    public static Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void preFMLInitialization(FMLPreInitializationEvent event) {
        Mixins.addConfiguration("mixins.miningware.json");

        File configDirectory = new File(event.getModConfigurationDirectory(), MODID);
        if (!configDirectory.exists() && !configDirectory.mkdir()) {
            configFailCallback();
        }
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        FeatureManager.init();

        // Register the event bus
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChatEventHandler());


        Config.getSettings().forEach((name, values) -> {
            KeyBindingManager.addKeybind(name, Keyboard.KEY_NONE, MiningWare.MODNAME);
            new Log("Adding keybind: " + name);
        });
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        for (KeyBinding keyBind: KeyBindingManager.getKeyBindings()) {
            if (keyBind.isPressed()) {
                if (!Config.getBoolean(keyBind.getKeyCategory())) {
                    // empty
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        // reset everything on the future
    }

    public static void configFailCallback() {
        System.out.println("Config creation failed.");
    }
}
