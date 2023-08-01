package me.synnk.managers;

import me.synnk.features.*;

import java.util.ArrayList;

public class FeatureManager {
    private static final ArrayList<Feature> modules = new ArrayList<>();
    public static void init() {
        new Flashlight();
        new LockYaw();
        new MVPCorrector();
        new ToggleSprint();
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
