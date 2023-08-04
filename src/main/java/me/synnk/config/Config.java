package me.synnk.config;

import me.synnk.MiningWare;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Config {
    private static final HashMap<String, List<Object>> settings = new HashMap<>();
    public static final List<String> settingNames = new ArrayList<>();

    public static void addSetting(String settingName, Object settingValue, String displayName) {
        settings.put(settingName, new ArrayList<>());
        settings.get(settingName).add(settingValue);
        settings.get(settingName).add(displayName);
    }

    public static Object getSetting(String settingName) {
        return settings.getOrDefault(settingName, null);
    }
    public static HashMap<String, List<Object>> getSettings() {
        return settings;
    }
    public static Object getDisplayName(String settingName) {
        if (settings.containsKey(settingName)) {
            List<Object> keySetting = settings.get(settingName);
            return keySetting.get(1);
        }
        return null;
    }

    public static boolean getBoolean(String settingName) {
        if (settings.containsKey(settingName)) {
            List<Object> keySetting = settings.get(settingName);
            if (keySetting.get(0) instanceof Boolean) {
                return (Boolean) keySetting.get(0);
            }
        }
        return false; // Default value if setting doesn't exist or has incorrect type
    }

    public static void setSetting(String settingName, Object settingValue, String displayName) {
        if (settings.containsKey(settingName)) {
            List<Object> keySetting = settings.get(settingName);
            keySetting.set(0, settingValue);
            keySetting.set(1, displayName);
        }
    }
}
