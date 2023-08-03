package me.synnk.managers;

import me.synnk.api.features.Feature;
import me.synnk.config.Config;
import me.synnk.features.*;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class FeatureManager {
    private static final ArrayList<Feature> modules = new ArrayList<>();

    public static void init() {
        // Add all features here
        modules.add(new Flashlight());
        modules.add(new LockYaw());
        modules.add(new MVPCorrector());
        modules.add(new ToggleSprint());
        modules.add(new BlockQueuer());

        // Add settings for all features
        modules.forEach(feature -> Config.addSetting(
                feature.getCommandName().toLowerCase(),
                false, // Initial value for the setting (you can change it to true if needed)
                feature.getDisplayName())
        );

        modules.forEach(MinecraftForge.EVENT_BUS::register);
    }

    public static ArrayList<Feature> getModules() {
        return modules;
    }

    public static Feature getModuleByName(String name) {
        return modules.stream()
                .filter(module -> module.getCommandName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
