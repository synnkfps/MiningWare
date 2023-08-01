package me.synnk;

import me.synnk.features.*;
import me.synnk.handlers.ChatEventHandler;
import me.synnk.managers.FeatureManager;
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

@Mod(modid = "miningware", useMetadata = true, clientSideOnly = true, name = "MiningWare", acceptedMinecraftVersions = "1.8.9", version = "1.0.0")
public class MiningWare {

    public static final String MODNAME = "@NAME@";
    public static final String VERSION = "@VERSION@";
    public static final String MODID = "@MODID";

    public static KeyBinding[] keyBinds = new KeyBinding[2];


    @Mod.Instance(MODID)

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


        keyBinds[0] = new KeyBinding("Open GUI", Keyboard.KEY_NONE, "MiningWare");
        keyBinds[1] = new KeyBinding("Lock Yaw", Keyboard.KEY_NONE, "MiningWare");

        for (KeyBinding keyBind : keyBinds) {
            ClientRegistry.registerKeyBinding(keyBind);
        }


    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (keyBinds[0].isPressed()) {
            // todo
        }

        if (keyBinds[1].isPressed()) {
            new LockYaw().onTrigger();
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
