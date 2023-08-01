package me.synnk.config;

import me.synnk.MiningWare;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

public class Config {
    private static final HashMap<String, Object> settings = new HashMap<>();

    public static void addSetting(String settingName, Object settingValue) {
        settings.put(settingName, settingValue);
    }

    public static Object getSetting(String settingName) {
        return settings.getOrDefault(settingName, null);
    }

    public static boolean getBoolean(String settingName) {
        return (boolean) settings.getOrDefault(settingName, false);
    }

    public static void setSetting(String settingName, Object newValue) {
        if (!(newValue == getSetting(settingName))) {
            settings.put(settingName, newValue);
        }
    }
}
