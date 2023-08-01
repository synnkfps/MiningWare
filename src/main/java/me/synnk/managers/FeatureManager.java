package me.synnk.managers;

import me.synnk.config.Config;
import me.synnk.features.*;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class FeatureManager {
    private static final ArrayList<Feature> modules = new ArrayList<>();
    public static void init() {
        modules.add(new Flashlight());
        modules.add(new LockYaw());
        modules.add(new MVPCorrector());
        modules.add(new ToggleSprint());
        modules.add(new BlockQueuer());

        /**
         * @TODO: Change this so i don't have to use it all the time...
         * @TODO: i think it can be done by just adding "new <feature>()>" to the addSetting parameter...
         */

        // It works by now!
        Config.addSetting("flashlight", false);
        Config.addSetting("lockyaw", false);
        Config.addSetting("mvpcorrector", false);
        Config.addSetting("togglesprint", false);
        Config.addSetting("blockqueuer", false);


        modules.forEach(MinecraftForge.EVENT_BUS::register);
    }

    public static ArrayList<Feature> getModules() {
        return modules;
    }

    public static Feature getModuleByName(String name) {
        return modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}