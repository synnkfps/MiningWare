package me.synnk.managers;

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